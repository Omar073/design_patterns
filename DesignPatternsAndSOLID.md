# Design Patterns and SOLID Principles

## Overview

This document analyzes the relationship between design patterns and SOLID principles, examining which patterns follow these principles and which patterns might violate them. Understanding this relationship helps in making informed decisions about when and how to use design patterns.

---

## Table of Contents

- [Patterns That Follow SOLID Principles](#patterns-that-follow-solid-principles)
  - [Single Responsibility Principle (SRP)](#single-responsibility-principle-srp)
  - [Open/Closed Principle (OCP)](#openclosed-principle-ocp)
  - [Liskov Substitution Principle (LSP)](#liskov-substitution-principle-lsp)
  - [Interface Segregation Principle (ISP)](#interface-segregation-principle-isp)
  - [Dependency Inversion Principle (DIP)](#dependency-inversion-principle-dip)
- [Patterns That May Violate SOLID Principles](#patterns-that-may-violate-solid-principles)
- [Context-Dependent Cases](#context-dependent-cases)
- [Summary Table](#summary-table)
- [Key Takeaways](#key-takeaways)

---

## Patterns That Follow SOLID Principles

### Single Responsibility Principle (SRP)

**Principle**: A class should have only one reason to change.

#### ✅ Patterns That Follow SRP

1. **Command Pattern**
   - Each command class has a single responsibility: encapsulating a specific request
   - Separates the request from the object that invokes it
   - Example: `TurnOnCommand`, `TurnOffCommand` each handle one action

2. **Strategy Pattern**
   - Each strategy class implements one algorithm
   - Context class delegates to strategies without knowing implementation details
   - Example: `QuickSort`, `MergeSort` each handle one sorting algorithm

3. **Observer Pattern**
   - Subject manages state and notifications (one responsibility)
   - Observers handle updates (one responsibility)
   - Clear separation of concerns

4. **Builder Pattern**
   - Separates object construction from representation
   - Builder focuses solely on construction logic
   - Product class focuses on data representation

5. **Factory Method Pattern**
   - Creator handles object creation logic
   - Product handles business logic
   - Clear separation of responsibilities

---

### Open/Closed Principle (OCP)

**Principle**: Open for extension, closed for modification.

#### ✅ Patterns That Follow OCP

1. **Strategy Pattern** ⭐ **Excellent Example**
   - Can add new algorithms without modifying existing code
   - Just create a new strategy class implementing the interface
   - Existing code remains unchanged

2. **Decorator Pattern** ⭐ **Excellent Example**
   - Can add new behaviors dynamically without modifying existing classes
   - Wraps objects to add functionality
   - Original classes remain untouched

3. **Factory Method Pattern**
   - Can add new product types by creating new creators
   - No need to modify existing factory code
   - Extends through inheritance

4. **Abstract Factory Pattern**
   - Can add new product families without modifying existing factories
   - New factories implement the abstract factory interface

5. **Template Method Pattern**
   - Defines algorithm skeleton, subclasses override steps
   - Can extend behavior by creating new subclasses

6. **Chain of Responsibility Pattern**
   - Can add new handlers without modifying existing chain
   - Just add new handler to the chain

---

### Liskov Substitution Principle (LSP)

**Principle**: Subtypes must be substitutable for their base types.

#### ✅ Patterns That Follow LSP

1. **Strategy Pattern**
   - All strategies are substitutable through the common interface
   - Any strategy can replace another without breaking functionality

2. **Factory Method Pattern**
   - All concrete products are substitutable for the abstract product
   - Client code works with abstract product type

3. **Abstract Factory Pattern**
   - All concrete factories are substitutable
   - Products from different factories are substitutable

4. **Template Method Pattern**
   - Subclasses must maintain the contract defined by the template
   - All subclasses are substitutable

5. **Composite Pattern**
   - Leaf and Composite nodes are substitutable
   - Client treats them uniformly through the Component interface

---

### Interface Segregation Principle (ISP)

**Principle**: Clients should not be forced to depend on interfaces they don't use.

#### ✅ Patterns That Follow ISP

1. **Adapter Pattern**
   - Creates focused interfaces for clients
   - Adapts incompatible interfaces to what clients need
   - Clients only depend on what they use

2. **Facade Pattern**
   - Provides a simplified, focused interface
   - Hides complex subsystem interfaces
   - Clients only see what they need

3. **Command Pattern**
   - Each command interface is focused and specific
   - Commands don't force clients to implement unused methods

4. **Observer Pattern**
   - Observer interface is minimal and focused
   - Only defines `update()` method that observers need

---

### Dependency Inversion Principle (DIP)

**Principle**: Depend on abstractions, not concretions.

#### ✅ Patterns That Follow DIP

1. **Factory Method Pattern** ⭐ **Excellent Example**
   - Clients depend on abstract product, not concrete products
   - Creator depends on abstract product interface

2. **Abstract Factory Pattern** ⭐ **Excellent Example**
   - Clients depend on abstract factory interface
   - Products depend on abstract interfaces

3. **Strategy Pattern** ⭐ **Excellent Example**
   - Context depends on strategy interface, not concrete strategies
   - High-level modules depend on abstractions

4. **Observer Pattern**
   - Subject depends on Observer interface
   - Not tied to concrete observer implementations

5. **Command Pattern**
   - Invoker depends on Command interface
   - Not tied to specific command implementations

6. **Bridge Pattern**
   - Abstraction depends on Implementation interface
   - Decouples abstraction from implementation

7. **Proxy Pattern**
   - Client depends on Subject interface
   - Proxy and RealSubject both implement the same interface

---

## Patterns That May Violate SOLID Principles

### ⚠️ Singleton Pattern

#### Violates Multiple Principles

**1. Single Responsibility Principle (SRP)**
- **Violation**: Singleton class has two responsibilities:
  - Managing its own instance (creation and access control)
  - Business logic (the actual functionality)
- **Example**: A `DatabaseConnection` singleton both manages the single instance AND handles database operations

**2. Dependency Inversion Principle (DIP)**
- **Violation**: Creates tight coupling to concrete classes
- **Problem**: Hard to test because you can't easily inject mocks or test doubles
- **Example**: Code directly depends on `Database.getInstance()` instead of an interface

**3. Open/Closed Principle (OCP)**
- **Violation**: Difficult to extend or modify behavior
- **Problem**: Can't easily create variations or test doubles
- **Example**: Can't create a "test database" singleton alongside the real one

**Mitigation Strategies**:
- Use dependency injection instead of direct singleton access
- Make singleton implement an interface
- Use lazy initialization with proper thread safety

---

### ⚠️ Facade Pattern

#### Potential Violations

**1. Single Responsibility Principle (SRP)**
- **Risk**: Facade can become a "God Object" if it grows too large
- **Problem**: If facade handles too many subsystems, it violates SRP
- **Example**: A `SystemFacade` that handles database, networking, file I/O, and UI

**Mitigation**: Keep facade focused on providing a simple interface to related subsystems

---

### ⚠️ Adapter Pattern

#### Potential Violations

**1. Open/Closed Principle (OCP)**
- **Risk**: If adapter modifies existing code instead of wrapping it
- **Problem**: Modifying existing classes violates OCP
- **Example**: Changing the `Adaptee` class directly instead of creating an adapter

**Mitigation**: Always wrap, never modify existing classes

---

## Context-Dependent Cases

### Factory Pattern and OCP

**Question**: Does Factory Pattern break Open/Closed Principle?

**Answer**: It depends on the implementation:

#### ✅ Factory Method Pattern - **Follows OCP**
- Can add new products by creating new creators
- No modification to existing code required
- Extends through inheritance

```java
// ✅ GOOD - Follows OCP
abstract class Creator {
    abstract Product createProduct();
}

class ConcreteCreator1 extends Creator {
    Product createProduct() { return new ConcreteProduct1(); }
}

// Adding new product - NO MODIFICATION needed
class ConcreteCreator2 extends Creator {
    Product createProduct() { return new ConcreteProduct2(); }
}
```

#### ⚠️ Simple Factory - **May Violate OCP**
- If implemented with `if-else` or `switch` statements
- Adding new types requires modifying the factory method
- Violates OCP if factory class must be changed

```java
// ⚠️ MAY VIOLATE OCP - Requires modification
class SimpleFactory {
    Product createProduct(String type) {
        if (type.equals("A")) return new ProductA();
        if (type.equals("B")) return new ProductB();
        // Adding ProductC requires modifying this method!
        return null;
    }
}
```

#### ✅ Abstract Factory - **Follows OCP**
- Can add new product families without modifying existing code
- New factories implement the abstract factory interface

**Conclusion**: Factory Method and Abstract Factory follow OCP, but Simple Factory implementations can violate it if not designed carefully.

---

### Builder Pattern

#### ✅ Follows SRP
- Separates construction from representation
- Builder handles construction logic
- Product handles data

#### ⚠️ May Violate OCP (in some implementations)
- If builder has hardcoded construction steps
- Adding new construction variations might require modification
- **Mitigation**: Use fluent interface and allow optional steps

---

### Decorator Pattern

#### ✅ Follows OCP
- Can add new decorators without modifying existing code
- Original classes remain unchanged

#### ⚠️ May Violate ISP (in some cases)
- If decorator interface is too large
- Decorators might be forced to implement unused methods
- **Mitigation**: Keep decorator interface focused

---

## Summary Table

| Pattern | SRP | OCP | LSP | ISP | DIP | Notes |
|---------|-----|-----|-----|-----|-----|-------|
| **Singleton** | ❌ | ⚠️ | ✅ | ✅ | ❌ | Violates SRP and DIP |
| **Factory Method** | ✅ | ✅ | ✅ | ✅ | ✅ | Excellent SOLID compliance |
| **Abstract Factory** | ✅ | ✅ | ✅ | ✅ | ✅ | Excellent SOLID compliance |
| **Simple Factory** | ✅ | ⚠️ | ✅ | ✅ | ⚠️ | May violate OCP if not careful |
| **Builder** | ✅ | ✅ | ✅ | ✅ | ✅ | Good SOLID compliance |
| **Prototype** | ✅ | ✅ | ✅ | ✅ | ✅ | Good SOLID compliance |
| **Adapter** | ✅ | ⚠️ | ✅ | ✅ | ✅ | Violates OCP if modifies code |
| **Bridge** | ✅ | ✅ | ✅ | ✅ | ✅ | Excellent SOLID compliance |
| **Composite** | ✅ | ✅ | ✅ | ✅ | ✅ | Good SOLID compliance |
| **Decorator** | ✅ | ✅ | ✅ | ⚠️ | ✅ | May violate ISP if interface too large |
| **Facade** | ⚠️ | ✅ | ✅ | ✅ | ✅ | May violate SRP if too large |
| **Flyweight** | ✅ | ✅ | ✅ | ✅ | ✅ | Good SOLID compliance |
| **Proxy** | ✅ | ✅ | ✅ | ✅ | ✅ | Excellent SOLID compliance |
| **Strategy** | ✅ | ✅ | ✅ | ✅ | ✅ | **Perfect SOLID example** |
| **Observer** | ✅ | ✅ | ✅ | ✅ | ✅ | Excellent SOLID compliance |
| **Command** | ✅ | ✅ | ✅ | ✅ | ✅ | Excellent SOLID compliance |
| **Chain of Responsibility** | ✅ | ✅ | ✅ | ✅ | ✅ | Good SOLID compliance |
| **Iterator** | ✅ | ✅ | ✅ | ✅ | ✅ | Good SOLID compliance |
| **Mediator** | ⚠️ | ✅ | ✅ | ✅ | ✅ | May violate SRP if too complex |
| **Memento** | ✅ | ✅ | ✅ | ✅ | ✅ | Good SOLID compliance |
| **State** | ✅ | ✅ | ✅ | ✅ | ✅ | Good SOLID compliance |
| **Template Method** | ✅ | ✅ | ✅ | ✅ | ✅ | Good SOLID compliance |

**Legend**:
- ✅ Follows the principle
- ⚠️ May violate depending on implementation
- ❌ Typically violates the principle

---

## Key Takeaways

### 1. **Most Patterns Follow SOLID Principles**
- Design patterns were created to solve common problems while maintaining good design
- Most patterns naturally align with SOLID principles
- **Strategy Pattern** is often cited as the "perfect" example of SOLID principles

### 2. **Implementation Matters**
- Some patterns can be implemented in ways that violate SOLID
- Simple Factory vs Factory Method: implementation choice affects OCP compliance
- Always consider the implementation, not just the pattern name

### 3. **Singleton is the Exception**
- Singleton pattern is known for violating multiple SOLID principles
- Consider alternatives: Dependency Injection, Factory patterns
- If using Singleton, mitigate violations through interfaces and dependency injection

### 4. **Context is Important**
- A pattern might follow SOLID in one context but violate it in another
- Facade can violate SRP if it becomes too large
- Adapter violates OCP if it modifies existing code instead of wrapping

### 5. **Patterns Support Each Other**
- Combining patterns often strengthens SOLID compliance
- Example: Factory + Strategy = Strong OCP and DIP compliance
- Example: Dependency Injection + Singleton = Better DIP compliance

### 6. **SOLID Principles Guide Pattern Selection**
- If you need OCP compliance → Consider Strategy, Decorator, Factory Method
- If you need DIP compliance → Consider Factory, Abstract Factory, Strategy
- If you need SRP compliance → Consider Command, Strategy, Observer

---

## Best Practices

1. **Prefer Patterns That Follow SOLID**
   - Strategy, Factory Method, Abstract Factory, Command, Observer
   - These patterns naturally support good design principles

2. **Be Careful with Singleton**
   - Consider alternatives first
   - Use dependency injection when possible
   - Make singleton implement an interface

3. **Watch Implementation Details**
   - Simple Factory can violate OCP if not careful
   - Adapter should wrap, not modify
   - Facade should stay focused

4. **Combine Patterns for Better SOLID Compliance**
   - Factory + Strategy = Strong OCP and DIP
   - Dependency Injection + Any Pattern = Better DIP
   - Interface Segregation + Adapter = Better ISP

5. **Refactor When Needed**
   - If a pattern implementation violates SOLID, refactor it
   - Patterns are tools, not rules
   - SOLID principles are more fundamental than patterns

---

## Related Topics

- [SOLID Principles](OtherTopics/SOLID/README.md) - Detailed explanation of each principle
- [Design Patterns](../../README.md) - Overview of all patterns
- [Anti-Patterns](OtherTopics/AntiPatterns/README.md) - What to avoid

---

**Remember**: Design patterns are implementations of SOLID principles. Understanding SOLID helps you understand why patterns work and when to use them. When in doubt, prioritize SOLID principles over strict pattern adherence.
