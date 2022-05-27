package com.kindnessocean.shared.mq.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "com.kindnessocean.shared.config.mq")
public class KafkaConnectionProperties {
    private String bootstrapServer;

}
