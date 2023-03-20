package com.kimdoubleb.serverless.controller;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.cglib.core.internal.Function;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class EndpointController implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public EndpointController() {
    }

    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent event) {
        System.out.println(event);
        var apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
        apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.OK.value());
        return apiGatewayProxyResponseEvent;
    }
}
