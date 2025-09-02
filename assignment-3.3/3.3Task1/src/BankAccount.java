public class BankAccount {
    private static int accountCount = 0;
    private final int accountNumber;
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
        accountCount++;
        this.accountNumber= accountCount;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
        }
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public static int getTotalAccounts() {
        return accountCount++;
    }


    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(2000);

        account1.deposit(500);
        account2.withdraw(800);

        System.out.println("Account " + account1.getAccountNumber() + " balance: " + account1.getBalance());
        System.out.println("Account " + account2.getAccountNumber() + " balance: " + account2.getBalance());

        System.out.println("Total number of accounts: " + BankAccount.getTotalAccounts());
    }
}
