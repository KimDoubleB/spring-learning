package com.bb.app.importBeanDefinitionRegistrar;

import com.bb.common.Payload.Type;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CommunicationConfigRegistrar.class)
public @interface EnableCommunicationUsingBeanRegistrar {

    Type type() default Type.JSON;

}
