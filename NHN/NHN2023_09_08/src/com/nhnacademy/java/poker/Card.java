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

    public int numberCompareTo(Card c) {
        return this.getNumber().compareTo(c.getNumber());
    }

    public int patternCompareTo(Card c) {
        return this.getPattern().compareTo(c.getPattern());
    }
}
