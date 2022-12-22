package com.papaco.papacoprojectservice.project.framework.adapter.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AWSSNS {
    @Value("${cloud.aws.arn.sns-change-project.fifo}")
    private String changeProject;

    @Value("${cloud.aws.arn.sns-change-project-broadcast.fifo}")
    private String changeProjectBroadcast;
}
