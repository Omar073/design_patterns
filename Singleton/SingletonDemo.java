// Singleton Pattern – Single-Threaded Implementations
// All classes are package-private except the public *Demo class with main()
// This file demonstrates singleton implementations for single-threaded environments:
// 1) Eager initialization
// 2) Lazy initialization (non-synchronized - NOT thread-safe)

// 1) Eager initialization (simple, thread-safe by default, may waste memory/startup time)
class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    private EagerSingleton() {}
    public static EagerSingleton getInstance() { return INSTANCE; }
    public String name() { return "EagerSingleton"; }
}

// 2) Lazy initialization (non-synchronized - only safe for single-threaded environments)
class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton() {}
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
    public String name() { return "LazySingleton"; }
}

// Demo
public class SingletonDemo {
    public static void main(String[] args) {
        System.out.println("== Singleton Implementations (Single-Threaded) ==\n");
        
        // Demonstrate eager initialization
        System.out.println("--- Eager Initialization ---");
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        System.out.println("Instance 1: " + eager1.name());
        System.out.println("Instance 2: " + eager2.name());
        System.out.println("Same instance? " + (eager1 == eager2));
        
        // Demonstrate lazy initialization
        System.out.println("\n--- Lazy Initialization ---");
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        System.out.println("Instance 1: " + lazy1.name());
        System.out.println("Instance 2: " + lazy2.name());
        System.out.println("Same instance? " + (lazy1 == lazy2));
        
        System.out.println("\n✓ Both implementations ensure a single instance in single-threaded environments");
        System.out.println("⚠ Note: LazySingleton is NOT thread-safe. Use synchronized version for multi-threaded environments.");
    }
}
