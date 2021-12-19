package com.bb.common.config;

import com.bb.common.ProtobufPayload;
import com.bb.common.service.CommunicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProtobufCommunicationConfig {

    @Bean
    public CommunicationService communicationService() {
        return new CommunicationService(new ProtobufPayload());
    }

}
