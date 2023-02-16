package com.papaco.papacoprojectservice.project.application.dto;

import com.papaco.papacoprojectservice.project.domain.entity.TechStack;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TechStackCreateRequest {
    private Long id;
    private String name;

    public TechStack toTechStack() {
        return new TechStack(id, name);
    }
}
