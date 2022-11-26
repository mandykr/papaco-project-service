package com.papaco.papacoprojectservice.application.port.input;

import com.papaco.papacoprojectservice.application.port.output.ProjectEventRepository;
import com.papaco.papacoprojectservice.application.usecase.ProjectEventUseCase;
import com.papaco.papacoprojectservice.domain.event.ProjectEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ProjectEventRecorder implements ProjectEventUseCase {
    private final ProjectEventRepository eventRepository;

    @Override
    public ProjectEvent recordEvent(ProjectEvent event) {
        return eventRepository.save(event);
    }
}
