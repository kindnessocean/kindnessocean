package com.kindnessocean.shared.sql.repository.user;

import com.kindnessocean.shared.sql.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(final UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(final User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public User getUserByUsername(final String username) {
        return userJpaRepository.getUserByUsername(username);
    }

    @Override
    public Boolean isExistUserByUsername(final String username) {
        return getUserByUsername(username) != null;
    }

    @Override
    public long count() {
        return userJpaRepository.count();
    }
}
