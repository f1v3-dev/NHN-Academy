package com.nhnacademy.java.poker;

public enum Suit {
    SPADE("\u2660"),
    DIAMOND("\u25C6"),
    HEART("\u2665"),
    CLOVER("\u2618");

    private final String pattern;

    Suit(String pattern) {
        this.pattern = pattern;
    }

    public String toString() {
        return this.pattern;
    }
}
