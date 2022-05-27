package com.kindnessocean.api.service.impl;

import com.kindnessocean.api.security.domain.EmailUserDetails;
import com.kindnessocean.api.service.interf.EmailService;
import com.kindnessocean.api.service.interf.RoleService;
import com.kindnessocean.api.service.interf.UserService;
import com.kindnessocean.shared.model.exception.IllegalArgAppException;
import com.kindnessocean.shared.model.exception.NotFoundObjectAppException;
import com.kindnessocean.shared.sql.entity.Email;
import com.kindnessocean.shared.sql.entity.User;
import com.kindnessocean.shared.sql.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final EmailService emailService;
    private final UserRepository userRepository;

    private final RoleService roleService;

    public UserServiceImpl(
            final EmailService emailService,
            final UserRepository userRepository,
            final RoleService roleService) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User signUp(final EmailUserDetails emailUserDetails,
                       final String firstName, final String lastName, final String username) {
        Email email = emailService.getEmailByEmailUserDetails(emailUserDetails);

        if (userRepository.isExistUserByUsername(username)) {
            throw new IllegalArgAppException("User with such username '" + username + "' exist");
        }

        User user = User.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .roles(roleService.getDefaultRoles())
                .build();

        user = userRepository.save(user);

        email.setUser(user);
        emailService.update(email);

        return user;
    }

    @Override
    public User getUserByEmailAddress(final String address) {
        Email email = emailService.getEmailByAddress(address);

        User user = email.getUser();

        if (user == null) {
            throw new NotFoundObjectAppException("Not found User with such address");
        }

        return user;
    }

    @Override
    public boolean isExistUserByEmailAddress(final String address) {
        Email email = emailService.getEmailByAddress(address);

        return email.getUser() != null;
    }
}