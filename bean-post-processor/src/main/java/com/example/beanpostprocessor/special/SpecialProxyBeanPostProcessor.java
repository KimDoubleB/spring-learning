package com.example.beanpostprocessor.special;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class SpecialProxyBeanPostProcessor implements BeanPostProcessor {

    private final Advisor advisor;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        var hasSpecialAnnotationMethod = Arrays.stream(bean.getClass().getMethods())
                                                 .anyMatch(method -> method.isAnnotationPresent(Special.class));
        if (hasSpecialAnnotationMethod) {
            log.info("[SpecialProxyBeanPostProcessor] %s".formatted(beanName));
            var proxyFactory = new ProxyFactory(bean);
            proxyFactory.addAdvisor(advisor);
            return proxyFactory.getProxy();
        }
        return bean;
    }

}
