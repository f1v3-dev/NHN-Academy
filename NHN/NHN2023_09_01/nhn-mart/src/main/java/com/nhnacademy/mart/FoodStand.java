package com.nhnacademy.mart;

import java.util.ArrayList;

public class FoodStand {

    private final ArrayList<Food> foods = new ArrayList<>();


    public ArrayList<Food> getFoods() {
        return foods;
    }

    // TODO add 메서드 구현
    public void add(Food food) {
        foods.add(food);
    }

    // TODO 장바구니에 담은 Food 삭제 구현
    public Food remove(String name) {
        int index = 0;
        for (Food food : foods) {
            if (food.getName().equals(name)) {
                return foods.remove(index);
            }
            index++;
        }
        throw new IllegalArgumentException("존재하지 않는 상품입니다.");
    }

}
