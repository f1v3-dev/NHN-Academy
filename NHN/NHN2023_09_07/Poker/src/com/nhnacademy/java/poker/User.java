package com.nhnacademy.java.poker;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Card> hand;
    
    public User() {
        hand = new ArrayList<>(5);
    }

    public void drawUserCard(Deck deck) {
    
        hand.add(deck.draw());
    }
}
