package com.kindnessocean.shared.mq.util;


import com.kindnessocean.shared.mq.util.KafkaPropertiesBuilderUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;


public final class KafkaProducerBuilder<K, V> {

    public Producer<K, V> buildKafkaProducer(String bootstrapServer, String transactionalId) {
        Producer<K, V> producer
                = new KafkaProducer<K, V>(
                KafkaPropertiesBuilderUtil
                        .buildBasicProducerConfig(bootstrapServer, transactionalId)
        );

        producer.initTransactions();

        return producer;
    }
}
