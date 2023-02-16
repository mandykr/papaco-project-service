package com.papaco.papacoprojectservice.project.framework.adapter.input;

import com.papaco.papacoprojectservice.project.framework.dto.TechStackEventPayload;
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
public class TechStackEventListener {
    @SqsListener(value = "sqs-techstack-change.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void saveTechStack(@NotificationMessage TechStackEventPayload payload) {
        log.info("listen from sqs-techstack-change.fifo: {}", payload);
    }
}
