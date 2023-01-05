package com.papaco.papacoprojectservice.project.application.port.output;

import com.papaco.papacoprojectservice.project.application.dto.ProjectQueryResponse;

import java.util.UUID;

import static com.papaco.papacoprojectservice.project.domain.vo.MateStatus.WAIT;

public class FakeProjectQueryServiceClient implements ProjectQueryServiceClient {
    private ProjectQueryResponse response;

    public FakeProjectQueryServiceClient() {
        ProjectQueryResponseFixture fixture = new ProjectQueryResponseFixture();
        response = fixture.createResponse(WAIT, 0L);
    }

    @Override
    public ProjectQueryResponse getProject(UUID id) {
        return new ProjectQueryResponse(
                id,
                response.getOwnerId(),
                response.getCodeStoreId(),
                response.getCodeStoreName(),
                response.getProjectDescription(),
                response.getMateStatus(),
                response.getReviewCount()
        );
    }
}
