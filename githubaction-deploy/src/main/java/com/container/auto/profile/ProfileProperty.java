package com.container.auto.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "server")
public record ProfileProperty(String profile) {
}
