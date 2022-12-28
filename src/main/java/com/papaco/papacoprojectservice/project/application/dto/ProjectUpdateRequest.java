package com.papaco.papacoprojectservice.project.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProjectUpdateRequest {
    private String codeStoreId;
    private String codeStoreName;
    private String projectDescription;
}
