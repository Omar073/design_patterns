/**
 * Problem 6: Computer Configuration Builder
 * Solution: Builder Pattern
 * 
 * This demo shows how Builder eliminates messy long parameter lists
 * when creating Computer objects with many configuration options.
 */

// Product class
class Computer {
    private String cpu;
    private int ram; // GB
    private int storage; // GB
    private String graphics;
    private boolean hasSSD;
    private String operatingSystem;
    
    // Private constructor - only Builder can create
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphics = builder.graphics;
        this.hasSSD = builder.hasSSD;
        this.operatingSystem = builder.operatingSystem;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Computer [CPU: %s, RAM: %dGB, Storage: %dGB, Graphics: %s, SSD: %s, OS: %s]",
            cpu, ram, storage, graphics, hasSSD, operatingSystem
        );
    }
    
    // Builder class
    public static class Builder {
        // Required parameters
        private String cpu;
        private int ram;
        
        // Optional parameters with defaults
        private int storage = 256;
        private String graphics = "Integrated";
        private boolean hasSSD = false;
        private String operatingSystem = "Windows 10";
        
        // Constructor for required parameters
        public Builder(String cpu, int ram) {
            this.cpu = cpu;
            this.ram = ram;
        }
        
        // Fluent setter methods
        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder graphics(String graphics) {
            this.graphics = graphics;
            return this;
        }
        
        public Builder hasSSD(boolean hasSSD) {
            this.hasSSD = hasSSD;
            return this;
        }
        
        public Builder operatingSystem(String os) {
            this.operatingSystem = os;
            return this;
        }
        
        // Build method
        public Computer build() {
            return new Computer(this);
        }
    }
}

// Demo
public class Problem6Demo {
    public static void main(String[] args) {
        System.out.println("=== Problem 6: Computer Configuration Builder ===\n");
        
        // Without Builder (messy - imagine 10+ parameters!)
        // Computer pc1 = new Computer("Intel i7", 16, 512, "NVIDIA RTX 3080", true, "Windows 11");
        
        // With Builder - clean and readable!
        System.out.println("--- Basic Computer ---");
        Computer basicPC = new Computer.Builder("Intel i5", 8)
            .build();
        System.out.println(basicPC);
        
        System.out.println("\n--- Gaming Computer ---");
        Computer gamingPC = new Computer.Builder("Intel i7", 32)
            .storage(1000)
            .graphics("NVIDIA RTX 4090")
            .hasSSD(true)
            .operatingSystem("Windows 11")
            .build();
        System.out.println(gamingPC);
        
        System.out.println("\n--- Workstation Computer ---");
        Computer workstation = new Computer.Builder("AMD Ryzen 9", 64)
            .storage(2000)
            .graphics("NVIDIA Quadro RTX 5000")
            .hasSSD(true)
            .operatingSystem("Linux Ubuntu")
            .build();
        System.out.println(workstation);
        
        System.out.println("\n✓ Builder makes configuration clean and flexible!");
    }
}

