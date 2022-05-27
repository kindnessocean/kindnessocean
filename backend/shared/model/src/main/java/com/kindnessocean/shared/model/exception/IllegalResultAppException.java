package com.kindnessocean.shared.model.exception;

public class IllegalResultAppException extends AppException {
    public IllegalResultAppException(final String message) {
        super(message);
    }

    public IllegalResultAppException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
