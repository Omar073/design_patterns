## Singleton Pattern

- **Intent**: Ensure a class has only one instance and provide a global access point to it.
- **When to use**: When you need exactly one instance of a class (shared configuration, logging, device drivers, caches, thread pools).

---

## Why Use the Singleton Pattern?

### The Problem: Multiple Instances Cause Inconsistency

When multiple parts of your application need to share the same resource, creating multiple instances can lead to problems:

```java
// Problem: Multiple instances cause inconsistency
class Logger {
    private List<String> logs = new ArrayList<>();
    
    public void log(String message) {
        logs.add(message);
    }
    
    public void printLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}

// Different parts of application create their own instances
Logger logger1 = new Logger();
Logger logger2 = new Logger();

logger1.log("Error in module A");
logger2.log("Error in module B");

// Problem: Each logger has its own log list - logs are split!
logger1.printLogs(); // Only shows "Error in module A"
logger2.printLogs(); // Only shows "Error in module B"
```

**Problems with multiple instances:**
- ❌ **Inconsistent state**: Each instance has its own state
- ❌ **Resource waste**: Unnecessary object creation
- ❌ **Configuration issues**: Settings might differ between instances
- ❌ **Thread safety**: Multiple instances can cause race conditions
- ❌ **Memory overhead**: Duplicate objects consume memory

### The Solution: Singleton Pattern

The Singleton pattern ensures:
- ✅ **Single instance**: Only one instance exists throughout the application
- ✅ **Global access**: Easy access from anywhere in the application
- ✅ **Consistent state**: All parts of the application share the same state
- ✅ **Resource efficiency**: No duplicate objects
- ✅ **Controlled initialization**: Can be eager or lazy

---

## With Singleton Pattern

### Eager Initialization (Thread-Safe)

```java
class EagerSingleton {
    // Instance created at class loading time (thread-safe)
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    // Private constructor prevents instantiation
    private EagerSingleton() {
        System.out.println("EagerSingleton instance created");
    }
    
    // Global access point
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
    
    public void doSomething() {
        System.out.println("Doing something...");
    }
}

// Usage
EagerSingleton instance1 = EagerSingleton.getInstance();
EagerSingleton instance2 = EagerSingleton.getInstance();
System.out.println(instance1 == instance2); // true - same instance
```

**Benefits:**
- ✅ **Thread-safe**: Instance created at class loading (before any threads)
- ✅ **Simple**: No synchronization needed
- ✅ **Guaranteed**: Instance always exists when needed

### Lazy Initialization (Single-Threaded)

```java
class LazySingleton {
    private static LazySingleton instance;
    
    private LazySingleton() {
        System.out.println("LazySingleton instance created");
    }
    
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

// Usage - instance created only when first accessed
LazySingleton instance = LazySingleton.getInstance();
```

**Benefits:**
- ✅ **Lazy loading**: Instance created only when needed
- ✅ **Memory efficient**: No instance if never used
- ⚠️ **Not thread-safe**: Requires synchronization for multi-threaded environments

---

## Without Singleton Pattern

### Multiple Instances Problem

```java
class Logger {
    private List<String> logs = new ArrayList<>();
    
    public void log(String message) {
        logs.add(message);
    }
    
    public void printLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}

// Problem: Each module creates its own logger
class ModuleA {
    private Logger logger = new Logger(); // Instance 1
    
    public void doWork() {
        logger.log("Module A: Starting work");
    }
}

class ModuleB {
    private Logger logger = new Logger(); // Instance 2 (different!)
    
    public void doWork() {
        logger.log("Module B: Starting work");
    }
}

// Result: Logs are split across different instances
ModuleA a = new ModuleA();
ModuleB b = new ModuleB();
a.doWork();
b.doWork();
// Can't get all logs from one place - they're in different instances!
```

**Problems:**
- ❌ **Inconsistent state**: Each instance has separate state
- ❌ **No global access**: Can't access the same instance from everywhere
- ❌ **Resource waste**: Multiple instances consume memory
- ❌ **Configuration issues**: Each instance might have different settings

### Static Class Alternative

```java
// Alternative: Static class (but not a true Singleton)
class StaticLogger {
    private static List<String> logs = new ArrayList<>();
    
    public static void log(String message) {
        logs.add(message);
    }
    
    public static void printLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}

// Usage
StaticLogger.log("Message");
StaticLogger.printLogs();
```

**Problems:**
- ❌ **No inheritance**: Can't extend or implement interfaces
- ❌ **No polymorphism**: Can't use with interfaces
- ❌ **No lazy initialization**: All static members initialized at class loading
- ❌ **Less flexible**: Can't pass instance around

