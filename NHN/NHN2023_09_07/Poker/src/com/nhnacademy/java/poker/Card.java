package com.nhnacademy.java.poker;

public class Card {

    private Suit pattern;
    private CardNumber number;

    public Card(Suit pattern, CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public CardNumber getNumber() {
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
