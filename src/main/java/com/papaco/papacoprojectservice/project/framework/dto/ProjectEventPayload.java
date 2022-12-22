package com.papaco.papacoprojectservice.project.framework.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.papaco.papacoprojectservice.project.domain.event.ProjectEvent;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class ProjectEventPayload extends EventPayload {
    private static final String TYPE = "ProjectEventPayload";
    private String aggregateId;
    private String eventType;
    private boolean published;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime publishedAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    public ProjectEventPayload() {
        super(null, null);
    }

    @JsonCreator
    public ProjectEventPayload(
            @JsonProperty("eventId") String eventId,
            @JsonProperty("aggregateId") String aggregateId,
            @JsonProperty("eventType") String eventType,
            @JsonProperty("published") boolean published,
            @JsonProperty("publishedAt") LocalDateTime publishedAt,
            @JsonProperty("createdAt") LocalDateTime createdAt) {
        super(eventId, TYPE);
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.published = published;
        this.publishedAt = publishedAt;
        this.createdAt = createdAt;
    }

    public static ProjectEventPayload from(ProjectEvent event) {
        return new ProjectEventPayload(
                String.valueOf(event.getId()),
                String.valueOf(event.getAggregateId()),
                String.valueOf(event.getEventType()),
                event.isPublished(),
                event.getPublishedAt(),
                event.getCreatedAt()
        );
    }
}
