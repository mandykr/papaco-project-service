package com.papaco.papacoprojectservice.application.port.output;

import com.papaco.papacoprojectservice.domain.event.ProjectEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectEventRepository extends JpaRepository<ProjectEvent, Long> {
}
