// Chain of Responsibility pattern demo: Email handling system.
// Each email is passed through a chain of handlers until one handles it.
// No package for simplicity; compile from this directory.

// Request object
class Email {
    private String type;
    private String content;
    private String sender;

    public Email(String type, String content, String sender) {
        this.type = type;
        this.content = content;
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "Email{type='" + type + "', sender='" + sender + "', content='" + content + "'}";
    }
}

// Handler interface
abstract class Handler {
    protected Handler successor; // Reference to next handler in chain

    // Set the next handler in the chain
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    // Handle the request or pass it to the next handler
    public abstract void handleRequest(Email email);
}

// Concrete Handler 1: Spam Handler
class SpamHandler extends Handler {
    @Override
    public void handleRequest(Email email) {
        if ("SPAM".equalsIgnoreCase(email.getType())) {
            System.out.println("SpamHandler: Handling spam email from " + email.getSender());
            System.out.println("  -> Moving to spam folder.");
        } else {
            // Pass to next handler
            if (successor != null) {
                System.out.println("SpamHandler: Not spam, passing to next handler...");
                successor.handleRequest(email);
            } else {
                System.out.println("SpamHandler: End of chain reached. Email not handled: " + email);
            }
        }
    }
}

// Concrete Handler 2: Fan Handler
class FanHandler extends Handler {
    @Override
    public void handleRequest(Email email) {
        if ("FAN".equalsIgnoreCase(email.getType())) {
            System.out.println("FanHandler: Handling fan email from " + email.getSender());
            System.out.println("  -> Forwarding to PR department.");
        } else {
            // Pass to next handler
            if (successor != null) {
                System.out.println("FanHandler: Not a fan email, passing to next handler...");
                successor.handleRequest(email);
            } else {
                System.out.println("FanHandler: End of chain reached. Email not handled: " + email);
            }
        }
    }
}

// Concrete Handler 3: Complaint Handler
class ComplaintHandler extends Handler {
    @Override
    public void handleRequest(Email email) {
        if ("COMPLAINT".equalsIgnoreCase(email.getType())) {
            System.out.println("ComplaintHandler: Handling complaint from " + email.getSender());
            System.out.println("  -> Escalating to customer service manager.");
        } else {
            // Pass to next handler
            if (successor != null) {
                System.out.println("ComplaintHandler: Not a complaint, passing to next handler...");
                successor.handleRequest(email);
            } else {
                System.out.println("ComplaintHandler: End of chain reached. Email not handled: " + email);
            }
        }
    }
}

// Concrete Handler 4: New Location Handler
class NewLocHandler extends Handler {
    @Override
    public void handleRequest(Email email) {
        if ("NEW_LOC".equalsIgnoreCase(email.getType())) {
            System.out.println("NewLocHandler: Handling new location request from " + email.getSender());
            System.out.println("  -> Forwarding to operations department.");
        } else {
            // Pass to next handler
            if (successor != null) {
                System.out.println("NewLocHandler: Not a new location request, passing to next handler...");
                successor.handleRequest(email);
            } else {
                System.out.println("NewLocHandler: End of chain reached. Email not handled: " + email);
            }
        }
    }
}

// Optional: Catch-all handler for unhandled emails
class CatchAllHandler extends Handler {
    @Override
    public void handleRequest(Email email) {
        System.out.println("CatchAllHandler: No specific handler found for email type: " + email.getType());
        System.out.println("  -> Moving to general inbox for manual review.");
    }
}

// Client
public class ChainOfResponsibilityEmailDemo {
    public static void main(String[] args) {
        // Build the chain: Spam -> Fan -> Complaint -> NewLoc -> CatchAll
        Handler spamHandler = new SpamHandler();
        Handler fanHandler = new FanHandler();
        Handler complaintHandler = new ComplaintHandler();
        Handler newLocHandler = new NewLocHandler();
        Handler catchAllHandler = new CatchAllHandler();

        // Set up the chain
        spamHandler.setSuccessor(fanHandler);
        fanHandler.setSuccessor(complaintHandler);
        complaintHandler.setSuccessor(newLocHandler);
        newLocHandler.setSuccessor(catchAllHandler);

        System.out.println("=== Email Processing Chain ===\n");

        // Send different types of emails through the chain
        System.out.println("1. Processing SPAM email:");
        Email spamEmail = new Email("SPAM", "Buy now! Limited offer!", "spammer@example.com");
        spamHandler.handleRequest(spamEmail);

        System.out.println("\n2. Processing FAN email:");
        Email fanEmail = new Email("FAN", "I love your product!", "fan@example.com");
        spamHandler.handleRequest(fanEmail);

        System.out.println("\n3. Processing COMPLAINT email:");
        Email complaintEmail = new Email("COMPLAINT", "Product arrived damaged", "customer@example.com");
        spamHandler.handleRequest(complaintEmail);

        System.out.println("\n4. Processing NEW_LOC email:");
        Email newLocEmail = new Email("NEW_LOC", "Request for new store location", "partner@example.com");
        spamHandler.handleRequest(newLocEmail);

        System.out.println("\n5. Processing UNKNOWN email (no specific handler):");
        Email unknownEmail = new Email("UNKNOWN", "Random email", "someone@example.com");
        spamHandler.handleRequest(unknownEmail);

        System.out.println("\n=== Chain Benefits ===");
        System.out.println("- Each handler only knows about the next handler (decoupling)");
        System.out.println("- Easy to add/remove handlers or change order");
        System.out.println("- Client doesn't need to know chain structure");
    }
}

