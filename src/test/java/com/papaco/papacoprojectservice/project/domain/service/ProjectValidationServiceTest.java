package com.papaco.papacoprojectservice.project.domain.service;

import com.papaco.papacoprojectservice.project.domain.vo.MateStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.papaco.papacoprojectservice.project.domain.vo.MateStatus.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.EnumSource.Mode.INCLUDE;

class ProjectValidationServiceTest {
    private ProjectValidationService projectValidationService;

    @BeforeEach
    void setUp() {
        projectValidationService = new ProjectValidationService();
    }

    @DisplayName("리뷰 이력이 없으면 예외가 발생하지 않는다")
    @Test
    void noReviewCount() {
        assertThatCode(() -> projectValidationService.validateToUpdate(0L, WAITING))
                .doesNotThrowAnyException();
    }

    @DisplayName("리뷰 이력이 있으면 예외가 발생한다")
    @Test
    void reviewCount() {
        assertThatThrownBy(() -> projectValidationService.validateToUpdate(3L, WAITING))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("리뷰어 매칭 상태가 아니면 예외가 발생하지 않는다")
    @ParameterizedTest
    @EnumSource(mode = INCLUDE, names = {"NONE", "PROPOSAL", "FINISH"})
    void notMatch(MateStatus status) {
        assertThatCode(() -> projectValidationService.validateToUpdate(0L, status))
                .doesNotThrowAnyException();
    }

    @DisplayName("리뷰어 매칭 상태면 예외가 발생한다")
    @Test
    void match() {
        assertThatThrownBy(() -> projectValidationService.validateToUpdate(0L, JOINED))
                .isInstanceOf(IllegalStateException.class);
    }
}
