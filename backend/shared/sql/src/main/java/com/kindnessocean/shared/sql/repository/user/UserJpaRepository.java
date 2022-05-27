package com.kindnessocean.shared.sql.repository.user;


import com.kindnessocean.shared.sql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
interface UserJpaRepository extends JpaRepository<User, Long> {
    Boolean existsUserByUsername(String username);

    Boolean existsUserByEmail(String email);

    User getUserByEmail(String email);

    User getUserByUsername(String username);
}
