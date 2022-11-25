package com.papaco.papacoprojectservice.application.port.output;

import com.papaco.papacoprojectservice.domain.event.ProjectEvent;

public interface ProjectEventPublisher {
    void publish(ProjectEvent event);
}
