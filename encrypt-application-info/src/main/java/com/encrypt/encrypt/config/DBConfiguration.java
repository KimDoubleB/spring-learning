package com.encrypt.encrypt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties(DBProperties.class)
public class DBConfiguration {
    DBProperties dbProperties;

    public DBConfiguration(DBProperties dbProperties) {
        this.dbProperties = dbProperties;
    }
}