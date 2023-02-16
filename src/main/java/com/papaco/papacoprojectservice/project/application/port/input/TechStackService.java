package com.papaco.papacoprojectservice.project.application.port.input;

import com.papaco.papacoprojectservice.project.application.dto.TechStackCreateRequest;
import com.papaco.papacoprojectservice.project.application.port.output.TechStackRepository;
import com.papaco.papacoprojectservice.project.application.usecase.TechStackUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TechStackService implements TechStackUseCase {
    private final TechStackRepository techStackRepository;

    @Override
    public void createTechStack(TechStackCreateRequest request) {
        techStackRepository.save(request.toTechStack());
    }
}
