package com.papaco.papacoprojectservice.worker.event.listener;

import com.papaco.papacoprojectservice.worker.event.dto.ProjectBroadcastMessage;

public interface MessageBroadcaster {
    void broadcast(ProjectBroadcastMessage message);
}
