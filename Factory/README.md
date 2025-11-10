## Factory Patterns

This folder contains examples of two related creational design patterns: **Factory Method** and **Abstract Factory**.

---

## Factory Method

- **Intent**: Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.
- **When to use**: When you have a class that can't anticipate the class of objects it must create, or when you want to localize knowledge of which subclass gets created.

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

### With Factory Method
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
```

### Without Factory Method
```java
class Dialog {
    void render(boolean isMac) {
        Button btn = isMac ? new MacButton() : new WindowsButton();
        btn.paint();
    }
}
```

### Pros
- Eliminates the need to bind application-specific classes into your code
- Provides hooks for subclasses to extend functionality
- Connects parallel class hierarchies

### Cons
- Requires subclassing just to create a particular product
- Can make code more complex with many creator subclasses

---

## Abstract Factory

- **Intent**: Provide an interface for creating families of related or dependent objects without specifying their concrete classes.
- **When to use**: The system must be configured with one of multiple families (e.g., Windows/Mac UI), and you must enforce consistency across the family.

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

### With Abstract Factory
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
```

### Without Abstract Factory
```java
boolean mac = /* runtime flag */;
Button b = mac ? new MacButton() : new WindowsButton();
Checkbox c = mac ? new MacCheckbox() : new WindowsCheckbox();
```

### Pros
- Enforces consistent families; easy to swap families
- Isolates creation logic; testable via factory doubles
- Prevents mixing incompatible products from different families

### Cons
- More indirection; adding new product types requires updating all factories
- Can be overkill if you only need to create one type of product

### OCP note
- Prefer registering factories (or suppliers) in a map to extend without modifying creation logic.

---

## Key Differences

| Aspect | Factory Method | Abstract Factory |
|--------|---------------|------------------|
| **Scope** | Creates one product type | Creates families of related products |
| **Structure** | One factory method in a class hierarchy | Multiple factory methods in one interface |
| **Purpose** | Defer instantiation to subclasses | Ensure consistency across product families |
| **Complexity** | Simpler, single product creation | More complex, multiple products |
| **Use Case** | One product with variants | Multiple related products that must work together |

### Comparison Summary
- **Factory Method**: One product creation per hierarchy. Subclasses decide which concrete class to instantiate.
- **Abstract Factory**: Families (multiple related products). Composes many factory methods to create entire families of objects.

**Note**: Abstract Factory often uses Factory Methods internally, but it composes multiple factory methods to create entire families of objects.

---

## Compare with Other Patterns

- **vs Builder**: Builder assembles one complex object step-by-step; Abstract Factory creates multiple related objects; Factory Method creates one product.
- **vs Prototype**: Prototype copies an existing configured instance; Factory patterns create new instances from scratch.

