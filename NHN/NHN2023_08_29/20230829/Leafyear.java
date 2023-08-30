import java.util.Scanner;

public class Leafyear {

    public static void main(String[] args) {

        // 윤년 : leaf year : 4로 나누어 떨어지는 년도, 100으로 나누어 떨어지나, 400으로 나누어 떨어지면 윤년
        // 평년 : common year : 4로 나누어 떨어지더라도 100으로 떨어지면 평년

        Scanner scanner = new Scanner(System.in);

        System.out.print("년도를 입력하세요 : ");
        int year = scanner.nextInt();


        boolean isLeaf = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
        if (isLeaf) {
            System.out.println(year + "년은 윤년입니다.");
        } else {
            System.out.println(year + "년은 평년입니다.");
        }

        /**
         * leafYear 판별 후
         * 숫자를 하나 더 받아 몇월 며칠인지 판독
         * 1. if문을 사용해서 -> 어떤 문제가 발생하는지 생각해보기
         * 2. 반복문으로 다음과 같이 해결하기
         */

        System.out.print("날짜를 입력하세요 : ");
        int num = scanner.nextInt();
        // String month = "January";

        if (num > 365) {
            throw new IllegalArgumentException("365일을 초과할 수 없습니다.");
        }

        // // 문제점 1. 31일을 입력했을 때 Feburary 0일 나온다.
        // // 문제점 2. 위의 if (num > 31)로 변경했을 경우 31을 입력하면
        // // 아래의 if문에 걸려 31 - 28 = 3이 나와 March 3가 나온다.
        
        // if (num >= 31) {
        //     month = "Feburary";
        //     num -= 31;
        // }


        // if (num >= 28) {
        //     month = "March";
        //     num -= 28;
        // }

        // if (num >= 31) {
        //     month = "April";
        //     num -= 31;
        // }
        // if (num > 30) {
        //     month = "May";
        //     num -= 30;
        // }
        // if (num > 31) {
        //     month = "June";
        //     num -= 31;
        // }
        // if (num > 30) {
        //     month = "July";
        //     num -= 30;
        // }
        // if (num > 31) {
        //     month = "Agust";
        //     num -= 31;
        // }

        // if (num > 31) {
        //     month = "September";
        //     num -= 31;
        // }
        
        // if (num > 30) {
        //     month = "October";
        //     num -= 30;
        // }
        
        // if (num > 31) {
        //     month = "November";
        //     num -= 31;
        // }
        
        // if (num > 30) {
        //     month = "December";
        //     num -= 30;
        // }

        // System.out.println(month + " " + num);
        

        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for (int i = 0; i < days.length; i++) {
            if (num > days[i]) {
                num -= days[i];
            } else {
                System.out.println((i + 1) + "월 " + num + "일");
                break;
            }
        }

        scanner.close();
    }
}