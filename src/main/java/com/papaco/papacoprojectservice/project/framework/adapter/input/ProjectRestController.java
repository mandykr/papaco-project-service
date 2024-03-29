package com.papaco.papacoprojectservice.project.framework.adapter.input;

import com.papaco.papacoprojectservice.project.application.dto.ProjectCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.ProjectResponse;
import com.papaco.papacoprojectservice.project.application.dto.ProjectUpdateRequest;
import com.papaco.papacoprojectservice.project.application.usecase.ProjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProject(
            @PathVariable UUID id, @RequestBody ProjectUpdateRequest request) {
        projectUseCase.updateProject(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/finish")
    public ResponseEntity<Void> finishProject(@PathVariable UUID id) {
        projectUseCase.finishProject(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        projectUseCase.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
