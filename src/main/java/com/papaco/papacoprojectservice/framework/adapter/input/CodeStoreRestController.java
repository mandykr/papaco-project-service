package com.papaco.papacoprojectservice.framework.adapter.input;

import com.papaco.papacoprojectservice.application.dto.CodeStoreFindResponse;
import com.papaco.papacoprojectservice.application.usecase.CodeStoreUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/code-stores")
@RestController
public class CodeStoreRestController {
    private final CodeStoreUseCase codeStoreUseCase;

    @GetMapping("/{memberId}")
    public ResponseEntity<Page<CodeStoreFindResponse>> findCodeStores(
            @PageableDefault(size = 10, page = 0, sort = "updated") Pageable page,
            @PathVariable Long memberId) {
        Page<CodeStoreFindResponse> codeStores = codeStoreUseCase.findCodeStores(page, memberId);
        return ResponseEntity.ok().body(codeStores);
    }
}
