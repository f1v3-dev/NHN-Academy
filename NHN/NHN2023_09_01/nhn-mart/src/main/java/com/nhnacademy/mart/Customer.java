package com.nhnacademy.mart;

import java.util.ArrayList;

public class Customer {

    // 고객 구매 목록
    private final BuyList buyList;

    // 고객 장바구니
    private Basket basket;

    private int customerMoney;

    public Customer(BuyList buyList) {
        this.buyList = buyList;
        this.customerMoney = 20_000;
    }


    public int getCustomerMoney() {
        return customerMoney;
    }

    public BuyList getBuyList() {
        return buyList;
    }

    public Basket getBasket() {
        return basket;
    }

    // 장바구니 챙기기
    public void bring(Basket basket) {
        this.basket = basket;
    }
    // TODO pickFoods 메서드 구현
    public void pickFoods(FoodStand foodStand) {
        ArrayList<BuyList.Item> items = buyList.getItems();
        for (BuyList.Item item : items) {
            for (int i = 0; i < item.getAmount(); i++) {
                basket.add(foodStand.remove(item.getName()));
            }
        }
    }

    // TODO payTox 메서드 구현
    public void payTox(Counter counter) {

        ArrayList<Food> foods = basket.getFoods();

        int total = 0;
        for (Food food : foods) {
            total += food.getPrice();
        }

        int change = counter.pay(getCustomerMoney(), total);
        if (change < 0) {
            throw new IllegalArgumentException("고객님이 가진 돈을 초과하는 상품을 구매할 수 없습니다.");
        }

        System.out.println("총 가격은 " + total + "원 입니다.");
        System.out.println("고객님 결제 후 잔액 : " + change);
    }
}
