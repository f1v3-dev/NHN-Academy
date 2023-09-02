package com.nhnacademy.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RangeTest {

    @Test
    @DisplayName("[Range] constructor Test")
    void constructorTest() {
        Range range = Range.of(1, 10);

        Assertions.assertEquals(1, range.min());
        Assertions.assertEquals(10, range.max());
    }

    @Test
    @DisplayName("[Range] classInvariant Test")
    void classInvariantTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Range.of(10, 1));
    }

    @Test
    @DisplayName("[Range] range Of Test")
    void rangeOfTest() {
        assertEquals(0, Mathx.sum(Range.of(0, 0)));
        assertEquals(55, Mathx.sum(Range.of(0, 10)));
        assertEquals(0, Mathx.sum(Range.of(-2, 2)));
    }

    @Test
    @DisplayName("[Range] sum Test")
    void sumTest() {
        Assertions.assertEquals(1 + 2 + 3 + 4 + 5, Mathx.sum(1, 2, 3, 4, 5));
        Assertions.assertEquals(1 + 2 + 3 + 4 + 5, Mathx.sum(Range.of(1, 5)));
        Assertions.assertEquals(Mathx.sum(1, 2, 3, 4, 5), Mathx.sum(Range.of(1, 5)));
    }

    @Test
    @DisplayName("[Range] product Test")
    void productTest() {
        Assertions.assertEquals(1 * 2 * 3 * 4 * 5, Mathx.product(1 * 3 * 4 * 2 * 5));
    }

    @Test
    @DisplayName("[Range] iterator Test")
    void iteratorTest() {
        Range r = Range.of(1, 5);
        Assertions.assertEquals(1 * 2 * 3 * 4 * 5, Mathx.product(r.iterator()));
    }


}