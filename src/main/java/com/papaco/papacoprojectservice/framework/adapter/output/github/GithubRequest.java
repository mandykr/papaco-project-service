package com.papaco.papacoprojectservice.framework.adapter.output.github;

import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Getter
public class GithubRequest {
    private final String baseUri;
    private final String path;
    private final UriComponentsBuilder builder;
    private URI uri;

    public GithubRequest(String baseUri, GithubURIs uri) {
        this.baseUri = baseUri;
        this.path = uri.getUri();
        this.builder = createUriComponentsBuilder();
    }

    private UriComponentsBuilder createUriComponentsBuilder() {
        return UriComponentsBuilder
                .fromHttpUrl(baseUri)
                .path(path);
    }

    public void addQueryParam(String key, String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(key, value);
        builder.queryParams(params);
    }

    public void addQueryParam(String key, String qualifier, String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(key, qualifier + ":" + value);
        builder.queryParams(params);
    }

    public void addPageParams(Pageable page) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("per_page", page.getPageSize() + "");
        params.add("page", page.getPageNumber() + "");
        builder.queryParams(params);
    }

    public void addSortParam(String sort, String order) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sort", sort);
        params.add("order", order);
        builder.queryParams(params);
    }

    public GithubRequest build() {
        UriComponents build = builder.build();
        this.uri = build.toUri();
        return this;
    }
}
