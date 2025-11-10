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

### Solution Overview

- **Product**: `Computer` class with all configuration fields
- **Builder**: `ComputerBuilder` with fluent methods for each parameter
- **Director (optional)**: Can provide pre-configured build patterns
- **Client**: Uses builder to construct computers step-by-step

Instead of `new Computer(cpu, ram, storage, graphics, ...)`, clients use:
```java
new Computer.Builder()
    .cpu("Intel i7")
    .ram(16)
    .storage(512)
    .graphics("NVIDIA RTX 3080")
    .build();
```

### Key Benefits

- Eliminates long parameter lists
- Self-documenting code
- Flexible configuration
- Can enforce required vs optional parameters
- Easy to add new configuration options

## Reference

For more details on the Builder pattern, see: [../../Builder/README.md](../../Builder/README.md)

