import java.math.*;

public class BankAccount {
    public long accountNumber;
    public String ownerName;
    public BigDecimal balance;

    //
    // add setData method here.
    //

    //
    // add getNumber method here.
    //
}

class CreateAccount {
    public static BankAccount createNewBankAccount(long accountNumber, String ownerName, BigDecimal balance) {
        BankAccount newAccount;
        
        newAccount.accountNumber = accountNumber;
        newAccount.ownerName = ownerName;
        newAccount.balance = balance;

        return newAccount;
    }

    //public static void main(String[] args) {
    //    BankAccount bankAccount = CreateAccount.createNewBankAccount(1, "Vesper Lind", new BigDecimal("0.0"));
    //    printBankAccount(bankAccount);
    //}

    public static void printBankAccount(BankAccount account) {
        System.out.println("Account Number: " + account.accountNumber);
        System.out.println("Owner Name: " + account.ownerName);
        System.out.println("Balance: " + account.balance);
    }
}