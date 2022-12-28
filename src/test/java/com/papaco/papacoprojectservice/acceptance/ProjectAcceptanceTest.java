package com.papaco.papacoprojectservice.acceptance;

import com.papaco.papacoprojectservice.project.application.port.output.FakeProjectQueryServiceClient;
import com.papaco.papacoprojectservice.project.application.port.output.ProjectQueryServiceClient;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

import static com.papaco.papacoprojectservice.acceptance.ProjectSteps.*;

class ProjectAcceptanceTest extends AcceptanceTest {
    private Long 사용자;
    private Map<String, String> 리포지토리;

    /**
     *   Background
     *     Given 리포지토리 조회됨
     */
    @BeforeEach
    public void setUp() {
        super.setUp();
        사용자 = 1L;
        리포지토리 = new HashMap<>();
        리포지토리.put("id", "54465484");
        리포지토리.put("name", "papaco-member-service");
    }

    @TestConfiguration
    public static class Config {
        @Qualifier("FakeProjectQueryServiceClient")
        @Bean
        public ProjectQueryServiceClient FakeProjectQueryServiceClient() {
            return new FakeProjectQueryServiceClient();
        }
    }

    /**
     * Feature: 프로젝트를 관리한다.
     *
     *   Scenario: 프로젝트를 관리
     *     When 프로젝트 생성 요청
     *     Then 프로젝트 생성됨
     *     When 프로젝트 수정 요청
     *     Then 프로젝트 수정됨
     *     When 프로젝트 삭제 요청
     *     Then 프로젝트 삭제됨
     */
    @DisplayName("프로젝트를 관리한다")
    @Test
    void manage() {
        ExtractableResponse<Response> createResponse = 프로젝트_생성_요청(사용자, 리포지토리, "msa 학습 프로젝트");
        프로젝트_생성됨(createResponse);

        ExtractableResponse<Response> updateResponse = 프로젝트_수정_요청(createResponse, 리포지토리, "msa, eda 학습 프로젝트");
        프로젝트_수정됨(updateResponse);

        ExtractableResponse<Response> deleteResponse = 프로젝트_삭제_요청(createResponse);
        프로젝트_삭제됨(deleteResponse);
    }
}
