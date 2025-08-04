package com.practice.Course.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Email '" + email + "' already exists.");
    }
}

