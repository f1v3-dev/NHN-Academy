package com.nhnacademy.mart;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodStandTest {

    @Test
    @DisplayName("[FoodStand] addTest")
    void addTest() {
        ArrayList<Food> foods = new ArrayList<>();

        Food food = new Food("딸기", 7000);
        foods.add(food);

        Assertions.assertEquals("딸기", food.getName());
        Assertions.assertEquals(7000, food.getPrice());
    }

    @Test
    @DisplayName("[FoodStand] removeTest")
    void removeTest() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("딸기", 7000));

        int index = 0;
        for (Food food : foods) {
            if (food.getName().equals("딸기")) {
                foods.remove(index);
                break;
            }
            index++;
        }

        Assertions.assertEquals(0, foods.size());
    }

}