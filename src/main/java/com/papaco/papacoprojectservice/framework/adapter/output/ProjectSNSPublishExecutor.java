package com.papaco.papacoprojectservice.framework.adapter.output;

import com.amazonaws.services.sns.model.PublishRequest;
import com.papaco.papacoprojectservice.framework.adapter.config.AWSConfigConstants;
import com.papaco.papacoprojectservice.framework.adapter.input.MessagePublisher;
import com.papaco.papacoprojectservice.framework.dto.ProjectEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectSNSPublishExecutor implements MessagePublisher {
    private final AWSConfigConstants constants;
    private final SNSPublisher snsPublisher;

    @Override
    public void publish(ProjectEventPayload payload) {
        String arn = constants.getSns().getChangeProject();
        String groupId = payload.getAggregateId();
        String duplicationId = payload.getEventId();

        PublishRequest request = snsPublisher.createRequest(arn, groupId, duplicationId, payload);
        snsPublisher.execute(request);
    }
}
