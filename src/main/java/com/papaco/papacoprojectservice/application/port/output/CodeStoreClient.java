package com.papaco.papacoprojectservice.application.port.output;

import com.papaco.papacoprojectservice.application.dto.CodeStoreFindResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CodeStoreClient {

    Page<CodeStoreFindResponse> fetchCodeStoresByMemberName(Pageable page, String memberName);
}
