package com.papaco.papacoprojectservice.project.framework.adapter.input;

import com.papaco.papacoprojectservice.project.application.usecase.ProjectEventRecordUseCase;
import com.papaco.papacoprojectservice.project.domain.event.ProjectEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProjectEventRecordListener {
    private final ProjectEventRecordUseCase projectEventRecordUseCase;

    @Transactional
    @EventListener
    public void handleEvent(ProjectEvent event) {
        projectEventRecordUseCase.record(event);
    }
}
