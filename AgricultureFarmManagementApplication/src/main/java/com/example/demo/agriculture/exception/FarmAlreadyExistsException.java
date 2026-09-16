package com.example.demo.agriculture.exception;

public class FarmAlreadyExistsException extends RuntimeException {

    public FarmAlreadyExistsException(String message)
    {

        super(message);
    }
}