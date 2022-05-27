package com.kindnessocean.shared.mq.model.emailrequest;

import com.kindnessocean.shared.mq.model.ClassDeSerializer;
import com.kindnessocean.shared.mq.model.ClassSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class EmailRequestKeySerde implements Serde<EmailRequestKey> {
    private final ClassDeSerializer<EmailRequestKey> deSerializer = new ClassDeSerializer<>(EmailRequestKey.class);
    private final ClassSerializer<EmailRequestKey> serializer = new ClassSerializer<>();

    @Override
    public Serializer<EmailRequestKey> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<EmailRequestKey> deserializer() {
        return deSerializer;
    }
}
