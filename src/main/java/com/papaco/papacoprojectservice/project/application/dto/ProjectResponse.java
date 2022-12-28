package com.papaco.papacoprojectservice.project.application.dto;

import com.papaco.papacoprojectservice.project.domain.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProjectResponse {
    private UUID id;
    private Long ownerId;
    private String codeStoreId;
    private String codeStoreName;
    private String projectDescription;

    public static ProjectResponse of(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getOwnerId(),
                project.getCodeStore().getId(),
                project.getCodeStore().getName(),
                project.getDescription()
        );
    }
}
