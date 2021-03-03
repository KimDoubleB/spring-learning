package com.encrypt.encrypt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "db")
public class DBProperties {
    private String driver;
    private String url;
    private String username;
    private String password;
}
