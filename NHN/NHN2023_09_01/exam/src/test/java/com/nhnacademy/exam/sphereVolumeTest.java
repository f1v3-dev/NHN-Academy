package com.nhnacademy.exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class sphereVolumeTest {

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
}
