package com.nhnacademy.java.poker;

public class Card {

    private Suit pattern;
    private Rank number;

    public Card(Suit pattern, Rank number) {
        this.pattern = pattern;
        this.number = number;
    }

    public Rank getNumber() {
        return number;
    }

    public Suit getPattern() {
        return pattern;
    }

    @Override
    public String toString() {
        return "[" + pattern + "] " + number;
    }
    
}
