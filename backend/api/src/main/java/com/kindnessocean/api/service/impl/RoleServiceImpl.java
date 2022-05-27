package com.kindnessocean.api.service.impl;

import com.kindnessocean.api.service.interf.RoleService;
import com.kindnessocean.shared.sql.entity.Role;
import com.kindnessocean.shared.sql.entity.RoleType;
import com.kindnessocean.shared.sql.repository.role.RoleRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final List<RoleType> DEFAULT_ROLE_TYPES = Arrays.asList(RoleType.UncompletedUser, RoleType.User);

    private final RoleRepository roleRepository;

    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getDefaultRoles() {
        Set<Role> roles = new HashSet<>();

        DEFAULT_ROLE_TYPES.forEach(roleType -> roles.add(getOrCreate(roleType)));

        return roles;
    }

    private Role getOrCreate(final RoleType roleType) {
        Role role = roleRepository.getRoleByRoleType(roleType);

        if (role != null) {
            return role;
        }

        return roleRepository.create(roleType);
    }
}