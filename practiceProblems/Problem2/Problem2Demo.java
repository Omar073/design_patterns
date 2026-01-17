/**
 * Problem 2: Web-Based Logging System
 * Solution: Singleton Pattern
 * 
 * This demo shows how Singleton ensures a single logger instance
 * across the entire application, preventing inconsistent file writes.
 *
 * Roles:
 *  - Singleton: Logger with private constructor and synchronized getInstance()
 *  - Clients: UserService, OrderService, PaymentService obtain the same logger
 */

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

// Singleton Logger class
class Logger {
    // Single instance
    private static Logger instance;
    private FileWriter fileWriter;
    
    // Private constructor prevents external instantiation
    private Logger() {
        try {
            // Open log file for appending
            fileWriter = new FileWriter("application.log", true);
            log("Logger initialized");
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }
    
    // Thread-safe getInstance method
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    // Logging method
    public void log(String message) {
        try {
            String logEntry = String.format("[%s] %s%n", 
                LocalDateTime.now(), message);
            fileWriter.write(logEntry);
            fileWriter.flush(); // Ensure immediate write
            System.out.print(logEntry); // Also print to console
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
        }
    }
    
    // Cleanup method
    public void close() {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close logger: " + e.getMessage());
        }
    }
}

// Multiple components trying to use the logger
class UserService {
    public void createUser(String username) {
        // All components get the same logger instance
        Logger logger = Logger.getInstance();
        logger.log("UserService: Creating user " + username);
    }
}

class OrderService {
    public void processOrder(int orderId) {
        Logger logger = Logger.getInstance();
        logger.log("OrderService: Processing order #" + orderId);
    }
}

class PaymentService {
    public void processPayment(double amount) {
        Logger logger = Logger.getInstance();
        logger.log("PaymentService: Processing payment of $" + amount);
    }
}

// Demo
public class Problem2Demo {
    public static void main(String[] args) {
        System.out.println("=== Problem 2: Web-Based Logging System ===\n");
        
        // Multiple components accessing the logger
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();
        
        // All components use the same logger instance
        userService.createUser("john_doe");
        orderService.processOrder(12345);
        paymentService.processPayment(99.99);
        
        // Verify it's the same instance
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        System.out.println("\n✓ Same instance? " + (logger1 == logger2));
        System.out.println("✓ All log entries go to the same file!");
        
        // Cleanup
        logger1.close();
    }
}

