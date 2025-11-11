# Problem 6: Computer Configuration Builder

## Problem Statement

A system must create Computer objects with various configurations (CPU, RAM, storage, graphics). The client code becomes messy when passing long argument lists.

## Design Pattern Solution: Builder

### Why Builder?

The Builder pattern is the perfect solution because:

1. **Many Optional Parameters**: Computer has many configuration options (CPU, RAM, storage, graphics, etc.)
2. **Readability**: Builder provides fluent, self-documenting code
3. **Flexibility**: Can set only the parameters needed, with sensible defaults
4. **Immutability**: Can create immutable Computer objects
5. **Validation**: Can validate configuration before building

### The Problem: Telescoping Constructors

Without Builder, you face the telescoping constructor problem:

```java
// Problem: Telescoping constructors
class Computer {
    private String cpu;
    private int ram;
    private int storage;
    private String graphics;
    private boolean hasSSD;
    private String operatingSystem;
    
    // Constructor with all parameters (messy!)
    public Computer(String cpu, int ram, int storage, String graphics, 
                    boolean hasSSD, String operatingSystem) {
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.graphics = graphics;
        this.hasSSD = hasSSD;
        this.operatingSystem = operatingSystem;
    }
    
    // Or multiple constructors for different combinations (explosion!)
    public Computer(String cpu, int ram) { /* ... */ }
    public Computer(String cpu, int ram, int storage) { /* ... */ }
    public Computer(String cpu, int ram, int storage, String graphics) { /* ... */ }
    // ... many more constructors!
}

// Usage: Hard to read, easy to make mistakes
Computer pc = new Computer("Intel i7", 16, 512, "NVIDIA RTX 3080", true, "Windows 11");
// Which parameter is which? Hard to remember order!
```

**Problems with telescoping constructors:**
- ❌ **Unreadable**: Hard to tell which parameter is which
- ❌ **Error-prone**: Easy to mix up parameter order
- ❌ **Inflexible**: Can't set only some parameters
- ❌ **Constructor explosion**: Need many constructors for different combinations
- ❌ **No defaults**: Must specify all parameters

### The Solution: Builder Pattern

The Builder pattern solves these problems by:
- ✅ **Fluent interface**: Self-documenting method names
- ✅ **Flexible**: Set only the parameters you need
- ✅ **Defaults**: Sensible defaults for optional parameters
- ✅ **Required vs Optional**: Can enforce required parameters
- ✅ **Readable**: Clear, easy-to-understand code

### Solution Overview

- **Product**: `Computer` class with all configuration fields
- **Builder**: `Computer.Builder` with fluent methods for each parameter
- **Required Parameters**: CPU and RAM (must be specified in constructor)
- **Optional Parameters**: Storage, graphics, SSD, OS (with defaults)
- **Client**: Uses builder to construct computers step-by-step

Instead of messy constructors, clients use:
```java
// Clean and readable!
Computer gamingPC = new Computer.Builder("Intel i7", 32)
    .storage(1000)
    .graphics("NVIDIA RTX 4090")
    .hasSSD(true)
    .operatingSystem("Windows 11")
    .build();
```

### Key Benefits

- ✅ Eliminates long parameter lists
- ✅ Self-documenting code
- ✅ Flexible configuration
- ✅ Can enforce required vs optional parameters
- ✅ Easy to add new configuration options
- ✅ Immutable objects (if desired)

### Comparison: Without vs With Builder

| Aspect | Without Builder | With Builder |
|--------|----------------|--------------|
| **Readability** | ❌ Hard to read | ✅ Self-documenting |
| **Flexibility** | ❌ All parameters required | ✅ Optional parameters |
| **Error-Prone** | ❌ Easy to mix up order | ✅ Method names prevent errors |
| **Maintainability** | ❌ Constructor explosion | ✅ Single builder class |
| **Defaults** | ❌ No defaults | ✅ Sensible defaults |

## Reference

For more details on the Builder pattern, see: [../../Builder/README.md](../../Builder/README.md)

