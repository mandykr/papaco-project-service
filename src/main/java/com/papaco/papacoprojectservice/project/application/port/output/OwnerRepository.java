package com.papaco.papacoprojectservice.project.application.port.output;

import com.papaco.papacoprojectservice.project.domain.entity.Owner;

import java.util.Optional;

public interface OwnerRepository {
    Owner save(Owner owner);

    Optional<String> findNameById(Long id);
}
