// Singleton Pattern – Synchronized Lazy Initialization Example
// Thread-safe using synchronized keyword - locks method so only one thread can access at a time

class SynchronizedSingleton {
    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {
        System.out.println("SynchronizedSingleton instance created");
    }

    // Synchronized method - locks so only one thread can access at a time
    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }

    public String name() {
        return "SynchronizedSingleton";
    }

    public void doSomething() {
        System.out.println("Doing something with " + name());
    }
}

public class SynchronizedSingletonDemo {
    public static void main(String[] args) {
        System.out.println("== Synchronized Lazy Initialization ==\n");

        // Single-threaded demonstration
        SynchronizedSingleton sync1 = SynchronizedSingleton.getInstance();
        SynchronizedSingleton sync2 = SynchronizedSingleton.getInstance();
        System.out.println("Instance 1: " + sync1.name());
        System.out.println("Instance 2: " + sync2.name());
        System.out.println("Same instance? " + (sync1 == sync2));

        System.out.println("\n== Multi-threaded Demonstration ==\n");

        // Multi-threaded demonstration
        Thread thread1 = new Thread(() -> {
            SynchronizedSingleton instance = SynchronizedSingleton.getInstance();
            System.out.println("Thread 1: " + instance.name() + " (hash: " + instance.hashCode() + ")");
        });

        Thread thread2 = new Thread(() -> {
            SynchronizedSingleton instance = SynchronizedSingleton.getInstance();
            System.out.println("Thread 2: " + instance.name() + " (hash: " + instance.hashCode() + ")");
        });

        Thread thread3 = new Thread(() -> {
            SynchronizedSingleton instance = SynchronizedSingleton.getInstance();
            System.out.println("Thread 3: " + instance.name() + " (hash: " + instance.hashCode() + ")");
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n✓ Synchronized method ensures thread-safety");
        System.out.println("✓ Only one thread can access getInstance() at a time (locking)");
        System.out.println("✓ Lazy initialization - instance created only when first accessed");
        System.out.println("⚠ Synchronization overhead - slower than non-synchronized version");
        System.out.println("⚠ All threads must wait for the lock, even after instance is created");
    }
}

