// Singleton Pattern – Lazy Initialization Example
// Non-synchronized - only safe for single-threaded environments
// Roles:
//   - Singleton class: LazySingleton with lazy static field
//   - Accessor: getInstance() instantiates on first call (not thread-safe)
//   - Client: main() shows single instance reuse

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public String name() {
        return "LazySingleton";
    }
}

public class LazySingletonDemo {
    public static void main(String[] args) {
        System.out.println("== Lazy Initialization ==\n");

        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        System.out.println("Instance 1: " + lazy1.name());
        System.out.println("Instance 2: " + lazy2.name());
        System.out.println("Same instance? " + (lazy1 == lazy2));

        System.out.println("\n✓ Lazy initialization creates instance only when needed");
        System.out.println("⚠ NOT thread-safe. Use synchronized version for multi-threaded environments.");
    }
}

