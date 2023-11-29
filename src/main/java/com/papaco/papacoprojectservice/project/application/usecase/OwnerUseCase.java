package com.papaco.papacoprojectservice.project.application.usecase;

import com.papaco.papacoprojectservice.project.application.dto.OwnerCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.OwnerCreateResponse;

public interface OwnerUseCase {
    OwnerCreateResponse createOwner(OwnerCreateRequest request);
}
