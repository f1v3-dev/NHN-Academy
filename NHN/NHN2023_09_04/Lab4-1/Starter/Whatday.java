import java.util.Scanner;

public class Whatday {
    static int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    static int[] daysInLeapMonth = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    static String[] monthNames = { "January", "Feburary", "March", "April", "May", "June", "July", "August",
            "Sepetember", "October", "November", "December" };

    public static void main(String[] args) {

        try {
            System.out.print("Enter year number: ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            int yearNum = Integer.parseInt(line);

            boolean isLeapYear = (yearNum % 4 == 0) && (yearNum % 100 != 0 || yearNum % 400 == 0);

            if (isLeapYear) {
                
                System.out.println("it's Leaf Year");
            }
            else {
                System.out.println("it's Common Year");
            }
            
            int maxDayNum = isLeapYear ? 366 : 365;

            System.out.print("Enter a digit 1 between " + maxDayNum + " : ");
            line = scanner.nextLine();
            int dayNum = Integer.parseInt(line);

            if (dayNum <= 0 || dayNum > maxDayNum) {
                throw new IllegalArgumentException("Out Of Date");
            }

            int monthNum = 0;
            if (!isLeapYear) {
                for (int days : daysInMonth) {
                    if (dayNum <= days) {
                        break;
                    } else {
                        dayNum -= days;
                        monthNum++;
                    }
                }
            }
            else {
                for (int days : daysInLeapMonth) {
                    if (dayNum <= days) {
                        break;
                    } else {
                        dayNum -= days;
                        monthNum++;
                    }
                }
            }
            
            String monthName = monthNames[monthNum];

            System.out.println(monthName + " " + dayNum);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}