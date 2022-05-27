package com.kindnessocean.shared.sql.repository.email;


import com.kindnessocean.shared.sql.entity.Email;

public interface EmailRepository {
    Email getEmailByAddress(String address);

    Email update(Email email);

    Email create(Email email);

    long count();

    boolean isExistEmailByAddress(String address);
}
