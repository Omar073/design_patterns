// Anti-Pattern Example: Spaghetti Code
// This demonstrates deep nesting and how to refactor it

// ❌ BAD: Spaghetti Code - Deep nesting, hard to read
class SpaghettiCodeBad {
    void processTransaction(User user) {
        if (user != null) {
            if (user.isActive()) {
                if (user.hasPermission()) {
                    if (user.getBalance() > 1000) {
                        System.out.println("Transaction processed");
                    } else {
                        System.out.println("Low balance");
                    }
                } else {
                    System.out.println("No permission");
                }
            } else {
                System.out.println("User inactive");
            }
        } else {
            System.out.println("User is null");
        }
    }
}

// ✅ GOOD: Refactored - Early returns, extracted methods
class SpaghettiCodeGood {
    void processTransaction(User user) {
        if (!isValidUser(user)) {
            return;
        }
        System.out.println("Transaction processed");
    }
    
    boolean isValidUser(User user) {
        if (user == null) {
            System.out.println("User is null");
            return false;
        }
        if (!user.isActive()) {
            System.out.println("User inactive");
            return false;
        }
        if (!user.hasPermission()) {
            System.out.println("No permission");
            return false;
        }
        if (user.getBalance() <= 1000) {
            System.out.println("Low balance");
            return false;
        }
        return true;
    }
}

// Simple User class for demonstration
class User {
    private boolean active;
    private boolean hasPermission;
    private double balance;
    
    public User(boolean active, boolean hasPermission, double balance) {
        this.active = active;
        this.hasPermission = hasPermission;
        this.balance = balance;
    }
    
    public boolean isActive() { return active; }
    public boolean hasPermission() { return hasPermission; }
    public double getBalance() { return balance; }
}

// Demo
public class SpaghettiCodeExample {
    public static void main(String[] args) {
        System.out.println("=== Spaghetti Code Anti-Pattern Example ===\n");
        
        System.out.println("❌ BAD: Deep nesting - hard to read and maintain");
        SpaghettiCodeBad bad = new SpaghettiCodeBad();
        bad.processTransaction(new User(true, true, 1500));
        bad.processTransaction(null);
        bad.processTransaction(new User(false, true, 1500));
        bad.processTransaction(new User(true, false, 1500));
        bad.processTransaction(new User(true, true, 500));
        
        System.out.println("\n✅ GOOD: Early returns and extracted methods");
        SpaghettiCodeGood good = new SpaghettiCodeGood();
        good.processTransaction(new User(true, true, 1500));
        good.processTransaction(null);
        good.processTransaction(new User(false, true, 1500));
        good.processTransaction(new User(true, false, 1500));
        good.processTransaction(new User(true, true, 500));
        
        System.out.println("\n--- Key Benefits of Refactoring ---");
        System.out.println("✓ Easier to read");
        System.out.println("✓ Easier to debug");
        System.out.println("✓ Easier to modify");
        System.out.println("✓ Better code structure");
    }
}
