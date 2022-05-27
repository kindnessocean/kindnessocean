package com.kindnessocean.api.security.interf;

import com.kindnessocean.api.security.domain.EmailUserDetails;
import com.kindnessocean.api.security.exception.EmailNotFoundAuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface EmailUserDetailsService extends UserDetailsService {
    EmailUserDetails loadUserByEmail(String email) throws EmailNotFoundAuthenticationException;
}
