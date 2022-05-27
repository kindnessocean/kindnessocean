package com.kindnessocean.shared.sql.repository.user;


import com.kindnessocean.shared.sql.entity.User;

public interface UserRepository {

    User save(User user);

    User getUserByUsername(String username);

    Boolean isExistUserByUsername(String username);

    long count();
}
