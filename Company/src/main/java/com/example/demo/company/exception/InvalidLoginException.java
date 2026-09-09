package com.example.demo.company.exception;

public class InvalidLoginException
        extends RuntimeException {

    public InvalidLoginException(String message) {
        super(message);
    }
}