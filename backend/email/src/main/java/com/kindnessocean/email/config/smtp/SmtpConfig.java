package com.kindnessocean.email.config.smtp;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class SmtpConfig {

    @Value("${com.kindnessocean.shared.config.smtp.host}")
    private String host;

    @Value("${com.kindnessocean.shared.config.smtp.port}")
    private Integer port;

    @Value("${com.kindnessocean.shared.config.smtp.username}")
    private String username;

    @Value("${com.kindnessocean.shared.config.smtp.password}")
    private String password;

    @Value("${com.kindnessocean.shared.config.smtp.auth}")
    private String auth;

    @Value("${com.kindnessocean.shared.config.smtp.starttls.enable}")
    private String starttlsEnable;

    @Value("${com.kindnessocean.shared.config.smtp.debug}")
    private String debug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttlsEnable);
        props.put("mail.debug", debug);

        return mailSender;
    }
}
