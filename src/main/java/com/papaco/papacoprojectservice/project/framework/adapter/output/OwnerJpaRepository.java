package com.papaco.papacoprojectservice.project.framework.adapter.output;

import com.papaco.papacoprojectservice.project.application.port.output.OwnerRepository;
import com.papaco.papacoprojectservice.project.domain.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OwnerJpaRepository extends OwnerRepository, JpaRepository<Owner, Long> {
    @Query("select o.name from Owner o where o.id = :id")
    Optional<String> findNameById(Long id);
}
