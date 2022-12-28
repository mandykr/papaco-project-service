package com.papaco.papacoprojectservice.project.framework.adapter.input;

import com.papaco.papacoprojectservice.project.domain.event.ProjectEvent;
import com.papaco.papacoprojectservice.project.framework.dto.ProjectEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class ProjectEventPublishListener {
    private final MessagePublisher messagePublisher;

    @Transactional
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEvent(ProjectEvent event) {
        messagePublisher.publish(ProjectEventPayload.from(event));
    }
}
