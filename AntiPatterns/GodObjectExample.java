// Anti-Pattern Example: God Object
// This demonstrates the God Object anti-pattern and its refactored solution

// ❌ BAD: God Object - One class doing too many things
class OrderManager {
    // Database operations
    void connectDatabase() {
        System.out.println("Connecting to database...");
    }
    
    // Order validation
    void validateOrder() {
        System.out.println("Validating order...");
    }
    
    // Price calculation
    void calculatePrice() {
        System.out.println("Calculating price...");
    }
    
    // Discount application
    void applyDiscount() {
        System.out.println("Applying discount...");
    }
    
    // Payment processing
    void processPayment() {
        System.out.println("Processing payment...");
    }
    
    // Invoice generation
    void generateInvoice() {
        System.out.println("Generating invoice...");
    }
    
    // Email sending
    void sendEmail() {
        System.out.println("Sending email...");
    }
    
    // Logging
    void logActivity() {
        System.out.println("Logging activity...");
    }
}

// ✅ GOOD: Refactored into focused services
class OrderService {
    void validateOrder() {
        System.out.println("Validating order...");
    }
}

class PaymentService {
    void processPayment() {
        System.out.println("Processing payment...");
    }
}

class InvoiceService {
    void generateInvoice() {
        System.out.println("Generating invoice...");
    }
}

class EmailService {
    void sendEmail() {
        System.out.println("Sending email...");
    }
}

class DatabaseService {
    void connectDatabase() {
        System.out.println("Connecting to database...");
    }
}

class LoggerService {
    void logActivity() {
        System.out.println("Logging activity...");
    }
}

// Demo
public class GodObjectExample {
    public static void main(String[] args) {
        System.out.println("=== God Object Anti-Pattern Example ===\n");
        
        System.out.println("❌ BAD: God Object - One class doing everything");
        OrderManager godObject = new OrderManager();
        godObject.connectDatabase();
        godObject.validateOrder();
        godObject.calculatePrice();
        godObject.processPayment();
        godObject.generateInvoice();
        godObject.sendEmail();
        godObject.logActivity();
        
        System.out.println("\n✅ GOOD: Separated responsibilities");
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();
        InvoiceService invoiceService = new InvoiceService();
        EmailService emailService = new EmailService();
        DatabaseService databaseService = new DatabaseService();
        LoggerService loggerService = new LoggerService();
        
        databaseService.connectDatabase();
        orderService.validateOrder();
        paymentService.processPayment();
        invoiceService.generateInvoice();
        emailService.sendEmail();
        loggerService.logActivity();
        
        System.out.println("\n--- Key Benefits of Refactoring ---");
        System.out.println("✓ Single Responsibility Principle");
        System.out.println("✓ Easier to test");
        System.out.println("✓ Easier to maintain");
        System.out.println("✓ Better code organization");
    }
}
