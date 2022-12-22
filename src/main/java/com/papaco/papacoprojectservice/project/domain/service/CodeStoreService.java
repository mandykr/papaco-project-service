package com.papaco.papacoprojectservice.project.domain.service;

import com.papaco.papacoprojectservice.project.application.port.output.ProjectRepository;
import com.papaco.papacoprojectservice.project.domain.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CodeStoreService {
    private final ProjectRepository projectRepository;

    public void validate(Project project) {
        boolean exists = projectRepository.existsByOwnerIdAndCodeStoreId(project.getOwnerId(), project.getCodeStore().getId());

        if (exists) {
            throw new IllegalStateException();
        }
    }
}
