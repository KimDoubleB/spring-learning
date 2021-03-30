package com.practice.webclient;

import com.practice.webclient.dto.UserPostResponse;
import com.practice.webclient.service.JsonPlaceWebClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebclientApplication implements CommandLineRunner {
    private final JsonPlaceWebClient jsonPlaceWebClient;

    public WebclientApplication(JsonPlaceWebClient jsonPlaceWebClient) {
        this.jsonPlaceWebClient = jsonPlaceWebClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebclientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // 예제 이기에 block 사용 -> 사용 X (비동기/논블로킹의 이점 못가져감)
        var result = jsonPlaceWebClient.request("/1", UserPostResponse.class).block();
        System.out.println(result);
    }
}
