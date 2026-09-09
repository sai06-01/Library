package com.example.demo.company.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory>
    tomcatCustomizer() {

        return factory -> {
            factory.setPort(8080);

            factory.addAdditionalTomcatConnectors(
                    createConnector(8081),
                    createConnector(8082)
            );
        };
    }

    private org.apache.catalina.connector.Connector
    createConnector(int port) {

        org.apache.catalina.connector.Connector connector =
                new org.apache.catalina.connector.Connector(
                        "org.apache.coyote.http11.Http11NioProtocol"
                );

        connector.setPort(port);

        return connector;
    }
}