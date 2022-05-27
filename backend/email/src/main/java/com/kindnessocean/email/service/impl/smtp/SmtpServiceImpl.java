package com.kindnessocean.email.service.impl.smtp;

import com.kindnessocean.email.service.interf.smtp.SmtpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpServiceImpl implements SmtpService {

    @Value("${com.kindnessocean.shared.config.smtp.username}")
    private String username;

    private final JavaMailSender emailSender;

    public SmtpServiceImpl(final JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendEmailWithCodeNumber(final String address, final Integer code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(address);
        message.setSubject("You token for SignIn");
        message.setText("Token : " + code);

        emailSender.send(message);
    }
//    // Sendinblue
//    ApiClient defaultClient = Configuration.getDefaultApiClient();
//
//    ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
//        apiKey.setApiKey("<API-KEY>");
//
//
//    TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
//    SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
//        sendSmtpEmail.sender(new SendSmtpEmailSender().email("ilyailyukou@gmail.com").name("Ilya Ilyukou"));
//        sendSmtpEmail.setSubject("You code");
//        sendSmtpEmail.setHtmlContent("<div>" + code + "</div>");
//        sendSmtpEmail.to(Arrays.asList(new SendSmtpEmailTo().email(address)));
//        try {
//        CreateSmtpEmail result = apiInstance.sendTransacEmail(sendSmtpEmail);
//        System.out.println(result);
//    } catch (ApiException e) {
//        System.err.println("Exception when calling TransactionalEmailsApi#sendTransacEmail");
//        e.printStackTrace();
//    }
}
