package TestCode;

public class DiceCalculator {

    public DiceCalculator() {
    }

    public static int addDice(Dice first, Dice second) {
        return first.getNum() + second.getNum();
    }

    public static String getIndexOf(String str, int index) {
        return str.substring(0, index + 1);
    }

    public static boolean odd(Dice dice) {
        return dice.getNum() % 2 != 0;
    }
}
