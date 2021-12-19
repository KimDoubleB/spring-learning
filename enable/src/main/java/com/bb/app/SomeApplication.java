package com.bb.app;

import com.bb.app.importConfig.EnableJsonCommunication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJsonCommunication
public class SomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SomeApplication.class, args);
	}

}
