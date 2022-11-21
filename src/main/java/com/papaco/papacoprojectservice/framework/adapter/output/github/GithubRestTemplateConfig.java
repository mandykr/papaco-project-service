package com.papaco.papacoprojectservice.framework.adapter.output.github;

import com.papaco.papacoprojectservice.framework.adapter.output.HttpClientRequestInterceptor;
import com.papaco.papacoprojectservice.framework.adapter.output.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GithubRestTemplateConfig {
    @Value("${api.client.github.key-header}")
    private String keyHeader;

    @Value("${api.client.github.key-value}")
    private String keyValue;

    @Bean(name = "githubRestTemplate")
    public RestTemplate githubRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(60000);
        clientHttpRequestFactory.setReadTimeout(60000);

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }

        interceptors.add(new HttpClientRequestInterceptor(keyHeader, keyValue));
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }
}
