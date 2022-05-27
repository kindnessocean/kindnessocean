package com.kindnessocean.api.service.impl;

import com.kindnessocean.api.security.jwt.JwtTokenProvider;
import com.kindnessocean.api.service.interf.AuthService;
import com.kindnessocean.api.service.interf.EmailService;
import com.kindnessocean.api.service.interf.UserService;
import com.kindnessocean.shared.model.exception.IllegalArgAppException;
import com.kindnessocean.shared.model.service.AuthenticationUser;
import com.kindnessocean.shared.sql.entity.Email;
import com.kindnessocean.shared.sql.entity.EmailCode;
import com.kindnessocean.shared.sql.entity.Role;
import com.kindnessocean.shared.sql.entity.RoleType;
import com.kindnessocean.shared.sql.entity.User;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final EmailService emailService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${com.kindnessocean.api.auth.token.validityTime}")
    private Long tokenValidityInMs;

    public AuthServiceImpl(
            final UserService userService,
            final EmailService emailService,
            final JwtTokenProvider jwtTokenProvider
    ) {
        this.userService = userService;
        this.emailService = emailService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private AuthenticationUser auth(final String address, final Set<RoleType> roles) {
        String token = jwtTokenProvider.createToken(address, roles);

        return AuthenticationUser.builder()
                .tokenValidityInMs(tokenValidityInMs)
                .token(token)
                .build();
    }

    @Override
    public EmailCode sendCodeToEmail(final String address) {
        Email email;

        if (emailService.isExistEmailByAddress(address)) {
            email = emailService.getEmailByAddress(address);
        } else {
            email = emailService.create(address);
        }

        return emailService.sendEmailCode(email);
    }

    @Override
    public AuthenticationUser requestJwtToken(final String email, final Integer code) {
        EmailCode emailCode = emailService.getEmailCodeByAddress(email);

        if (emailCode == null || !Objects.equals(emailCode.getCode(), code)) {
            throw new IllegalArgAppException("That code " + code + " didn't send to " + email);
        }

        final long expiredAtTime = emailCode.getExpiredAt().getTime();

        // Delete all letter to this email address
        emailService.expireEmailCode(emailCode);

        if (new Date().getTime() >= expiredAtTime) {
            throw new IllegalArgAppException("That code " + code + " expired");
        }

        if (userService.isExistUserByEmailAddress(email)) {

            User user = userService.getUserByEmailAddress(email);

            return auth(
                    email,
                    user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet())
            );
        }

        return auth(email, Set.of(RoleType.UncompletedUser));
    }
}