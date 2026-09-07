package com.example.demo.company.logger;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Component
public class CompanyLogger {

    private static final Logger LOGGER =
            Logger.getLogger("Companylogger");

    static {
        try {
            FileHandler fileHandler =
                    new FileHandler("company.log", true);

            fileHandler.setFormatter(new SimpleFormatter());

            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info(String message) {
        LOGGER.info(message);
    }

    public void warning(String message) {
        LOGGER.warning(message);
    }

    public void severe(String message) {
        LOGGER.severe(message);
    }
}