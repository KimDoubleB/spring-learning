package com.example.beanpostprocessor.special.advice;

import com.example.beanpostprocessor.special.Special;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class SpecialAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (invocation.getMethod().isAnnotationPresent(Special.class)) {
            log.info("[Special Advice] 실행");
            Object result = invocation.proceed();
            log.info("[Special Advice] 종료");
            return result;
        }
        return invocation.proceed();
    }

}
