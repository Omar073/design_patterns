/**
 * Problem 3: Notification Service
 * Solution: Abstract Factory Pattern
 * 
 * This demo shows how Abstract Factory hides notification creation
 * complexity from the client, allowing easy extension to new types.
 */

// Abstract product
interface Notification {
    void send(String recipient, String message);
    String getType();
}

// Concrete products
class SMSNotification implements Notification {
    public void send(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
    
    public String getType() {
        return "SMS";
    }
}

class EmailNotification implements Notification {
    public void send(String recipient, String message) {
        System.out.println("Sending Email to " + recipient + ": " + message);
        System.out.println("  Subject: Notification");
        System.out.println("  Body: " + message);
    }
    
    public String getType() {
        return "Email";
    }
}

class PushNotification implements Notification {
    public void send(String recipient, String message) {
        System.out.println("Sending Push notification to device " + recipient);
        System.out.println("  Title: Alert");
        System.out.println("  Message: " + message);
    }
    
    public String getType() {
        return "Push";
    }
}

// Abstract Factory
interface NotificationFactory {
    Notification createNotification();
}

// Concrete Factories
class SMSNotificationFactory implements NotificationFactory {
    public Notification createNotification() {
        return new SMSNotification();
    }
}

class EmailNotificationFactory implements NotificationFactory {
    public Notification createNotification() {
        return new EmailNotification();
    }
}

class PushNotificationFactory implements NotificationFactory {
    public Notification createNotification() {
        return new PushNotification();
    }
}

// Client code - doesn't know about concrete implementations
class NotificationService {
    private NotificationFactory factory;
    
    public NotificationService(NotificationFactory factory) {
        this.factory = factory;
    }
    
    public void sendNotification(String recipient, String message) {
        // Client doesn't know which concrete notification is created
        Notification notification = factory.createNotification();
        System.out.println("Creating " + notification.getType() + " notification...");
        notification.send(recipient, message);
    }
}

// Demo
public class Problem3Demo {
    public static void main(String[] args) {
        System.out.println("=== Problem 3: Notification Service ===\n");
        
        // Client can use any notification type without knowing implementation
        System.out.println("--- Using SMS Factory ---");
        NotificationService smsService = new NotificationService(new SMSNotificationFactory());
        smsService.sendNotification("+1234567890", "Your order has been shipped!");
        
        System.out.println("\n--- Using Email Factory ---");
        NotificationService emailService = new NotificationService(new EmailNotificationFactory());
        emailService.sendNotification("user@example.com", "Your order has been shipped!");
        
        System.out.println("\n--- Using Push Factory ---");
        NotificationService pushService = new NotificationService(new PushNotificationFactory());
        pushService.sendNotification("device_abc123", "Your order has been shipped!");
        
        System.out.println("\n✓ Client code works with any notification type!");
    }
}

