package com.container.auto.router;

import com.container.auto.handler.AutoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AutoRouter {

    @Bean
    RouterFunction<ServerResponse> autoRouterFunction(AutoHandler autoHandler) {
        return RouterFunctions.route()
                .GET("/profile", autoHandler::getProfile)
                .build();
    }

}
