package com.example.errors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
public class ErrorConfig {

    @Bean
    SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties excMapping = new Properties();
        excMapping.put(NullPointerException.class.getSimpleName(), "npe");

        resolver.setExceptionMappings(excMapping);

        return resolver;
    }
}
