package com.oauth0.demo.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/login", produces = "application/moit.api.v1+json")
public class LoginController {

	@GetMapping
	public ResponseEntity<String> login(@AuthenticationPrincipal OidcUser principal) {
		log.info("principal: {}", principal);
		if (principal != null) {
			return ResponseEntity.ok(principal.toString());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/oauth2/authorization/auth0"));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}

}
