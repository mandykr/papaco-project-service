package com.papaco.papacoprojectservice.project.domain.entity;

import com.papaco.papacoprojectservice.project.domain.vo.CodeStore;
import com.papaco.papacoprojectservice.project.domain.vo.ProjectDescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ProjectTest {
    private CodeStore codeStore;
    private ProjectDescription description;

    @BeforeEach
    void setUp() {
        codeStore = new CodeStore("485654180", "papaco-member-service");
        description = new ProjectDescription("msa 학습 프로젝트");
    }

    @DisplayName("프로젝트는 식별자와 오너 id, 코드 저장소, 설명을 가진다")
    @Test
    void create() {
        assertThatCode(() -> new Project(UUID.randomUUID(), 1L, codeStore, description))
                .doesNotThrowAnyException();
    }

    @DisplayName("프로젝트의 코드 저장소를 변경할 수 있다")
    @Test
    void changeCodeStore() {
        Project project = new Project(UUID.randomUUID(), 1L, codeStore, description);
        CodeStore newStore = new CodeStore("585654181", "papaco-auth-service");

        project.changeCodeStore(newStore);

        assertThat(project.getCodeStore()).isEqualTo(newStore);
    }

    @DisplayName("프로젝트의 설명을 수정할 수 있다")
    @Test
    void editDescription() {
        Project project = new Project(UUID.randomUUID(), 1L, codeStore, description);
        ProjectDescription description = new ProjectDescription("프로젝트 설명 링크: ");

        project.editDescription(description);

        assertThat(project.getDescription()).isEqualTo("프로젝트 설명 링크: ");
    }
}
