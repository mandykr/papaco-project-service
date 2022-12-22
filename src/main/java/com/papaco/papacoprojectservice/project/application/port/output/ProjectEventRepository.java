package com.papaco.papacoprojectservice.project.application.port.output;

import com.papaco.papacoprojectservice.project.domain.event.ProjectEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectEventRepository extends JpaRepository<ProjectEvent, Long> {
}
