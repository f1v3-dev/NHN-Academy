package com.nhnacademy.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FractionalTest {

    @Test
    @DisplayName("[Fractional] constructor Test")
    void constructor() {
        Fractional fractional = Fractional.of(1, 4);

        Assertions.assertEquals(1, fractional.getNumerator());
        Assertions.assertEquals(4, fractional.getDenominator());
    }

    @Test
    @DisplayName("[Fractional] 분자가 0인 경우 테스트")
    void denominatorZeroTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Fractional.of(1, 0));
    }

    @Test
    @DisplayName("[Fractional] normalize Test")
    void normalizeTest() {

        // normalize()가 실행된다
        Fractional fractional = Fractional.of(2, 4);
        Assertions.assertEquals(1, fractional.getNumerator());
        Assertions.assertEquals(2, fractional.getDenominator());
    }


    @Test
    @DisplayName("[Fractional] add Test")
    void addTest() {
        Fractional firstFrac = Fractional.of(1, 2);
        Fractional secondFrac = Fractional.of(4, 8);

        Fractional result = Fractional.of(1, 1);
        assertEquals(result, firstFrac.add(secondFrac));
    }

    @Test
    @DisplayName("[Fractional] equals Test")
    void equalsTest() {
        Fractional firstFraction = Fractional.of(5, 10);
        Fractional secondFraction = Fractional.of(1, 2);

        assertTrue(firstFraction.equals(secondFraction));
    }
}