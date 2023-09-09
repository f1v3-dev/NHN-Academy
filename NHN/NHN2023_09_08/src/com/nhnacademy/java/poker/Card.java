package com.nhnacademy.java.poker;

public class Card {

    private Suit suit;
    private CardNumber number;

    public Card(Suit suit, CardNumber number) {
        this.suit = suit;
        this.number = number;
    }

    public CardNumber getNumber() {
        return number;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "[" + suit + "] " + number;
    }

    public int numberCompareTo(Card c) {
        return this.getNumber().compareTo(c.getNumber());
    }

    public int patternCompareTo(Card c) {
        return this.getSuit().compareTo(c.getSuit());
    }
}
