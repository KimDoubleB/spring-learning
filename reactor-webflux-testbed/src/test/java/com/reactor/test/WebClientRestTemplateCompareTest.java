package com.reactor.test;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Supplier;

class WebClientRestTemplateCompareTest {

    private static final String baseUrl = "http://localhost:8080";

    @Test
    void almostSameConsumeTimeTest() {
        // WebClient
        var wcResponse = requestOnTimeChecker(() -> {
            var webclient = WebClient.create(baseUrl)
                    .get()
                    .retrieve().bodyToMono(String.class);
            return webclient.block();
        });
        var rtResponse = requestOnTimeChecker(() -> {
            var restTemplateResponse = new RestTemplate()
                    .exchange(baseUrl, HttpMethod.GET, null, String.class);
            return restTemplateResponse.getBody();
        });

        System.out.println("webclientResponse = " + wcResponse);
        System.out.println("restTemplateResponse = " + rtResponse);
    }

    @Test
    void bigDifferenceTimeTest() {
        var payloads = List.of("some-data1", "some-data2", "some-data3");

        var wsResponse = requestOnTimeChecker(() -> Flux.fromIterable(payloads)
                .flatMap(payload -> WebClient.create(baseUrl)
                        .post()
                        .bodyValue(payload)
                        .retrieve()
                        .bodyToMono(String.class))
                .collectList()
                .block());

        var rtResponse = requestOnTimeChecker(() -> Flux.fromIterable(payloads)
                .map(payload -> new RestTemplate().exchange(baseUrl,
                        HttpMethod.POST,
                        new HttpEntity<>(payload),
                        String.class))
                .map(HttpEntity::getBody)
                .collectList()
                .block());

        System.out.println("webClientResponse = " + wsResponse);
        System.out.println("restTemplateResponse = " + rtResponse);
    }

    private <T> T requestOnTimeChecker(Supplier<T> s) {
        long start = System.nanoTime();
        var response = s.get();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
        return response;
    }

}
