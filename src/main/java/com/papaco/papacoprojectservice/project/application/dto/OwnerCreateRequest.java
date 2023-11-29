package com.papaco.papacoprojectservice.project.application.dto;

import com.papaco.papacoprojectservice.project.domain.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerCreateRequest {
    private Long id;
    private String name;

    public Owner toMember() {
        return new Owner(id, name);
    }
}
