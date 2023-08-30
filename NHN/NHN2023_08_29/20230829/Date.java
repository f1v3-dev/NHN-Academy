import java.util.Scanner;

public class Date {
    
    public static void main(String[] args) {

        /**
         * 숫자를 입력하고
         * 몇 월 며칠인지 계산
         * 30 -> 1월 30일
         */
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the Number: ");
        int num = scanner.nextInt();

        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        for (int i = 0; i < days.length; i++) {
            if (num > days[i]){
                num -= days[i];
            } else {
                System.out.println((i + 1) + "월 " + num + "일");
                break;
            }

        }
    }
}
