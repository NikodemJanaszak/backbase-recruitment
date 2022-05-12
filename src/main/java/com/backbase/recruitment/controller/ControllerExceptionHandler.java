package com.backbase.recruitment.controller;

import com.backbase.recruitment.service.IncorrectRatingException;
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
}
