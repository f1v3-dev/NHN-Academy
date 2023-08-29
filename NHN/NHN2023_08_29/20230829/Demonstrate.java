import java.util.Scanner;

public class Demonstrate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a digit: ");
        int f = scanner.nextInt();
        float c = (f - 32) * 5.0f / 9.0f;

        System.out.println("입력하신 " + f + "°F는 " + c + "°C입니다.");

        scanner.close();

    }
}