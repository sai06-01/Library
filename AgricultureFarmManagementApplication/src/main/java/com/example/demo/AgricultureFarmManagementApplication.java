package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AgricultureFarmManagementApplication 
{

    public static void main(String[] args) 
    {

        SpringApplication.run(AgricultureFarmManagementApplication.class,args);

        System.out.println("Agriculture Farm Management Application Started Successfully");
    }
}