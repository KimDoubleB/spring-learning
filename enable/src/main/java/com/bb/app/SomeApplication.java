package com.bb.app;

import com.bb.app.importselector.EnableCommunicationUsingSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.bb.common.Payload.Type;

@SpringBootApplication
@EnableCommunicationUsingSelector(type = Type.PROTOBUF)
public class SomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SomeApplication.class, args);
    }

}
