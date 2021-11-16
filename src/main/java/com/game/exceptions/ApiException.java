package com.game.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException {

    private final String message;
    private final Throwable exception;
    private final HttpStatus httpStatus;

    public ApiException(String message, Throwable exception, HttpStatus httpStatus) {
        this.message = message;
        this.exception = exception;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getException() {
        return exception;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
