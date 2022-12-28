package com.papaco.papacoprojectservice.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectSteps {
    private static final String PROJECT_ENDPOINT = "/projects";

    public static ExtractableResponse<Response> 프로젝트_생성_요청(Long memberId, Map<String, String> repo, String description) {
        Map<String, String> params = new HashMap<>();
        params.put("ownerId", memberId + "");
        params.put("codeStoreId", repo.get("id"));
        params.put("codeStoreName", repo.get("name"));
        params.put("projectDescription", description);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post(PROJECT_ENDPOINT)
                .then().log().all().extract();
    }

    public static void 프로젝트_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static ExtractableResponse<Response> 프로젝트_수정_요청(ExtractableResponse<Response> response, Map<String, String> repo, String description) {
        String uri = response.header("Location");
        Map<String, String> params = new HashMap<>();
        params.put("codeStoreId", repo.get("id"));
        params.put("codeStoreName", repo.get("name"));
        params.put("projectDescription", description);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().put(uri)
                .then().log().all().extract();
    }

    public static void 프로젝트_수정됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static ExtractableResponse<Response> 프로젝트_삭제_요청(ExtractableResponse<Response> response) {
        String uri = response.header("Location");

        return RestAssured.given().log().all()
                .when().delete(uri)
                .then().log().all().extract();
    }

    public static void 프로젝트_삭제됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
