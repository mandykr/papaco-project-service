package com.papaco.papacoprojectservice.framework.adapter.output;

import com.amazonaws.services.sns.model.PublishRequest;

public interface SNSPublisher {
    PublishRequest createRequest(String arn, String groupId, String deduplicationId, Object payload);

    void execute(PublishRequest request);
}
