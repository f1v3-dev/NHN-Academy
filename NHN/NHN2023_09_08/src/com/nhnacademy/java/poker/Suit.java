package com.nhnacademy.java.poker;

public enum Suit {
    SPADE("\u2660", 1),
    DIAMOND("\u25C6", 2),
    HEART("\u2665", 3),
    CLOVER("\u2618", 4);

    private final String pattern;
    private final int priority;

    Suit(String pattern, int priority) {
        this.pattern = pattern;
        this.priority = priority;
    }

    public String toString() {
        return this.pattern;
    }
}
