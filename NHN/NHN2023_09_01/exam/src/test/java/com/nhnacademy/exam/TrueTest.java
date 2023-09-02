package com.nhnacademy.exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrueTest {

    @Test
    @DisplayName("[True] preCondition 실패 테스트")
    void preConditionFalse() {
        String[] str = {"true", "true"};
        True t = new True();

        Assertions.assertThrows(IllegalArgumentException.class, () -> t.preCondition(str));
    }

    @Test
    @DisplayName("[True] twoMoreTrue")
    void twoMoreTrue() {
        String[] str = {"true", "true", "false"};
        True t = new True();

        Assertions.assertTrue(t.twoMoreTrue(str));
    }

    @Test
    @DisplayName("[Ture] false가 출력되는 경우 테스트")
    void falseTest() {
        String[] str = {"false", "FALSE", "True"};
        True t = new True();

        Assertions.assertFalse(t.twoMoreTrue(str));
    }
}
