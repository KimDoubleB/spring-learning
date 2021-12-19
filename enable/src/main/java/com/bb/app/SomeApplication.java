package com.bb.app;

import com.bb.app.importBeanDefinitionRegistrar.EnableCommunicationUsingBeanRegistrar;
import com.bb.app.importConfig.EnableJsonCommunication;
import com.bb.app.importConfig.EnableProtobufCommunication;
import com.bb.app.importselector.EnableCommunicationUsingSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.bb.common.Payload.Type;

@SpringBootApplication
//@EnableJsonCommunication
//@EnableProtobufCommunication
//@EnableCommunicationUsingSelector(type = Type.PROTOBUF)
//@EnableCommunicationUsingBeanRegistrar(type = Type.JSON)
public class SomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SomeApplication.class, args);
    }

}
