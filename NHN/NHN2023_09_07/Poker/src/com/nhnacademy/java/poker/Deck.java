package com.nhnacademy.java.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> deck = new ArrayList<>();

    public Deck() {
        init();
        shuffle();
        printDeck();
    }

    private void init() {

        Rank[] ranks = Rank.values();
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

    public List<Card> draw() {

        if (deck.isEmpty()) {
            throw new IllegalArgumentException("Deck is Empty!!!");
        }

        List<Card> list = new ArrayList<>();

        for (int i = 0; i < 5 ; i++){
            list.add(deck.remove(i));
        }
        return list;
    }
}
