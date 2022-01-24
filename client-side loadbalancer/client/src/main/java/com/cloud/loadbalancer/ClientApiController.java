package com.cloud.loadbalancer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ClientApiController {

    private final WebClient.Builder loadBalancedClientBuilder;

    @GetMapping("/server")
    public Mono<String> callServer() {
        log.info("Client to Server - call `api/v1/hello` for client-side load balancing");
        return loadBalancedClientBuilder.build()
                .get()
                .uri("http://doubleb-server/api/v1/hello")
                .retrieve()
                .bodyToMono(String.class);
    }


}
