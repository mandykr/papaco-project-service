package com.papaco.papacoprojectservice.project.framework.adapter.input;

import com.papaco.papacoprojectservice.project.framework.dto.ProjectBroadcastMessage;

public interface MessageBroadcaster {
    void broadcast(ProjectBroadcastMessage message);
}
