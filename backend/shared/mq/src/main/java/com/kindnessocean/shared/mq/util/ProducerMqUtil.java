package com.kindnessocean.shared.mq.util;


import java.util.concurrent.Future;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;


public class ProducerMqUtil<K, V> {

    private final Logger log;

    public ProducerMqUtil(Logger log) {
        this.log = log;
    }

    public void produce(Producer<K, V> producer, String topic, K key, V value) {
        log.info("Produce message in topic='{}'; key='{}'; value='{}'", topic, key, value);
        producer.beginTransaction();
        ProducerRecord<K, V> record =
                new ProducerRecord<K, V>(
                        topic,
                        key,
                        value
                );

        Future<RecordMetadata> future = producer.send(record);
        producer.flush();
        producer.commitTransaction();
    }
}
