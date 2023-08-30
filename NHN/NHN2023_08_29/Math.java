public class Math {

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }

    public static int multi(int a, int b) {
        return a * b;
    }

    public static int div(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }
        return a / b;
    }

    public static void main(String[] args) {
        int a = 3;
        int b = 5;
        int zero = 0;

        System.out.println(sum(a, b));
        System.out.println(sub(b, a));
        System.out.println(multi(a, b));
        System.out.println(div(a, zero));
    }

}