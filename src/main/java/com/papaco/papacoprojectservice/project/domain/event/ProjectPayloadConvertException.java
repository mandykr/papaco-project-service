package com.papaco.papacoprojectservice.project.domain.event;

public class ProjectPayloadConvertException extends RuntimeException {
    public ProjectPayloadConvertException(Throwable throwable) {
        super(throwable);
    }
}
