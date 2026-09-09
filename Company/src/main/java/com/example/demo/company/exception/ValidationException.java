package com.example.demo.company.exception;

public class ValidationException
        extends RuntimeException {

    public ValidationException(
            String message) {

        super(message);
    }
}