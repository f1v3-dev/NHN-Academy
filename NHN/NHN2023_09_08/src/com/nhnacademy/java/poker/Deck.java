package com.nhnacademy.java.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck = new ArrayList<>();

    public Deck() {
        init();
        shuffle();
//        printDeck();
    }

    private void init() {

        CardNumber[] ranks = CardNumber.values();
        Suit[] suits = Suit.values();

        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                deck.add(new Card(suits[i], ranks[j]));
            }
        }
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

    private void printDeck() {
        for (Card card : deck) {
            System.out.println(card);
        }
    }

    public Card draw() {

        if (deck.isEmpty()) {
            throw new IllegalArgumentException("Deck is Empty!!!");
        }

        return deck.remove(0);
    }
}
