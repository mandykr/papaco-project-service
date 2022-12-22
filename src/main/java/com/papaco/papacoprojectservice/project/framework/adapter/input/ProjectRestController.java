package com.papaco.papacoprojectservice.project.framework.adapter.input;

import com.papaco.papacoprojectservice.project.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.ProjectResponse;
import com.papaco.papacoprojectservice.project.application.usecase.ProjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class ProjectRestController {
    private final ProjectUseCase projectUseCase;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectCreateRequest request) {
        ProjectResponse project = projectUseCase.createProject(request);
        return ResponseEntity.created(URI.create("/projects/" + project.getId())).body(project);
    }
}
