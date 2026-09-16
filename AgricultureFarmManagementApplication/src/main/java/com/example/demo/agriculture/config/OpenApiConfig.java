package com.example.demo.agriculture.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig 
{

    @Bean
    public OpenAPI agricultureOpenAPI() 
    {
    	return new OpenAPI().info(new Info().title("Agriculture Farm Management API")
    			.version("1.0")
                .description("Farm, Crop, Task, "+ "Transaction and Inventory APIs"));
    }
}