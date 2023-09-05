public class Test {

    public static void main(String[] args) {
    //    Movie.movieRating(10, 2, 3);
        BankAccount account1 = new BankAccount();
        account1.accountNumber = 103;
        account1.balance = 100;
        account1.owenrName = "Celine";

        BankAccount account2 = new BankAccount();
        account2.accountNumber = 104;
        account2.balance = 100;
        account2.owenrName = "James";

        System.out.println(BankAccount.accountNumber);
    }
    
}
