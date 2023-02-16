package com.papaco.papacoprojectservice.integration;

import com.papaco.papacoprojectservice.project.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.ProjectResponse;
import com.papaco.papacoprojectservice.project.application.port.input.ProjectService;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectEventRepository;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectRepository;
import com.papaco.papacoprojectservice.project.domain.entity.Project;
import com.papaco.papacoprojectservice.project.domain.event.EventType;
import com.papaco.papacoprojectservice.project.domain.event.ProjectEvent;
import com.papaco.papacoprojectservice.project.framework.adapter.input.ProjectEventRecordListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@RecordApplicationEvents
class ProjectEventRecorderTest extends IntegrationTest {
    @Autowired
    private ApplicationEvents events;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectEventRecordListener eventListener;

    @Autowired
    private ProjectEventRepository eventRepository;

    private ProjectCreateRequest request;

    @BeforeEach
    void setUp() {
        request = new ProjectCreateRequest(1L, "54485264", "papaco-project-service", "msa 학습 프로젝트", List.of(1L, 2L));
        projectRepository.deleteAllInBatch();
        eventRepository.deleteAllInBatch();
    }

    @DisplayName("프로젝트가 생성되면 이벤트가 저장된다")
    @Test
    void success() {
        ProjectResponse response = projectService.createProject(request);

        Project project = projectRepository.findById(response.getId()).get();
        int count = (int) events.stream(ProjectEvent.class)
                .filter(e -> e.getEventType() == EventType.CREATED)
                .count();

        assertAll(
                () -> assertThat(project).isNotNull(),
                () -> assertThat(count).isEqualTo(1),
                () -> assertThat(eventRepository.findAll()).hasSize(1)
        );
    }

    @DisplayName("이벤트 저장에 실패하면 프로젝트가 저장되지 않는다")
    @Test
    void fail() {
        ReflectionTestUtils.setField(eventListener, "projectEventRecordUseCase", null);
        AtomicReference<ProjectResponse> response = null;

        assertThatThrownBy(() -> response.set(projectService.createProject(request)))
            .isInstanceOf(NullPointerException.class);

        Optional<Project> project = projectRepository.findById(UUID.randomUUID());
        int count = (int) events.stream(ProjectEvent.class)
                .filter(e -> e.getEventType() == EventType.CREATED)
                .count();

        assertAll(
                () -> assertThat(project).isEmpty(),
                () -> assertThat(count).isZero(),
                () -> assertThat(eventRepository.findAll()).isEmpty()
        );

    }
}
