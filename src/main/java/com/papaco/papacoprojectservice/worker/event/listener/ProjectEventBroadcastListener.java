package com.papaco.papacoprojectservice.worker.event.listener;

import com.papaco.papacoprojectservice.worker.event.dto.ProjectBroadcastMessage;
import com.papaco.papacoprojectservice.project.framework.dto.ProjectEventPayload;
import io.awspring.cloud.messaging.config.annotation.NotificationMessage;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class ProjectEventBroadcastListener {
    private final MessageBroadcaster messageBroadcaster;

    @SqsListener(value = "sqs-change-project-broadcast.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void handleBroadcast(@NotificationMessage ProjectEventPayload payload) {
        log.info("listen from sqs-change-project-broadcast.fifo: {}", payload);
        messageBroadcaster.broadcast(ProjectBroadcastMessage.from(payload));
    }
}
