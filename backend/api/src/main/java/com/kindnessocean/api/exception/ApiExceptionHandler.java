package com.kindnessocean.api.exception;

import com.kindnessocean.shared.model.exception.NotFoundObjectAppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundObjectAppException.class)
    public ResponseEntity<Object> handle(NotFoundObjectAppException e) {
        return ResponseEntity.notFound().build();
    }
}