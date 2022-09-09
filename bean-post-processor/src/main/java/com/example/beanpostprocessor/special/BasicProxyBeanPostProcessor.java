package com.example.beanpostprocessor.special;

import com.example.beanpostprocessor.BBComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
@RequiredArgsConstructor
public class BasicProxyBeanPostProcessor implements BeanPostProcessor {

    private final Advisor advisor;

    // After 여야 한다?
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        var packageName = bean.getClass().getPackageName();
        if (packageName.startsWith("com.example.beanpostprocessor") && bean instanceof BBComponent) {
            log.info("[BasicProxyBeanPostProcessor] %s".formatted(beanName));
            var proxyFactory = new ProxyFactory(bean);
            proxyFactory.addAdvisor(advisor);
            return proxyFactory.getProxy();
        }
        return bean;
    }

}
