package com.example.demo.company.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI companyOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Company API")
                                .version("1.0")
                                .description(
                                        "Company Management REST API"
                                )
                );
    }
}