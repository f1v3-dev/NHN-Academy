package com.nhnacademy.java.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Table {

    private static List<Player> playerList;

    private static Scanner sc = new Scanner(System.in);

    public static Player findWinner() {

        // TODO: 각 플레이어들의 패를 정렬한다.
        sortPlayersHands();

        // TODO: 각 플레이어들의 패에서 RANK에 어디에 속하는지 찾는 메서드를 호출
        for (Player player : playerList) {
            player.findRank();
        }

        // TODO: 가장 높은 RANK를 가진 플레이어가 승리
        Player winnder = playerList.get(0);
        for (int i = 1; i < playerList.size(); i++) {
            int result = winnder.getRank().compareTo(playerList.get(i).getRank());
            if (result == 0) {
                // TODO: RANK가 같을 경우에는 숫자를 비교해서 승자를 찾는다.
                int compareNumber = winnder.compareNumber(playerList.get(i));
                if (compareNumber < 0) {
                    winnder = playerList.get(i);
                } else if (compareNumber == 0) {
                    // TODO: RANK와 숫자가 같을 경우에는 무늬를 비교한다.
                    int comparePattern = winnder.comparePattern(playerList.get(i));
                    if (comparePattern < 0) {
                        winnder = playerList.get(i);
                    }
                }
            } else if (result < 0) {
                winnder = playerList.get(i);
            }
        }

        return winnder;
    }

    // 플레이어들의 카드를 숫자 오름차순으로 정렬
    public static void sortPlayersHands() {
        for (Player player : playerList) {
            player.sortHand();
        }
    }

    public static void setPlayer() {
        System.out.println("[Enter the number of players] ");
        System.out.print("(Between 2 and 10): ");

        int playerNumber = sc.nextInt();
        if (playerNumber < 2 || playerNumber > 10) {
            throw new IllegalArgumentException("There must be at least two players.");
        }

        playerList = new ArrayList<>(playerNumber);

        for (int i = 0; i < playerNumber; i++) {
            addPlayer();
        }

    }

    public static void addPlayer() {
        System.out.print("Enter the name of player: ");
        String name = sc.next();
        playerList.add(new Player(name));
    }

    public static void startGame() {
        System.out.println("----------------------------------");
        System.out.println("       Start Poker Game !!        ");
        System.out.println("----------------------------------\n");

        Deck deck = new Deck();
        setPlayer();

        for (int i = 0; i < 5; i++) {
            for (Player player : playerList) {
                player.setHand(deck.draw());
            }
        }

        System.out.println("\nPlayers are given cards.");

        Player winner = findWinner();
        for (Player player : playerList) {
            System.out.println(player + " [" + player.getRank() + "]");
        }
        System.out.println("Winner is \"" + winner.getUserName() + "\"!!! [" + winner.getRank() + "]");

    }

    public static void main(String[] args) {
        startGame();
    }
}
