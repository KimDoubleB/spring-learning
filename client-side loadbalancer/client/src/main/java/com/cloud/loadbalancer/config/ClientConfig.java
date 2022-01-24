package com.cloud.loadbalancer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@LoadBalancerClient(name = "doubleb-server", configuration = GreetingConfig.class)
public class ClientConfig {

    @LoadBalanced
    @Bean
    public WebClient.Builder loadbalancerClient() {
        return WebClient.builder();
    }

}
