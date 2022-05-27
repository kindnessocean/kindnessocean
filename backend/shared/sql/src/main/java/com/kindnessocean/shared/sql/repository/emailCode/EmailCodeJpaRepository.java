package com.kindnessocean.shared.sql.repository.emailCode;

import com.kindnessocean.shared.sql.entity.EmailCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EmailCodeJpaRepository extends JpaRepository<EmailCode, Long> {

}
