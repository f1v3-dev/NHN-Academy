package com.nhnacademy;

import java.util.Scanner;

public class Tictactoe {

    private static final int MAP_SIZE = 3;

    private static char[][] map = new char[MAP_SIZE][MAP_SIZE];

    private static Scanner scanner = new Scanner(System.in);
    private static int count = 0;

    private static void initializedMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = ' ';
            }
        }
    }

    static void printMap() {
        for (int i = 0; i < 2; i++) {
            System.out.println(" " + map[i][0] + " |  " + map[i][1] + "  | " + map[i][2]);
            System.out.println("- - - - - - -");
        }
        System.out.println(" " + map[2][0] + " |  " + map[2][1] + "  | " + map[2][2]);
        System.out.println();
    }

    private static boolean isValidMove(int x, int y) {
        return ((x >= 0 && x < MAP_SIZE) && (y >= 0 && y < MAP_SIZE) && map[x][y] == ' ');
    }

    static void fillMap(int x, int y, boolean isPlayer1) {
        if (isPlayer1) {
            map[x][y] = 'X';
        } else {
            map[x][y] = 'O';
        }
    }

    public static boolean isGameOver() {

        count++;
        if (count > MAP_SIZE * MAP_SIZE) {
            System.out.println("Draw...");
            return true;
        }

        for (int i = 0; i < map.length; i++) {
            if ((map[i][0] == map[i][1] && map[i][1] == map[i][2]) && map[i][0] != ' ') {
                // Check rows and columns
                if (map[i][0] == 'X') {
                    System.out.println("[Player1] Win !!");
                    return true;
                } else {
                    System.out.println("[Player2] Win !!");
                    return true;
                }
            } else if ((map[0][i] == map[1][i] && map[1][i] == map[2][i]) && map[0][i] != ' ') {
                // Check columns
                if (map[0][i] == 'X') {
                    System.out.println("[Player1] Win !!");
                    return true;
                } else {
                    System.out.println("[Player2] Win !!");
                    return true;
                }
            }
        }

        // Check diagonals
        if ((map[0][0] == map[1][1] && map[1][1] == map[2][2] && map[0][0] != ' ') ||
                (map[0][2] == map[1][1] && map[1][1] == map[2][0] && map[0][2] != ' ')) {
            if (map[0][0] == 'X') {
                System.out.println("[Player1] Win !!");
                return true;
            } else {
                System.out.println("[Player2] Win !!");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        initializedMap();
        boolean isPlayer1 = true;

        while (true) {
            printMap();
            if (isGameOver()) {
                break;
            }

            int currentPlayer = isPlayer1 ? 1 : 2;
            System.out.print("[Player " + currentPlayer + "] Enter the x y : ");
            String[] input = scanner.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            if (isValidMove(x, y)) {
                fillMap(x, y, isPlayer1);
                isPlayer1 = !isPlayer1;
            } else {
                System.out.println("Invalid Move !!! Try again.");
            }
        }

        scanner.close();
    }
}