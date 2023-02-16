package com.papaco.papacoprojectservice.project.application.dto;

import com.papaco.papacoprojectservice.project.domain.entity.Project;
import com.papaco.papacoprojectservice.project.domain.entity.TechStack;
import com.papaco.papacoprojectservice.project.domain.vo.CodeStore;
import com.papaco.papacoprojectservice.project.domain.vo.ProjectDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateRequest {
    private Long ownerId;
    private String codeStoreId;
    private String codeStoreName;
    private String projectDescription;
    private List<Long> techStackIds;

    public Project toProject(UUID id) {
        CodeStore codeStore = new CodeStore(codeStoreId, codeStoreName);
        ProjectDescription description = new ProjectDescription(projectDescription);
        return new Project(id, ownerId, codeStore, description);
    }
}
