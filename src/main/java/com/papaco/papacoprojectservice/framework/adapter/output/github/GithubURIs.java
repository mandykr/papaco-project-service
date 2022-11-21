package com.papaco.papacoprojectservice.framework.adapter.output.github;

import lombok.Getter;

@Getter
public enum GithubURIs {
    REPOSITORIES_FOR_A_USER("/users/{username}/repos"),
    SEARCH_REPOSITORIES("/search/repositories")
    ;

    private final String uri;

    GithubURIs(String uri) {
        this.uri = uri;
    }
}
