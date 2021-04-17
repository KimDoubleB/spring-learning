package com.test.importconfig.config;

import com.test.importconfig.service.CommonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public CommonService commonService() {
        return new CommonService();
    }

}
