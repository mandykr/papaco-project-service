package com.papaco.papacoprojectservice.framework.adapter.output.github;

import com.papaco.papacoprojectservice.framework.adapter.output.CodeStoreFetchFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class GithubClientTest {
    @Autowired
    private GithubClient githubClient;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(githubClient, "baseUri", "https://api.github.com/invalid");
    }

    @DisplayName("Github api 요청에 실패하면 예외를 반환한다")
    @Test
    void requestFailedException() {
        PageRequest page = PageRequest.of(0, 10, Sort.by("updated").descending());

        assertThatThrownBy(() -> githubClient.fetchCodeStoresByMemberName(page, "mandykr"))
                .isInstanceOf(CodeStoreFetchFailedException.class);
    }
}

