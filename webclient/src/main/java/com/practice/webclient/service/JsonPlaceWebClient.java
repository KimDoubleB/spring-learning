package com.practice.webclient.service;

import com.practice.webclient.config.WebClientProperty;
import com.practice.webclient.dto.JsonPlaceResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class JsonPlaceWebClient {
    private final WebClient webClient;

    public JsonPlaceWebClient(WebClientProperty webClientProperty) {
        webClient = WebClient.builder()
                .baseUrl(webClientProperty.getUri())
                .defaultHeaders(header -> header.setBasicAuth(webClientProperty.getToken()))
                .build();
    }

    public <T extends JsonPlaceResponse> Mono<T> request(String path, Class<T> targetClass) {
        return webClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(targetClass);
    }
}
