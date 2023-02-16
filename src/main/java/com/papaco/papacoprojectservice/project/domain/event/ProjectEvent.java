package com.papaco.papacoprojectservice.project.domain.event;

import com.papaco.papacoprojectservice.project.domain.entity.Project;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProjectEvent extends BaseEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aggregateId", columnDefinition = "varbinary(16)")
    private UUID aggregateId;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private String payload;

    public ProjectEvent(UUID aggregateId, EventType eventType, String payload) {
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
    }

    public static ProjectEvent of(Project project, EventType eventType) {
        return new ProjectEvent(project.getId(), eventType, convertPayload(project));
    }

    private static String convertPayload(Project project) {
        ProjectPayloadConverter converter = new ProjectPayloadConverter(project);
        return converter.convert();
    }
}
