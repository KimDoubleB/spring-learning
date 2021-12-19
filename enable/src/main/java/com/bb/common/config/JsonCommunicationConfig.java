package com.bb.common.config;

import com.bb.common.JsonPayload;
import com.bb.common.service.CommunicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonCommunicationConfig {

    @Bean
    public CommunicationService communicationService() {
        return new CommunicationService(new JsonPayload());
    }

}
