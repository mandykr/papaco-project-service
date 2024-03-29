package com.papaco.papacoprojectservice.project.application.port.input;

import com.papaco.papacoprojectservice.project.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.ProjectQueryResponse;
import com.papaco.papacoprojectservice.project.application.dto.ProjectUpdateRequest;
import com.papaco.papacoprojectservice.project.application.port.output.*;
import com.papaco.papacoprojectservice.project.domain.entity.Project;
import com.papaco.papacoprojectservice.project.domain.service.CodeStoreValidationService;
import com.papaco.papacoprojectservice.project.domain.service.ProjectValidationService;
import com.papaco.papacoprojectservice.project.domain.vo.CodeStore;
import com.papaco.papacoprojectservice.project.domain.vo.ProjectDescription;
import com.papaco.papacoprojectservice.project.framework.adapter.output.ProjectSpringEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static com.papaco.papacoprojectservice.project.domain.vo.MateStatus.JOINED;
import static com.papaco.papacoprojectservice.project.domain.vo.MateStatus.PROPOSED;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectServiceTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TechStackRepository techStackRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    private CodeStoreValidationService codeStoreValidationService;
    private ProjectService projectService;
    private ProjectQueryServiceClient queryServiceClient;
    private ProjectQueryResponseFixture queryResponseFixture;

    private Project project;
    private CodeStore codeStore;

    @BeforeEach
    void setUp() {
        ProjectSpringEventPublisher projectEventPublisher = new ProjectSpringEventPublisher(eventPublisher);
        codeStoreValidationService = new CodeStoreValidationService(projectRepository);
        ProjectValidationService projectValidationService = new ProjectValidationService();
        queryServiceClient = new FakeProjectQueryServiceClient();
        queryResponseFixture = new ProjectQueryResponseFixture();

        projectService = new ProjectService(
                projectRepository, techStackRepository, projectEventPublisher, codeStoreValidationService, projectValidationService, queryServiceClient);

        codeStore = new CodeStore("54465484", "papaco-member-service");
        project = new Project(UUID.randomUUID(), 1L, codeStore, new ProjectDescription("msa 학습 프로젝트"));
    }

    @DisplayName("프로젝트를 생성할 수 있다")
    @Test
    void create() {
        ProjectCreateRequest createRequest = new ProjectCreateRequest(1L, codeStore.getId(), codeStore.getName(), "msa, eda 학습 프로젝트", List.of(1L, 2L));
        assertThatCode(() -> projectService.createProject(createRequest))
                .doesNotThrowAnyException();
    }

    @DisplayName("프로젝트의 설명을 수정할 수 있다")
    @Test
    void updateDescription() {
        projectRepository.save(project);
        ProjectUpdateRequest request = new ProjectUpdateRequest(codeStore.getId(), codeStore.getName(), "msa, eda 학습 프로젝트");

        projectService.updateProject(project.getId(), request);

        Project updateProject = projectRepository.findById(project.getId()).get();
        assertThat(updateProject.getDescription()).isEqualTo("msa, eda 학습 프로젝트");
    }

    @DisplayName("프로젝트의 코드 저장소를 변경한다")
    @Test
    void updateCodeStore() {
        projectRepository.save(project);
        CodeStore newCodeStore = new CodeStore("54465485", "papaco-project-service");
        ProjectUpdateRequest request = new ProjectUpdateRequest(newCodeStore.getId(), newCodeStore.getName(), project.getDescription());

        projectService.updateProject(project.getId(), request);

        Project updateProject = projectRepository.findById(project.getId()).get();
        assertThat(updateProject.getCodeStore()).isEqualTo(newCodeStore);
    }

    @DisplayName("리뷰어 매칭된 상태에서 코드 저장소를 변경하면 예외가 발생한다")
    @Test
    void matchingReviewer() {
        projectRepository.save(project);
        CodeStore newCodeStore = new CodeStore("54465485", "papaco-project-service");
        ProjectUpdateRequest request = new ProjectUpdateRequest(newCodeStore.getId(), newCodeStore.getName(), project.getDescription());
        ProjectQueryResponse response = queryResponseFixture.createResponse(project.getId(), JOINED, 0L);
        ReflectionTestUtils.setField(queryServiceClient, "response", response);

        assertThatThrownBy(() -> projectService.updateProject(project.getId(), request))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("리뷰 이력이 있는 상태에서 코드 저장소를 변경하면 예외가 발생한다")
    @Test
    void reviewHistory() {
        projectRepository.save(project);
        CodeStore newCodeStore = new CodeStore("54465485", "papaco-project-service");
        ProjectUpdateRequest request = new ProjectUpdateRequest(newCodeStore.getId(), newCodeStore.getName(), project.getDescription());
        ProjectQueryResponse response = queryResponseFixture.createResponse(project.getId(), PROPOSED, 1L);
        ReflectionTestUtils.setField(queryServiceClient, "response", response);

        assertThatThrownBy(() -> projectService.updateProject(project.getId(), request))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("프로젝트를 종료한다")
    @Test
    void finish() {
        projectRepository.save(project);

        projectService.finishProject(project.getId());

        Project finished = projectRepository.findById(project.getId()).get();
        assertThat(finished.isFinished()).isTrue();
    }

    @DisplayName("프로젝트를 삭제한다")
    @Test
    void delete() {
        projectRepository.save(project);

        projectService.deleteProject(project.getId());

        Project deleted = projectRepository.findById(project.getId()).get();
        assertThat(deleted.isDeleted()).isTrue();
    }

    @DisplayName("삭제된 프로젝트는 수정할 수 없다")
    @Test
    void deleted() {
        projectRepository.save(project);
        project.delete();
        ProjectUpdateRequest request = new ProjectUpdateRequest(codeStore.getId(), codeStore.getName(), "msa, eda 학습 프로젝트");

        entityManager.clear();
        assertThat(project.isDeleted()).isTrue();
        assertThatThrownBy(() -> projectService.updateProject(project.getId(), request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
