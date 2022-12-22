package com.papaco.papacoprojectservice.project.application.usecase;

import com.papaco.papacoprojectservice.project.domain.event.ProjectEvent;

public interface ProjectEventRecordUseCase {
    ProjectEvent record(ProjectEvent event);
    void recordPublish(Long eventId);
}
