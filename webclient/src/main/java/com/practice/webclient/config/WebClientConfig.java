package com.practice.webclient.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(WebClientProperty.class)
@Configuration
public class WebClientConfig {
}
