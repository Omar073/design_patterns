// Anti-Pattern Example: Magic Numbers
// This demonstrates magic numbers and how to use named constants

// ❌ BAD: Magic numbers - unclear what they represent
class MagicNumbersBad {
    void checkEmploymentEligibility(int age) {
        if (age >= 18 && age <= 65) {  // What do 18 and 65 mean?
            System.out.println("Eligible for employment");
        } else {
            System.out.println("Not eligible");
        }
    }
    
    double calculateTax(double income) {
        if (income > 50000) {  // What does 50000 represent?
            return income * 0.20;  // What does 0.20 represent?
        } else if (income > 20000) {  // What does 20000 represent?
            return income * 0.15;  // What does 0.15 represent?
        } else {
            return income * 0.10;  // What does 0.10 represent?
        }
    }
    
    void processOrder(int quantity) {
        if (quantity > 100) {  // Why 100?
            System.out.println("Bulk order discount applied");
        }
    }
}

// ✅ GOOD: Named constants - clear and maintainable
class MagicNumbersGood {
    // Employment age constants
    private static final int MIN_EMPLOYMENT_AGE = 18;
    private static final int MAX_EMPLOYMENT_AGE = 65;
    
    // Tax bracket constants
    private static final double HIGH_INCOME_THRESHOLD = 50000.0;
    private static final double MEDIUM_INCOME_THRESHOLD = 20000.0;
    private static final double HIGH_TAX_RATE = 0.20;
    private static final double MEDIUM_TAX_RATE = 0.15;
    private static final double LOW_TAX_RATE = 0.10;
    
    // Order quantity constants
    private static final int BULK_ORDER_THRESHOLD = 100;
    
    void checkEmploymentEligibility(int age) {
        if (age >= MIN_EMPLOYMENT_AGE && age <= MAX_EMPLOYMENT_AGE) {
            System.out.println("Eligible for employment");
        } else {
            System.out.println("Not eligible");
        }
    }
    
    double calculateTax(double income) {
        if (income > HIGH_INCOME_THRESHOLD) {
            return income * HIGH_TAX_RATE;
        } else if (income > MEDIUM_INCOME_THRESHOLD) {
            return income * MEDIUM_TAX_RATE;
        } else {
            return income * LOW_TAX_RATE;
        }
    }
    
    void processOrder(int quantity) {
        if (quantity > BULK_ORDER_THRESHOLD) {
            System.out.println("Bulk order discount applied");
        }
    }
}

// Demo
public class MagicNumbersExample {
    public static void main(String[] args) {
        System.out.println("=== Magic Numbers Anti-Pattern Example ===\n");
        
        System.out.println("❌ BAD: Magic numbers - unclear meaning");
        MagicNumbersBad bad = new MagicNumbersBad();
        bad.checkEmploymentEligibility(25);
        bad.checkEmploymentEligibility(70);
        System.out.println("Tax for 60000: " + bad.calculateTax(60000));
        System.out.println("Tax for 30000: " + bad.calculateTax(30000));
        System.out.println("Tax for 10000: " + bad.calculateTax(10000));
        bad.processOrder(150);
        
        System.out.println("\n✅ GOOD: Named constants - clear and maintainable");
        MagicNumbersGood good = new MagicNumbersGood();
        good.checkEmploymentEligibility(25);
        good.checkEmploymentEligibility(70);
        System.out.println("Tax for 60000: " + good.calculateTax(60000));
        System.out.println("Tax for 30000: " + good.calculateTax(30000));
        System.out.println("Tax for 10000: " + good.calculateTax(10000));
        good.processOrder(150);
        
        System.out.println("\n--- Key Benefits of Named Constants ---");
        System.out.println("✓ Self-documenting code");
        System.out.println("✓ Easy to change values in one place");
        System.out.println("✓ Prevents typos and errors");
        System.out.println("✓ Better code readability");
    }
}
