package com.example.demo.agriculture.scheduler;

import com.example.demo.agriculture.service.FarmService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgricultureScheduler 
{

    private final FarmService farmService;

    public AgricultureScheduler(
            FarmService farmService) 
    {

        this.farmService = farmService;
    }

    @Scheduled(fixedRate = 30000)
    public void runFarmReport()
    {

        farmService.generateReport();

        System.out.println(
                "Agriculture scheduler executed at "
                        + LocalDateTime.now());
    }
}