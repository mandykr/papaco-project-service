package com.papaco.papacoprojectservice.project.application.usecase;

import com.papaco.papacoprojectservice.project.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.ProjectResponse;
import com.papaco.papacoprojectservice.project.application.dto.ProjectUpdateRequest;

import java.util.UUID;

public interface ProjectUseCase {
    ProjectResponse createProject(ProjectCreateRequest request);

    void updateProject(UUID id, ProjectUpdateRequest request);

    void finishProject(UUID id);

    void deleteProject(UUID id);
}
