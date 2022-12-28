package com.papaco.papacoprojectservice.project.application.port.input;

import com.papaco.papacoprojectservice.project.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.ProjectQueryResponse;
import com.papaco.papacoprojectservice.project.application.dto.ProjectResponse;
import com.papaco.papacoprojectservice.project.application.dto.ProjectUpdateRequest;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectEventPublisher;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectRepository;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectQueryServiceClient;
import com.papaco.papacoprojectservice.project.application.usecase.ProjectUseCase;
import com.papaco.papacoprojectservice.project.domain.entity.Project;
import com.papaco.papacoprojectservice.project.domain.event.EventType;
import com.papaco.papacoprojectservice.project.domain.event.ProjectEvent;
import com.papaco.papacoprojectservice.project.domain.service.CodeStoreValidationService;
import com.papaco.papacoprojectservice.project.domain.service.ProjectValidationService;
import com.papaco.papacoprojectservice.project.domain.vo.CodeStore;
import com.papaco.papacoprojectservice.project.domain.vo.ProjectDescription;
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
    private final CodeStoreValidationService codeStoreValidationService;
    private final ProjectValidationService projectValidationService;
    private final ProjectQueryServiceClient projectQueryServiceClient;

    @Override
    public ProjectResponse createProject(ProjectCreateRequest request) {
        Project project = request.toProject(UUID.randomUUID());
        codeStoreValidationService.validateToApply(project);

        Project saveProject = projectRepository.save(project);
        eventPublisher.publish(ProjectEvent.of(saveProject, EventType.CREATED));
        return ProjectResponse.of(saveProject);
    }

    @Override
    public void updateProject(UUID id, ProjectUpdateRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
        editDescription(request, project);
        changeCodeStore(request, project);
        eventPublisher.publish(ProjectEvent.of(project, EventType.UPDATED));
    }

    private void editDescription(ProjectUpdateRequest request, Project project) {
        project.editDescription(new ProjectDescription(request.getProjectDescription()));
    }

    private void changeCodeStore(ProjectUpdateRequest request, Project project) {
        CodeStore codeStore = new CodeStore(request.getCodeStoreId(), request.getCodeStoreName());
        ProjectQueryResponse queryResponse = projectQueryServiceClient.getProject(project.getId());
        projectValidationService.validateToUpdate(queryResponse.getReviewCount(), queryResponse.getReviewerMatchStatus());
        project.changeCodeStore(codeStore);
    }

    @Override
    public void deleteProject(UUID id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
        project.delete();
        eventPublisher.publish(ProjectEvent.of(project, EventType.DELETED));
    }
}
