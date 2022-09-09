package com.example.beanpostprocessor;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BeanPostProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeanPostProcessorApplication.class, args);
	}

}
