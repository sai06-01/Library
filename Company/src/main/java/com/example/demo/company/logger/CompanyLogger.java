package com.example.demo.company.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

@Component
public class CompanyLogger {

    private static final Logger logger =
            LoggerFactory.getLogger("Companylogger");

    public void info(String message) {

        logger.info(message);
    }

    public void error(String message) {

        logger.error(message);
    }

    public void warn(String message) {

        logger.warn(message);
    }

    public void debug(String message) {

        logger.debug(message);
    }
}