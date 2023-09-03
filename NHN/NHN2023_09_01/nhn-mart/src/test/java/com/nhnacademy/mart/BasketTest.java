package com.nhnacademy.mart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BasketTest {

    @Test
    @DisplayName("[Basket] addTest")
    void addTest() {
        Basket basket = new Basket();

        Food food = new Food("양파", 1500);
        basket.add(food);

        Assertions.assertEquals("양파", basket.getFoods().get(0).getName());
        Assertions.assertEquals(1500, basket.getFoods().get(0).getPrice());
    }

}