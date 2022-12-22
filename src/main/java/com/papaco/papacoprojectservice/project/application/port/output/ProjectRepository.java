package com.papaco.papacoprojectservice.project.application.port.output;

import com.papaco.papacoprojectservice.project.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    boolean existsByOwnerIdAndCodeStoreId(Long ownerId, String codeStoreId);
}
