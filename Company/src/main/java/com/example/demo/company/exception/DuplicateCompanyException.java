package com.example.demo.company.exception;

public class DuplicateCompanyException
        extends RuntimeException {

    public DuplicateCompanyException(String message) {
        super(message);
    }
}