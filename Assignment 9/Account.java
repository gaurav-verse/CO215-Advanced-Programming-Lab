public class Account {

    private String accountNumber;
    private String ownerName;
    private double balance;

    // Constructor 1 — chains to Constructor 2
    public Account(String accountNumber, String ownerName) {
        this(accountNumber, ownerName, 0.0);
    }

    // Constructor 2 — full constructor
    public Account(String accountNumber, String ownerName, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName()     { return ownerName; }
    public double getBalance()       { return balance; }

    // Setter for ownerName only
    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
        this.ownerName = ownerName;
    }

    // Protected so subclasses (CurrentAccount) can update balance
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Deposited Rs." + amount + " | New balance: Rs." + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds. Balance: Rs." + balance);
        }
        balance -= amount;
        System.out.println("Withdrawn Rs." + amount + " | New balance: Rs." + balance);
    }

    public void display() {
        System.out.println("------------------------------");
        System.out.println("Account No : " + accountNumber);
        System.out.println("Owner      : " + ownerName);
        System.out.println("Balance    : Rs." + balance);
    }
}