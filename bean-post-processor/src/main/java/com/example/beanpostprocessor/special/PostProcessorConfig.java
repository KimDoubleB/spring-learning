package com.example.beanpostprocessor.special;

import com.example.beanpostprocessor.special.advice.BasicAdvice;
import com.example.beanpostprocessor.special.advice.SpecialAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostProcessorConfig {

    @Bean
    public BasicProxyBeanPostProcessor basicProxyBeanPostProcessor() {
        return new BasicProxyBeanPostProcessor(new DefaultPointcutAdvisor(Pointcut.TRUE, new BasicAdvice()));
    }

    @Bean
    public SpecialProxyBeanPostProcessor specialProxyBeanPostProcessor() {
        return new SpecialProxyBeanPostProcessor(new DefaultPointcutAdvisor(Pointcut.TRUE, new SpecialAdvice()));
    }

}
