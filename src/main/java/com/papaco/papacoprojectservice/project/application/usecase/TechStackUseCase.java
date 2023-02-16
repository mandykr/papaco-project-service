package com.papaco.papacoprojectservice.project.application.usecase;

import com.papaco.papacoprojectservice.project.application.dto.TechStackCreateRequest;

public interface TechStackUseCase {
    void createTechStack(TechStackCreateRequest request);
}
