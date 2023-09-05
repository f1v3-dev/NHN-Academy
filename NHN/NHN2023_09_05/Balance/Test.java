public class Test {
    
    public static void main(String[] args) {
        Balance b1 = new Balance(5);
        Balance b2 = new Balance(5);

        System.out.println(b1.add(b2));
        
        if (b1.compareTo(b2) == 0) {
            System.out.println("It's same");
        }

        else if (b1.compareTo(b2) > 1) {
            System.out.println("It's bigger than b2");
        }

        else {
            System.out.println("It's smaller than b2");
        }
    }
}
