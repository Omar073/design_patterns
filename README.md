# Design Patterns in Java

A comprehensive collection of 8 essential design patterns implemented in Java, with detailed examples, explanations, and comparisons.

## Overview

This repository contains practical implementations of design patterns from the Gang of Four (GoF) book, organized into separate directories. Each pattern includes:

- **Full Java examples** with detailed inline comments explaining each component
- **Comprehensive README documentation** covering:
  - What the pattern is and when to use it
  - Advantages and disadvantages
  - Code examples with and without the pattern
  - Problem it solves and when it's needed
  - Multiple implementation variants
  - Comparisons with related patterns

## Design Patterns Covered

### Creational Patterns

1. **[Singleton](Singleton/)** - Ensures a class has only one instance and provides global access to it
2. **[Prototype](Prototype/)** - Creates new objects by cloning existing instances
3. **[Abstract Factory](AbstractFactory/)** - Creates families of related objects without specifying concrete classes
4. **[Builder](Builder/)** - Constructs complex objects step by step

### Structural Patterns

5. **[Proxy](Proxy/)** - Provides a placeholder or surrogate for another object to control access
6. **[Decorator](Decorator/)** - Dynamically adds behavior to objects without altering their structure
7. **[Adapter](Adapter/)** - Allows incompatible interfaces to work together
8. **[Facade](Facade/)** - Provides a simplified interface to a complex subsystem

## Repository Structure

```
design_patterns/
├── README.md                    # This file
├── Comparisons.md              # Cross-pattern comparisons
├── Singleton/
│   ├── SingletonDemo.java     # Multiple singleton implementations
│   └── README.md               # Singleton pattern documentation
├── Prototype/
│   ├── PrototypeDemo.java      # Shallow/deep clone + registry examples
│   └── README.md
├── AbstractFactory/
│   ├── AbstractFactoryDemo.java # GUI family example (Windows/Mac)
│   └── README.md
├── Builder/
│   ├── BuilderDemo.java        # Fluent builder + Director pattern
│   └── README.md
├── Proxy/
│   ├── ProxyDemo.java          # Protection, virtual, and logging proxies
│   └── README.md
├── Decorator/
│   ├── DecoratorDemo.java      # Beverage condiments + Java I/O analogy
│   └── README.md
├── Adapter/
│   ├── AdapterDemo.java        # Object adapter (SquarePeg → RoundHole)
│   └── README.md
└── Facade/
    ├── FacadeDemo.java         # Home theater subsystem
    └── README.md
```

## How to Use

### Running the Examples

All Java files use the default package and can be compiled and run directly:

```bash
# Navigate to a pattern directory
cd Singleton

# Compile
javac SingletonDemo.java

# Run
java SingletonDemo
```

### Example Output

Each demo includes a `main` method that demonstrates the pattern in action, showing:
- How the pattern works
- Different implementation variants
- Before/after comparisons where applicable

## Key Features

- **Multiple Implementations**: Each pattern includes various implementation approaches (e.g., Singleton has 5+ variants)
- **Real-World Examples**: Practical scenarios like college internet proxy, Starbucks drinks, home theater systems
- **Comprehensive Documentation**: Each pattern has detailed README explaining concepts, use cases, pros/cons
- **Code Comparisons**: Side-by-side examples showing code with and without the pattern
- **Pattern Relationships**: Discussions on similarities and differences between related patterns

## Pattern Comparisons

See [Comparisons.md](Comparisons.md) for detailed comparisons between:
- Facade vs Builder/Factory
- Facade vs Proxy
- Abstract Factory vs Builder
- And more...

## Contributing

This is an educational repository. Each pattern directory is self-contained with:
- Runnable Java code
- Detailed comments explaining each part
- Comprehensive markdown documentation

For contributions, feel free to contact me on omarahmed7703@gmail.com or fork the repo.

## Notes

- All Java files use the **default package** (no package declaration)
- Examples are designed to be **self-contained** and **runnable**
- Code includes **extensive inline comments** explaining design decisions
- Multiple implementation variants are included where applicable

## Quick Navigation

| Pattern | Purpose | Category |
|---------|----------|----------|
| [Singleton](Singleton/) | Single instance guarantee | Creational |
| [Prototype](Prototype/) | Clone-based object creation | Creational |
| [Abstract Factory](AbstractFactory/) | Family of related objects | Creational |
| [Builder](Builder/) | Step-by-step object construction | Creational |
| [Proxy](Proxy/) | Access control placeholder | Structural |
| [Decorator](Decorator/) | Dynamic behavior addition | Structural |
| [Adapter](Adapter/) | Interface compatibility | Structural |
| [Facade](Facade/) | Simplified subsystem interface | Structural |

---

**Happy Learning!** 🎓

For detailed information about each pattern, navigate to its respective directory and read the README.md file.
