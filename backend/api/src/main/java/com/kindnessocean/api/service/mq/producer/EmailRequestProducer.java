package com.kindnessocean.api.service.mq.producer;

import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestKey;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestValue;
import com.kindnessocean.shared.mq.util.ProducerMqUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailRequestProducer {

    private final Producer<EmailRequestKey, EmailRequestValue> producer;

    private final ProducerMqUtil<EmailRequestKey, EmailRequestValue> producerMqUtil;
    @Value("${com.kindnessocean.shared.config.mq.topic.emailRequest}")
    private String topic;

    public EmailRequestProducer(final Producer<EmailRequestKey, EmailRequestValue> emailRequestProducer) {
        this.producer = emailRequestProducer;
        producerMqUtil = new ProducerMqUtil<EmailRequestKey, EmailRequestValue>(log);
    }

    public void produceRequest(EmailRequestKey key, EmailRequestValue value) {
        producerMqUtil.produce(producer, topic, key, value);
    }
}
