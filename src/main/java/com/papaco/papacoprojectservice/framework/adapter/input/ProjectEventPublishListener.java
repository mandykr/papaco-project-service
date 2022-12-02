package com.papaco.papacoprojectservice.framework.adapter.input;

import com.papaco.papacoprojectservice.domain.event.ProjectEvent;
import com.papaco.papacoprojectservice.framework.dto.ProjectEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class ProjectEventPublishListener {
    private final MessagePublisher messagePublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEvent(ProjectEvent event) {
        messagePublisher.publish(ProjectEventPayload.from(event));
    }
}
