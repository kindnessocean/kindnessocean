package com.kindnessocean.api.service.interf;


import com.kindnessocean.shared.model.service.AuthenticationUser;
import com.kindnessocean.shared.sql.entity.EmailCode;

public interface AuthService {

    EmailCode sendCodeToEmail(String address);

    AuthenticationUser requestJwtToken(String emailAddress, Integer code);
}
