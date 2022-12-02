package com.papaco.papacoprojectservice.application.usecase;

import com.papaco.papacoprojectservice.domain.event.ProjectEvent;

public interface ProjectEventRecordUseCase {
    ProjectEvent record(ProjectEvent event);
    void recordPublish(Long eventId);
}
