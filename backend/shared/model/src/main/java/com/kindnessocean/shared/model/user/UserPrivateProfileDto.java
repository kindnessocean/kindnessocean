package com.kindnessocean.shared.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPrivateProfileDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
