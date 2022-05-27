package com.kindnessocean.shared.model.exception;

public class NotFoundObjectAppException extends AppException {
    public NotFoundObjectAppException(final String message) {
        super(message);
    }

    public NotFoundObjectAppException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
