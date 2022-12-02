package com.papaco.papacoprojectservice.framework.adapter.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@RequiredArgsConstructor
@Configuration
public class SNSConfig {
    private final AWSConfigConstants constants;

    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
        return new NotificationMessagingTemplate(amazonSNS);
    }

    @Bean
    @Primary
    public AmazonSNS amazonSNS() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(constants.getAccessKey(), constants.getSecretKey());
        return AmazonSNSAsyncClientBuilder
                .standard()
                .withRegion(constants.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
