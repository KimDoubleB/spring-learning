package com.queue.push.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class MessageReceiver {

    @SqsListener(value = "${cloud.aws.sqs.queue.some-queue-name}")
    public void receiveSomeQueue(@Headers Map<String, String> header,
                                 @Payload String message) {
        log.info("header {}", header);
        log.info("message {}", message);
    }

}
