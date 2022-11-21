package com.papaco.papacoprojectservice.framework.adapter.output.github;

import com.papaco.papacoprojectservice.application.dto.CodeStoreFindResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

import static com.papaco.papacoprojectservice.framework.adapter.output.github.CodeStoreGithubResponseFixture.CODE_STORES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GithubClientMockTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GithubClient githubClient;

    private GithubSearchResponse searchResponse;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(githubClient, "baseUri", "https://api.github.com");
        searchResponse = new GithubSearchResponse(12L, true, CODE_STORES);
    }

    @DisplayName("사용자 이름으로 Github 리포지토리 목록을 요청해 페이징 목록을 반환한다")
    @Test
    void fetchAllByMemberName() {
        ResponseEntity<GithubSearchResponse> response = new ResponseEntity<>(searchResponse, HttpStatus.OK);
        given(restTemplate.getForEntity(any(URI.class), any(Class.class))).willReturn(response);
        PageRequest page = PageRequest.of(0, 10, Sort.by("updated").descending());

        Page<CodeStoreFindResponse> findResponses = githubClient.fetchCodeStoresByMemberName(page, "mandykr");

        assertThat(findResponses.getTotalElements()).isEqualTo(12L);
        assertThat(findResponses.getContent()).hasSize(CODE_STORES.size());
    }

    @DisplayName("Github 리포지토리 응답 데이터가 없으면 빈 리스트를 반환한다")
    @Test
    void fetchEmptyList() {
        ResponseEntity<GithubSearchResponse> response = new ResponseEntity<>(new GithubSearchResponse(), HttpStatus.OK);
        given(restTemplate.getForEntity(any(URI.class), any(Class.class))).willReturn(response);
        PageRequest page = PageRequest.of(0, 10, Sort.by("updated").descending());

        Page<CodeStoreFindResponse> findResponses = githubClient.fetchCodeStoresByMemberName(page, "mandykr");

        assertThat(findResponses.getTotalElements()).isZero();
        assertThat(findResponses.getContent()).isEqualTo(Collections.emptyList());
    }
}

