package com.container.auto.config;

import com.container.auto.handler.AutoHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ProfileProperty.class)
public class ServerConfig {

    @Bean
    AutoHandler autoHandler(ProfileProperty profileProperty) {
        return new AutoHandler(profileProperty);
    }

}
