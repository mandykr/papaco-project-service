package com.papaco.papacoprojectservice.framework.adapter.output;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class HttpClientRequestInterceptor implements ClientHttpRequestInterceptor {
    private final String headerName;
    private final String headerValue;

    public HttpClientRequestInterceptor(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        request.getHeaders().set(headerName, headerValue);
        return execution.execute(request, body);
    }
}
