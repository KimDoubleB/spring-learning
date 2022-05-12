package com.example.propertyvalidation;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class PropertyValidationApplication implements CommandLineRunner {

	private final ServerProperties serverProperties;

	public static void main(String[] args) {
		SpringApplication.run(PropertyValidationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(serverProperties);
	}
}
