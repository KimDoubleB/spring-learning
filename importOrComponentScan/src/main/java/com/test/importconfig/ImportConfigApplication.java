package com.test.importconfig;

import com.test.importconfig.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(AppConfiguration.class)
@EnableAutoConfiguration
@Configuration
public class ImportConfigApplication {

    public static ApplicationContext ac;

    public static void main(String[] args) {
        ac = SpringApplication.run(ImportConfigApplication.class, args);

        String[] beans = ac.getBeanDefinitionNames();
        for (String beanName : beans) {
            System.out.println(beanName);
        }
    }

}
