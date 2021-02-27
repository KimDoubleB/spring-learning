package com.tistory.bbdevstory.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
Target: 어노테이션이 사용될 위치
- ElementType.PARAMETER: 파라미터에서 사용되는 어노테이션임을 의미

@interface: 어노테이션임을 의미
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
