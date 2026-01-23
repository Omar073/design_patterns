// SOLID Principle: Interface Segregation Principle (ISP)
// Clients should not be forced to depend on interfaces they do not use

// ❌ BAD: Fat interface - forces classes to implement methods they don't need
interface WorkerBad {
    void work();
    void eat();
    void sleep();
}

class OfficeWorkerBad implements WorkerBad {
    public void work() {
        System.out.println("Office worker is working...");
    }
    
    public void eat() {
        System.out.println("Office worker is eating...");
    }
    
    public void sleep() {
        System.out.println("Office worker is sleeping...");
    }
}

// Robot forced to implement methods it doesn't need
class RobotBad implements WorkerBad {
    public void work() {
        System.out.println("Robot is working...");
    }
    
    public void eat() {
        throw new UnsupportedOperationException("Robots don't eat!");
    }
    
    public void sleep() {
        throw new UnsupportedOperationException("Robots don't sleep!");
    }
}

// ✅ GOOD: Segregated interfaces - classes only implement what they need
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

// OfficeWorker implements all interfaces it needs
class OfficeWorker implements Workable, Eatable, Sleepable {
    public void work() {
        System.out.println("Office worker is working...");
    }
    
    public void eat() {
        System.out.println("Office worker is eating...");
    }
    
    public void sleep() {
        System.out.println("Office worker is sleeping...");
    }
}

// Robot only implements what it needs
class Robot implements Workable {
    public void work() {
        System.out.println("Robot is working...");
    }
    // No need to implement eat() or sleep()
}

// Human can implement multiple interfaces as needed
class Human implements Workable, Eatable, Sleepable {
    public void work() {
        System.out.println("Human is working...");
    }
    
    public void eat() {
        System.out.println("Human is eating...");
    }
    
    public void sleep() {
        System.out.println("Human is sleeping...");
    }
}

// Demo
public class InterfaceSegregationExample {
    public static void main(String[] args) {
        System.out.println("=== Interface Segregation Principle (ISP) ===\n");
        
        System.out.println("❌ BAD: Fat interface forces unnecessary implementations");
        RobotBad badRobot = new RobotBad();
        badRobot.work();
        try {
            badRobot.eat();  // Throws exception!
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n✅ GOOD: Segregated interfaces - implement only what's needed");
        Robot robot = new Robot();
        robot.work();  // Only implements work()
        
        OfficeWorker worker = new OfficeWorker();
        worker.work();
        worker.eat();
        worker.sleep();
        
        Human human = new Human();
        human.work();
        human.eat();
        human.sleep();
        
        System.out.println("\n--- Key Benefits ---");
        System.out.println("✓ Classes only implement what they need");
        System.out.println("✓ No unnecessary methods or exceptions");
        System.out.println("✓ Interfaces are focused and specific");
        System.out.println("✓ Follows ISP - no forced dependencies");
    }
}
