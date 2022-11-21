package com.papaco.papacoprojectservice.application.usecase;

import com.papaco.papacoprojectservice.application.dto.CodeStoreFindResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CodeStoreUseCase {
    Page<CodeStoreFindResponse> findCodeStores(Pageable page, Long memberId);
}
