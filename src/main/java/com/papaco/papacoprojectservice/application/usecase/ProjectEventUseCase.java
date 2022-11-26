package com.papaco.papacoprojectservice.application.usecase;

import com.papaco.papacoprojectservice.domain.event.ProjectEvent;

public interface ProjectEventUseCase {
    ProjectEvent recordEvent(ProjectEvent event);
}
