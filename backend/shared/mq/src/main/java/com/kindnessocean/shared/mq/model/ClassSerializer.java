package com.kindnessocean.shared.mq.model;


import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class ClassSerializer<T> implements Serializer<T> {

    private final Serializer<T> serializer = new JsonSerializer<>();

    @Override
    public byte[] serialize(String topic, T type) {

        return serializer.serialize(topic, type);
    }

}

