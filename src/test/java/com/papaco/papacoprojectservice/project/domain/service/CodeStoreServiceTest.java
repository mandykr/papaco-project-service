package com.papaco.papacoprojectservice.project.domain.service;

import com.papaco.papacoprojectservice.project.application.port.output.ProjectRepository;
import com.papaco.papacoprojectservice.project.domain.entity.Project;
import com.papaco.papacoprojectservice.project.domain.vo.CodeStore;
import com.papaco.papacoprojectservice.project.domain.vo.ProjectDescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CodeStoreServiceTest {
    @Autowired
    private ProjectRepository projectRepository;
    private CodeStoreService codeStoreService;

    @BeforeEach
    void setUp() {
        codeStoreService = new CodeStoreService(projectRepository);
    }

    @DisplayName("기존에 등록되어 있는 코드 저장소로 프로젝트를 생성하면 예외가 발생한다")
    @Test
    void exists() {
        CodeStore codeStore = new CodeStore("54465484", "papaco-member-service");
        Project project = new Project(UUID.randomUUID(), 1L, codeStore, new ProjectDescription("msa 학습 프로젝트"));
        projectRepository.save(project);

        Project newProject = new Project(UUID.randomUUID(), 1L, codeStore, new ProjectDescription("msa 학습 프로젝트"));

        assertThatThrownBy(() -> codeStoreService.validate(newProject))
                .isInstanceOf(IllegalStateException.class);
    }
}
