package com.nhnacademy.mart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CounterTest {

    @Test
    @DisplayName("[Counter] payTest")
    void payTest() {
        Counter counter = new Counter();
        int customerMoney = 10000;
        int total = 5000;

        Assertions.assertEquals(5000, counter.pay(customerMoney, total));

    }

    @Test
    @DisplayName("[Counter] 가진 돈을 초과하는 경우 테스트")
    void overpayTest() {
        Counter counter = new Counter();
        int customerMoney = 10_000;
        int total = 20_000;

        assertThrows(IllegalArgumentException.class, () -> counter.pay(customerMoney, total));
    }

}