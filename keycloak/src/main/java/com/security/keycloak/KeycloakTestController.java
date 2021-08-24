package com.security.keycloak;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class KeycloakTestController {

    @GetMapping("/permit")
    public ResponseEntity<String> permitEndpoint() {
        return ResponseEntity.ok("THIS ENDPOINT IS PERMITTED");
    }

    @GetMapping("/remainder/restrict")
    public ResponseEntity<String> restrictEndpoint() {
        return ResponseEntity.ok("THIS ENDPOINT IS RESTRICTED");
    }

}
