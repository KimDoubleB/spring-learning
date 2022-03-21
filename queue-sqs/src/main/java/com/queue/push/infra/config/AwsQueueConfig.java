/**
 *
 */
package com.queue.push.infra.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AwsCredentialProperty.class, AwsSqsProperty.class})
public class AwsQueueConfig {

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider(AwsCredentialProperty awsCredentialProperty) {
        return new AWSStaticCredentialsProvider(
            new BasicAWSCredentials(awsCredentialProperty.accessKey(), awsCredentialProperty.secretKey()));
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSQSAsync);
        factory.setMaxNumberOfMessages(10);
        factory.setWaitTimeOut(20);
        return factory;
    }

}
