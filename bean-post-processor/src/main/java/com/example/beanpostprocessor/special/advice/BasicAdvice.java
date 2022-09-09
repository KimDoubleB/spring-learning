package com.example.beanpostprocessor.special.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class BasicAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("[Basic Advice] 실행");
        Object result = invocation.proceed();
        log.info("[Basic Advice] 종료");
        return result;
    }

}
