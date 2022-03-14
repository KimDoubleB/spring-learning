package com.reactor.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class ErrorTest {

    Logger log = LoggerFactory.getLogger(ErrorTest.class);

    @Test
    void doOnErrorTest1() {
        Flux.range(1, 6)
                .map(index -> {
                    if (index % 2 == 0) {
                        throw new RuntimeException("Error ! index: " + index);
                    }
                    return index;
                })
                .doOnError(throwable -> log.error("doOnError: {}", throwable.getMessage()))
                .log()
                .subscribe();
    }


    @Test
    void doOnErrorTest2() {
        Flux.range(1, 6)
                .map(index -> {
                    if (index % 2 == 0) {
                        throw new IllegalArgumentException("Error ! index: " + index);
                    }
                    return index;
                })
                .doOnError(NullPointerException.class,
                        throwable -> log.error("doOnError: {}", throwable.getMessage()))
                .log()
                .subscribe();
    }

    @Test
    void doOnErrorTest3() {
        Flux.range(1, 6)
                .map(index -> {
                    if (index % 2 == 0) {
                        throw new RuntimeException("Error ! index: " + index);
                    }
                    return index;
                })
                .doOnError(throwable -> throwable.getMessage().contains("index"),
                        throwable -> log.error("doOnError: {}", throwable.getMessage()))
                .log()
                .subscribe();
    }

    @Test
    void onErrorResumeTest() {
        Flux.range(1, 6)
                .map(index -> {
                    if (index % 2 == 0) {
                        throw new RuntimeException("Error ! index: " + index);
                    }
                    return index;
                })
                .onErrorResume(throwable -> Flux.range(10, 15))
                .log()
                .subscribe();
    }

    @Test
    void onErrorResumeTest2() {
        Flux.range(1, 6)
                .map(index -> {
                    if (index % 2 == 0) {
                        throw new RuntimeException("Error ! index: " + index);
                    }
                    return index;
                })
                .onErrorResume(NullPointerException.class,
                        throwable -> Flux.range(10, 15))
                .log()
                .subscribe();
    }



    @Test
    void onErrorResumeTest3() {
        Flux.range(1, 6)
                .map(index -> {
                    if (index % 2 == 0) {
                        throw new RuntimeException("Error ! index: " + index);
                    }
                    return index;
                })
                .onErrorResume(throwable -> throwable.getMessage().contains("index"),
                        throwable -> Flux.range(10, 15))
                .log()
                .subscribe();
    }


    @Test
    void onErrorReturnTest() {
        Flux.range(1, 6)
                .map(index -> {
                    if (index % 2 == 0) {
                        throw new RuntimeException("Error ! index: " + index);
                    }
                    return index;
                })
                .onErrorReturn(100)
                .log()
                .subscribe();
    }



}
