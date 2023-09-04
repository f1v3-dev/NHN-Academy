public class Sample {

    // 정수형 2개를 받은 후 같으면 true, 아니면 false
    static boolean compare(int i, int j) {
        if (i == j) {
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        int i = 1;
        int k = 2;

        if (compare(i, k)) {
            System.out.println("Escape");
        }

        if (i == k) {
            System.out.println("escape");
        } else {
            System.out.println("Stay");
        }

        i = 5;
        k = 9;

        if (i == k) {
            System.out.println("Escape");
        } else {
            System.out.println("Stay");
        }

        i = 5;
        k = 10;

        if (i == k) {
            System.out.println("Escape");
        } else {
            System.out.println("Stay");
        }

    }
}
