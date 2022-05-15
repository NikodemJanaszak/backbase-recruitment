package com.backbase.recruitment.service.exception;

public class UnauthenticatedUserException extends Exception {
    public UnauthenticatedUserException(String message) {
        super(message);
    }
}