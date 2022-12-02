package com.papaco.papacoprojectservice.application.port.input;

import com.papaco.papacoprojectservice.application.port.output.ProjectEventRepository;
import com.papaco.papacoprojectservice.application.usecase.ProjectEventRecordUseCase;
import com.papaco.papacoprojectservice.domain.event.ProjectEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional
@Service
public class ProjectEventRecorder implements ProjectEventRecordUseCase {
    private final ProjectEventRepository eventRepository;

    @Override
    public ProjectEvent record(ProjectEvent event) {
        return eventRepository.save(event);
    }

    @Override
    public void recordPublish(Long eventId) {
        ProjectEvent event = eventRepository.findById(eventId)
                .orElseThrow(EntityNotFoundException::new);
        event.publish();
    }
}
