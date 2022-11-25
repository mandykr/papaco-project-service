package com.papaco.papacoprojectservice.domain.event;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEvent {
    @CreatedDate
    private LocalDateTime createdAt;
    private boolean published;

    protected BaseEvent() {
        this.published = false;
    }
}
