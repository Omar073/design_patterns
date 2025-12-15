// Simple Factory Pattern – parameterized factory method that creates objects based on a string parameter
// Demo: Notification system where a factory creates Email or SMS notifications based on user preference.
// This is a simpler variant than Factory Method - uses a single factory class with a parameterized method.
// Roles:
//   - Product: Notification (EmailNotification, SMSNotification)
//   - Factory: NotificationFactory centralizes the branching logic
//   - Client: User chooses a type and lets the factory handle concrete classes
// Contrast: DirectUser shows the client becoming tightly coupled to concrete classes.

// Product interface
interface Notification {
    void encryptMessage();

    void send();

    String getType();
}

// Concrete products
class EmailNotification implements Notification {
    private final String message;
    private String encryptedMessage;

    EmailNotification(String message) {
        this.message = message;
    }

    public void encryptMessage() {
        // Simple encryption simulation
        this.encryptedMessage = "[ENCRYPTED] " + message;
        System.out.println("Email message encrypted: " + encryptedMessage);
    }

    public void send() {
        System.out.println("Sending Email notification: " + (encryptedMessage != null ? encryptedMessage : message));
        System.out.println("  Subject: Notification");
        System.out.println("  Body: " + (encryptedMessage != null ? encryptedMessage : message));
    }

    public String getType() {
        return "Email";
    }
}

class SMSNotification implements Notification {
    private final String message;
    private String encryptedMessage;

    SMSNotification(String message) {
        this.message = message;
    }

    public void encryptMessage() {
        // Simple encryption simulation
        this.encryptedMessage = "[ENCRYPTED] " + message;
        System.out.println("SMS message encrypted: " + encryptedMessage);
    }

    public void send() {
        System.out.println("Sending SMS notification: " + (encryptedMessage != null ? encryptedMessage : message));
    }

    public String getType() {
        return "SMS";
    }
}

// Simple Factory - creates objects based on a parameter
class NotificationFactory {
    public Notification createNotification(String notificationType, String message) {
        if (notificationType.equals("Email")) {
            return new EmailNotification(message); // select concrete product
        } else if (notificationType.equals("SMS")) {
            return new SMSNotification(message);
        }
        throw new IllegalArgumentException("Unsupported notification type: " + notificationType);
    }
}

// Client class that uses the factory
class User {
    String notificationType;
    NotificationFactory factory;

    public User(String notificationType, NotificationFactory factory) {
        this.notificationType = notificationType;
        this.factory = factory;
    }

    public void placeOrder() {
        // Ask factory, stay unaware of concrete classes
        Notification notification = factory.createNotification(notificationType, "Order Placed");
        notification.encryptMessage();
        notification.send();
    }

    public void cancelOrder() {
        Notification notification = factory.createNotification(notificationType, "Order Canceled");
        notification.encryptMessage();
        notification.send();
    }
}

// Variant without factory (tighter coupling)
class DirectUser {
    String notificationType;

    DirectUser(String notificationType) {
        this.notificationType = notificationType;
    }

    public void placeOrder() {
        Notification notification;
        if (notificationType.equals("Email")) {
            notification = new EmailNotification("Order Placed"); // direct dependency
        } else if (notificationType.equals("SMS")) {
            notification = new SMSNotification("Order Placed");
        } else {
            throw new IllegalArgumentException("Unsupported notification type: " + notificationType);
        }
        notification.encryptMessage();
        notification.send();
    }

    public void cancelOrder() {
        Notification notification;
        if (notificationType.equals("Email")) {
            notification = new EmailNotification("Order Canceled");
        } else if (notificationType.equals("SMS")) {
            notification = new SMSNotification("Order Canceled");
        } else {
            throw new IllegalArgumentException("Unsupported notification type: " + notificationType);
        }
        notification.encryptMessage();
        notification.send();
    }
}

public class SimpleFactoryDemo {
    public static void main(String[] args) {
        System.out.println("== Simple Factory Pattern ==");
        NotificationFactory factory = new NotificationFactory();

        User emailUser = new User("Email", factory);
        System.out.println("\n--- Email User Places Order ---");
        emailUser.placeOrder();

        System.out.println("\n--- Email User Cancels Order ---");
        emailUser.cancelOrder();

        User smsUser = new User("SMS", factory);
        System.out.println("\n--- SMS User Places Order ---");
        smsUser.placeOrder();

        System.out.println("\n--- SMS User Cancels Order ---");
        smsUser.cancelOrder();

        System.out.println("\n== Without Simple Factory (direct) ==");
        DirectUser directUser = new DirectUser("Email");
        directUser.placeOrder();
    }
}
