package com.queue.push.queue.sender;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.queue.push.infra.config.AwsSqsProperty;
import org.springframework.cloud.aws.messaging.core.QueueMessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SomeQueueMessageSender {

    private final QueueMessageChannel messageChannel;

    public SomeQueueMessageSender(AmazonSQSAsync amazonSQSAsync,
                                  AwsSqsProperty awsSqsProperty) {
        this.messageChannel = new QueueMessageChannel(amazonSQSAsync, awsSqsProperty.someQueueUrl());
    }

    public boolean send(final String messagePayload) {
        var message = MessageBuilder.withPayload(messagePayload)
            .setHeader("sender", "app")
            .setHeaderIfAbsent("country", "KOR")
            .build();

        return messageChannel.send(message, 5000);
    }

}
