package com.kindnessocean.shared.sql.repository.role;


import com.kindnessocean.shared.sql.entity.Role;
import com.kindnessocean.shared.sql.entity.RoleType;

public interface RoleRepository {
    Role create(RoleType roleType);

    Role save(Role role);

    Role getRoleByRoleType(RoleType roleType);
}
