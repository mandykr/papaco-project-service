package com.papaco.papacoprojectservice.application.dto;

import com.papaco.papacoprojectservice.domain.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProjectResponse {
    private UUID id;
    private String ownerId;
    private String codeStoreId;
    private String codeStoreName;
    private String projectDescription;

    public static ProjectResponse of(Project project) {
        return new ProjectResponse(
                project.getId(),
                String.valueOf(project.getOwnerId()),
                project.getCodeStore().getId(),
                project.getCodeStore().getName(),
                project.getDescription()
        );
    }
}
