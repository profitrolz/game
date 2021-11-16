package com.game.exceptions;

import com.google.protobuf.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiException> handleBadRequestException(BadRequestException exception) {
        ApiException apiException = new ApiException(exception.getMessage(), exception, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiException> handleNotFoundException(NotFoundException exception) {
        ApiException apiException = new ApiException(exception.getMessage(), exception, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

}
