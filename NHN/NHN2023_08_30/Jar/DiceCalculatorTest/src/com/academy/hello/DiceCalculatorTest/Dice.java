package com.academy.hello.DiceCalculatorTest;

public class Dice {

    private int num;

    public Dice(int num) {
        if (num <= 0 || num >= 7) {
            throw new IllegalArgumentException("Invalid Number");
        }
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
