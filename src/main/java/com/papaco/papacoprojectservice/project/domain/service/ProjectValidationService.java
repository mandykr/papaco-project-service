package com.papaco.papacoprojectservice.project.domain.service;

import com.papaco.papacoprojectservice.project.domain.vo.ReviewerMatchStatus;
import org.springframework.stereotype.Service;

import static com.papaco.papacoprojectservice.project.domain.vo.ReviewerMatchStatus.ACCEPT;

@Service
public class ProjectValidationService {

    public void validateToUpdate(Long reviewCount, ReviewerMatchStatus status) {
        // FailedChangeCodeStoreException
        if (reviewCount > 0L) {
            throw new IllegalStateException(String.format("리뷰 이력이 있는 프로젝트는 리포지토리를 변경할 수 없습니다. review count: %d", reviewCount));
        }

        if (status == ACCEPT) {
            throw new IllegalStateException(String.format("리뷰어가 매칭된 프로젝트는 리포지토리를 변경할 수 없습니다. status: %s", status));
        }
    }
}
