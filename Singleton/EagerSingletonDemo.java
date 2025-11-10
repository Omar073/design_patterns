// Singleton Pattern – Eager Initialization Example
// Simple, thread-safe by default, but may waste memory/startup time

class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public String name() {
        return "EagerSingleton";
    }
}

public class EagerSingletonDemo {
    public static void main(String[] args) {
        System.out.println("== Eager Initialization ==\n");

        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        System.out.println("Instance 1: " + eager1.name());
        System.out.println("Instance 2: " + eager2.name());
        System.out.println("Same instance? " + (eager1 == eager2));

        System.out.println("\n✓ Eager initialization ensures a single instance");
        System.out.println("✓ Thread-safe by default (instance created at class loading time)");
        System.out.println("⚠ May waste memory/startup time if instance is never used");
    }
}

