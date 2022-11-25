package com.papaco.papacoprojectservice.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    /**
     * Feature: 프로젝트를 관리한다.
     *
     *   Scenario: 프로젝트를 관리
     *     When 프로젝트 생성 요청
     *     Then 프로젝트 생성됨
     *     When 회원 수정 요청
     *     Then 회원 수정됨
     */
    @DisplayName("프로젝트를 관리한다")
    @Test
    void manage() {
        ExtractableResponse<Response> createResponse = 프로젝트_생성_요청(사용자, 리포지토리, "msa 학습 프로젝트");
        프로젝트_생성됨(createResponse);
    }
}
