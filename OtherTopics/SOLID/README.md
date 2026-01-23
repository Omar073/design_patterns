# SOLID Principles

## What are SOLID Principles?

**SOLID** is an acronym for five object-oriented design principles that help create maintainable, flexible, and scalable software:

- **S** - Single Responsibility Principle
- **O** - Open/Closed Principle
- **L** - Liskov Substitution Principle
- **I** - Interface Segregation Principle
- **D** - Dependency Inversion Principle

These principles were introduced by Robert C. Martin (Uncle Bob) and are fundamental to writing clean, maintainable code.

---

## Table of Contents

- [Single Responsibility Principle (SRP)](#single-responsibility-principle-srp)
- [Open/Closed Principle (OCP)](#openclosed-principle-ocp)
- [Liskov Substitution Principle (LSP)](#liskov-substitution-principle-lsp)
- [Interface Segregation Principle (ISP)](#interface-segregation-principle-isp)
- [Dependency Inversion Principle (DIP)](#dependency-inversion-principle-dip)
- [SOLID Principles Summary](#solid-principles-summary)
- [Benefits of SOLID Principles](#benefits-of-solid-principles)
- [Relationship with Design Patterns](#relationship-with-design-patterns)

---

## Single Responsibility Principle (SRP)

**Definition**: A class should have only one reason to change. In other words, a class should have only one responsibility or job.

**Key Points**:
- Each class should have a single, well-defined purpose
- If a class has multiple responsibilities, it becomes harder to maintain
- Changes to one responsibility may affect others unnecessarily

### Example: Violating SRP

**❌ BAD - Multiple Responsibilities**

```java
class Employee {
    private String name;
    private String position;
    
    // Responsibility 1: Employee data management
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    // Responsibility 2: Database operations
    public void saveToDatabase() {
        // Database save logic
        System.out.println("Saving " + name + " to database...");
    }
    
    // Responsibility 3: Report generation
    public void generateReport() {
        // Report generation logic
        System.out.println("Generating report for " + name + "...");
    }
    
    // Responsibility 4: Email sending
    public void sendEmail(String message) {
        // Email sending logic
        System.out.println("Sending email to " + name + "...");
    }
}
```

**Problems**:
- Employee class handles data, database, reports, and emails
- Changes to database logic affect the Employee class
- Changes to email logic affect the Employee class
- Hard to test each responsibility independently

### Example: Following SRP

**✅ GOOD - Single Responsibility**

```java
// Responsibility 1: Employee data only
class Employee {
    private String name;
    private String position;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

// Responsibility 2: Database operations
class EmployeeRepository {
    public void save(Employee employee) {
        System.out.println("Saving " + employee.getName() + " to database...");
    }
}

// Responsibility 3: Report generation
class EmployeeReportGenerator {
    public void generateReport(Employee employee) {
        System.out.println("Generating report for " + employee.getName() + "...");
    }
}

// Responsibility 4: Email operations
class EmailService {
    public void sendEmail(Employee employee, String message) {
        System.out.println("Sending email to " + employee.getName() + "...");
    }
}
```

**Benefits**:
- Each class has a single, clear responsibility
- Changes to database logic don't affect Employee class
- Changes to email logic don't affect Employee class
- Easy to test each class independently

---

## Open/Closed Principle (OCP)

**Definition**: Software entities (classes, modules, functions) should be open for extension but closed for modification.

**Key Points**:
- You should be able to add new functionality without changing existing code
- Use abstraction (interfaces/abstract classes) to achieve this
- Extend behavior through inheritance or composition

### Example: Violating OCP

**❌ BAD - Modifying Existing Code**

```java
class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            return rect.width * rect.height;
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.radius * circle.radius;
        }
        // To add Triangle, we must modify this class!
        return 0;
    }
}
```

**Problems**:
- Must modify `AreaCalculator` to add new shapes
- Violates OCP - not closed for modification
- Risk of breaking existing functionality

### Example: Following OCP

**✅ GOOD - Open for Extension**

```java
// Abstract base class
abstract class Shape {
    public abstract double calculateArea();
}

class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Can add Triangle without modifying AreaCalculator
class Triangle extends Shape {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();  // No modification needed!
    }
}
```

**Benefits**:
- Can add new shapes without modifying `AreaCalculator`
- Existing code remains unchanged
- Follows OCP - open for extension, closed for modification

---

## Liskov Substitution Principle (LSP)

**Definition**: Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.

**Key Points**:
- Subclasses should be substitutable for their base classes
- Subclasses should not weaken the base class contract
- Subclasses should not throw exceptions that base class doesn't throw

### Example: Violating LSP

**❌ BAD - Subclass Changes Behavior**

```java
class Rectangle {
    protected int width;
    protected int height;
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getArea() {
        return width * height;
    }
}

// Square violates LSP - changes expected behavior
class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;  // Changes height too!
    }
    
    @Override
    public void setHeight(int height) {
        this.width = height;  // Changes width too!
        this.height = height;
    }
}

// This breaks when Square is substituted for Rectangle
void testRectangle(Rectangle rect) {
    rect.setWidth(5);
    rect.setHeight(4);
    // Expects 20, but Square returns 16!
    assert rect.getArea() == 20;  // Fails for Square!
}
```

**Problems**:
- Square changes the expected behavior of Rectangle
- Code that works with Rectangle breaks with Square
- Violates LSP - not substitutable

### Example: Following LSP

**✅ GOOD - Proper Substitution**

```java
// Base class
abstract class Shape {
    public abstract int getArea();
}

class Rectangle extends Shape {
    private int width;
    private int height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public int getArea() {
        return width * height;
    }
}

class Square extends Shape {
    private int side;
    
    public Square(int side) {
        this.side = side;
    }
    
    public void setSide(int side) {
        this.side = side;
    }
    
    @Override
    public int getArea() {
        return side * side;
    }
}

// Both can be used interchangeably through Shape interface
void testShape(Shape shape) {
    int area = shape.getArea();  // Works for both Rectangle and Square
}
```

**Benefits**:
- Subclasses maintain expected behavior
- Can substitute subclasses without breaking code
- Follows LSP - substitutable

---

## Interface Segregation Principle (ISP)

**Definition**: Clients should not be forced to depend on interfaces they do not use.

**Key Points**:
- Create specific interfaces instead of one general-purpose interface
- Classes should not be forced to implement methods they don't need
- Split large interfaces into smaller, focused ones

### Example: Violating ISP

**❌ BAD - Fat Interface**

```java
interface Worker {
    void work();
    void eat();
    void sleep();
}

// OfficeWorker needs all methods
class OfficeWorker implements Worker {
    public void work() { System.out.println("Working..."); }
    public void eat() { System.out.println("Eating..."); }
    public void sleep() { System.out.println("Sleeping..."); }
}

// Robot only needs work(), but forced to implement eat() and sleep()
class Robot implements Worker {
    public void work() { System.out.println("Working..."); }
    public void eat() { 
        throw new UnsupportedOperationException("Robots don't eat!");
    }
    public void sleep() { 
        throw new UnsupportedOperationException("Robots don't sleep!");
    }
}
```

**Problems**:
- Robot forced to implement methods it doesn't need
- Throws exceptions for methods that don't make sense
- Violates ISP - interface too large

### Example: Following ISP

**✅ GOOD - Segregated Interfaces**

```java
// Segregated interfaces
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

// OfficeWorker implements all interfaces it needs
class OfficeWorker implements Workable, Eatable, Sleepable {
    public void work() { System.out.println("Working..."); }
    public void eat() { System.out.println("Eating..."); }
    public void sleep() { System.out.println("Sleeping..."); }
}

// Robot only implements what it needs
class Robot implements Workable {
    public void work() { System.out.println("Working..."); }
    // No need to implement eat() or sleep()
}
```

**Benefits**:
- Classes only implement what they need
- No unnecessary methods
- Follows ISP - interfaces are focused

---

## Dependency Inversion Principle (DIP)

**Definition**: High-level modules should not depend on low-level modules. Both should depend on abstractions.

**Key Points**:
- Depend on abstractions (interfaces), not concrete classes
- High-level modules define what they need (interface)
- Low-level modules implement the interface

### Example: Violating DIP

**❌ BAD - Direct Dependency**

```java
// Low-level module
class MySQLDatabase {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

// High-level module depends on low-level module
class UserService {
    private MySQLDatabase database;  // Direct dependency!
    
    public UserService() {
        this.database = new MySQLDatabase();  // Tight coupling!
    }
    
    public void saveUser(String userData) {
        database.save(userData);
    }
}
```

**Problems**:
- `UserService` depends on concrete `MySQLDatabase`
- Can't easily switch to PostgreSQL or MongoDB
- Hard to test (can't mock database)
- Violates DIP - depends on concrete class

### Example: Following DIP

**✅ GOOD - Dependency on Abstraction**

```java
// Abstraction (interface)
interface Database {
    void save(String data);
}

// Low-level module implements interface
class MySQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

class PostgreSQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to PostgreSQL: " + data);
    }
}

// High-level module depends on abstraction
class UserService {
    private Database database;  // Depends on interface!
    
    public UserService(Database database) {  // Dependency injection
        this.database = database;
    }
    
    public void saveUser(String userData) {
        database.save(userData);
    }
}
```

**Benefits**:
- `UserService` depends on `Database` interface
- Can easily switch database implementations
- Easy to test (can inject mock database)
- Follows DIP - depends on abstraction

---

## SOLID Principles Summary

| Principle | Acronym | Key Idea | Main Benefit |
|-----------|---------|----------|--------------|
| **Single Responsibility** | SRP | One class, one responsibility | Easier to maintain and test |
| **Open/Closed** | OCP | Open for extension, closed for modification | Add features without changing existing code |
| **Liskov Substitution** | LSP | Subclasses must be substitutable | Reliable polymorphism |
| **Interface Segregation** | ISP | Many specific interfaces, not one fat interface | No unnecessary dependencies |
| **Dependency Inversion** | DIP | Depend on abstractions, not concretions | Flexible and testable code |

---

## Benefits of SOLID Principles

- ✅ **Maintainability**: Code is easier to understand and modify
- ✅ **Testability**: Classes can be tested independently
- ✅ **Flexibility**: Easy to extend functionality
- ✅ **Reusability**: Components can be reused in different contexts
- ✅ **Reduced Coupling**: Classes depend on abstractions, not concrete implementations
- ✅ **Better Design**: Leads to cleaner, more organized code

---

## Relationship with Design Patterns

SOLID principles are the foundation that many design patterns are built upon:

- **SRP** → Patterns like Strategy, Command, Observer help separate responsibilities
- **OCP** → Patterns like Strategy, Decorator, Factory enable extension without modification
- **LSP** → Ensures polymorphism works correctly in patterns like Strategy, Template Method
- **ISP** → Patterns like Adapter help create focused interfaces
- **DIP** → Patterns like Dependency Injection, Factory, Abstract Factory depend on abstractions

**Key Insight**: Design patterns are implementations of SOLID principles. Understanding SOLID helps you understand why patterns work and when to use them.

---

## Key Takeaways

1. **SOLID principles guide good design** - They help create maintainable, flexible code
2. **Each principle addresses a specific problem** - Understanding the problem helps apply the solution
3. **Design patterns implement SOLID** - Patterns are concrete applications of these principles
4. **Start with SOLID, then apply patterns** - SOLID provides the foundation, patterns provide solutions
5. **Practice refactoring** - Apply SOLID principles to improve existing code

---

## Further Reading

- Study design patterns to see SOLID principles in action
- Practice refactoring code to follow SOLID principles
- Use code reviews to identify SOLID violations
- Apply SOLID principles when designing new features
