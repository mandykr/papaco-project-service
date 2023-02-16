package com.papaco.papacoprojectservice.project.domain.event;

import com.papaco.papacoprojectservice.project.domain.entity.TechStack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProjectPayload {
    private UUID id;
    private Long ownerId;
    private String codeStoreId;
    private String codeStoreName;
    private String description;
    private List<TechStack> techStacks;
    private boolean finished;
    private boolean deleted;
}
