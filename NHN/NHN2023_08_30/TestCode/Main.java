package TestCode;

/**
 * Main 클래스입니다.
 */

public class Main {

    /**
     * main 메서드 입니다.
     *
     * @param args input 인자
     */

    public static void main(String[] args) {

        int random1 = (int) (Math.random() * 6) + 1;
        int random2 = (int) (Math.random() * 6) + 1;

        Dice first = new Dice(random1);
        Dice second = new Dice(random2);

        System.out.println("주사위 합 = " + DiceCalculator.addDice(first, second));

        // random 값의 인덱스만큼 문자열 출력
        // 0 -> "a"
        // 1 -> "ab"
        // 2 -> "abc"
        String str = "abcdefg";
        int val = (int) (Math.random() * str.length());
        System.out.println(DiceCalculator.getIndexOf(str, val));

        int random4 = (int) (Math.random() * 6) + 1;
        Dice dice = new Dice(random4);

        System.out.println("주사위 눈(" + random4 + ")이 홀수 ? : " + DiceCalculator.odd(dice));



        /*
         * 출력 결과
         *
         * 주사위 합 = 9
         * ab
         * 주사위 눈(2)이 홀수 ? : false
         * */

        // 도서관 생성
        // 생성자 (size)
        // 음수가 들어올 경우 예외 발생

        try {
            new Library(-1);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        Library library = new Library(5);

        String title = "샬롯의 거미줄";

        library.add("해리포터");
        library.add(title);

        try {
            library.add(title);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        library.delete("해리포터");

        try {
            library.delete("스파이더맨");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        library.find(title);
        library.find("샬롯의 거미줄2");

        library.findAll();

        library.add("Java 7");
        library.add("Java 8");
        library.add("Java 9");
        library.add("Java 11");

        library.findAll();
        try {
            library.add("Java 19");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
