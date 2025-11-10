## Factory Patterns

This folder contains examples of three related creational design patterns: **Simple Factory**, **Factory Method**, and **Abstract Factory**. These patterns progressively increase in complexity and flexibility.

---

## File Overview

| File | Pattern | Example Domain | Key Concept |
|------|---------|----------------|-------------|
| `SimpleFactoryDemo.java` | Simple Factory | Notification system (Email/SMS) | Single factory class with parameterized method |
| `ShapeFactoryDemo.java` | Simple Factory | Shape system (Circle/Rectangle/Square) | Classic factory example with shape creation |
| `FactoryMethodDemo.java` | Factory Method | Dialog system (Windows/Mac buttons) | Abstract creator with factory method in subclasses |
| `AbstractFactoryDemo.java` | Abstract Factory | GUI widgets (Button + Checkbox families) | Factory interface creating multiple related products |

---

## Simple Factory

- **Intent**: Encapsulate object creation in a single factory class with a parameterized method that returns different product types based on input.
- **When to use**: When you have a small, fixed set of product types and want to centralize creation logic without the complexity of inheritance hierarchies.
- **Example**: `SimpleFactoryDemo.java` - Notification system where users can choose Email or SMS notifications.

### Structure
```java
class SimpleFactory {
    Product createProduct(String type) {
        if (type.equals("TypeA")) return new ProductA();
        if (type.equals("TypeB")) return new ProductB();
        throw new IllegalArgumentException();
    }
}
```

### Example Code
```java
class NotificationFactory {
    Notification createNotification(String type, String message) {
        if (type.equals("Email")) return new EmailNotification(message);
        if (type.equals("SMS")) return new SMSNotification(message);
        throw new IllegalArgumentException();
    }
}

class User {
    void placeOrder() {
        Notification n = factory.createNotification(notificationType, "Order Placed");
        n.encryptMessage();
        n.send();
    }
}
```

### Pros
- Simple and straightforward
- Centralizes creation logic
- Easy to understand and implement

### Cons
- Violates Open/Closed Principle (must modify factory to add new types)
- Uses conditional logic (if/else or switch)
- Not extensible without changing factory code

---

## Factory Method

- **Intent**: Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.
- **When to use**: When you have a class that can't anticipate the class of objects it must create, or when you want to localize knowledge of which subclass gets created.
- **Example**: `FactoryMethodDemo.java` - Dialog system where WindowsDialog and MacDialog create platform-specific buttons.

### Structure
```java
abstract class Creator {
    abstract Product createProduct();  // Factory method
    
    void operation() {
        Product p = createProduct();  // Uses factory method
        p.use();
    }
}

class ConcreteCreator extends Creator {
    Product createProduct() { return new ConcreteProduct(); }
}
```

### Example Code
```java
abstract class Dialog {
    abstract Button createButton();  // Factory method
    
    void render() {
        Button btn = createButton();
        btn.paint();
    }
}

class WindowsDialog extends Dialog {
    Button createButton() { return new WindowsButton(); }
}

class MacDialog extends Dialog {
    Button createButton() { return new MacButton(); }
}
```

### Pros
- Eliminates the need to bind application-specific classes into your code
- Provides hooks for subclasses to extend functionality
- Connects parallel class hierarchies
- Follows Open/Closed Principle (extend via new subclasses)

### Cons
- Requires subclassing just to create a particular product
- Can make code more complex with many creator subclasses

---

## Abstract Factory

- **Intent**: Provide an interface for creating families of related or dependent objects without specifying their concrete classes.
- **When to use**: The system must be configured with one of multiple families (e.g., Windows/Mac UI), and you must enforce consistency across the family.
- **Example**: `AbstractFactoryDemo.java` - GUI system where factories create complete families of widgets (Button + Checkbox) that must be from the same OS theme.

### Structure
```java
interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}

class ConcreteFactory1 implements AbstractFactory {
    ProductA createProductA() { return new ProductA1(); }
    ProductB createProductB() { return new ProductB1(); }
}
```

### Example Code
```java
interface GUIFactory { 
    Button createButton(); 
    Checkbox createCheckbox(); 
}

class MacFactory implements GUIFactory { 
    Button createButton() { return new MacButton(); }
    Checkbox createCheckbox() { return new MacCheckbox(); }
}

class WindowsFactory implements GUIFactory { 
    Button createButton() { return new WindowsButton(); }
    Checkbox createCheckbox() { return new WindowsCheckbox(); }
}

class Application {
    Application(GUIFactory factory) {
        button = factory.createButton();      // All from same family
        checkbox = factory.createCheckbox();  // Ensures consistency
    }
}
```

### Pros
- Enforces consistent families; easy to swap families
- Isolates creation logic; testable via factory doubles
- Prevents mixing incompatible products from different families
- Follows Open/Closed Principle

