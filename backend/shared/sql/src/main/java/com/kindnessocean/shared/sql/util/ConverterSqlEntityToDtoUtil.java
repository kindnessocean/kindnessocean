package com.kindnessocean.shared.sql.util;

import com.kindnessocean.shared.model.user.UserPrivateProfileDto;
import com.kindnessocean.shared.sql.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConverterSqlEntityToDtoUtil {

    public static UserPrivateProfileDto convert(User user) {
        return UserPrivateProfileDto
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail().getAddress())
                .username(user.getUsername())
                .build();
    }
}