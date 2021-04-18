package com.practice.webclient.jsonplace.config;

import com.practice.webclient.jsonplace.service.JsonPlaceWebClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(WebClientProperty.class)
@Configuration
public class WebClientConfig {
    @Bean
    public JsonPlaceWebClient jsonPlaceWebClient(WebClientProperty webClientProperty) {
        return new JsonPlaceWebClient(webClientProperty);
    }
}
