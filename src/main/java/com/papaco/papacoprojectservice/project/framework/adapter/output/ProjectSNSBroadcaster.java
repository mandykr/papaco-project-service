package com.papaco.papacoprojectservice.project.framework.adapter.output;

import com.amazonaws.services.sns.model.PublishRequest;
import com.papaco.papacoprojectservice.project.framework.adapter.config.AWSConfigConstants;
import com.papaco.papacoprojectservice.project.framework.adapter.input.MessageBroadcaster;
import com.papaco.papacoprojectservice.project.framework.dto.ProjectBroadcastMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectSNSBroadcaster implements MessageBroadcaster {
    private final AWSConfigConstants constants;
    private final SNSPublisher snsPublisher;

    @Override
    public void broadcast(ProjectBroadcastMessage message) {
        String arn = constants.getSns().getChangeProjectBroadcast();
        String groupId = message.getAggregateId();
        String duplicationId = message.getEventId();

        PublishRequest request = snsPublisher.createRequest(arn, groupId, duplicationId, message);
        snsPublisher.publish(request);
    }
}
