package com.nhnacademy.java.poker;

public enum CardNumber {

    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    Jack(11, "J"),
    Queen(12, "Q"),
    King(13, "K"),
    ACE(14, "A");
    
    private final int number;
    private final String stringNumber;

    CardNumber(int number, String stringNumber) {
        this.number = number;
        this.stringNumber = stringNumber;
    }


    public String toString() {
        return stringNumber;
    }
}
