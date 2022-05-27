package com.kindnessocean.api.security.domain;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

public class EmailAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object email;


    public EmailAuthenticationToken(Object email) {
        super(null);
        this.email = email;
        setAuthenticated(false);
    }

    public EmailAuthenticationToken(Object email, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.email = email;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        throw new UnsupportedOperationException("This implementation didn't implement this method");
    }

    @Override
    public Object getPrincipal() {
        return this.email;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }
}