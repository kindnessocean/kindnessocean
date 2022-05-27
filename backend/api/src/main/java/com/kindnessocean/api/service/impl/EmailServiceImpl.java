package com.kindnessocean.api.service.impl;

import com.kindnessocean.api.security.domain.EmailUserDetails;
import com.kindnessocean.api.service.interf.EmailService;
import com.kindnessocean.api.service.mq.producer.EmailRequestProducer;
import com.kindnessocean.shared.model.exception.NotFoundObjectAppException;
import com.kindnessocean.shared.model.util.CodeUtil;
import com.kindnessocean.shared.model.util.UuidUtil;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestKey;
import com.kindnessocean.shared.mq.model.emailrequest.EmailRequestValue;
import com.kindnessocean.shared.sql.entity.Email;
import com.kindnessocean.shared.sql.entity.EmailCode;
import com.kindnessocean.shared.sql.repository.email.EmailRepository;
import com.kindnessocean.shared.sql.repository.emailCode.EmailCodeRepository;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final EmailCodeRepository emailCodeRepository;
    private final EmailRequestProducer emailRequestProducer;

    private final UuidUtil uuidUtil;

    private final CodeUtil codeUtil;

    @Value("${com.kindnessocean.api.code.expiredTime}")
    private Integer expiredTime;

    public EmailServiceImpl(
            final EmailRepository emailRepository,
            final EmailCodeRepository emailCodeRepository, final EmailRequestProducer emailRequestProducer,
            final UuidUtil uuidUtil, final CodeUtil codeUtil) {
        this.emailRepository = emailRepository;
        this.emailCodeRepository = emailCodeRepository;
        this.emailRequestProducer = emailRequestProducer;
        this.uuidUtil = uuidUtil;
        this.codeUtil = codeUtil;
    }

    @Override
    public Email getEmailByAddress(final String address) {
        Email email = emailRepository.getEmailByAddress(address);

        if (email == null) {
            throw new NotFoundObjectAppException("Not found Email with such address=" + address);
        }

        return email;
    }

    @Override
    public EmailCode sendEmailCode(Email email) {
        UUID uuid = uuidUtil.randomUUID();
        Date created = new Date();
        Date expiredAt = new Date(created.getTime() + expiredTime);
        Integer code = codeUtil.generateCodeAsNumber();

        EmailCode emailCode;

        if (email.getCode() != null) {
            // Update previous code
            emailCode = email.getCode();

            emailCode.setCode(code);
            emailCode.setExpiredAt(expiredAt);

            emailCode = emailCodeRepository.update(emailCode);


        } else {
            // Create another
            emailCode = EmailCode.builder()
                    .email(email)
                    .expiredAt(expiredAt)
                    .code(code)
                    .build();

            emailCode = emailCodeRepository.save(emailCode);

            email.setCode(emailCode);

            email = emailRepository.update(email);
        }

        // Push to MQ topic
        emailRequestProducer.produceRequest(
                new EmailRequestKey(uuid),
                new EmailRequestValue(
                        email.getAddress(),
                        code
                )
        );

        return emailCode;
    }

    @Override
    public EmailCode getEmailCodeByAddress(final String address) {
        Email email = emailRepository.getEmailByAddress(address);

        if (email == null || email.getCode() == null) {
            throw new NotFoundObjectAppException("Email not found for this address " + address);
        }

        return email.getCode();
    }

    @Override
    public void expireEmailCode(final EmailCode emailCode) {
        //        fixme
        emailCode.setExpiredAt(new Date());

        emailCodeRepository.update(emailCode);
    }

    @Override
    public Email getEmailByEmailUserDetails(final EmailUserDetails emailUserDetails) {
        return getEmailByAddress(emailUserDetails.getEmail());
    }

    @Override
    public Email update(final Email email) {
        return emailRepository.update(email);
    }

    @Override
    public Email create(final String address) {
        Email email = emailRepository.create(
                Email.builder().address(address).build()
        );

        EmailCode emailCode = emailCodeRepository.save(
                EmailCode.builder()
                        .email(email)
                        .build()
        );

        email.setCode(emailCode);

        return emailRepository.update(email);
    }

    @Override
    public Boolean isExistEmailByAddress(final String address) {
        return emailRepository.getEmailByAddress(address) != null;
    }
}