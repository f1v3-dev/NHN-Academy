public class Sample {

    public static int sigma(int start, int end, int step) {
        return calc(new Adder(), start, end, step, 0);

    }

    public static int pi(int start, int end, int step) {
        return calc(new Multiplier(), start, end, step, 1);

    }

    private static int calc(Adder binder, int start, int end, int step, int init) {
        int result = init;

        for (int i = start; i <= end; i += step) {
            result = binder.apply(result, i);
        }
        return result;
    }

    private static int calc(Multiplier binder, int start, int end, int step, int init) {
        int result = init;

        for (int i = start; i <= end; i += step) {
            result = binder.apply(result, i);
        }
        return result;

    }
    
    public static void main(String[] args) {
        System.out.println(sigma(1, 10, 1));
        System.out.println(pi(1, 10, 1));
    }
}
