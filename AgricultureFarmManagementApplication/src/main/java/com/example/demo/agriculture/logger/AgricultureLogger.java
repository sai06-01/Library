package com.example.demo.agriculture.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

@Component
public class AgricultureLogger 
{

    private static final Logger LOGGER =LoggerFactory.getLogger("AgricultureLogger");

    public void info(String message) 
    {
        LOGGER.info(message);
    }

    public void warn(String message)
    {
        LOGGER.warn(message);
    }

    public void error(String message)
    {
        LOGGER.error(message);
    }
}