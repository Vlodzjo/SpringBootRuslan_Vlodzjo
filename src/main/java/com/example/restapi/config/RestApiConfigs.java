package com.example.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class RestApiConfigs {

    @Bean
    public Random random() {
        return new Random();
    }
}
