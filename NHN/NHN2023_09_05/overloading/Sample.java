public class Sample {

    public static int sigma(int start, int end, int step) {
        return calc((i, j) -> i + j, start, end, step, 0);

    }

    public static int pi(int start, int end, int step) {
        return calc((i, j) -> i * j, start, end, step, 1);

    }

    private static int calc(BinaryOp binder, int start, int end, int step, int init) {
        int result = init;

        for (int i = start; i <= end; i += step) {
            result = binder.apply(result, i);
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(sigma(1, 10, 1));
        System.out.println(pi(1, 10, 1));

        System.out.println(calc((i, j) -> i + j, 1, 10, 1, 0));
        System.out.println(calc((i, j) -> i * j, 1, 10, 1, 1));

        System.out.println(calc(new BinaryOp() {
            @Override
            public int apply(int i, int j) {
                return i + j;
            }
        }, 1, 10, 1, 0));
    }
}
