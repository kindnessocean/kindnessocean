package com.kindnessocean.api.security.domain;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class EmailUserDetails extends User {

    private final String email;

    public EmailUserDetails(final String username, final String email,
                            final Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, null, authorities);
        this.email = email;
    }

    public EmailUserDetails(final String username, final String email,
                            final boolean enabled, final boolean accountNonExpired,
                            final boolean credentialsNonExpired, final boolean accountNonLocked,
                            final Collection<? extends GrantedAuthority> authorities) {
        super(username, null, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException("This implementation is password less");
    }
}
