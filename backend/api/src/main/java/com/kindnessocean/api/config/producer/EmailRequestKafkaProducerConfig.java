package com.kindnessocean.api.config.producer;

import com.kindnessocean.shared.model.util.StringUtil;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestKey;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestValue;
import com.kindnessocean.shared.mq.util.KafkaProducerBuilder;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailRequestKafkaProducerConfig {

    private final Long TRANSACTIONAL_ID_LENGTH = 10L;
    @Value("${com.kindnessocean.shared.config.mq.bootstrapServer}")
    private String bootstrapServer;

    @Bean
    public Producer<EmailRequestKey, EmailRequestValue> createEntityEventProducer() {
        return new KafkaProducerBuilder<EmailRequestKey, EmailRequestValue>()
                .buildKafkaProducer(bootstrapServer, StringUtil.generateString(TRANSACTIONAL_ID_LENGTH));
    }
}