### Cons
- More indirection; adding new product types requires updating all factories
- Can be overkill if you only need to create one type of product

### OCP note
- Prefer registering factories (or suppliers) in a map to extend without modifying creation logic.

---

## Pattern Comparison

| Aspect | Simple Factory | Factory Method | Abstract Factory |
|--------|---------------|----------------|------------------|
| **Complexity** | Simplest | Medium | Most complex |
| **Structure** | Single factory class | Abstract creator + subclasses | Factory interface + implementations |
| **Creation Method** | Parameterized method (if/else) | Abstract method in subclasses | Multiple methods in interface |
| **Extensibility** | Must modify factory | Add new creator subclass | Add new factory implementation |
| **OCP Compliance** | ❌ Violates (modify to extend) | ✅ Follows (extend via subclass) | ✅ Follows (extend via implementation) |
| **Use Case** | Fixed set of types | One product with variants | Multiple related products |
| **Example** | Notification (Email/SMS) | Dialog (Windows/Mac buttons) | GUI (Button + Checkbox families) |
| **When to Use** | Small, fixed product set | One product, multiple creators | Product families requiring consistency |

### Detailed Comparison

**Simple Factory vs Factory Method:**
- Simple Factory: Uses conditional logic in a single class. Adding new types requires modifying the factory.
- Factory Method: Uses inheritance. Adding new types requires creating new creator subclasses (no modification needed).

**Factory Method vs Abstract Factory:**
- Factory Method: Creates one product type. Subclasses implement a single factory method.
- Abstract Factory: Creates families of products. Implementations provide multiple factory methods.

**Simple Factory vs Abstract Factory:**
- Simple Factory: One factory class, parameterized creation, single product type.
- Abstract Factory: Factory interface, multiple implementations, multiple related products.

### Evolution Path

```
Simple Factory → Factory Method → Abstract Factory
     ↓                ↓                  ↓
  Parameter      Inheritance      Interface
  if/else        Subclasses       Implementations
```

**Progression:**
1. **Simple Factory**: Start here for basic object creation with a few types.
2. **Factory Method**: Move here when you need extensibility without modifying existing code.
3. **Abstract Factory**: Use when you need to create families of related objects that must work together.

---

## When to Use Which Pattern

### Use Simple Factory when:
- You have a small, fixed set of product types (2-5 types)
- Creation logic is straightforward
- You don't need extensibility
- Example: Notification types (Email, SMS, Push)

### Use Factory Method when:
- You have one product type with multiple variants
- You want extensibility without modifying existing code
- You need to defer instantiation to subclasses
- Example: Different dialog types creating different buttons

### Use Abstract Factory when:
- You need multiple related products that must work together
- You need to ensure consistency across a product family
- You want to swap entire families at runtime
- Example: Complete GUI theme (all widgets from same OS)

---

## Compare with Other Patterns

- **vs Builder**: Builder assembles one complex object step-by-step; Factory patterns create new instances. Simple Factory creates one product; Abstract Factory creates multiple related products.
- **vs Prototype**: Prototype copies an existing configured instance; Factory patterns create new instances from scratch.
- **vs Singleton**: Singleton ensures one instance; Factory patterns create multiple instances of different types.

---

## Code Examples Summary

### SimpleFactoryDemo.java
- **Domain**: Notification system
- **Products**: EmailNotification, SMSNotification
- **Factory**: NotificationFactory (single class with parameterized method)
- **Client**: User class that uses factory to send notifications
- **Key Point**: Centralized creation with simple conditional logic
- **Use Case**: Users can choose notification type (Email/SMS) for order updates

### ShapeFactoryDemo.java
- **Domain**: Shape drawing system
- **Products**: Circle, Rectangle, Square
- **Factory**: ShapeFactory (single class with getShape method)
- **Client**: Direct factory usage in demo class
- **Key Point**: Classic Simple Factory example - factory creates shapes based on string input
- **Use Case**: Creating different shapes without knowing their concrete classes
- **Note**: This is the most common textbook example of Simple Factory pattern

### FactoryMethodDemo.java
- **Domain**: Dialog system
- **Products**: WindowsButton, MacButton
- **Creators**: Dialog (abstract), WindowsDialog, MacDialog
- **Key Point**: Subclasses decide which button to create
- **Use Case**: Platform-specific dialog rendering

### AbstractFactoryDemo.java
- **Domain**: GUI widget system
- **Products**: Button + Checkbox (families)
- **Factories**: GUIFactory (interface), WindowsFactory, MacFactory
- **Key Point**: Ensures Button and Checkbox come from same OS family
- **Use Case**: Complete GUI theme consistency (all widgets from same OS)
