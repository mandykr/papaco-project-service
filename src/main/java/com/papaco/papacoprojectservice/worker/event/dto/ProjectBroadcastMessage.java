package com.papaco.papacoprojectservice.worker.event.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.papaco.papacoprojectservice.project.framework.dto.ProjectEventPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProjectBroadcastMessage {
    private String eventId;
    private String aggregateId;
    private String eventType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    public static ProjectBroadcastMessage from(ProjectEventPayload payload) {
        return new ProjectBroadcastMessage(
                payload.getEventId(),
                payload.getAggregateId(),
                payload.getEventType(),
                payload.getCreatedAt()
        );
    }
}
