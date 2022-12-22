package com.papaco.papacoprojectservice.project.framework.adapter.output;

import com.amazonaws.services.sns.model.PublishRequest;
import com.papaco.papacoprojectservice.project.framework.adapter.config.AWSConfigConstants;
import com.papaco.papacoprojectservice.project.framework.adapter.input.MessagePublisher;
import com.papaco.papacoprojectservice.project.framework.dto.ProjectEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectSNSPublisher implements MessagePublisher {
    private final AWSConfigConstants constants;
    private final SNSPublisher snsPublisher;

    @Override
    public void publish(ProjectEventPayload payload) {
        String arn = constants.getSns().getChangeProject();
        String groupId = payload.getAggregateId();
        String duplicationId = payload.getEventId();

        PublishRequest request = snsPublisher.createRequest(arn, groupId, duplicationId, payload);
        snsPublisher.publish(request);
    }
}
