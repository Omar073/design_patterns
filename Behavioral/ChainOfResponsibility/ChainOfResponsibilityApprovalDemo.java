// Chain of Responsibility pattern demo: Purchase approval system.
// Purchase requests flow through a chain of approvers based on amount.
// No package for simplicity; compile from this directory.

// Request object
class PurchaseRequest {
    private double amount;
    private String item;
    private String requester;

    public PurchaseRequest(double amount, String item, String requester) {
        this.amount = amount;
        this.item = item;
        this.requester = requester;
    }

    public double getAmount() {
        return amount;
    }

    public String getItem() {
        return item;
    }

    public String getRequester() {
        return requester;
    }

    @Override
    public String toString() {
        return String.format("PurchaseRequest{amount=$%.2f, item='%s', requester='%s'}", 
                            amount, item, requester);
    }
}

// Handler abstract class
abstract class Approver {
    protected Approver successor; // Next approver in chain
    protected String name;
    protected double approvalLimit;

    public Approver(String name, double approvalLimit) {
        this.name = name;
        this.approvalLimit = approvalLimit;
    }

    // Set the next approver in the chain
    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    // Handle the request or pass it to the next approver
    public void handleRequest(PurchaseRequest request) {
        if (request.getAmount() <= approvalLimit) {
            // This approver can handle it
            approve(request);
        } else {
            // Pass to next approver
            if (successor != null) {
                System.out.println(name + " cannot approve $" + request.getAmount() + 
                                 ". Forwarding to " + successor.name + "...");
                successor.handleRequest(request);
            } else {
                // End of chain - no one can approve
                reject(request);
            }
        }
    }

    protected void approve(PurchaseRequest request) {
        System.out.println(name + " approved $" + request.getAmount() + 
                         " for " + request.getItem() + " (requested by " + request.getRequester() + ")");
    }

    protected void reject(PurchaseRequest request) {
        System.out.println("REJECTED: No approver can handle $" + request.getAmount() + 
                         " for " + request.getItem() + " (exceeds all approval limits)");
    }
}

// Concrete Handler 1: Team Lead (can approve up to $1000)
class TeamLead extends Approver {
    public TeamLead(String name) {
        super(name, 1000.0);
    }
}

// Concrete Handler 2: Manager (can approve up to $5000)
class Manager extends Approver {
    public Manager(String name) {
        super(name, 5000.0);
    }
}

// Concrete Handler 3: Director (can approve up to $20000)
class Director extends Approver {
    public Director(String name) {
        super(name, 20000.0);
    }
}

// Concrete Handler 4: VP (can approve up to $100000)
class VP extends Approver {
    public VP(String name) {
        super(name, 100000.0);
    }
}

// Client
public class ChainOfResponsibilityApprovalDemo {
    public static void main(String[] args) {
        // Build the approval chain: TeamLead -> Manager -> Director -> VP
        Approver teamLead = new TeamLead("Alice (Team Lead)");
        Approver manager = new Manager("Bob (Manager)");
        Approver director = new Director("Carol (Director)");
        Approver vp = new VP("David (VP)");

        // Set up the chain
        teamLead.setSuccessor(manager);
        manager.setSuccessor(director);
        director.setSuccessor(vp);

        System.out.println("=== Purchase Approval Chain ===\n");
        System.out.println("Approval Limits:");
        System.out.println("  Team Lead: up to $1,000");
        System.out.println("  Manager: up to $5,000");
        System.out.println("  Director: up to $20,000");
        System.out.println("  VP: up to $100,000\n");

        // Test different purchase amounts
        System.out.println("1. Request for $500 (within Team Lead limit):");
        PurchaseRequest req1 = new PurchaseRequest(500, "Office supplies", "John");
        teamLead.handleRequest(req1);

        System.out.println("\n2. Request for $2,500 (requires Manager approval):");
        PurchaseRequest req2 = new PurchaseRequest(2500, "New monitor", "Jane");
        teamLead.handleRequest(req2);

        System.out.println("\n3. Request for $12,000 (requires Director approval):");
        PurchaseRequest req3 = new PurchaseRequest(12000, "Server equipment", "Mike");
        teamLead.handleRequest(req3);

        System.out.println("\n4. Request for $75,000 (requires VP approval):");
        PurchaseRequest req4 = new PurchaseRequest(75000, "Company car", "Sarah");
        teamLead.handleRequest(req4);

        System.out.println("\n5. Request for $150,000 (exceeds all limits):");
        PurchaseRequest req5 = new PurchaseRequest(150000, "Building renovation", "Tom");
        teamLead.handleRequest(req5);

        System.out.println("\n=== Chain Benefits ===");
        System.out.println("- Each approver only knows about the next approver");
        System.out.println("- Easy to add new approval levels or change limits");
        System.out.println("- Request automatically finds the right approver");
    }
}

