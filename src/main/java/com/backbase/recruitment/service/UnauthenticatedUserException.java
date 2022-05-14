package com.backbase.recruitment.service;

public class UnauthenticatedUserException extends Exception {
    public UnauthenticatedUserException(String message) {
        super(message);
    }
}