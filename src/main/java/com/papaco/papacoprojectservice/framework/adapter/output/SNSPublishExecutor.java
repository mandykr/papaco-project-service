package com.papaco.papacoprojectservice.framework.adapter.output;

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
public class SNSPublishExecutor implements SNSPublisher {
    private final AmazonSNS amazonSNS;
    private final ObjectMapper objectMapper;

    @Override
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

    @Override
    public void execute(PublishRequest request) {
        try {
            amazonSNS.publish(request);
            log.info("publish: {}", request);
        } catch (Exception e) {
            log.error("sns publish error: {}", request, e);
        }
    }
}
