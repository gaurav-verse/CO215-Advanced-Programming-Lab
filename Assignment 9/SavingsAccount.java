public class SavingsAccount extends Account {

    private double interestRate;

    // Constructor 1 — chains to Constructor 2
    public SavingsAccount(String accountNumber, String ownerName) {
        this(accountNumber, ownerName, 0.0, 0.04);
    }

    // Constructor 2 — full constructor
    public SavingsAccount(String accountNumber, String ownerName,
                          double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        if (interestRate < 0 || interestRate > 1) {
            throw new IllegalArgumentException("Rate must be between 0 and 1.");
        }
        this.interestRate = interestRate;
    }

    public double getInterestRate() { return interestRate; }

    public void setInterestRate(double rate) {
        if (rate < 0 || rate > 1) {
            throw new IllegalArgumentException("Rate must be between 0 and 1.");
        }
        this.interestRate = rate;
    }

    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Type       : Savings Account");
        System.out.println("Int. Rate  : " + (interestRate * 100) + "%");
        System.out.println("Yearly Int.: Rs." + calculateInterest());
        System.out.println("------------------------------");
    }
}