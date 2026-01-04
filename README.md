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

1. **[Singleton](Creational/Singleton/)** - Ensures a class has only one instance and provides global access to it
2. **[Prototype](Creational/Prototype/)** - Creates new objects by cloning existing instances
3. **[Factory](Creational/Factory/)** - Creates families of related objects without specifying concrete classes
4. **[Builder](Creational/Builder/)** - Constructs complex objects step by step

### Structural Patterns

5. **[Adapter](Structural/Adapter/)** - Allows incompatible interfaces to work together
6. **[Bridge](Structural/Bridge/)** - Decouples an abstraction from its implementation so both can vary independently
7. **[Composite](Structural/Composite/)** - Composes objects into tree structures to represent part-whole hierarchies
8. **[Decorator](Structural/Decorator/)** - Dynamically adds behavior to objects without altering their structure
9. **[Facade](Structural/Facade/)** - Provides a simplified interface to a complex subsystem
10. **[Flyweight](Structural/Flyweight/)** - Reduces memory usage by sharing common object state between many objects
11. **[Proxy](Structural/Proxy/)** - Provides a placeholder or surrogate for another object to control access

### Behavioral Patterns

12. **[Strategy](Behavioral/Startegy/)** - Encapsulates interchangeable algorithms or behaviors and lets you swap them at runtime
13. **[Chain of Responsibility](Behavioral/ChainOfResponsibility/)** - Passes a request along a chain of handlers until one handles it
14. **[Command](Behavioral/Command/)** - Encapsulates requests as objects, allowing parameterization, queuing, logging, and undo/redo functionality
15. **[Iterator](Behavioral/Iterator/)** - Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation
16. **[Mediator](Behavioral/Mediator/)** - Reduces communication complexity between multiple objects by centralizing their interactions through a mediator
17. **[Memento](Behavioral/Memento/)** - Captures and externalizes an object's internal state so it can be restored later, enabling undo/redo functionality
18. **[Observer](Behavioral/Observer/)** - Defines a one-to-many dependency so that when one object changes state, all its dependents are notified and updated automatically
19. **[State](Behavioral/State/)** - Allows an object to alter its behavior when its internal state changes, eliminating large conditional statements

## Repository Structure

