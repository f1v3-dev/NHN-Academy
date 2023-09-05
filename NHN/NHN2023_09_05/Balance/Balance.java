public class Balance implements Comparable<Balance>{

    private int balance;

    public Balance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public Balance add(Balance value) {
        return new Balance(this.balance + value.getBalance());
    }

    public int compareTo(Balance value) {
        return this.balance - value.getBalance();
    }
}