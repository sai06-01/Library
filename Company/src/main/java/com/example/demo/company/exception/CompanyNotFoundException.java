package com.example.demo.company.exception;

public class CompanyNotFoundException
        extends RuntimeException {

    public CompanyNotFoundException(String message) {
        super(message);
    }
}