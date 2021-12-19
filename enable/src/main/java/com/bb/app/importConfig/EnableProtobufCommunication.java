package com.bb.app.importConfig;

import com.bb.common.config.ProtobufCommunicationConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ProtobufCommunicationConfig.class)
public @interface EnableProtobufCommunication {
}
