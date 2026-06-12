public class CurrentAccount extends Account {

    private double overdraftLimit;

    // Constructor 1 — chains to Constructor 2
    public CurrentAccount(String accountNumber, String ownerName) {
        this(accountNumber, ownerName, 0.0, 10000.0);
    }

    // Constructor 2 — full constructor
    public CurrentAccount(String accountNumber, String ownerName,
                          double balance, double overdraftLimit) {
        super(accountNumber, ownerName, balance);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative.");
        }
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() { return overdraftLimit; }

    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative.");
        }
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > getBalance() + overdraftLimit) {
            throw new IllegalStateException(
                "Exceeds overdraft limit. Max you can withdraw: Rs." + (getBalance() + overdraftLimit)
            );
        }
        setBalance(getBalance() - amount);
        System.out.println("Withdrawn Rs." + amount + " | New balance: Rs." + getBalance());
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Type       : Current Account");
        System.out.println("Overdraft  : Rs." + overdraftLimit);
        System.out.println("------------------------------");
    }
}