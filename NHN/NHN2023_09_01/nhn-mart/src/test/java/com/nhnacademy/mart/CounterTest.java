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

        Assertions.assertEquals(5000 ,counter.pay(customerMoney, total));

    }

}