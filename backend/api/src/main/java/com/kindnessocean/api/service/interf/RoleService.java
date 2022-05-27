package com.kindnessocean.api.service.interf;

import com.kindnessocean.shared.sql.entity.Role;
import java.util.Set;

public interface RoleService {
    Set<Role> getDefaultRoles();
}
