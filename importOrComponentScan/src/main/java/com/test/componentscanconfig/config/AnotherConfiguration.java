package com.test.componentscanconfig.config;

import com.test.componentscanconfig.service.AnotherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnotherConfiguration {

    @Bean
    public AnotherService anotherService() {
        return new AnotherService();
    }

}
