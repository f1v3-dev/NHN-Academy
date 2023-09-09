package com.nhnacademy.java.poker;

public enum Rank {

    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    Jack(11),
    Queen(12),
    King(13);
    
    private final int number;

    Rank(int number) {
        this.number = number;
    }


    public String toString() {
        return String.valueOf(number);
    }
}
