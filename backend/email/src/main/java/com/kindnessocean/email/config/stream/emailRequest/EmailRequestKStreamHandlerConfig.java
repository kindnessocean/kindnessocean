package com.kindnessocean.email.config.stream.emailRequest;

import com.kindnessocean.email.service.impl.mq.emailRequest.EmailRequestKStreamHandler;
import com.kindnessocean.email.service.interf.smtp.SmtpService;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestKey;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestKeySerde;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestValue;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestValueSerde;
import com.kindnessocean.shared.mq.model.emailrequestresult.EmailRequestResultKey;
import com.kindnessocean.shared.mq.model.emailrequestresult.EmailRequestResultValue;
import com.kindnessocean.shared.mq.properties.KafkaConnectionProperties;
import com.kindnessocean.shared.mq.properties.KafkaTopicProperties;
import com.kindnessocean.shared.mq.service.KStreamHandler;
import com.kindnessocean.shared.mq.util.KafkaPropertiesBuilderUtil;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

/**
 * Configuration EmailRequest Kafka Streams.
 */
@Slf4j
@Configuration
public class EmailRequestKStreamHandlerConfig {

    private static final String EMAIL_REQUEST_STREAM = "EmailRequestKafkaStream";
    private static final String applicationId = "EMAIL_APPLICATION_ID";

    private final KafkaConnectionProperties kafkaConnectionProperties;
    private final KafkaTopicProperties kafkaTopicProperties;

    private final SmtpService smtpService;

    private final Class<EmailRequestKeySerde> keySerdeClass = EmailRequestKeySerde.class;
    private final Class<EmailRequestValueSerde> valueSerdeClass = EmailRequestValueSerde.class;

    private Map<String, Object> props;

    public EmailRequestKStreamHandlerConfig(
            final KafkaConnectionProperties kafkaConnectionProperties,
            final KafkaTopicProperties kafkaTopicProperties,
            final SmtpService smtpService
    ) {
        this.kafkaConnectionProperties = kafkaConnectionProperties;
        this.kafkaTopicProperties = kafkaTopicProperties;
        this.smtpService = smtpService;
    }


    @Bean(name = EMAIL_REQUEST_STREAM)
    public StreamsBuilderFactoryBean emailRequestStreamsBuilderFactoryBean() {
        log.info("SetUp StreamsBuilderFactoryBean " + EMAIL_REQUEST_STREAM + " bean");
        props = KafkaPropertiesBuilderUtil.buildKafkaStreamProperties(
                keySerdeClass,
                valueSerdeClass,
                applicationId,
                kafkaConnectionProperties.getBootstrapServer()

        );

        return new StreamsBuilderFactoryBean(new KafkaStreamsConfiguration(props));
    }

    @Bean
    public KStreamHandler<EmailRequestKey, EmailRequestValue, EmailRequestResultKey, EmailRequestResultValue> emailRequestKStreamHandler() throws Exception {
        return new EmailRequestKStreamHandler(
                smtpService,
                kafkaTopicProperties.getEmailRequest(),
                kafkaTopicProperties.getEmailRequestResult(),
                applicationId,
                kafkaConnectionProperties.getBootstrapServer(),
                EMAIL_REQUEST_STREAM,
                emailRequestStreamsBuilderFactoryBean(),
                keySerdeClass,
                valueSerdeClass
        );
    }
}
