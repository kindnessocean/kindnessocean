package com.kindnessocean.shared.mq.model.emailrequest;

import com.kindnessocean.shared.mq.model.ClassDeSerializer;
import com.kindnessocean.shared.mq.model.ClassSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class EmailRequestValueSerde implements Serde<EmailRequestValue> {
    private final ClassDeSerializer<EmailRequestValue> deSerializer = new ClassDeSerializer<>(EmailRequestValue.class);
    private final ClassSerializer<EmailRequestValue> serializer = new ClassSerializer<>();

    @Override
    public Serializer<EmailRequestValue> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<EmailRequestValue> deserializer() {
        return deSerializer;
    }
}
