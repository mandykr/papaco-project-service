package com.papaco.papacoprojectservice.project.framework.adapter.output;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SNSPublisher {
    private final AmazonSNS amazonSNS;
    private final ObjectMapper objectMapper;

    public PublishRequest createRequest(String arn, String groupId, String deduplicationId, Object payload) {
        String convert = null;
        try {
            convert = objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            log.error("convert error: {}", payload, e);
        }

        return new PublishRequest()
                .withTopicArn(arn)
                .withMessageGroupId(groupId)
                .withMessageDeduplicationId(deduplicationId)
                .withMessage(convert);
    }

    public void publish(PublishRequest request) {
        try {
            amazonSNS.publish(request);
        } catch (Exception e) {
            log.error("sns publish error: {}", request, e);
        }
        log.info("publish: {}", request);
    }
}
