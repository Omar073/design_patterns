# Design Patterns in Java

A comprehensive collection of  essential design patterns implemented in Java, with detailed examples, explanations, and comparisons.

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
3. **[Factory](Factory/)** - Creates families of related objects without specifying concrete classes
4. **[Builder](Builder/)** - Constructs complex objects step by step

### Structural Patterns

5. **[Proxy](Proxy/)** - Provides a placeholder or surrogate for another object to control access
6. **[Decorator](Decorator/)** - Dynamically adds behavior to objects without altering their structure
7. **[Adapter](Adapter/)** - Allows incompatible interfaces to work together
8. **[Facade](Facade/)** - Provides a simplified interface to a complex subsystem
9. **[Bridge](Bridge/)** - Decouples an abstraction from its implementation so both can vary independently
10. **[Flyweight](Flyweight/)** - Reduces memory usage by sharing common object state between many objects

## Repository Structure

```
design_patterns/
├── README.md                    # This file
├── Comparisons.md              # Cross-pattern comparisons
├── Singleton/
│   ├── EagerSingletonDemo.java    # Eager initialization singleton
│   ├── LazySingletonDemo.java      # Lazy initialization singleton
│   └── README.md                   # Singleton pattern documentation
├── Prototype/
│   ├── PrototypeDirectDemo.java         # Direct cloning example
│   ├── PrototypeRegistryDemo.java       # Registry-based cloning
│   ├── PrototypeShallowDeepDemo.java    # Shallow vs deep clone
│   └── README.md
├── Factory/
│   ├── SimpleFactoryDemo.java      # Simple Factory - Notification system
│   ├── ShapeFactoryDemo.java       # Simple Factory - Shape creation
│   ├── FactoryMethodDemo.java      # Factory Method - Dialog system
│   ├── AbstractFactoryDemo.java    # Abstract Factory - GUI widgets
│   └── README.md
├── Builder/
│   ├── FluentBuilderDemo.java           # Fluent builder pattern
│   ├── DirectorBuilderDemo.java        # Director-based builder
│   ├── TelescopingConstructorDemo.java # Telescoping constructors (contrast)
│   └── README.md
├── Proxy/
│   ├── ProtectionProxyDemo.java    # Protection proxy - Internet access
│   ├── VirtualProxyDemo.java       # Virtual proxy - Lazy image loading
│   ├── LoggingProxyDemo.java       # Logging proxy - Dynamic proxy
│   └── README.md
├── Decorator/
│   ├── DecoratorDemo.java          # Beverage condiments + Java I/O analogy
│   └── README.md
├── Adapter/
│   ├── AdapterDemo.java            # Object adapter (SquarePeg → RoundHole)
│   └── README.md
├── Facade/
│   ├── WithFacadeDemo.java         # Home theater with facade
│   ├── WithoutFacadeDemo.java      # Home theater without facade
│   └── README.md
├── Bridge/
│   ├── BridgeTransportDemo.java    # Transport example (abstraction/implementation split)
│   ├── BridgeGuiApiDemo.java       # GUI/API bridge example
│   └── README.md
├── Flyweight/
│   ├── FlyweightForestDemo.java    # Forest (shared tree types) example
│   ├── FlyweightTextEditorDemo.java# Text editor (shared glyphs) example
│   └── README.md
└── testExamProblems/               # Test exam problems with pattern solutions
    ├── Problem1/                  # Factory - Enterprise Reporting
    ├── Problem2/                  # Singleton - Logging System
    ├── Problem3/                  # Factory - Notifications
    ├── Problem4/                  # Adapter - ML Data Format
    ├── Problem5/                  # Proxy - University Network
    ├── Problem6/                  # Builder - Computer Configurations
    ├── Problem7/                  # Decorator - Ice-Cream Toppings
    └── Problem8/                  # Prototype - Game Enemies
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

- **Multiple Implementations**: Each pattern includes various implementation approaches
- **Real-World Examples**: Practical scenarios like college internet proxy, Starbucks drinks, home theater systems
- **Comprehensive Documentation**: Each pattern has detailed README explaining concepts, use cases, pros/cons
- **Code Comparisons**: Side-by-side examples showing code with and without the pattern
- **Pattern Relationships**: Discussions on similarities and differences between related patterns
- **Test Exam Problems**: 8 real-world problems with pattern solutions for practice and exam preparation

## Test Exam Problems

The **[testExamProblems](testExamProblems/)** directory contains 8 real-world problems with design pattern solutions. Each problem includes:

- **Problem Statement**: Real-world scenario requiring a design pattern
- **Solution Explanation**: Why the chosen pattern is the best fit
- **Runnable Demo**: Complete Java code demonstrating the solution
- **Pattern Reference**: Links to detailed pattern documentation

### Problems Covered

1. **Problem 1**: Enterprise Reporting System → [Factory](testExamProblems/Problem1/)
2. **Problem 2**: Web-Based Logging System → [Singleton](testExamProblems/Problem2/)
3. **Problem 3**: Notification Service → [Factory](testExamProblems/Problem3/)
4. **Problem 4**: ML Data Format Compatibility → [Adapter](testExamProblems/Problem4/)
5. **Problem 5**: University Network Access Control → [Proxy](testExamProblems/Problem5/)
6. **Problem 6**: Computer Configuration Builder → [Builder](testExamProblems/Problem6/)
7. **Problem 7**: Ice-Cream Ordering System → [Decorator](testExamProblems/Problem7/)
8. **Problem 8**: Game Enemy Character Creation → [Prototype](testExamProblems/Problem8/)

Each problem demonstrates how to identify the right pattern for a given scenario and implement it effectively.

## Pattern Comparisons

See [Comparisons.md](Comparisons.md) for detailed comparisons between:
- Facade vs Builder/Factory
- Facade vs Proxy
- Factory vs Builder
- And more...

## Contributing

This is an educational repository. Each pattern directory is self-contained with:
- Runnable Java code
- Detailed comments explaining each part
- Comprehensive markdown documentation

For contributions, feel free to contact me on omarahmed7703@gmail.com or fork the repo.


## Quick Navigation

| Pattern | Purpose | Category |
|---------|----------|----------|
| [Singleton](Singleton/) | Single instance guarantee | Creational |
| [Prototype](Prototype/) | Clone-based object creation | Creational |
| [Factory](Factory/) | Family of related objects | Creational |
| [Builder](Builder/) | Step-by-step object construction | Creational |
| [Proxy](Proxy/) | Access control placeholder | Structural |
| [Decorator](Decorator/) | Dynamic behavior addition | Structural |
| [Adapter](Adapter/) | Interface compatibility | Structural |
| [Facade](Facade/) | Simplified subsystem interface | Structural |
| [Bridge](Bridge/) | Abstraction/implementation decoupling | Structural |
| [Flyweight](Flyweight/) | Shared state for memory efficiency | Structural |

---

**Happy Learning!** 🎓

For detailed information about each pattern, navigate to its respective directory and read the README.md file.
