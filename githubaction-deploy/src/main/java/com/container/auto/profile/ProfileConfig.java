package com.container.auto.profile;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ProfileProperty.class)
public class ProfileConfig {
}
