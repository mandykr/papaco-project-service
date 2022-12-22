package com.papaco.papacoprojectservice.project.framework.adapter.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AWSConfigConstants {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    private final AWSSNS sns;

    @Autowired
    public AWSConfigConstants(AWSSNS sns) {
        this.sns = sns;
    }
}
