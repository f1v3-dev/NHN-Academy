package com.nhnacademy.mart;

import java.util.ArrayList;

/**
 * FoodStand Class.
 */
public class FoodStand {

    private final ArrayList<Food> foods = new ArrayList<>();


    public ArrayList<Food> getFoods() {
        return foods;
    }

    /**
     * 상품 진열대에 제품을 추가한다.
     */
    public void add(Food food) {
        foods.add(food);
    }

    /**
     * 상품 진열대에서 제품을 뺀다.
     */
    public Food remove(String name) {
        int index = 0;
        for (Food food : foods) {
            if (food.getName().equals(name)) {
                return foods.remove(index);
            }
            index++;
        }
        throw new IllegalArgumentException("재고가 부족합니다.");
    }

}
