package com.kindnessocean.shared.mq;

import com.kindnessocean.shared.mq.properties.KafkaConnectionProperties;
import com.kindnessocean.shared.mq.properties.KafkaTopicProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties({
        KafkaConnectionProperties.class,
        KafkaTopicProperties.class
})
@PropertySource("classpath:application-mq.properties")
public class SharedKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharedKafkaApplication.class, args);
    }
}