package com.papaco.papacoprojectservice.application.usecase;

import com.papaco.papacoprojectservice.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.application.dto.ProjectResponse;

public interface ProjectUseCase {
    ProjectResponse createProject(ProjectCreateRequest request);
}
