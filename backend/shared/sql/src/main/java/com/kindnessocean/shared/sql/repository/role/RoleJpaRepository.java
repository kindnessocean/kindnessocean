package com.kindnessocean.shared.sql.repository.role;

import com.kindnessocean.shared.sql.entity.Role;
import com.kindnessocean.shared.sql.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
interface RoleJpaRepository extends JpaRepository<Role, Long> {
    Role findByRole(RoleType roleType);
}
