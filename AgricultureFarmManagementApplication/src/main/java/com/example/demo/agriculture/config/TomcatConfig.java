package com.example.demo.agriculture.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>
{

    @Override
    public void customize(TomcatServletWebServerFactory factory)
    {

        factory.setPort(8080);
    }
}