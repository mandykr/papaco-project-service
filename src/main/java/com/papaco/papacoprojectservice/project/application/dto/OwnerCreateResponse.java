package com.papaco.papacoprojectservice.project.application.dto;

import com.papaco.papacoprojectservice.project.domain.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OwnerCreateResponse {
    private Long id;
    private String name;

    public static OwnerCreateResponse of(Owner owner) {
        return new OwnerCreateResponse(owner.getId(), owner.getName());
    }
}
