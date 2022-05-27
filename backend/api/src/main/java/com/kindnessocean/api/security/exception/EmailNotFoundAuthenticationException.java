package com.kindnessocean.api.security.exception;

import org.springframework.security.core.AuthenticationException;

public class EmailNotFoundAuthenticationException extends AuthenticationException {

    /**
     * Constructs a <code>EmailNotFoundAuthenticationException</code> with the specified message.
     * @param msg the detail message.
     */
    public EmailNotFoundAuthenticationException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code EmailNotFoundAuthenticationException} with the specified message and root
     * cause.
     * @param msg the detail message.
     * @param cause root cause
     */
    public EmailNotFoundAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
