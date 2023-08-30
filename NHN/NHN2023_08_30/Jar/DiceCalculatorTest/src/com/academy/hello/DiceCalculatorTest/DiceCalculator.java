package com.academy.hello.DiceCalculatorTest;

public class DiceCalculator {

    public DiceCalculator() {
    }

    public static int addDice(Dice first, Dice second) {
        return first.getNum() + second.getNum();
    }

    public static String getIndexOf(String str, int index) {
        // exception 처리 해보기 (str의 lenth가 6보다 넘어가거나 index가 음수인경우)

        return str.substring(0, index + 1);
    }

    public static boolean odd(Dice dice) {
        return dice.getNum() % 2 != 0;
    }
}
