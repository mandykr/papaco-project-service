package com.papaco.papacoprojectservice.application.port.input;

import com.papaco.papacoprojectservice.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.application.dto.ProjectResponse;
import com.papaco.papacoprojectservice.application.port.output.ProjectEventPublisher;
import com.papaco.papacoprojectservice.application.port.output.ProjectRepository;
import com.papaco.papacoprojectservice.application.usecase.ProjectUseCase;
import com.papaco.papacoprojectservice.domain.entity.Project;
import com.papaco.papacoprojectservice.domain.event.EventType;
import com.papaco.papacoprojectservice.domain.event.ProjectEvent;
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

    @Override
    public ProjectResponse createProject(ProjectCreateRequest request) {
        Project project = request.toProject(UUID.randomUUID());
        Project saveProject = projectRepository.save(project);
        eventPublisher.publish(ProjectEvent.of(saveProject, EventType.CREATED));
        return ProjectResponse.of(saveProject);
    }
}
