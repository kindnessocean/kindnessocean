package com.kindnessocean.shared.sql.repository.emailCode;

import com.kindnessocean.shared.sql.entity.EmailCode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmailCodeRepositoryImpl implements EmailCodeRepository {

    private final EmailCodeJpaRepository jpaRepository;

    public EmailCodeRepositoryImpl(final EmailCodeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public EmailCode update(final EmailCode emailCode) {
        return jpaRepository.save(emailCode);
    }

    @Override
    public EmailCode save(final EmailCode emailCode) {
        return jpaRepository.save(emailCode);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }
}
