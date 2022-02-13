package com.container.auto.handler;

import com.container.auto.config.ProfileProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
public class AutoHandler {

    private final ProfileProperty profileProperty;

    public AutoHandler(ProfileProperty profileProperty) {
        this.profileProperty = profileProperty;
    }

    public Mono<ServerResponse> getProfile(ServerRequest serverRequest) {
        log.info("Request headers: {}", serverRequest.headers());
        var currentProfile = profileProperty.profile();
        log.info("Current Profile: {}", currentProfile);
        return ServerResponse.ok().body(Mono.just(currentProfile), String.class);
    }

}
