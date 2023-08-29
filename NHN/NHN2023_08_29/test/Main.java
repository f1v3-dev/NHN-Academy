package test;
public class Main {


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
        Library library = new Library(5);

        // 책 추가
        library.add("해리포터");
        library.add("샬롯의 거미줄");
        library.add("샬롯의 거미줄");

        library.delete("스파이더맨");

        library.find("샬롯의 거미줄");
        library.find("해리포터");

        library.findAll();

        library.delete("샬롯의 거미줄");

        library.add("Java 7");
        library.add("Java 8");
        library.add("Java 9");
        library.add("Java 11");

        library.findAll();

        library.add("Java 19");
    }
}
