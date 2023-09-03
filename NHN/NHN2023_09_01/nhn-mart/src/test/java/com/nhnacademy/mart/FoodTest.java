package com.nhnacademy.mart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodTest {

    @Test
    @DisplayName("[Food] constructorTest")
    void constructorTest() {
        Food food = new Food("배", 8000);

        Assertions.assertEquals("배", food.getName());
        Assertions.assertEquals(8000, food.getPrice());
    }
}