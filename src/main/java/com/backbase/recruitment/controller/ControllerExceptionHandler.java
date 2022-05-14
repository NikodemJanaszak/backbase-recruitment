package com.backbase.recruitment.controller;

import com.backbase.recruitment.service.IncorrectRatingException;
import com.backbase.recruitment.service.UnauthenticatedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {IncorrectRatingException.class})
    public ResponseEntity<String> resourceNotFoundException(IncorrectRatingException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UnauthenticatedUserException.class})
    public ResponseEntity<String> unauthorizedUserException(UnauthenticatedUserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
