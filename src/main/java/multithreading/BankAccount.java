package multithreading;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void transferFunds(BankAccount targetAccount, double amount) {
        synchronized (this) {  // Lock this account object
            synchronized (targetAccount) {  // Lock the target account object
                if (this.balance >= amount) {
                    this.balance -= amount;
                    targetAccount.balance += amount;
                }
            }
        }
    }

    public double getBalance() {
        return balance;
    }
}
