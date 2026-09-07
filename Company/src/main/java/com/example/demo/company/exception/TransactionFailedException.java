package com.example.demo.company.exception;

public class TransactionFailedException
        extends RuntimeException {

    public TransactionFailedException(String message) {
        super(message);
    }
}