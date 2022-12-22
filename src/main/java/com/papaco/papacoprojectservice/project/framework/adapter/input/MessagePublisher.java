package com.papaco.papacoprojectservice.project.framework.adapter.input;

import com.papaco.papacoprojectservice.project.framework.dto.ProjectEventPayload;

public interface MessagePublisher {
    void publish(ProjectEventPayload payload);
}
