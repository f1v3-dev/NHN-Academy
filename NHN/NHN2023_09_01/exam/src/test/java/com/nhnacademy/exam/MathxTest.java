package com.nhnacademy.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MathxTest {

    @Test
    @DisplayName("[sphereVolume] Test")
    void sphereVolumeTest() {
        Assertions.assertEquals(2304.0 * Math.PI, Mathx.sphereVolume(12));
    }

    @Test
    @DisplayName("[sphereVolume] radius : 0")
    void radiusZeroTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Mathx.sphereVolume(0));
    }


    @Test
    @DisplayName("[sphereVolume] equals Test")
    void equalsTest() {
        double radius = 1.0;
        double result = 1.0;
        Assertions.assertTrue(Mathx.equals(radius, result));
    }


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
    @DisplayName("[Mathx] sum Test")
    void sumTest() {
        Assertions.assertEquals(1 + 2 + 3 + 4 + 5, Mathx.sum(1, 2, 3, 4, 5));
        Assertions.assertEquals(1 + 2 + 3 + 4 + 5, Mathx.sum(Range.of(1, 5)));
        Assertions.assertEquals(Mathx.sum(1, 2, 3, 4, 5), Mathx.sum(Range.of(1, 5)));
    }

    @Test
    @DisplayName("[Mathx] product Test")
    void productTest() {
        Assertions.assertEquals(1 * 2 * 3 * 4 * 5, Mathx.product(1 * 3 * 4 * 2 * 5));
    }

    @Test
    @DisplayName("[Iterator] iterator Test")
    void iteratorTest() {
        Range r = Range.of(1, 5);
        Assertions.assertEquals(1 * 2 * 3 * 4 * 5, Mathx.product(r.iterator()));
        Assertions.assertEquals(1 + 2 + 3 + 4 + 5, Mathx.sum(r.iterator()));

        double even = Mathx.sum(r.iterator().filter(Mathx.even));
        double odd = Mathx.sum(r.iterator().filter(Mathx::odd));
        double iter = Mathx.sum(r.iterator());
        Assertions.assertEquals(even + odd, iter);
    }

    @Test
    @DisplayName("[Iterator] take Test")
    void takeTest() {
        Range r = Range.of(1, 100);
        Assertions.assertEquals(55, r.iterator().take(10).reduce((x, y) -> x + y, 0));
    }

    //     범위 사이의 랜덤한 값을 가져오는 메서드이기 때문에 테스트를 하기 어렵다.
    @Test
    @DisplayName("[Iterator] forEachRemaining Test")
    void forEachRemainingTest() {
        Range r = Range.of(1, 100);
        double[] result = new double[1];
        Mathx.randomNumbers(r).take(10).forEachRemaining(x -> System.out.println(x));
    }

}