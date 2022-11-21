package com.papaco.papacoprojectservice.application.port.input;

import com.papaco.papacoprojectservice.application.dto.CodeStoreFindResponse;
import com.papaco.papacoprojectservice.application.port.output.CodeStoreClient;
import com.papaco.papacoprojectservice.application.port.output.OwnerRepository;
import com.papaco.papacoprojectservice.application.usecase.CodeStoreUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional
@Service
public class CodeStoreService implements CodeStoreUseCase {
    private final OwnerRepository ownerRepository;
    private final CodeStoreClient codeStoreClient;

    @Override
    public Page<CodeStoreFindResponse> findCodeStores(Pageable page, Long memberId) {
        String memberName = ownerRepository.findNameById(memberId).orElseThrow(EntityNotFoundException::new);
        return codeStoreClient.fetchCodeStoresByMemberName(page, memberName);
    }
}
