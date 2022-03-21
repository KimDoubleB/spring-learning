package com.queue.push.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws.sqs.queue")
public record AwsSqsProperty(String someQueueUrl) {
}
