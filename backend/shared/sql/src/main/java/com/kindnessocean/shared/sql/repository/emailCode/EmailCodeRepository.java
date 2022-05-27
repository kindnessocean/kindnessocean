package com.kindnessocean.shared.sql.repository.emailCode;


import com.kindnessocean.shared.sql.entity.EmailCode;

public interface EmailCodeRepository {
    EmailCode update(EmailCode emailCode);

    EmailCode save(EmailCode emailCode);

    long count();
}
