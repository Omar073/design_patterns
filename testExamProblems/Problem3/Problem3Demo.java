/**
 * Problem 3: Notification Service
 * Solution: Simple Factory Pattern
 * 
 * This demo shows how Simple Factory hides notification creation
 * complexity from the client, allowing easy extension to new types.
 * 
 * Note: This creates only ONE product type (Notification), so Simple Factory
 * is the appropriate pattern. Abstract Factory is for creating FAMILIES
 * of related products.
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

// Simple Factory - single factory class with parameterized method
class NotificationFactory {
    public Notification createNotification(String notificationType) {
        if (notificationType == null) {
            throw new IllegalArgumentException("Notification type cannot be null");
        }
        if (notificationType.equalsIgnoreCase("SMS")) {
            return new SMSNotification();
        } else if (notificationType.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        } else if (notificationType.equalsIgnoreCase("PUSH")) {
            return new PushNotification();
        }
        throw new IllegalArgumentException("Unsupported notification type: " + notificationType);
    }
}

// Client code - doesn't know about concrete implementations
class NotificationService {
    private NotificationFactory factory;

    public NotificationService() {
        this.factory = new NotificationFactory();
    }

    public void sendNotification(String notificationType, String recipient, String message) {
        // Client doesn't know which concrete notification is created
        Notification notification = factory.createNotification(notificationType);
        System.out.println("Creating " + notification.getType() + " notification...");
        notification.send(recipient, message);
    }
}

// Demo
public class Problem3Demo {
    public static void main(String[] args) {
        System.out.println("=== Problem 3: Notification Service ===\n");

        NotificationService service = new NotificationService();

        // Client can use any notification type without knowing implementation
        System.out.println("--- Using SMS Notification ---");
        service.sendNotification("SMS", "+1234567890", "Your order has been shipped!");

        System.out.println("\n--- Using Email Notification ---");
        service.sendNotification("EMAIL", "user@example.com", "Your order has been shipped!");

        System.out.println("\n--- Using Push Notification ---");
        service.sendNotification("PUSH", "device_abc123", "Your order has been shipped!");

        System.out.println("\n✓ Simple Factory is sufficient - only one product type (Notification)!");
        System.out.println("✓ Abstract Factory is for creating FAMILIES of related products.");
    }
}
