/**
 * Problem 8: Game Enemy Character Creation
 * Solution: Prototype Pattern
 * 
 * This demo shows how Prototype avoids expensive initialization
 * by cloning pre-initialized enemy prototypes.
 *
 * Roles:
 *  - Prototype interface: Enemy with clone()
 *  - Concrete prototypes: Goblin, Orc (expensive to construct)
 *  - Client: main() clones prototypes, then customizes the copies
 */

// Prototype interface
// Note: Using copy constructor pattern instead of Object.clone() for better control
interface Enemy {
    Enemy clone();
    void attack();
    void display();
}

// Concrete prototype - Goblin
class Goblin implements Enemy {
    private String name;
    private int health;
    private int attackPower;
    
    // Expensive initialization - loading 3D models, textures, etc.
    public Goblin() {
        System.out.println("Loading Goblin 3D model...");
        System.out.println("Loading Goblin textures...");
        System.out.println("Initializing Goblin animations...");
        // Simulate expensive operations
        try {
            Thread.sleep(100); // Simulate loading time
        } catch (InterruptedException e) {}
        this.name = "Goblin";
        this.health = 50;
        this.attackPower = 10;
        System.out.println("✓ Goblin prototype initialized (expensive operation done once)");
    }
    
    // Constructor for clones
    private Goblin(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }
    
    public Enemy clone() {
        try {
            // Fast clone - no expensive initialization!
            return new Goblin(this.name, this.health, this.attackPower);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void attack() {
        System.out.println(name + " attacks with power " + attackPower);
    }
    
    public void display() {
        System.out.println(name + " [Health: " + health + ", Attack: " + attackPower + "]");
    }
    
    // Setters for customization
    public void setName(String name) { this.name = name; }
    public void setHealth(int health) { this.health = health; }
}

// Concrete prototype - Orc
class Orc implements Enemy {
    private String name;
    private int health;
    private int attackPower;
    
    public Orc() {
        System.out.println("Loading Orc 3D model...");
        System.out.println("Loading Orc textures...");
        System.out.println("Initializing Orc animations...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        this.name = "Orc";
        this.health = 100;
        this.attackPower = 20;
        System.out.println("✓ Orc prototype initialized");
    }
    
    private Orc(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }
    
    public Enemy clone() {
        try {
            return new Orc(this.name, this.health, this.attackPower);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void attack() {
        System.out.println(name + " attacks with power " + attackPower);
    }
    
    public void display() {
        System.out.println(name + " [Health: " + health + ", Attack: " + attackPower + "]");
    }
    
    public void setName(String name) { this.name = name; }
    public void setHealth(int health) { this.health = health; }
}

// Demo
public class Problem8Demo {
    public static void main(String[] args) {
        System.out.println("=== Problem 8: Game Enemy Character Creation ===\n");
        
        // Without Prototype: Creating 10 goblins would load resources 10 times!
        // new Goblin(); // Expensive!
        // new Goblin(); // Expensive again!
        // ...
        
        // With Prototype: Load once, clone many times
        System.out.println("--- Creating Prototypes (expensive, done once) ---");
        Goblin goblinPrototype = new Goblin();
        Orc orcPrototype = new Orc();
        
        System.out.println("\n--- Cloning Enemies (fast, no expensive initialization) ---");
        // Create multiple enemies by cloning
        Enemy goblin1 = goblinPrototype.clone();
        Enemy goblin2 = goblinPrototype.clone();
        Enemy goblin3 = goblinPrototype.clone();
        
        // Customize clones
        ((Goblin)goblin2).setName("Goblin Warrior");
        ((Goblin)goblin2).setHealth(75);
        ((Goblin)goblin3).setName("Goblin Scout");
        
        Enemy orc1 = orcPrototype.clone();
        Enemy orc2 = orcPrototype.clone();
        
        System.out.println("\n--- Enemy Army ---");
        goblin1.display();
        goblin2.display();
        goblin3.display();
        orc1.display();
        orc2.display();
        
        System.out.println("\n✓ Prototype pattern avoids expensive initialization!");
        System.out.println("✓ Created 5 enemies with only 2 expensive initializations!");
    }
}

