package com.test.componentscanconfig.config;

import com.test.componentscanconfig.service.CommonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public CommonService commonService() {
        return new CommonService();
    }

}
