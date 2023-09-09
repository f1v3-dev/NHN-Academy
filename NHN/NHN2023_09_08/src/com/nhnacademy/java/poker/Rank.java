package com.nhnacademy.java.poker;

public enum Rank {
    HIGH_CARD(0, "High Card"),
    PAIR(1, "One Pair"),
    TWO_PAIR(2, "Two Pair"),
    TRIPLE(3, "TRIPLE"),
    FOUR_CARD(4, "FOUR CARD");

    private final int rankNum;
    private final String name;

    Rank(int rank, String name) {
        this.rankNum = rank;
        this.name = name;
    }

    public int getRankNum() {
        return rankNum;
    }
}
