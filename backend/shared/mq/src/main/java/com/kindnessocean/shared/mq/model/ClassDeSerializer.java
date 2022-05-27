package com.kindnessocean.shared.mq.model;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class ClassDeSerializer<T> implements Deserializer<T> {

    private final Deserializer<T> deserializer;

    public ClassDeSerializer(Class<T> destinationClass) {
        this.deserializer = new JsonDeserializer<>(destinationClass);
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        return deserializer.deserialize(topic, bytes);
    }
}
