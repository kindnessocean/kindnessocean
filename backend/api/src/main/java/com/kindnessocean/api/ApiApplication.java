package com.kindnessocean.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@ComponentScan(basePackages = {
        "com.kindnessocean.api",
        "com.kindnessocean.shared.mq",
        "com.kindnessocean.shared.model",
        "com.kindnessocean.shared.sql",
})
@PropertySources({
        @PropertySource("classpath:application-mq.properties"),
        @PropertySource("classpath:application-api.properties")
})
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}