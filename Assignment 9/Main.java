import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Creating accounts using both constructors
        SavingsAccount s1 = new SavingsAccount("SAV001", "Gaurav Nath", 5000.0, 0.06);
        SavingsAccount s2 = new SavingsAccount("SAV002", "Riya Sen");
        CurrentAccount c1 = new CurrentAccount("CUR001", "Tezpur Traders", 2000.0, 15000.0);

        // Operations on s1
        s1.deposit(3000.0);
        s1.withdraw(1000.0);

        // CurrentAccount going into overdraft
        c1.withdraw(15000.0);

        
        // POLYMORPHISM: Account reference holds Savings and Current objects
        List<Account> accounts = new ArrayList<>();
        accounts.add(s1);
        accounts.add(s2);
        accounts.add(c1);

        System.out.println("\n========= ALL ACCOUNTS =========");
        for (Account acc : accounts) {
            acc.display(); // calls the actual subclass display() at runtime
        }

        // Exception handling demo
        System.out.println("\n--- Exception Demo ---");
        try {
            s2.withdraw(999999.0);
        } catch (IllegalStateException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            s1.deposit(-500.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}