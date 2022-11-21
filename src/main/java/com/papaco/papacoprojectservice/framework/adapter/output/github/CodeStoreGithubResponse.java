package com.papaco.papacoprojectservice.framework.adapter.output.github;

import com.papaco.papacoprojectservice.application.dto.CodeStoreFindResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CodeStoreGithubResponse {
    private String id;
    private String name;

    public CodeStoreFindResponse toCodeStoreFindResponse() {
        return new CodeStoreFindResponse(id, name);
    }
}
