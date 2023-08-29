import java.util.Scanner;

public class Demonstrate {

    public static boolean calcYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                }
                return false;
            }
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {

        // 윤년 : leaf year : 4로 나누어 떨어지는 년도, 100으로 나누어 떨어지나, 400으로 나누어 떨어지면 윤년
        // 평년 : common year : 4로 나누어 떨어지더라도 100으로 떨어지면 평년

        Scanner scanner = new Scanner(System.in);

        System.out.print("년도를 입력하세요 : ");
        int year = scanner.nextInt();

        // if (year % 4 == 0) {
        //     if (year % 100 == 0) {
        //         if (year % 400 == 0) {
        //             System.out.println(year + "년은 윤년입니다.");
        //             return;
        //         }
        //         System.out.println(year + "년은 평년입니다.");
        //         return;
        //     }
        //     System.out.println(year + "년은 윤년입니다.");
        // }

        // else {
        //     System.out.println(year + "년은 평년입니다.");
        // }

        boolean isLeaf = calcYear(year);
        if (isLeaf) {
            System.out.println(year + "년은 윤년입니다.");
        } else {
            System.out.println(year + "년은 평년입니다.");
        }

    }
}