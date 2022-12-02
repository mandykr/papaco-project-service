package com.papaco.papacoprojectservice.framework.adapter.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.messaging.support.NotificationMessageArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class SQSConfig {
    private final AWSConfigConstants constants;

    @Primary
    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync){
        return new QueueMessagingTemplate(amazonSQSAsync());
    }

    private AmazonSQSAsync amazonSQSAsync() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(constants.getAccessKey(), constants.getSecretKey());
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(constants.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean
    public QueueMessageHandlerFactory queueMessageHandlerFactory(MessageConverter messageConverter) {
        var factory = new QueueMessageHandlerFactory();
        factory.setArgumentResolvers(List.of(new NotificationMessageArgumentResolver(messageConverter)));
        return factory;
    }

    @Bean
    protected MessageConverter messageConverter() {
        var converter = new MappingJackson2MessageConverter();
        converter.setSerializedPayloadClass(String.class);
        converter.setStrictContentTypeMatch(false);
        return converter;
    }
}
