package com.papaco.papacoprojectservice.project.application.port.output;

import com.papaco.papacoprojectservice.project.domain.entity.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechStackRepository extends JpaRepository<TechStack, Long> {
}
