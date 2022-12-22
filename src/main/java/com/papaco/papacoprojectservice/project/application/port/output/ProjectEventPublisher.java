package com.papaco.papacoprojectservice.project.application.port.output;

import com.papaco.papacoprojectservice.project.domain.event.ProjectEvent;

public interface ProjectEventPublisher {
    void publish(ProjectEvent event);
}
