package com.papaco.papacoprojectservice.project.application.dto;

import com.papaco.papacoprojectservice.project.domain.vo.MateStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProjectQueryResponse {
    private UUID id;
    private Long ownerId;
    private String codeStoreId;
    private String codeStoreName;
    private String projectDescription;
    private MateStatus mateStatus;
    private Long reviewCount;
}
