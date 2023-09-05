import java.util.Scanner;

public class Tictactoe {

    private static final int MAP_SIZE = 3;

    private static int count = 0;

    private static char[][] map = new char[MAP_SIZE][MAP_SIZE];

    private static Scanner scanner = new Scanner(System.in);

    static void printMap() {
        for (int i = 0; i < 2; i++) {
            System.out.printf(" %c |  %c  | %c \n", map[i][0], map[i][1], map[i][2]);
            System.out.println("- - - - - - -");
        }
        System.out.printf(" %c |  %c  | %c \n", map[2][0], map[2][1], map[2][2]);
        System.out.println();
    }

    static void fillMap(int x, int y, boolean isPlayer1) {

        if (map[x][y] != ' ') {
            throw new IllegalArgumentException("this position is not empty!!!");
        }

        if (isPlayer1) {
            map[x][y] = 'X';
        } else {
            map[x][y] = 'O';
        }
    }

    public static boolean isGameOver(char[][] map) {

        count++;
        if (count > 9) {
            System.out.println("Draw...");
            return true;
        }

        for (int i = 0; i < map.length; i++) {
            if ((map[i][0] == map[i][1] && map[i][1] == map[i][2]) && map[i][0] != ' ') {
                if (map[i][0] == 'X') {
                    System.out.println("[Player1] Win !!");
                    return true;
                } else {
                    System.out.println("[Player2] Win !!");
                    return true;
                }
            } else if ((map[0][i] == map[1][i] && map[1][i] == map[2][i]) && map[0][i] != ' ') {
                if (map[0][i] == 'X') {
                    System.out.println("[Player1] Win !!");
                    return true;
                } else {
                    System.out.println("[Player2] Win !!");
                    return true;
                }
            }
        }

        if ((map[0][0] == map[1][1] && map[1][1] == map[2][2] && map[0][0] != ' ')
            || (map[0][2] == map[1][1] && map[1][1] == map[2][0] && map[0][2] != ' ')){
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

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = ' ';
            }
        }

        String[] input;
        boolean isPlayer1 = true;
        while (true) {
            printMap();
            if (isGameOver(map)) {
                break;
            }

            System.out.print("[Player 1] Enter the position (x y): ");
            input = scanner.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            if ((x < 0 || 2 < x) || (y < 0 || 2 < y)) {
                throw new IllegalArgumentException();
            }
            fillMap(x, y, isPlayer1);
            printMap();
            if (isGameOver(map)) {
                break;
            }

            isPlayer1 = false;
            System.out.print("[Player 2] Enter the position (x y): ");
            input = scanner.nextLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            if ((x < 0 || 2 < x) || (y < 0 || 2 < y)) {
                throw new IllegalArgumentException();
            }
            fillMap(x, y, isPlayer1);
            isPlayer1 = true;

        }
    }
}