```
design_patterns/
├── README.md                    # This file
├── Comparisons.md              # Cross-pattern comparisons
├── PatternsQuickRevision.md    # Quick revision summary
├── Prompt.md                   # Original prompt and context
├── Creational/                 # Creational Design Patterns
│   ├── Singleton/
│   │   ├── EagerSingletonDemo.java    # Eager initialization singleton
│   │   ├── LazySingletonDemo.java      # Lazy initialization singleton
│   │   └── README.md                   # Singleton pattern documentation
│   ├── Prototype/
│   │   ├── PrototypeDirectDemo.java         # Direct cloning example
│   │   ├── PrototypeRegistryDemo.java       # Registry-based cloning
│   │   ├── PrototypeShallowDeepDemo.java    # Shallow vs deep clone
│   │   └── README.md
│   ├── Factory/
│   │   ├── SimpleFactoryDemo.java      # Simple Factory - Notification system
│   │   ├── ShapeFactoryDemo.java       # Simple Factory - Shape creation
│   │   ├── FactoryMethodDemo.java      # Factory Method - Dialog system
│   │   ├── AbstractFactoryDemo.java    # Abstract Factory - GUI widgets
│   │   └── README.md
│   └── Builder/
│       ├── CarBuilderDemo.java           # Fluent builder pattern
│       ├── DirectorBuilderDemo.java        # Director-based builder
│       ├── TelescopingConstructorDemo.java # Telescoping constructors (contrast)
│       └── README.md
├── Structural/                 # Structural Design Patterns
│   ├── Adapter/
│   │   ├── AdapterDemo.java            # Object adapter (SquarePeg → RoundHole)
│   │   └── README.md
│   ├── Bridge/
│   │   ├── BridgeTransportDemo.java    # Transport example (abstraction/implementation split)
│   │   ├── BridgeGuiApiDemo.java       # GUI/API bridge example
│   │   └── README.md
│   ├── Composite/              # Composite pattern (if exists)
│   ├── Decorator/
│   │   ├── DecoratorDemo.java          # Beverage condiments + Java I/O analogy
│   │   └── README.md
│   ├── Facade/
│   │   ├── WithFacadeDemo.java         # Home theater with facade
│   │   ├── WithoutFacadeDemo.java      # Home theater without facade
│   │   └── README.md
│   ├── Flyweight/
│   │   ├── FlyweightForestDemo.java    # Forest (shared tree types) example
│   │   ├── FlyweightTextEditorDemo.java# Text editor (shared glyphs) example
│   │   └── README.md
│   └── Proxy/
│       ├── ProtectionProxyDemo.java    # Protection proxy - Internet access
│       ├── VirtualProxyDemo.java       # Virtual proxy - Lazy image loading
│       ├── LoggingProxyDemo.java       # Logging proxy - Dynamic proxy
│       └── README.md
├── Behavioral/                 # Behavioral Design Patterns
│   ├── Startegy/
│   │   ├── StrategyDuckDemo.java       # Duck behaviors using Strategy pattern
│   │   ├── StrategyEncryptionDemo.java # Encryption algorithms as strategies
│   │   └── README.md                   # Strategy pattern documentation and diagrams
│   ├── ChainOfResponsibility/
│   │   ├── ChainOfResponsibilityEmailDemo.java    # Email processing chain
│   │   ├── ChainOfResponsibilityApprovalDemo.java # Purchase approval chain
│   │   └── README.md                              # Chain of Responsibility documentation
│   ├── Command/
│   │   ├── CommandPatternDemo.java                # Basic Command pattern - Smart home remote control
│   │   ├── CommandUndoDemo.java                   # Command with undo/redo support
│   │   ├── CommandMacroDemo.java                  # Macro commands (composite commands)
│   │   ├── CommandQueueDemo.java                  # Command queue for batch processing
│   │   ├── diagram1.png                           # General Command pattern structure
│   │   ├── diagram2.png                           # Command pattern implementation diagram
│   │   └── README.md                              # Command pattern documentation
│   ├── Iterator/
│   │   ├── IteratorPatternDemo.java               # Iterator pattern - Book collection traversal
│   │   └── README.md                              # Iterator pattern documentation
│   ├── Mediator/
│   │   ├── MediatorPatternDemo.java               # Mediator pattern - Smart home automation system
│   │   ├── MediatorAirplaneDemo.java              # Mediator pattern - Air traffic control system
│   │   ├── diagram1.png                           # General Mediator pattern structure
│   │   ├── diagram2.png                           # Air Traffic Control example diagram
│   │   └── README.md                              # Mediator pattern documentation
│   ├── Memento/
│   │   ├── MementoPatternDemo.java                # Memento pattern - Text editor with undo/redo
│   │   └── README.md                              # Memento pattern documentation
│   ├── Observer/
│   │   ├── ObserverPatternDemo.java               # Observer pattern - Weather station with display devices
│   │   └── README.md                              # Observer pattern documentation
│   └── State/
│       ├── StatePatternDemo.java                  # State pattern - Vending machine with state transitions
│       └── README.md                              # State pattern documentation
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

1. **Problem 1**: Enterprise Reporting System → [Factory](testExamProblems/Problem1/) → [Factory Pattern](Creational/Factory/)
2. **Problem 2**: Web-Based Logging System → [Singleton](testExamProblems/Problem2/) → [Singleton Pattern](Creational/Singleton/)
3. **Problem 3**: Notification Service → [Factory](testExamProblems/Problem3/) → [Factory Pattern](Creational/Factory/)
4. **Problem 4**: ML Data Format Compatibility → [Adapter](testExamProblems/Problem4/) → [Adapter Pattern](Structural/Adapter/)
5. **Problem 5**: University Network Access Control → [Proxy](testExamProblems/Problem5/) → [Proxy Pattern](Structural/Proxy/)
6. **Problem 6**: Computer Configuration Builder → [Builder](testExamProblems/Problem6/) → [Builder Pattern](Creational/Builder/)
7. **Problem 7**: Ice-Cream Ordering System → [Decorator](testExamProblems/Problem7/) → [Decorator Pattern](Structural/Decorator/)
8. **Problem 8**: Game Enemy Character Creation → [Prototype](testExamProblems/Problem8/) → [Prototype Pattern](Creational/Prototype/)

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
| [Singleton](Creational/Singleton/) | Single instance guarantee | Creational |
| [Prototype](Creational/Prototype/) | Clone-based object creation | Creational |
| [Factory](Creational/Factory/) | Family of related objects | Creational |
| [Builder](Creational/Builder/) | Step-by-step object construction | Creational |
| [Adapter](Structural/Adapter/) | Interface compatibility | Structural |
| [Bridge](Structural/Bridge/) | Abstraction/implementation decoupling | Structural |
| [Composite](Structural/Composite/) | Tree structure composition | Structural |
| [Decorator](Structural/Decorator/) | Dynamic behavior addition | Structural |
| [Facade](Structural/Facade/) | Simplified subsystem interface | Structural |
| [Flyweight](Structural/Flyweight/) | Shared state for memory efficiency | Structural |
| [Proxy](Structural/Proxy/) | Access control placeholder | Structural |
| [Strategy](Behavioral/Startegy/) | Pluggable algorithms/behaviors | Behavioral |
| [Chain of Responsibility](Behavioral/ChainOfResponsibility/) | Request handling chain | Behavioral |
| [Command](Behavioral/Command/) | Request/operation encapsulation | Behavioral |
| [Iterator](Behavioral/Iterator/) | Sequential collection traversal | Behavioral |
| [Mediator](Behavioral/Mediator/) | Centralized object communication | Behavioral |
| [Memento](Behavioral/Memento/) | State snapshot for undo/redo | Behavioral |
| [Observer](Behavioral/Observer/) | One-to-many state notification | Behavioral |
| [State](Behavioral/State/) | Behavior changes with object state | Behavioral |

---

**Happy Learning!** 🎓

For detailed information about each pattern, navigate to its respective directory and read the README.md file.
