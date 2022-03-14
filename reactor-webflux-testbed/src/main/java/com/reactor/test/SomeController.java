package com.reactor.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class SomeController {

    @GetMapping
    public Mono<String> someGet() {
        return Mono.just("hello");
    }

    @PostMapping
    public Mono<String> somePost(@RequestBody Mono<String> data) {
        return data.doOnNext(d -> {
                    sleep();
                    log.info("Request {} in somePost", d);
                })
                .then(Mono.just("umm... tired today"));
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
