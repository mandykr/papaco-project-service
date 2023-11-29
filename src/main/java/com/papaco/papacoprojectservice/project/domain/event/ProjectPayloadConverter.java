package com.papaco.papacoprojectservice.project.domain.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.papaco.papacoprojectservice.project.domain.entity.Project;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectPayloadConverter {
    private final Project project;
    private ProjectPayload projectPayload;

    public ProjectPayloadConverter(Project project) {
        this.project = project;
    }

    public String convert() {
        projectPayload = new ProjectPayload(
                project.getId(),
                project.getOwnerId(),
                project.getCodeStore().getId(),
                project.getCodeStore().getName(),
                project.getDescription(),
                project.getProjectTechStacks(),
                project.isFinished(),
                project.isDeleted()
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String convert;
        try {
            convert = objectMapper.writeValueAsString(projectPayload);
        } catch (JsonProcessingException e) {
            log.error("project payload convert exception :" + projectPayload.getId());
            throw new ProjectPayloadConvertException(e);
        }

        return convert;
    }
}
