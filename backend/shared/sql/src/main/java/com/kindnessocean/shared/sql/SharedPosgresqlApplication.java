package com.kindnessocean.shared.sql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-sql.properties")
public class SharedPosgresqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharedPosgresqlApplication.class, args);
    }
}