package com.kimdoubleb.serverless;

import java.util.Locale;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ServerlessApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerlessApplication.class, args);
	}

	@Bean
	public Function<String, String> lowercase() {
		return request -> {
			log.info("** Request: {}", request);
			return request.toLowerCase(Locale.ROOT);
		};
	}
}
