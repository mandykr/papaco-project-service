package com.papaco.papacoprojectservice.framework.adapter.input;

import com.papaco.papacoprojectservice.framework.dto.ProjectBroadcastMessage;

public interface MessageBroadcaster {
    void broadcast(ProjectBroadcastMessage message);
}
