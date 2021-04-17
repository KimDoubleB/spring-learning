package com.test.importconfig.config;

import com.test.importconfig.service.OneMoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OneMoreConfiguration {

    @Bean
    public OneMoreService oneMoreService() {
        return new OneMoreService();
    }
    
}
