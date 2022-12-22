package com.papaco.papacoprojectservice.project.application.usecase;

import com.papaco.papacoprojectservice.project.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.ProjectResponse;

public interface ProjectUseCase {
    ProjectResponse createProject(ProjectCreateRequest request);
}
