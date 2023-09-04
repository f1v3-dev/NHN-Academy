import java.util.Scanner;

public class Whatday {
    static int[] daysInMonth  = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] monthNames = {"January", "Feburary", "March", "April", "May", "June", "July", "August", "Sepetember", "October", "November", "December"};
    
    public static void main(String[] args) {
        System.out.print("Enter a digit 1 between 365: ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int dayNum = Integer.parseInt(line);

        int monthNum = 0; 

        for (int days: daysInMonth) {
            if (dayNum <= days) {
                break;
            }
            else {
                dayNum -= days;
                monthNum++;
            }
        }

        String monthName = monthNames[monthNum];

        System.out.printf("%s, %d \n", monthName, dayNum);
        scanner.close();
    }
}