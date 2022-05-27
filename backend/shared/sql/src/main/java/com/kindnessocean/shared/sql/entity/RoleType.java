package com.kindnessocean.shared.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    UncompletedUser("ROLE_UNCOMPLETED_USER"),
    User("ROLE_USER"),
    Moderator("ROLE_MODERATOR"),
    Admin("ROLE_ADMIN");

    private final String value;

}
