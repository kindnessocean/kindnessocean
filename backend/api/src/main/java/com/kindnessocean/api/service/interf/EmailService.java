package com.kindnessocean.api.service.interf;


import com.kindnessocean.api.security.domain.EmailUserDetails;
import com.kindnessocean.shared.sql.entity.Email;
import com.kindnessocean.shared.sql.entity.EmailCode;

public interface EmailService {
    Email getEmailByAddress(String address);

    EmailCode sendEmailCode(Email email);

    EmailCode getEmailCodeByAddress(String address);

    void expireEmailCode(EmailCode emailCode);

    Email getEmailByEmailUserDetails(EmailUserDetails emailUserDetails);

    Email update(Email email);

    Email create(String address);

    Boolean isExistEmailByAddress(String address);
}
