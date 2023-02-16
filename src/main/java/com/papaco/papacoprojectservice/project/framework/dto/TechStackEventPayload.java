package com.papaco.papacoprojectservice.project.framework.dto;

import com.papaco.papacoprojectservice.project.application.dto.TechStackCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TechStackEventPayload {
    private Long id;
    private String name;

    public TechStackCreateRequest to() {
        return new TechStackCreateRequest(id, name);
    }
}
