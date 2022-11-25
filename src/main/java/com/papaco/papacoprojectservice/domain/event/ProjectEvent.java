package com.papaco.papacoprojectservice.domain.event;

import com.papaco.papacoprojectservice.domain.entity.Project;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime publishedAt;

    public ProjectEvent(UUID aggregateId, EventType eventType, LocalDateTime publishedAt) {
        super();
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.publishedAt = publishedAt;
    }

    public static ProjectEvent of(Project project, EventType eventType) {
        return new ProjectEvent(project.getId(), eventType, LocalDateTime.now());
    }
}
