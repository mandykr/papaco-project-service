package com.papaco.papacoprojectservice.project.application.port.input;

import com.papaco.papacoprojectservice.project.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.ProjectResponse;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectEventPublisher;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectRepository;
import com.papaco.papacoprojectservice.project.application.usecase.ProjectUseCase;
import com.papaco.papacoprojectservice.project.domain.entity.Project;
import com.papaco.papacoprojectservice.project.domain.event.EventType;
import com.papaco.papacoprojectservice.project.domain.event.ProjectEvent;
import com.papaco.papacoprojectservice.project.domain.service.CodeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class ProjectService implements ProjectUseCase {
    private final ProjectRepository projectRepository;
    private final ProjectEventPublisher eventPublisher;
    private final CodeStoreService codeStoreService;

    @Override
    public ProjectResponse createProject(ProjectCreateRequest request) {
        Project project = request.toProject(UUID.randomUUID());
        codeStoreService.validate(project);

        Project saveProject = projectRepository.save(project);
        eventPublisher.publish(ProjectEvent.of(saveProject, EventType.CREATED));
        return ProjectResponse.of(saveProject);
    }
}
