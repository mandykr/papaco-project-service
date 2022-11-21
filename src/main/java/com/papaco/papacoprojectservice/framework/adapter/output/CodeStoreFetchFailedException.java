package com.papaco.papacoprojectservice.framework.adapter.output;

import org.springframework.http.HttpStatus;

public class CodeStoreFetchFailedException extends RuntimeException {
    public CodeStoreFetchFailedException(HttpStatus statusCode) {
        super(statusCode.getReasonPhrase());
    }
}
