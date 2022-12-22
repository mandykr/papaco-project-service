package com.papaco.papacoprojectservice.project.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Getter
@EqualsAndHashCode
@Embeddable
public class ProjectDescription {
    @Lob
    private final String description;

    public ProjectDescription() {
        this.description = null;
    }

    public ProjectDescription(String description) {
        this.description = description;
    }
}
