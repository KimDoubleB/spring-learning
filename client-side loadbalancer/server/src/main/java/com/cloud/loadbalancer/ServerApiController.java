package com.cloud.loadbalancer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ServerApiController {

    private final Random rand = new Random();
    private final List<String> greetings;

    @Value("${server.port}")
    private int serverPort;

    public ServerApiController() {
        this.greetings = List.of("Hello ~! ", "Greeting !!!", "Welcome~~~ ");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> greeting() {
        var index = rand.nextInt(greetings.size());
        var greeting = greetings.get(index);

        log.info("Access `/api/v1/hello` - return {} by port {}", greeting, serverPort);
        return ResponseEntity.ok(greeting);
    }

}
