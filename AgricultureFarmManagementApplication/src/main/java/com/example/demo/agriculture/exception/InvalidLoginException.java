package com.example.demo.agriculture.exception;

public class InvalidLoginException extends RuntimeException 
{

    public InvalidLoginException(String message) 
    {
        super(message);
    }
}