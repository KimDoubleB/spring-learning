package com.test.componentscanconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ComponentScanConfigApplication {
    private static ApplicationContext ac;

    public static void main(String[] args) {
        ac = SpringApplication.run(ComponentScanConfigApplication.class, args);
        String[] beans = ac.getBeanDefinitionNames();
        for (String bean : beans) {
            System.out.println(bean);
        }
    }

}
