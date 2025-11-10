// Singleton Pattern – Multiple Implementations with notes and pitfalls
// All classes are package-private except the public *Demo class with main()
// This file demonstrates:
// 1) Eager initialization
// 2) Lazy (synchronized)
// 3) Double-checked locking (DCL)
// 4) Initialization-on-demand holder
// 5) Enum-based singleton (robust against reflection/serialization)
// Along with a small concurrent access demonstration

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

// 1) Eager initialization (simple, thread-safe, may waste memory/startup time)
class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    private EagerSingleton() {}
    public static EagerSingleton getInstance() { return INSTANCE; }
    public String name() { return "EagerSingleton"; }
}

// 2) Lazy with synchronized accessor (simple but may suffer contention)
class LazySynchronizedSingleton {
    private static LazySynchronizedSingleton instance;
    private LazySynchronizedSingleton() {}
    public static synchronized LazySynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new LazySynchronizedSingleton();
        }
        return instance;
    }
    public String name() { return "LazySynchronizedSingleton"; }
}

// 3) Double-checked locking (DCL) – performant and safe since Java 5 (volatile)
class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton instance;
    private DoubleCheckedLockingSingleton() {}
    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) { // first check (no locking)
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) { // second check (with lock)
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
    public String name() { return "DoubleCheckedLockingSingleton"; }
}

// 4) Initialization-on-demand holder – lazy and thread-safe without synchronization cost
class HolderSingleton {
    private HolderSingleton() {}
    private static class Holder { static final HolderSingleton INSTANCE = new HolderSingleton(); }
    public static HolderSingleton getInstance() { return Holder.INSTANCE; }
    public String name() { return "HolderSingleton"; }
}

// 5) Enum singleton – simplest, protects against serialization and reflection attacks
enum EnumSingleton {
    INSTANCE;
    public String name() { return "EnumSingleton"; }
}

// A tiny reflection/serialization note:
// - Non-enum singletons can be broken via reflection unless the constructor defends
//   against multiple instantiation. Enum is safest.

public class SingletonDemo {
    private static void printInstances() {
        System.out.println(EagerSingleton.getInstance().name());
        System.out.println(LazySynchronizedSingleton.getInstance().name());
        System.out.println(DoubleCheckedLockingSingleton.getInstance().name());
        System.out.println(HolderSingleton.getInstance().name());
        System.out.println(EnumSingleton.INSTANCE.name());
    }

    private static void concurrentAccessDemo() throws InterruptedException {
        // Demonstrate many threads accessing Holder/DCL to show same identity
        int threads = 8;
        CountDownLatch latch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                HolderSingleton s = HolderSingleton.getInstance();
                System.out.println("Holder id=" + System.identityHashCode(s));
                latch.countDown();
            }).start();
        }
        latch.await();
    }

    private static void reflectionAttackDemo() {
        try {
            Constructor<HolderSingleton> ctor = HolderSingleton.class.getDeclaredConstructor();
            ctor.setAccessible(true);
            HolderSingleton s1 = HolderSingleton.getInstance();
            HolderSingleton s2 = ctor.newInstance();
            System.out.println("Reflection broke singleton? " + (s1 != s2));
        } catch (Exception e) {
            System.out.println("Reflection failed (good): " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("== Singleton Implementations ==");
        printInstances();
        System.out.println("\n== Concurrent Access Demo (Holder) ==");
        concurrentAccessDemo();
        System.out.println("\n== Reflection Attack Note ==");
        reflectionAttackDemo();
        System.out.println("\n== Enum is robust against both reflection and serialization ==");
    }
}


