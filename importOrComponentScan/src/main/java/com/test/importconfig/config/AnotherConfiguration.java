package com.test.importconfig.config;

import com.test.importconfig.service.AnotherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnotherConfiguration {

    @Bean
    public AnotherService anotherService() {
        return new AnotherService();
    }

}
