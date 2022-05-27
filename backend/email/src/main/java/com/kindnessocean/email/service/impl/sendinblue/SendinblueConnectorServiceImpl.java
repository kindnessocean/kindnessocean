package com.kindnessocean.email.service.impl.sendinblue;

import com.kindnessocean.email.service.interf.sendinblue.SendinblueConnectorService;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.CreateSmtpEmail;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

@Slf4j
@Service
public class SendinblueConnectorServiceImpl implements SendinblueConnectorService {

    @Value("${com.kindnessocean.api.sendinblue.api-key}")
    private String sendinblueApiKey;

    @Override
    public void sendEmailCodeToUser(final String address, final Integer code) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        // Configure API key authorization: api-key
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(sendinblueApiKey);

        TransactionalEmailsApi api = new TransactionalEmailsApi();
        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        sender.setEmail("ilyailyukou@gmail.com");
        sender.setName("Ilya Ilyukou");

        List<SendSmtpEmailTo> toList = new ArrayList<SendSmtpEmailTo>();
        SendSmtpEmailTo to = new SendSmtpEmailTo();
        to.setEmail(address);
//        to.setName("John Doe");
        toList.add(to);

        Properties params = new Properties();
        params.setProperty("code", String.valueOf(code));
        params.setProperty("subject", "Subject");

        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        sendSmtpEmail.setSender(sender);
        sendSmtpEmail.setTo(toList);
        sendSmtpEmail.setHtmlContent("<html><body><h1>This is my first transactional email {{params.code}}</h1></body></html>");
        sendSmtpEmail.setSubject("My {{params.subject}}");
        sendSmtpEmail.setParams(params);

        CreateSmtpEmail response = null;
        try {
            response = api.sendTransacEmail(sendSmtpEmail);
            log.info("Send mail {}", response);
        } catch (ApiException e) {
            log.warn("Send mail error {}", response);
        }
    }
}
