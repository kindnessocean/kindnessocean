package com.kindnessocean.api.service.interf;

import com.kindnessocean.api.security.domain.EmailUserDetails;
import com.kindnessocean.shared.sql.entity.User;

public interface UserService {

    User signUp(EmailUserDetails emailUserDetails, String firstName, String lastName, String username);

    User getUserByEmailAddress(String address);

    boolean isExistUserByEmailAddress(String address);
}
