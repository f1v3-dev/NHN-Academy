import java.math.*;

public class BankAccount {
    private long accountNumber;
    private String ownerName;
    private BigDecimal balance;

    private static long nextAccountNumber;

    private static long nextNumber() {
        return nextAccountNumber++;
    }

    public void setData(String ownerName, BigDecimal balance) {
        this.accountNumber = nextNumber();
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public long getNumber() {
        return this.accountNumber;
    }

    public String getName() {
        return this.ownerName;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }
}

class CreateAccount {
    public static BankAccount createNewBankAccount(String ownerName, BigDecimal balance) {
        BankAccount newAccount = new BankAccount();

        newAccount.setData(ownerName, balance);

        return newAccount;
    }

    public static void main(String[] args) {
        BankAccount bankAccount1 = CreateAccount.createNewBankAccount("Vesper Lind", new BigDecimal("0.0"));
        printBankAccount(bankAccount1);

        System.out.println();

        BankAccount bankAccount2 = CreateAccount.createNewBankAccount("James Bond", new BigDecimal("0.0"));
        printBankAccount(bankAccount2);
    }

    public static void printBankAccount(BankAccount account) {
        System.out.println("Account Number: " + account.getNumber());
        System.out.println("Owner Name: " + account.getName());
        System.out.println("Balance: " + account.getBalance());
    }
}