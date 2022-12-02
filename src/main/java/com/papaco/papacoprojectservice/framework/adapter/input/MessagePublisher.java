package com.papaco.papacoprojectservice.framework.adapter.input;

import com.papaco.papacoprojectservice.framework.dto.ProjectEventPayload;

public interface MessagePublisher {
    void publish(ProjectEventPayload payload);
}
