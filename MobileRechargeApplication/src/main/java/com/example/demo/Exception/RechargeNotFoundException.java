package com.example.demo.Exception;

public class RechargeNotFoundException extends RuntimeException {

    public RechargeNotFoundException(String message) {
        super(message);
    }
}