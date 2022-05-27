package com.kindnessocean.shared.sql.repository.role;


import com.kindnessocean.shared.sql.entity.Role;
import com.kindnessocean.shared.sql.entity.RoleType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryImpl(final RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Role create(final RoleType roleType) {
        return roleJpaRepository
                .save(Role
                        .builder()
                        .role(roleType)
                        .build()
                );
    }

    @Override
    public Role save(final Role role) {
        return roleJpaRepository.save(role);
    }

    @Override
    public Role getRoleByRoleType(final RoleType roleType) {
        return roleJpaRepository.findByRole(roleType);
    }
}