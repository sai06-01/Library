package com.example.demo.agriculture.exception;

public class FarmNotFoundException extends RuntimeException 
{

    public FarmNotFoundException(String message)
    {
        super(message);
    }
}