package com.cloud.loadbalancer.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(TargetServerProperty.class)
public class GreetingConfig {

    @Bean
    @Primary
    ServiceInstanceListSupplier serviceInstanceListSupplier(TargetServerProperty serverProperty) {
        return new GreetingServiceInstanceListSuppler("doubleb-server", serverProperty);
    }

}
