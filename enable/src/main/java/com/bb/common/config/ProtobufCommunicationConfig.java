package com.bb.common.config;

import com.bb.common.ProtobufPayload;
import com.bb.common.service.CommunicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProtobufCommunicationConfig {

    @Bean
    public CommunicationService communicationService() {
        log.info("Register ProtobufPayload to CommunicationService");
        return new CommunicationService(new ProtobufPayload());
    }

}
