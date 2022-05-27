package com.kindnessocean.api.security;

import com.kindnessocean.api.security.domain.EmailUserDetails;
import com.kindnessocean.api.security.exception.EmailNotFoundAuthenticationException;
import com.kindnessocean.api.security.interf.EmailUserDetailsService;
import com.kindnessocean.shared.sql.entity.Email;
import com.kindnessocean.shared.sql.entity.RoleType;
import com.kindnessocean.shared.sql.entity.User;
import com.kindnessocean.shared.sql.repository.email.EmailRepository;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EmailUserDetailsServiceImpl implements EmailUserDetailsService {

    private final EmailRepository emailRepository;

    public EmailUserDetailsServiceImpl(final EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }


    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("This implementation didn't implement this method");
    }

    @Override
    public EmailUserDetails loadUserByEmail(final String email) throws EmailNotFoundAuthenticationException {
        Email result = emailRepository.getEmailByAddress(email);

        if (result == null) {
            throw new UsernameNotFoundException("Address: " + result + " not found");
        }


        if (result.getUser() != null) {
            return new EmailUserDetails(
                    result.getUser().getUsername(),
                    email,
                    getGrantedAuthorities(result.getUser())
            );
        } else {
            return new EmailUserDetails(
                    null,
                    email,
                    getDefaultGrantedAuthorities()
            );
        }
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {

        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();

        user.getRoles().forEach(role -> grantedAuthority.add(new SimpleGrantedAuthority(role.getRole().getValue())));

        return grantedAuthority;
    }

    private Collection<GrantedAuthority> getDefaultGrantedAuthorities() {

        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();

        grantedAuthority.add(new SimpleGrantedAuthority(RoleType.UncompletedUser.getValue()));

        return grantedAuthority;
    }
}