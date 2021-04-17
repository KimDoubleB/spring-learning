package com.test.importconfig.config;

import com.test.importconfig.service.AllNewService;
import com.test.importconfig.service.AnotherService;
import com.test.importconfig.service.CommonService;
import com.test.importconfig.service.OneMoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({CommonConfiguration.class, AnotherConfiguration.class, OneMoreConfiguration.class})
@Configuration
public class AppConfiguration {

    @Bean
    public AllNewService allNewService(final CommonService commonService,
                                       final AnotherService anotherService,
                                       final OneMoreService oneMoreService) {
        return new AllNewService(commonService, anotherService, oneMoreService);
    }

}
