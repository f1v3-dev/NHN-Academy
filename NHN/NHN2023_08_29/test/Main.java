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
    }
}
