package com.kindnessocean.shared.mq.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.DefaultProductionExceptionHandler;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.springframework.kafka.support.serializer.JsonSerializer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaPropertiesBuilderUtil {

    public static Map<String, Object> buildKafkaStreamProperties(
            Class<?> keySerde,
            Class<?> valueSerde,
            String applicationId,
            String bootstrapServer
    ) {
        Map<String, Object> props = new HashMap<>();

        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, keySerde.getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, valueSerde.getName());

        props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        //        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 1);

        props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class.getName());
        props.put(StreamsConfig.DEFAULT_PRODUCTION_EXCEPTION_HANDLER_CLASS_CONFIG, DefaultProductionExceptionHandler.class.getName());

        return props;
    }

    public static Properties buildBasicProducerConfig(String bootstrapServer, String transactionalId) {
        Properties settings = new Properties();

        settings.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        settings.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionalId);

        settings.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        settings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return settings;
    }
}