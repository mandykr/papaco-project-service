package com.papaco.papacoprojectservice.application.port.output;

import com.papaco.papacoprojectservice.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
