package com.nhnacademy.exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FibonacciTest {

    @Test
    @DisplayName("[Fibonacci] baseCase Test")
    void baseCaseTest() {
        Assertions.assertEquals(0, Mathx.fibonacci(0));
        Assertions.assertEquals(1, Mathx.fibonacci(1));
    }

    @Test
    @DisplayName("[Fibonacci] recursionCase Test")
    void recursionTest() {
        int[][] answers = {{5, 5}, {6, 8}, {9, 34}, {14, 377}, {18, 2584}};

        for (int[] answer : answers) {
            Assertions.assertEquals(answer[1], Mathx.fibonacci(answer[0]));
        }
    }

    @Test
    @DisplayName("[Fibonacci] preCondition Test")
    void preConditionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Mathx.fibonacci(-1));
    }

    @Test
    @DisplayName("[Fibonacci] postCondition Test")
    void postConditionTest() {
        // Assertions.assertThrows(ArithmeticException.class, () -> Mathx.fibonacci(Integer.MAX_VALUE));
        Assertions.assertThrows(StackOverflowError.class, () -> Mathx.fibonacci(Integer.MAX_VALUE));
    }
}
