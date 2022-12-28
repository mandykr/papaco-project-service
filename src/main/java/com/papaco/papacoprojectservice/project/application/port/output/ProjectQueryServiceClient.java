package com.papaco.papacoprojectservice.project.application.port.output;

import com.papaco.papacoprojectservice.project.application.dto.ProjectQueryResponse;

import java.util.UUID;

public interface ProjectQueryServiceClient {
    ProjectQueryResponse getProject(UUID id);
}
