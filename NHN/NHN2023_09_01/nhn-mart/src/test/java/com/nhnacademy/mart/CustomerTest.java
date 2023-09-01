package com.nhnacademy.mart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    @DisplayName("[Customer] constructorTest")
    void constructorTest() {

        BuyList buyList = new BuyList();
        Customer customer = new Customer(buyList);

        Assertions.assertEquals(20_000, customer.getCustomerMoney());
        Assertions.assertEquals(buyList, customer.getBuyList());
    }

    @Test
    @DisplayName("[Customer] bringTest")
    void bringTest() {
        Customer customer = new Customer(new BuyList());
        Basket basket = new Basket();
        customer.bring(basket);

        Assertions.assertEquals(basket, customer.getBasket());
    }

    @Test
    @DisplayName("[Customer] pickFoodsTest")
    void pickFoodsTest() {
        Basket basket = new Basket();

        FoodStand foodStand = new FoodStand();
        foodStand.add(new Food("사과", 1000));
        foodStand.add(new Food("사과", 1000));

        BuyList buyList = new BuyList();
        buyList.add(new BuyList.Item("사과", 2));

        ArrayList<BuyList.Item> items = buyList.getItems();
        for (BuyList.Item item : items) {
            for (int i = 0; i < item.getAmount(); i++) {
                basket.add(foodStand.remove(item.getName()));
            }
        }

        Assertions.assertEquals(2, basket.getFoods().size());

    }

    @Test
    @DisplayName("[Customer] payToxTest")
    void payToxTest(){

        Food food = new Food("사과", 1000);
        Customer customer = new Customer(new BuyList());

        int total = food.getPrice();
        int change = new Counter().pay(customer.getCustomerMoney(), total);

        Assertions.assertEquals(1000, total);
        Assertions.assertEquals(19000, change);
    }

}