package com.kindnessocean.shared.sql.repository.email;

import com.kindnessocean.shared.sql.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
interface EmailJpaRepository extends JpaRepository<Email, Long> {
    Email getEmailByAddress(String address);
}
