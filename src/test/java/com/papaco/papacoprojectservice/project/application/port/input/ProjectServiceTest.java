package com.papaco.papacoprojectservice.project.application.port.input;

import com.papaco.papacoprojectservice.project.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.ProjectQueryResponse;
import com.papaco.papacoprojectservice.project.application.dto.ProjectUpdateRequest;
import com.papaco.papacoprojectservice.project.application.port.output.FakeProjectQueryServiceClient;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectQueryResponseFixture;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectQueryServiceClient;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectRepository;
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
import java.util.UUID;

import static com.papaco.papacoprojectservice.project.domain.vo.MateStatus.JOIN;
import static com.papaco.papacoprojectservice.project.domain.vo.MateStatus.PROPOSAL;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectServiceTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProjectRepository projectRepository;

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
                projectRepository, projectEventPublisher, codeStoreValidationService, projectValidationService, queryServiceClient);

        codeStore = new CodeStore("54465484", "papaco-member-service");
        project = new Project(UUID.randomUUID(), 1L, codeStore, new ProjectDescription("msa ?????? ????????????"));
    }

    @DisplayName("??????????????? ????????? ??? ??????")
    @Test
    void create() {
        ProjectCreateRequest createRequest = new ProjectCreateRequest(1L, codeStore.getId(), codeStore.getName(), "msa, eda ?????? ????????????");
        assertThatCode(() -> projectService.createProject(createRequest))
                .doesNotThrowAnyException();
    }

    @DisplayName("??????????????? ????????? ????????? ??? ??????")
    @Test
    void updateDescription() {
        projectRepository.save(project);
        ProjectUpdateRequest request = new ProjectUpdateRequest(codeStore.getId(), codeStore.getName(), "msa, eda ?????? ????????????");

        projectService.updateProject(project.getId(), request);

        Project updateProject = projectRepository.findById(project.getId()).get();
        assertThat(updateProject.getDescription()).isEqualTo("msa, eda ?????? ????????????");
    }

    @DisplayName("??????????????? ?????? ???????????? ????????????")
    @Test
    void updateCodeStore() {
        projectRepository.save(project);
        CodeStore newCodeStore = new CodeStore("54465485", "papaco-project-service");
        ProjectUpdateRequest request = new ProjectUpdateRequest(newCodeStore.getId(), newCodeStore.getName(), project.getDescription());

        projectService.updateProject(project.getId(), request);

        Project updateProject = projectRepository.findById(project.getId()).get();
        assertThat(updateProject.getCodeStore()).isEqualTo(newCodeStore);
    }

    @DisplayName("????????? ????????? ???????????? ?????? ???????????? ???????????? ????????? ????????????")
    @Test
    void matchingReviewer() {
        projectRepository.save(project);
        CodeStore newCodeStore = new CodeStore("54465485", "papaco-project-service");
        ProjectUpdateRequest request = new ProjectUpdateRequest(newCodeStore.getId(), newCodeStore.getName(), project.getDescription());
        ProjectQueryResponse response = queryResponseFixture.createResponse(project.getId(), JOIN, 0L);
        ReflectionTestUtils.setField(queryServiceClient, "response", response);

        assertThatThrownBy(() -> projectService.updateProject(project.getId(), request))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("?????? ????????? ?????? ???????????? ?????? ???????????? ???????????? ????????? ????????????")
    @Test
    void reviewHistory() {
        projectRepository.save(project);
        CodeStore newCodeStore = new CodeStore("54465485", "papaco-project-service");
        ProjectUpdateRequest request = new ProjectUpdateRequest(newCodeStore.getId(), newCodeStore.getName(), project.getDescription());
        ProjectQueryResponse response = queryResponseFixture.createResponse(project.getId(), PROPOSAL, 1L);
        ReflectionTestUtils.setField(queryServiceClient, "response", response);

        assertThatThrownBy(() -> projectService.updateProject(project.getId(), request))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("??????????????? ????????????")
    @Test
    void delete() {
        projectRepository.save(project);

        projectService.deleteProject(project.getId());

        Project deleted = projectRepository.findById(project.getId()).get();
        assertThat(deleted.isDeleted()).isTrue();
    }

    @DisplayName("????????? ??????????????? ????????? ??? ??????")
    @Test
    void deleted() {
        projectRepository.save(project);
        project.delete();
        ProjectUpdateRequest request = new ProjectUpdateRequest(codeStore.getId(), codeStore.getName(), "msa, eda ?????? ????????????");

        entityManager.clear();
        assertThat(project.isDeleted()).isTrue();
        assertThatThrownBy(() -> projectService.updateProject(project.getId(), request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
