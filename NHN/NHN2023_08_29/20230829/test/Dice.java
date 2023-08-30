package test;
public class Dice {

    private int num;

    public Dice(int num) {
        this.num = num % 6;
    }

    public int getNum() {
        return num;
    }
}
