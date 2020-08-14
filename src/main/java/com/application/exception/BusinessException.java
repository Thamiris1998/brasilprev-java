package com.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BusinessException extends ResponseStatusException {
    public BusinessException(HttpStatus status) {
        super(status);
    }
    public BusinessException(String message) {
        super(HttpStatus.BAD_REQUEST , message);
    }
}
