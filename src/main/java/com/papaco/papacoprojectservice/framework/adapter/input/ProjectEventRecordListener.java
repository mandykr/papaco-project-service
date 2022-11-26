package com.papaco.papacoprojectservice.framework.adapter.input;

import com.papaco.papacoprojectservice.application.usecase.ProjectEventUseCase;
import com.papaco.papacoprojectservice.domain.event.ProjectEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ProjectEventRecordListener {
    private final ProjectEventUseCase projectEventUseCase;

    @Transactional
    @EventListener
    public void handleEvent(ProjectEvent event) {
        projectEventUseCase.recordEvent(event);
    }
}
