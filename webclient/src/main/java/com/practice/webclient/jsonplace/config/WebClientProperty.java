package com.practice.webclient.jsonplace.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "base")
public class WebClientProperty {
    private String uri;
    private String token;
}
