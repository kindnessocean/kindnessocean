package com.kindnessocean.shared.sql.repository.email;

import com.kindnessocean.shared.sql.entity.Email;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmailRepositoryImpl implements EmailRepository {

    private final EmailJpaRepository jpaRepository;

    public EmailRepositoryImpl(final EmailJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    public Email getEmailByAddress(final String address) {
        return jpaRepository.getEmailByAddress(address);
    }

    @Override
    public Email update(final Email email) {
        return jpaRepository.save(email);
    }

    @Override
    public Email create(final Email email) {
        return jpaRepository.save(email);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public boolean isExistEmailByAddress(final String address) {
        return getEmailByAddress(address) != null;
    }
}