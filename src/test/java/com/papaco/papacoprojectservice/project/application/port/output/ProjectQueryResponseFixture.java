package com.papaco.papacoprojectservice.project.application.port.output;

import com.papaco.papacoprojectservice.project.application.dto.ProjectQueryResponse;
import com.papaco.papacoprojectservice.project.domain.vo.MateStatus;

import java.util.UUID;

public class ProjectQueryResponseFixture {
    private long OWNER_ID = 1L;
    private String CODE_STORE_ID = "54465484";
    private String CODE_STORE_NAME = "papaco-member-service";
    private String DESCRIPTION = "msa 학습 프로젝트";

    public ProjectQueryResponse createResponse(UUID id, MateStatus status, Long reviewCount) {
        return new ProjectQueryResponse(
                id,
                OWNER_ID,
                CODE_STORE_ID,
                CODE_STORE_NAME,
                DESCRIPTION,
                status,
                reviewCount
        );
    }

    public ProjectQueryResponse createResponse(MateStatus status, Long reviewCount) {
        return new ProjectQueryResponse(
                UUID.randomUUID(),
                OWNER_ID,
                CODE_STORE_ID,
                CODE_STORE_NAME,
                DESCRIPTION,
                status,
                reviewCount
        );
    }
}
