package com.nhnacademy.mart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BuyListTest {

    @Test
    @DisplayName("[BuyList] addTest")
    void addTest() {
        BuyList buyList = new BuyList();

        buyList.add(new BuyList.Item("양파", 3));

        Assertions.assertEquals("양파", buyList.getItems().get(0).getName());
        Assertions.assertEquals(3, buyList.getItems().get(0).getAmount());

    }

    @Test
    @DisplayName("[BuyList - Item] itemConstructorTest")
    void itemConstructorTest(){

        BuyList.Item item = new BuyList.Item("사과", 3);

        Assertions.assertEquals("사과", item.getName());
        Assertions.assertEquals(3, item.getAmount());
    }

    @Test
    @DisplayName("[BuyList - Item] itemConstructorTest (amount : 0)")
    void inputBuyListAmountTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BuyList.Item("양파", 0));
    }

}