---

## Comparison: Singleton vs Alternatives

| Aspect | Multiple Instances | Static Class | Singleton Pattern |
|--------|-------------------|--------------|-------------------|
| **Instance Count** | ❌ Multiple | N/A (no instance) | ✅ Single |
| **Inheritance** | ✅ Yes | ❌ No | ✅ Yes |
| **Polymorphism** | ✅ Yes | ❌ No | ✅ Yes |
| **Lazy Loading** | ✅ Yes | ❌ No | ✅ Yes (lazy variant) |
| **Thread Safety** | ⚠️ Depends | ✅ Yes | ✅ Yes (properly implemented) |
| **Memory** | ❌ Multiple objects | ✅ No objects | ✅ Single object |
| **Flexibility** | ⚠️ Medium | ❌ Low | ✅ High |

---

## Pros

- ✅ **Single instance**: Guarantees only one instance exists
- ✅ **Global access**: Easy access from anywhere in the application
- ✅ **Controlled access**: Can control when and how instance is created
- ✅ **Memory efficient**: No duplicate objects
- ✅ **Consistent state**: All parts of application share same state
- ✅ **Lazy or eager**: Can choose initialization strategy
- ✅ **Thread-safe**: Properly implemented singletons are thread-safe

---

## Cons

- ❌ **Global state**: Can lead to hidden dependencies and tight coupling
- ❌ **Testing difficulties**: Hard to mock or replace in tests
- ❌ **Thread safety complexity**: Lazy initialization requires careful synchronization
- ❌ **Reflection attacks**: Can be broken using reflection (unless using enum)
- ❌ **Serialization issues**: Can create multiple instances during deserialization
- ❌ **Violates Single Responsibility**: Class manages both its purpose and instance creation
- ❌ **Hidden dependencies**: Makes dependencies less explicit

---

## Variants

### 1. Eager Initialization
- Instance created at class loading time
- Thread-safe by default
- May waste memory if never used
- Example: `EagerSingletonDemo.java`

### 2. Lazy Initialization
- Instance created on first access
- Not thread-safe (needs synchronization for multi-threaded)
- Memory efficient
- Example: `LazySingletonDemo.java`

### 3. Thread-Safe Lazy (Synchronized)
- Uses `synchronized` keyword
- Thread-safe but slower (synchronization overhead)

### 4. Double-Checked Locking
- Checks twice before synchronizing
- More efficient than fully synchronized

### 5. Bill Pugh Solution (Holder Pattern)
- Uses static inner class
- Thread-safe and lazy
- No synchronization overhead

### 6. Enum Singleton
- Most robust (resists reflection and serialization attacks)
- Thread-safe
- Recommended by Joshua Bloch

---

## When to Use Singleton Pattern

### ✅ Use Singleton When:
- You need **exactly one instance** of a class
- Instance must be **accessible globally**
- You need **shared state** across the application
- **Resource management**: Database connections, thread pools, caches
- **Configuration**: Application settings, logging
- **Hardware access**: Device drivers, printers

### ❌ Don't Use Singleton When:
- You need **multiple instances** with different configurations
- **Testing**: Makes unit testing difficult (hard to mock)
- **Dependency injection**: Prefer dependency injection frameworks
- **Simple utility classes**: Use static methods instead
- **Stateful objects**: Can lead to hidden dependencies

---

## Best Practices

1. **Prefer Dependency Injection**: Use frameworks like Spring, Guice instead of Singleton
2. **Use Enum for Robustness**: Enum singletons resist reflection and serialization attacks
3. **Make Thread-Safe**: Use proper synchronization for lazy initialization
4. **Consider Alternatives**: Static utility classes or dependency injection might be better
5. **Document Usage**: Clearly document why Singleton is needed

---

## Compare with Other Patterns

- **vs Factory**: Factory creates objects; Singleton ensures only one instance exists
- **vs Prototype**: Prototype creates copies; Singleton ensures only one instance
- **vs Dependency Injection**: DI provides instances; Singleton manages its own instance
- **vs Static Class**: Static class has no instance; Singleton has one instance

---

## File Examples

- **`EagerSingletonDemo.java`**: Eager initialization (thread-safe, created at class loading)
- **`LazySingletonDemo.java`**: Lazy initialization (created on first access, not thread-safe)

---

## Notes

- ⚠️ **Prefer dependency injection** where feasible - it's more testable and flexible
- ⚠️ **Use `enum`** to resist reflection/serialization attacks (most robust approach)
- ⚠️ **Be careful with thread safety** - lazy initialization requires synchronization
- ⚠️ **Consider alternatives** - Singleton is often overused; static classes or DI might be better
