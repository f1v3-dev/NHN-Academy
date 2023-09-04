public class Sample {

    static int add(int i, int j) {
        return i + j;
    }

    static int add(int i, double d) {
        return i + (int) d;
    }
    public static void main(String[] args) {
        double d = 5.0d;
        int result = add(1, d);
        result = add(2, 1);

    }
}
