package com.encrypt.encrypt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "db")
@Data
public class DBConfig {
    private String driver;
    private String url;
    private String username;
    private String password;
}