package com.papaco.papacoprojectservice.framework.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProjectEventPayload.class, name = "ProjectEventPayload")}
)
@Getter
@ToString
@AllArgsConstructor
public abstract class EventPayload {
    private final String eventId;
    private final String type;

    protected EventPayload() {
        this.eventId = null;
        this.type = null;
    }
}
