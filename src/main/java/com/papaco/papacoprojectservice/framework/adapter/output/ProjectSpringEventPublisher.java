package com.papaco.papacoprojectservice.framework.adapter.output;

import com.papaco.papacoprojectservice.application.port.output.ProjectEventPublisher;
import com.papaco.papacoprojectservice.domain.event.ProjectEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class ProjectSpringEventPublisher implements ProjectEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publish(ProjectEvent event) {
        eventPublisher.publishEvent(event);
    }
}
