package com.kindnessocean.email.service.impl.mq.emailRequest;

import com.kindnessocean.email.service.interf.smtp.SmtpService;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestKey;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestValue;
import com.kindnessocean.shared.mq.model.emailrequestresult.EmailRequestResultKey;
import com.kindnessocean.shared.mq.model.emailrequestresult.EmailRequestResultValue;
import com.kindnessocean.shared.mq.service.KStreamHandler;
import java.util.AbstractMap;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

public class EmailRequestKStreamHandler extends KStreamHandler<EmailRequestKey, EmailRequestValue, EmailRequestResultKey, EmailRequestResultValue> {

    private final SmtpService smtpService;

    public EmailRequestKStreamHandler(
            final SmtpService smtpService,
            final String inputTopic, final String outputTopic,
            final String applicationId, final String bootstrapServer,
            final String streamName, final StreamsBuilderFactoryBean entityEventStreamsBuilder,
            final Class<?> keySerde, final Class<?> valueSerde) throws Exception {

        super(inputTopic, outputTopic,
                applicationId, bootstrapServer,
                streamName, entityEventStreamsBuilder,
                keySerde, valueSerde);

        this.smtpService = smtpService;
    }


    @Override
    public AbstractMap.SimpleEntry<EmailRequestResultKey, EmailRequestResultValue> handleException(final EmailRequestKey key, final EmailRequestValue value, final Exception e) {
        return build(key, value, false);
    }

    @Override
    public AbstractMap.SimpleEntry<EmailRequestResultKey, EmailRequestResultValue> handleMessage(final EmailRequestKey key, final EmailRequestValue value) {
        smtpService.sendEmailWithCodeNumber(value.getAddress(), value.getCode());

        return build(key, value, true);
    }

    private AbstractMap.SimpleEntry<EmailRequestResultKey, EmailRequestResultValue> build(
            final EmailRequestKey key,
            final EmailRequestValue value,
            final Boolean isSuccess
    ){
        return new AbstractMap.SimpleEntry<>(
                new EmailRequestResultKey(key.getUuid()),
                new EmailRequestResultValue(
                        value.getAddress(),
                        isSuccess
                )
        );
    }
}
