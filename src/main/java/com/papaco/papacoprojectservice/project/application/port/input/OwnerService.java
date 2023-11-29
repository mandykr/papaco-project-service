package com.papaco.papacoprojectservice.project.application.port.input;

import com.papaco.papacoprojectservice.project.application.dto.OwnerCreateRequest;
import com.papaco.papacoprojectservice.project.application.dto.OwnerCreateResponse;
import com.papaco.papacoprojectservice.project.application.port.output.OwnerRepository;
import com.papaco.papacoprojectservice.project.application.usecase.OwnerUseCase;
import com.papaco.papacoprojectservice.project.domain.entity.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class OwnerService implements OwnerUseCase {
    private final OwnerRepository ownerRepository;

    @Override
    public OwnerCreateResponse createOwner(OwnerCreateRequest request) {
        Owner saveOwner = ownerRepository.save(request.toMember());
        return OwnerCreateResponse.of(saveOwner);
    }
}
