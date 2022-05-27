package com.kindnessocean.email;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@ComponentScan(basePackages = {
        "com.kindnessocean.email",
        "com.kindnessocean.shared.mq",
})
@PropertySources({
        @PropertySource("classpath:application-mq.properties"),
        @PropertySource("classpath:application-email.properties")
})
@SpringBootApplication
public class EmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }
}
