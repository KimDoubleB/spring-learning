package com.lab.etag;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sample")
@Slf4j
public class SampleController {

	@GetMapping("/no-etag")
	public String noEtagApi() {
		log.info("no-etag-api called");
		return "hello";
	}

	@GetMapping("/etag/{id}")
	public String etagApi(@PathVariable Long id) {
		log.info("etag-api called");
		return String.format("hello %s id", id);
	}
	
	@GetMapping("/etag/minute")
	public String etagMinuteApi() {
		log.info("etag-minute-api called");
		var now = LocalDateTime.now();
		return String.format("hello - current minute %d", now.getMinute());
	}

}
