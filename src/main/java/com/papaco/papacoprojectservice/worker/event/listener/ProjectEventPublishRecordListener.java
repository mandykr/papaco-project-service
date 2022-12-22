package com.papaco.papacoprojectservice.worker.event.listener;

import com.papaco.papacoprojectservice.project.application.usecase.ProjectEventRecordUseCase;
import com.papaco.papacoprojectservice.project.framework.dto.EventPayload;
import io.awspring.cloud.messaging.config.annotation.NotificationMessage;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProjectEventPublishRecordListener {
    private final ProjectEventRecordUseCase projectEventRecordUseCase;

    @SqsListener(value = "sqs-project-event-publish-record.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void recordEventPublish(@NotificationMessage EventPayload payload) {
        log.info("listen from sqs-project-event-publish-record.fifo: {}", payload);
        projectEventRecordUseCase.recordPublish(Long.valueOf(payload.getEventId()));
    }
}
