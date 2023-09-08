package com.nhnacademy.java.poker;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String userName;

    private List<Card> hand;

    private Rank rank;

    private List<Card> pairCard;

    public Player(String name) {
        this.userName = name;
        hand = new ArrayList<>(5);
    }

    public void setHand(Card card) {
        hand.add(card);
    }

    public void sortHand() {
        hand.sort((c1, c2) -> c1.getNumber().compareTo(c2.getNumber()));
    }

    public void findRank() {

        // TODO : hand에 있는 카드들을 비교해서 어디 RANK에 속하는지 찾는다.
        // TODO : 찾은 RANK를 setRank() 메서드를 호출해서 저장한다.
        // TODO : RANK를 찾을 때는 hand에 있는 카드들의 숫자만을 비교한다.

        int pairCount = pairCheck();

        if (pairCount == 0) {
            setRank(Rank.HIGH_CARD);
        } else if (pairCount == 1) {
            setRank(Rank.PAIR);
        } else if (pairCount == 2) {
            setRank(Rank.TWO_PAIR);
        } else if (pairCount >= 3) {
            setRank(Rank.TRIPLE);
        }
    }

    private int pairCheck() {
        pairCard = new ArrayList<>();
        int pairCount = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getNumber() == hand.get(j).getNumber()) {
                    pairCard.add(hand.get(j));
                    pairCount++;
                }
            }
        }
        return pairCount;
    }

    private void setRank(Rank rank) {
        this.rank = rank;
    }

    public Rank getRank() {
        return this.rank;
    }

    public String getUserName() {
        return userName;
    }

    public List<Card> getPairCard() {
        return pairCard;
    }

    public int compareRank(Player player) {
        return this.getRank().compareTo(player.getRank());
    }

    public int compareNumber(Player player) {

        // pair가 아닌 경우
        if (pairCard.isEmpty()) {
            return this.hand.get(hand.size() - 1).getNumber().compareTo(player.hand.get(hand.size() - 1).getNumber());
        }

        return this.pairCard.get(pairCard.size() - 1).numberCompareTo(player.pairCard.get(pairCard.size() - 1));
    }

    public int comparePattern(Player player) {

        if (pairCard.isEmpty()) {
            return this.hand.get(hand.size() - 1).getPattern().compareTo(player.hand.get(hand.size() - 1).getPattern());
        }
        return this.pairCard.get(pairCard.size() - 1).patternCompareTo(player.pairCard.get(pairCard.size() - 1));

    }

    @Override
    public String toString() {
        String message = userName + " : ";
        for (Card card : hand) {
            message += card.toString();
            message += " ";
        }
        return message;
    }

}
