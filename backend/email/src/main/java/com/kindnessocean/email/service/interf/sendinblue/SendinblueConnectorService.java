package com.kindnessocean.email.service.interf.sendinblue;

public interface SendinblueConnectorService {
    void sendEmailCodeToUser(String address, Integer code);
}
