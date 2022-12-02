package com.papaco.papacoprojectservice.framework.adapter.input;

import com.papaco.papacoprojectservice.application.usecase.ProjectEventRecordUseCase;
import com.papaco.papacoprojectservice.domain.event.ProjectEvent;
import com.papaco.papacoprojectservice.framework.dto.EventPayload;
import io.awspring.cloud.messaging.config.annotation.NotificationMessage;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProjectEventRecordListener {
    private final ProjectEventRecordUseCase projectEventRecordUseCase;

    @Transactional
    @EventListener
    public void handleEvent(ProjectEvent event) {
        projectEventRecordUseCase.record(event);
    }

    @SqsListener(value = "sqs-project-event-publish-record.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void recordEventPublish(@NotificationMessage EventPayload payload) {
        log.info("listen from sqs-project-event-publish-record.fifo: {}", payload);
        projectEventRecordUseCase.recordPublish(Long.valueOf(payload.getEventId()));
    }
}
