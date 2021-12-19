package com.bb.app.importselector;

import com.bb.common.Payload.Type;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CommunicationIConfigSelector.class)
public @interface EnableCommunicationUsingSelector {

    Type type() default Type.JSON;

}
