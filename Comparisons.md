# Cross-Pattern Comparisons

A comprehensive guide to understanding the differences, similarities, and use cases for design patterns in this repository.

---

## Table of Contents

1. [Creational Patterns](#creational-patterns)
   - [Abstract Factory vs Factory Method](#abstract-factory-vs-factory-method)
   - [Factory vs Builder](#factory-vs-builder)
   - [Builder vs Prototype](#builder-vs-prototype)
   - [Factory vs Prototype](#factory-vs-prototype)
   - [Singleton vs Static Class](#singleton-vs-static-class)

2. [Structural Patterns](#structural-patterns)
   - [Adapter vs Decorator vs Facade](#adapter-vs-decorator-vs-facade)
   - [Proxy vs Facade](#proxy-vs-facade)
   - [Proxy vs Decorator](#proxy-vs-decorator)
   - [Bridge vs Decorator](#bridge-vs-decorator)
   - [Adapter vs Proxy](#adapter-vs-proxy)

3. [Behavioral Patterns](#behavioral-patterns)
   - [Mediator vs Facade](#mediator-vs-facade)
   - [Mediator vs Observer](#mediator-vs-observer)
   - [Mediator vs Command](#mediator-vs-command)

4. [Cross-Category Comparisons](#cross-category-comparisons)
   - [Facade vs Builder/Factory](#facade-vs-builderfactory)
   - [Builder vs Facade](#builder-vs-facade)
   - [Flyweight vs Singleton vs Prototype](#flyweight-vs-singleton-vs-prototype)

---

## Creational Patterns

### Abstract Factory vs Factory Method

#### Overview

Both patterns deal with object creation, but at different levels of abstraction and for different purposes.

| Aspect | Factory Method | Abstract Factory |
|--------|---------------|------------------|
| **Intent** | Create one product type | Create families of related products |
| **Structure** | Abstract creator with factory method | Factory interface with multiple methods |
| **Products** | Single product per factory method | Multiple related products per factory |
| **Complexity** | Medium | High |
| **Use Case** | One product with variants | Product families requiring consistency |
| **Example** | Dialog creating platform-specific buttons | GUI factory creating Button + Checkbox families |

#### Code Comparison

**Factory Method:**
```java
// One product type (Button)
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

**Abstract Factory:**
```java
// Multiple related products (Button + Checkbox)
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();  // Multiple products
}

class MacFactory implements GUIFactory {
    Button createButton() { return new MacButton(); }
    Checkbox createCheckbox() { return new MacCheckbox(); }
}

class WindowsFactory implements GUIFactory {
    Button createButton() { return new WindowsButton(); }
    Checkbox createCheckbox() { return new WindowsCheckbox(); }
}

// Ensures consistency: all widgets from same OS
class Application {
    Application(GUIFactory factory) {
        button = factory.createButton();      // Mac button
        checkbox = factory.createCheckbox();   // Mac checkbox (consistent!)
    }
}
```

#### Key Differences

1. **Number of Products**
   - **Factory Method**: Creates **one product type** (e.g., Button)
   - **Abstract Factory**: Creates **multiple related products** (e.g., Button + Checkbox)

2. **Consistency**
   - **Factory Method**: No consistency requirement (one product)
   - **Abstract Factory**: **Enforces consistency** across product family (all from same theme)

3. **Structure**
   - **Factory Method**: Abstract class with abstract factory method
   - **Abstract Factory**: Interface with multiple factory methods

4. **When to Use**
   - **Factory Method**: When you need one product with multiple variants
   - **Abstract Factory**: When you need multiple related products that must work together

#### Decision Matrix

| Scenario | Pattern to Use |
|----------|---------------|
| Need to create buttons for different platforms | **Factory Method** |
| Need to create complete GUI theme (buttons, checkboxes, menus) | **Abstract Factory** |
| One product type with variants | **Factory Method** |
| Multiple products requiring consistency | **Abstract Factory** |

---

### Factory vs Builder

#### Overview

Both patterns hide object creation complexity, but for different purposes and at different stages.

| Aspect | Factory Patterns | Builder Pattern |
|--------|-----------------|-----------------|
| **Intent** | Create new instances | Construct complex objects step-by-step |
| **Focus** | What to create | How to construct |
| **Complexity** | Creation logic | Construction process |
| **Parameters** | Type selection | Many optional parameters |
| **Result** | Ready-to-use object | Configured object |
| **Use Case** | Object instantiation | Complex object assembly |

#### Code Comparison

**Factory (Simple Factory):**
```java
// Factory: Creates objects based on type
class NotificationFactory {
    Notification createNotification(String type, String message) {
        if (type.equals("Email")) {
            return new EmailNotification(message);
        } else if (type.equals("SMS")) {
            return new SMSNotification(message);
        }
        throw new IllegalArgumentException();
    }
}

// Usage: Simple creation
Notification email = factory.createNotification("Email", "Hello");
email.send();
```

**Builder:**
```java
// Builder: Constructs complex objects step-by-step
class Car {
    private final String engine;
    private final int wheels;
    private final String color;
    private final boolean hasSunroof;
    private final boolean hasGPS;
    
    private Car(Builder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.color = builder.color;
        this.hasSunroof = builder.hasSunroof;
        this.hasGPS = builder.hasGPS;
    }
    
    public static class Builder {
        private final String engine;  // Required
        private final int wheels;     // Required
        private String color = "white";  // Optional with default
        private boolean hasSunroof = false;
        private boolean hasGPS = false;
        
        public Builder(String engine, int wheels) {
            this.engine = engine;
            this.wheels = wheels;
        }
        
        public Builder color(String color) {
            this.color = color;
            return this;
        }
        
        public Builder sunroof(boolean has) {
            this.hasSunroof = has;
            return this;
        }
        
        public Car build() {
            return new Car(this);
        }
    }
}

// Usage: Step-by-step construction
Car car = new Car.Builder("V8", 4)
    .color("red")
    .sunroof(true)
    .build();
```

#### Key Differences

1. **Purpose**
   - **Factory**: Decides **what type** of object to create
   - **Builder**: Decides **how to construct** a complex object

2. **Complexity**
   - **Factory**: Handles creation logic (which class to instantiate)
   - **Builder**: Handles construction complexity (many optional parameters)

3. **Parameters**
   - **Factory**: Type selection parameter (e.g., "Email" vs "SMS")
   - **Builder**: Many optional parameters with defaults

4. **Result**
   - **Factory**: Returns ready-to-use object immediately
   - **Builder**: Returns configured object after step-by-step construction

5. **Problem Solved**
   - **Factory**: "Which class should I instantiate?"
   - **Builder**: "How do I construct this complex object with many optional parameters?"

#### When to Use Which

| Use Factory When: | Use Builder When: |
|------------------|-------------------|
| Need to create different types of objects | Need to construct objects with many optional parameters |
| Creation logic is complex | Object has many optional attributes |
| Want to hide instantiation details | Want readable, flexible construction |
| Need to swap implementations | Need immutable objects with flexible construction |
| Example: Notification types (Email/SMS) | Example: Car with engine, wheels, color, sunroof, GPS |

---

### Builder vs Prototype

#### Overview

Both patterns help with object creation, but Builder constructs new objects while Prototype clones existing ones.

| Aspect | Builder | Prototype |
|--------|---------|-----------|
| **Intent** | Construct complex objects step-by-step | Clone existing configured instances |
| **Approach** | Explicit construction | Copy existing object |
| **Source** | Build from scratch | Clone from prototype |
| **Use Case** | Many optional parameters | Expensive object creation |
| **Flexibility** | Step-by-step configuration | Clone and modify |

#### Code Comparison

**Builder:**
```java
// Builder: Constructs new object step-by-step
Car car = new Car.Builder("V8", 4)
    .color("red")
    .sunroof(true)
    .hasGPS(true)
    .build();
```

**Prototype:**
```java
// Prototype: Clones existing configured object
interface Shape extends Cloneable {
    Shape clone();
    void draw();
}

class Circle implements Shape {
    private int radius;
    private String color;
    
    public Circle(int radius, String color) {
        this.radius = radius;
        this.color = color;
        // Expensive initialization
        loadTexture();
        connectToDatabase();
    }
    
    public Circle clone() {
        return new Circle(this.radius, this.color);
    }
    
    public void draw() {
        System.out.println("Circle r=" + radius + " color=" + color);
    }
}

// Usage: Clone instead of constructing
Circle prototype = new Circle(10, "red");  // Expensive init runs once
Circle clone1 = prototype.clone();  // Fast clone
Circle clone2 = prototype.clone();  // Fast clone
```

#### Key Differences

1. **Approach**
   - **Builder**: **Constructs** new object from scratch
   - **Prototype**: **Clones** existing configured object

2. **Performance**
   - **Builder**: Normal construction (may be expensive)
   - **Prototype**: Fast cloning (avoids expensive initialization)

3. **Use Case**
   - **Builder**: When object has many optional parameters
   - **Prototype**: When object creation is expensive

4. **Flexibility**
   - **Builder**: Explicit step-by-step configuration
   - **Prototype**: Clone and modify as needed

5. **Problem Solved**
   - **Builder**: "How do I construct this complex object?"
   - **Prototype**: "How do I avoid expensive object creation?"

#### When to Use Which

| Use Builder When: | Use Prototype When: |
|------------------|-------------------|
| Object has many optional parameters | Object creation is expensive |
| Need step-by-step construction | Need many similar objects |
| Want readable construction code | Want to avoid expensive initialization |
| Need immutable objects | Objects have few differences |
| Example: Car with many options | Example: Expensive graphics objects |

#### Combined Usage

You can combine both patterns:
```java
// Use Builder to create prototype
Car prototype = new Car.Builder("V8", 4)
    .color("red")
    .sunroof(true)
    .build();

// Use Prototype to clone prototype
Car car1 = prototype.clone();
Car car2 = prototype.clone();
```

---

### Factory vs Prototype

#### Overview

Factory creates new instances from scratch, while Prototype clones existing instances.

| Aspect | Factory | Prototype |
|--------|---------|-----------|
| **Intent** | Create new instances | Clone existing instances |
| **Approach** | Instantiate from class | Copy existing object |
| **Performance** | Normal (may be expensive) | Fast (avoids expensive init) |
| **Flexibility** | Type selection | Runtime type creation |
| **Use Case** | Different object types | Many similar objects |

#### Code Comparison

**Factory:**
```java
// Factory: Creates new instances
class NotificationFactory {
    Notification createNotification(String type) {
        if (type.equals("Email")) {
            return new EmailNotification();  // New instance
        } else if (type.equals("SMS")) {
            return new SMSNotification();    // New instance
        }
        throw new IllegalArgumentException();
    }
}

// Each call creates new instance
Notification n1 = factory.createNotification("Email");
Notification n2 = factory.createNotification("Email");  // New instance
```

**Prototype:**
```java
// Prototype: Clones existing instances
interface Notification extends Cloneable {
    Notification clone();
    void send();
}

class EmailNotification implements Notification {
    private String message;
    
    public EmailNotification(String message) {
        this.message = message;
        // Expensive initialization
        connectToEmailServer();
        authenticate();
    }
    
    public EmailNotification clone() {
        return new EmailNotification(this.message);
    }
    
    public void send() {
        System.out.println("Sending email: " + message);
    }
}

// Clone instead of creating
EmailNotification prototype = new EmailNotification("Hello");  // Expensive
EmailNotification n1 = prototype.clone();  // Fast
EmailNotification n2 = prototype.clone();  // Fast
```

#### Key Differences

1. **Creation Method**
   - **Factory**: **Instantiates** new objects from classes
   - **Prototype**: **Clones** existing configured objects

2. **Performance**
   - **Factory**: Normal construction (expensive operations run each time)
   - **Prototype**: Fast cloning (expensive operations run once)

3. **Flexibility**
   - **Factory**: Type selection at compile time
   - **Prototype**: Runtime type creation via registry

4. **Use Case**
   - **Factory**: Different object types
   - **Prototype**: Many similar objects

#### When to Use Which

| Use Factory When: | Use Prototype When: |
|------------------|-------------------|
| Need different object types | Object creation is expensive |
| Object creation is cheap | Need many similar objects |
| Want type selection | Want to avoid expensive initialization |
| Example: Different notification types | Example: Expensive graphics objects |

---

### Singleton vs Static Class

#### Overview

Both ensure single instance/access point, but Singleton is an object while static class has no instances.

| Aspect | Singleton | Static Class |
|--------|-----------|--------------|
| **Type** | Object instance | Class with static methods |
| **Memory** | Instance in heap | No instance |
| **Inheritance** | Can implement interfaces | Cannot inherit |
| **Polymorphism** | Supports | Does not support |
| **Lazy Loading** | Possible | Not applicable |
| **Testing** | Can mock | Hard to mock |

#### Code Comparison

**Singleton:**
```java
// Singleton: Single instance
class DatabaseConnection {
    private static DatabaseConnection instance;
    
    private DatabaseConnection() {
        // Private constructor
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public void connect() {
        System.out.println("Connected to database");
    }
}

// Usage: Instance-based
DatabaseConnection db = DatabaseConnection.getInstance();
db.connect();
```

**Static Class:**
```java
// Static Class: No instances
class DatabaseConnection {
    private DatabaseConnection() {
        // Private constructor prevents instantiation
    }
    
    public static void connect() {
        System.out.println("Connected to database");
    }
}

// Usage: Direct method call
DatabaseConnection.connect();
```

#### Key Differences

1. **Type**
   - **Singleton**: **Object instance** (can be passed around)
   - **Static Class**: **No instance** (just static methods)

2. **Inheritance**
   - **Singleton**: Can **implement interfaces**, extend classes
   - **Static Class**: **Cannot inherit** or implement interfaces

3. **Polymorphism**
   - **Singleton**: Supports **polymorphism** (can be used as interface)
   - **Static Class**: **No polymorphism** (direct method calls)

4. **Lazy Loading**
   - **Singleton**: Can be **lazy loaded**
   - **Static Class**: **Not applicable** (no instance)

5. **Testing**
   - **Singleton**: Can be **mocked** (if using interface)
   - **Static Class**: **Hard to mock** (direct method calls)

#### When to Use Which

| Use Singleton When: | Use Static Class When: |
|-------------------|----------------------|
| Need single instance | Utility methods only |
| Need to implement interfaces | No state needed |
| Need polymorphism | Simple helper functions |
| Need lazy loading | No inheritance needed |
| Example: Database connection | Example: Math utilities |

---

## Structural Patterns

### Adapter vs Decorator vs Facade

#### Overview

Three structural patterns that wrap objects, but for different purposes.

| Aspect | Adapter | Decorator | Facade |
|--------|---------|-----------|--------|
| **Intent** | Convert interface | Add behavior | Simplify interface |
| **Interface** | Changes | Preserves | Simplifies |
| **Purpose** | Compatibility | Enhancement | Simplification |
| **Objects** | One adaptee | One component | Multiple subsystems |
| **Behavior** | Translates calls | Adds functionality | Hides complexity |

#### Code Comparison

**Adapter:**
```java
// Adapter: Converts interface
interface IChargerTypeC {
    void chargeTypeC();
}

class ChargerMicroUSB {
    public void chargeMicroUSB() {
        System.out.println("Charging using Micro USB.");
    }
}

class ChargerAdapter implements IChargerTypeC {
    private ChargerMicroUSB chargerMicroUSB;
    
    public ChargerAdapter(ChargerMicroUSB charger) {
        this.chargerMicroUSB = charger;
    }
    
    @Override
    public void chargeTypeC() {
        System.out.println("Converting Type C to Micro USB.");
        chargerMicroUSB.chargeMicroUSB();  // Translates call
    }
}

// Usage: Adapter converts interface
IChargerTypeC adapter = new ChargerAdapter(new ChargerMicroUSB());
adapter.chargeTypeC();  // Uses Type C interface, but calls Micro USB
```

**Decorator:**
```java
// Decorator: Adds behavior
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Shape: Circle");
    }
}

class RedShapeDecorator implements Shape {
    private Shape decoratedShape;
    
    public RedShapeDecorator(Shape shape) {
        this.decoratedShape = shape;
    }
    
    public void draw() {
        decoratedShape.draw();  // Preserves interface
        setRedBorder();         // Adds behavior
    }
    
    private void setRedBorder() {
        System.out.println("Border Color: Red");
    }
}

// Usage: Decorator adds functionality
Shape circle = new Circle();
Shape redCircle = new RedShapeDecorator(circle);
redCircle.draw();  // Same interface, but with red border
```

**Facade:**
```java
// Facade: Simplifies subsystem
class CPU {
    void freeze() { System.out.println("CPU freeze"); }
    void jump(long position) { System.out.println("CPU jump"); }
    void execute() { System.out.println("CPU execute"); }
}

class Memory {
    void load(long position, byte[] data) { System.out.println("Memory load"); }
}

class HardDrive {
    byte[] read(long lba, int size) { return new byte[0]; }
}

class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    
    public void startComputer() {
        // Simplifies complex sequence
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
    }
}

// Usage: Facade simplifies subsystem
ComputerFacade computer = new ComputerFacade();
computer.startComputer();  // Simple interface, hides complexity
```

#### Key Differences

1. **Purpose**
   - **Adapter**: **Converts** incompatible interface to compatible one
   - **Decorator**: **Adds** behavior while preserving interface
   - **Facade**: **Simplifies** complex subsystem interface

2. **Interface**
   - **Adapter**: **Changes** interface (adaptee → target)
   - **Decorator**: **Preserves** interface (same interface)
   - **Facade**: **Simplifies** interface (many methods → few methods)

3. **Objects**
   - **Adapter**: Wraps **one adaptee**
   - **Decorator**: Wraps **one component** (can chain)
   - **Facade**: Wraps **multiple subsystems**

4. **Behavior**
   - **Adapter**: **Translates** method calls
   - **Decorator**: **Adds** functionality before/after calls
   - **Facade**: **Orchestrates** multiple subsystem calls

#### Decision Matrix

| Scenario | Pattern to Use |
|----------|---------------|
| Need to use incompatible interface | **Adapter** |
| Need to add behavior dynamically | **Decorator** |
| Need to simplify complex subsystem | **Facade** |
| Legacy code integration | **Adapter** |
| Add features to objects | **Decorator** |
| Hide subsystem complexity | **Facade** |

---

### Proxy vs Facade

#### Overview

Both patterns wrap objects, but Proxy controls access to one object while Facade simplifies access to a subsystem.

| Aspect | Proxy | Facade |
|--------|-------|--------|
| **Intent** | Control access to object | Simplify subsystem interface |
| **Objects** | Single object | Multiple subsystems |
| **Purpose** | Access control, lazy loading | Simplification |
| **Interface** | Same as real object | Simplified interface |
| **Access** | Can block direct access | Does not block access |

#### Code Comparison

**Proxy:**
```java
// Proxy: Controls access to one object
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();  // Expensive operation
    }
    
    private void loadFromDisk() {
        System.out.println("Loading " + filename + " from disk");
    }
    
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    
    public ProxyImage(String filename) {
        this.filename = filename;
    }
    
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);  // Lazy loading
        }
        realImage.display();
    }
}

// Usage: Proxy controls access
Image image = new ProxyImage("photo.jpg");
image.display();  // Loads only when needed
```

**Facade:**
```java
// Facade: Simplifies subsystem
class CPU {
    void freeze() { System.out.println("CPU freeze"); }
    void jump(long position) { System.out.println("CPU jump"); }
    void execute() { System.out.println("CPU execute"); }
}

class Memory {
    void load(long position, byte[] data) { System.out.println("Memory load"); }
}

class HardDrive {
    byte[] read(long lba, int size) { return new byte[0]; }
}

class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    
    public void startComputer() {
        // Simplifies complex sequence
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
    }
}

// Usage: Facade simplifies subsystem
ComputerFacade computer = new ComputerFacade();
computer.startComputer();  // Simple interface
```

#### Key Differences

1. **Scope**
   - **Proxy**: Controls **one object**
   - **Facade**: Simplifies **multiple subsystems**

2. **Purpose**
   - **Proxy**: **Access control**, lazy loading, logging
   - **Facade**: **Simplification** of complex subsystem

3. **Interface**
   - **Proxy**: **Same interface** as real object
   - **Facade**: **Simplified interface** (fewer methods)

4. **Access Control**
   - **Proxy**: **Can block** direct access to real object
   - **Facade**: **Does not block** access to subsystems

5. **Behavior**
   - **Proxy**: Adds behavior **around** object access
   - **Facade**: **Orchestrates** multiple subsystem calls

#### When to Use Which

| Use Proxy When: | Use Facade When: |
|----------------|-----------------|
| Need to control access to one object | Need to simplify complex subsystem |
| Need lazy loading | Have multiple subsystems |
| Need access control/security | Have common usage sequences |
| Need logging/caching | Want to reduce coupling |
| Example: Image lazy loading | Example: Home theater system |

---

### Proxy vs Decorator

#### Overview

Both patterns wrap objects and preserve interface, but Proxy controls access while Decorator adds behavior.

| Aspect | Proxy | Decorator |
|--------|-------|-----------|
| **Intent** | Control access | Add behavior |
| **Purpose** | Access control, lazy loading | Dynamic enhancement |
| **Behavior** | Controls when/how to access | Adds functionality |
| **Composition** | One-to-one | Can chain multiple |
| **Transparency** | Client may not know | Client may not know |

#### Code Comparison

**Proxy:**
```java
// Proxy: Controls access
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();  // Expensive
    }
    
    private void loadFromDisk() {
        System.out.println("Loading from disk");
    }
    
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    
    public ProxyImage(String filename) {
        this.filename = filename;
    }
    
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);  // Lazy loading
        }
        realImage.display();  // Controls access
    }
}

// Usage: Proxy controls access
Image image = new ProxyImage("photo.jpg");
image.display();  // Loads only when needed
```

**Decorator:**
```java
// Decorator: Adds behavior
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Shape: Circle");
    }
}

class RedShapeDecorator implements Shape {
    private Shape decoratedShape;
    
    public RedShapeDecorator(Shape shape) {
        this.decoratedShape = shape;
    }
    
    public void draw() {
        decoratedShape.draw();  // Preserves original
        setRedBorder();        // Adds behavior
    }
    
    private void setRedBorder() {
        System.out.println("Border Color: Red");
    }
}

// Usage: Decorator adds functionality
Shape circle = new Circle();
Shape redCircle = new RedShapeDecorator(circle);
redCircle.draw();  // Circle + red border
```

#### Key Differences

1. **Purpose**
   - **Proxy**: **Controls access** (when/how to access)
   - **Decorator**: **Adds behavior** (enhances functionality)

2. **Focus**
   - **Proxy**: **Access management** (lazy loading, security)
   - **Decorator**: **Functionality enhancement** (adds features)

3. **Composition**
   - **Proxy**: Usually **one-to-one** (one proxy per object)
   - **Decorator**: **Can chain** multiple decorators

4. **Behavior**
   - **Proxy**: Controls **when/how** to access real object
   - **Decorator**: Adds functionality **before/after** original behavior

#### When to Use Which

| Use Proxy When: | Use Decorator When: |
|----------------|-------------------|
| Need lazy loading | Need to add behavior dynamically |
| Need access control | Need to enhance functionality |
| Need logging/caching | Need to chain multiple enhancements |
| Need to defer expensive operations | Need to preserve interface |
| Example: Image lazy loading | Example: Coffee with condiments |

---

### Bridge vs Decorator

#### Overview

Both patterns use **composition** to work with another object, but they solve **different problems**:

- **Bridge** splits a class into **Abstraction** and **Implementor** hierarchies so each can vary independently.
- **Decorator** wraps a component to **add or layer behavior** dynamically while keeping the same interface.

| Aspect | Bridge | Decorator |
|--------|--------|-----------|
| **Intent** | Separate abstraction from implementation | Add behavior dynamically |
| **Structure** | Two parallel hierarchies (Abstraction ↔ Implementor) | Component plus chainable decorators |
| **Variation** | Two independent dimensions (e.g., Transport, Engine) | Many optional features around one type |
| **Interface** | Abstraction usually defines its own API | Decorator preserves Component interface |

#### Code Intuition

- **Bridge**: `new Car(new GasEngine())` vs `new Plane(new ElectricEngine())` – combine any transport with any engine.
- **Decorator**: `new Milk(new Sugar(new SimpleCoffee()))` – wrap a coffee with extra responsibilities.

#### When to Use Which

- Use **Bridge** when:
  - You have **two dimensions** that can grow independently (e.g., `Shape` vs `Color`, `Transport` vs `Engine`).
  - You want to avoid a class explosion like `RedCircle`, `BlueCircle`, `RedSquare`, `BlueSquare`, etc.
- Use **Decorator** when:
  - You want to **add responsibilities** to individual objects at runtime.
  - You have many optional features and want to avoid subclass explosion.

---

### Adapter vs Proxy

#### Overview

Both patterns wrap objects, but Adapter converts interface while Proxy controls access.

| Aspect | Adapter | Proxy |
|--------|---------|-------|
| **Intent** | Convert interface | Control access |
| **Interface** | Changes | Preserves |
| **Purpose** | Compatibility | Access control |
| **Problem** | Incompatible interfaces | Access management |

#### Code Comparison

**Adapter:**
```java
// Adapter: Converts interface
interface IChargerTypeC {
    void chargeTypeC();
}

class ChargerMicroUSB {
    public void chargeMicroUSB() {
        System.out.println("Charging using Micro USB.");
    }
}

class ChargerAdapter implements IChargerTypeC {
    private ChargerMicroUSB chargerMicroUSB;
    
    public ChargerAdapter(ChargerMicroUSB charger) {
        this.chargerMicroUSB = charger;
    }
    
    @Override
    public void chargeTypeC() {
        chargerMicroUSB.chargeMicroUSB();  // Converts interface
    }
}

// Usage: Adapter converts interface
IChargerTypeC adapter = new ChargerAdapter(new ChargerMicroUSB());
adapter.chargeTypeC();  // Uses Type C interface
```

**Proxy:**
```java
// Proxy: Controls access
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    
    private void loadFromDisk() {
        System.out.println("Loading from disk");
    }
    
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    
    public ProxyImage(String filename) {
        this.filename = filename;
    }
    
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);  // Lazy loading
        }
        realImage.display();  // Controls access
    }
}

// Usage: Proxy controls access
Image image = new ProxyImage("photo.jpg");
image.display();  // Same interface, but controls access
```

#### Key Differences

1. **Purpose**
   - **Adapter**: **Converts** incompatible interface
   - **Proxy**: **Controls access** to object

2. **Interface**
   - **Adapter**: **Changes** interface (adaptee → target)
   - **Proxy**: **Preserves** interface (same interface)

3. **Problem Solved**
   - **Adapter**: "How do I use incompatible interface?"
   - **Proxy**: "How do I control access to object?"

#### When to Use Which

| Use Adapter When: | Use Proxy When: |
|------------------|----------------|
| Need to use incompatible interface | Need to control access |
| Need to integrate legacy code | Need lazy loading |
| Need interface conversion | Need access control/security |
| Example: Micro USB to Type C | Example: Image lazy loading |

---

## Cross-Category Comparisons

### Facade vs Builder/Factory

#### Overview

Facade simplifies subsystem usage, while Builder/Factory simplify object creation.

| Aspect | Facade | Builder/Factory |
|--------|--------|----------------|
| **Intent** | Simplify subsystem interface | Simplify object creation |
| **Focus** | Usage complexity | Creation complexity |
| **Stage** | Runtime usage | Object construction |
| **Objects** | Multiple subsystems | Single/multiple objects |

#### Code Comparison

**Facade:**
```java
// Facade: Simplifies subsystem usage
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    
    public void startComputer() {
        // Simplifies complex sequence
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
    }
}

// Usage: Simple interface
ComputerFacade computer = new ComputerFacade();
computer.startComputer();  // Hides subsystem complexity
```

**Builder:**
```java
// Builder: Simplifies object construction
Car car = new Car.Builder("V8", 4)
    .color("red")
    .sunroof(true)
    .build();
```

**Factory:**
```java
// Factory: Simplifies object creation
Notification notification = factory.createNotification("Email", "Hello");
```

#### Key Differences

1. **Stage**
   - **Facade**: **Runtime usage** (simplifies how to use subsystem)
   - **Builder/Factory**: **Construction time** (simplifies how to create)

2. **Complexity**
   - **Facade**: Hides **usage complexity** (sequence of calls)
   - **Builder/Factory**: Hides **creation complexity** (which class/how to construct)

3. **Objects**
   - **Facade**: Works with **multiple subsystems**
   - **Builder**: Works with **one complex object**
   - **Factory**: Works with **one or multiple objects**

---

### Builder vs Facade

#### Overview

Builder simplifies object construction, while Facade simplifies subsystem usage.

| Aspect | Builder | Facade |
|--------|---------|--------|
| **Intent** | Construct complex objects | Simplify subsystem interface |
| **Focus** | Construction process | Usage process |
| **Stage** | Object creation | Object usage |
| **Complexity** | Many optional parameters | Complex subsystem interactions |

#### Code Comparison

**Builder:**
```java
// Builder: Simplifies construction
Car car = new Car.Builder("V8", 4)
    .color("red")
    .sunroof(true)
    .hasGPS(true)
    .build();
```

**Facade:**
```java
// Facade: Simplifies usage
ComputerFacade computer = new ComputerFacade();
computer.startComputer();  // Hides complex sequence
```

#### Key Differences

1. **Stage**
   - **Builder**: **Construction time** (how to build object)
   - **Facade**: **Runtime** (how to use subsystem)

2. **Complexity**
   - **Builder**: Many **optional parameters**
   - **Facade**: Complex **subsystem interactions**

3. **Result**
   - **Builder**: **Configured object**
   - **Facade**: **Simplified interface** to subsystem

---

### Flyweight vs Singleton vs Prototype

#### Overview

These three patterns all deal with **object instances**, but in different ways:

- **Singleton**: ensures there is **exactly one** instance of a class.
- **Prototype**: creates **new instances by cloning** existing ones.
- **Flyweight**: lets **many logical objects share intrinsic state** (data).

| Aspect | Singleton | Prototype | Flyweight |
|--------|-----------|-----------|-----------|
| **Goal** | One shared instance | Efficient cloning | Save memory |
| **Instances** | Single instance | Many full objects | Many objects share few flyweights |
| **State** | All state in one object | Each copy has full state | Intrinsic shared, extrinsic external |
| **Best for** | Global services | Expensive-to-create objects | Huge numbers of similar objects |

#### Code Intuition

- **Singleton**: `Logger.getInstance()` always returns the same logger instance.
- **Prototype**: `Circle copy = prototype.clone();` gives a new circle you can tweak independently.
- **Flyweight**: `TreeFactory.getTreeType("Oak", GREEN)` returns a shared `TreeType` used by many `Tree` positions.

#### Key Differences

1. **Number of instances**
   - Singleton: exactly **one** instance.
   - Prototype: **many** independent instances.
   - Flyweight: **many logical objects**, but backed by a **small pool of shared flyweights**.

2. **Memory focus**
   - Singleton: saves memory by not duplicating a shared object.
   - Prototype: focuses on **creation speed/flexibility**, not memory reduction.
   - Flyweight: aggressively reduces memory by sharing intrinsic data.

3. **Typical use cases**
   - Singleton: configuration, logging, caches, connection pools.
   - Prototype: shapes, GUI elements, or objects with expensive initialization that you want to duplicate.
   - Flyweight: characters in documents, trees in a forest, tiles or particles in games.

---

## Behavioral Patterns

### Mediator vs Facade

#### Overview

Both patterns simplify interactions, but Mediator coordinates peer objects while Facade simplifies subsystem access.

| Aspect | Mediator | Facade |
|--------|----------|--------|
| **Intent** | Centralize communication between peer objects | Simplify subsystem interface |
| **Participants** | Peer objects (colleagues) | Subsystem components |
| **Communication** | Objects communicate with each other (through mediator) | Client uses facade instead of subsystem |
| **Coupling** | Reduces coupling between colleagues | Reduces coupling between client and subsystem |
| **Complexity** | O(n) communication (through mediator) | Simplified interface to complex subsystem |
| **Focus** | Coordination logic | Interface simplification |

#### Code Comparison

**Mediator:**
```java
// Mediator: Coordinates peer objects
Alarm alarm = new Alarm();
CoffeePot coffeePot = new CoffeePot();
Calendar calendar = new Calendar();

SmartHomeMediator mediator = new SmartHomeMediator();
mediator.registerComponents(alarm, coffeePot, calendar);

alarm.ring();  // Mediator coordinates: checks calendar, starts coffee
```

**Facade:**
```java
// Facade: Simplifies subsystem access
ComputerFacade computer = new ComputerFacade();
computer.startComputer();  // Hides complex subsystem interactions
```

#### Key Differences

1. **Object Relationships**
   - **Mediator**: Coordinates **peer objects** that need to communicate with each other
   - **Facade**: Provides simplified interface to a **subsystem** (hierarchical relationship)

2. **Communication Pattern**
   - **Mediator**: Objects communicate **with each other** through the mediator
   - **Facade**: Client communicates **with subsystem** through the facade (one-way)

3. **Complexity Reduction**
   - **Mediator**: Reduces **inter-object communication complexity** (O(n²) to O(n))
   - **Facade**: Reduces **subsystem usage complexity** (hides internal complexity)

4. **Use Cases**
   - **Mediator**: GUI components in dialog, chat applications, workflow systems
   - **Facade**: Home theater systems, computer startup sequences, API wrappers

---

### Mediator vs Observer

#### Overview

Both patterns handle communication, but Mediator centralizes it while Observer distributes notifications.

| Aspect | Mediator | Observer |
|--------|----------|----------|
| **Intent** | Centralize communication between objects | Notify multiple observers of state changes |
| **Communication** | Centralized through one mediator | Distributed through subject-observer relationships |
| **Coupling** | Colleagues don't know about each other | Observers know about subject, subject knows about observers |
| **Coordination** | Mediator actively coordinates | Subject passively notifies |
| **Complexity** | O(n) through mediator | O(n) notifications (one-to-many) |

#### Code Comparison

**Mediator:**
```java
// Mediator: Centralized coordination
class SmartHomeMediator {
    void onEvent(String event, Colleague sender) {
        if ("ALARM_RING".equals(event)) {
            // Mediator coordinates multiple actions
            if (calendar.isWeekend()) {
                sprinkler.start();
            }
            coffeePot.startBrewing();
        }
    }
}
```

**Observer:**
```java
// Observer: Distributed notifications
class WeatherStation extends Subject {
    void setTemperature(int temp) {
        this.temperature = temp;
        notifyObservers();  // Notifies all observers
    }
}

class PhoneDisplay implements Observer {
    void update(int temperature) {
        display(temperature);  // Each observer handles notification independently
    }
}
```

#### Key Differences

1. **Communication Model**
   - **Mediator**: **Centralized** - all communication flows through one mediator
   - **Observer**: **Distributed** - subject notifies multiple observers directly

2. **Coordination**
   - **Mediator**: Mediator **actively coordinates** colleagues based on events
   - **Observer**: Subject **passively notifies** observers, observers handle independently

3. **Coupling**
   - **Mediator**: Colleagues **don't know about each other** (only know mediator)
   - **Observer**: Observers **know about subject**, subject knows about observers

4. **Use Cases**
   - **Mediator**: When you need centralized coordination logic (GUI dialogs, workflow systems)
   - **Observer**: When you need one-to-many notifications (event systems, MVC architecture)

---

### Mediator vs Command

#### Overview

Mediator coordinates multiple objects, while Command encapsulates single operations.

| Aspect | Mediator | Command |
|--------|----------|---------|
| **Intent** | Coordinate communication between multiple objects | Encapsulate requests as objects |
| **Focus** | Object coordination | Operation encapsulation |
| **Participants** | Multiple colleagues + mediator | Command + invoker + receiver |
| **Complexity** | Reduces inter-object communication | Enables queuing, logging, undo |
| **Use Case** | Complex object interactions | Request queuing, undo/redo, logging |

#### Code Comparison

**Mediator:**
```java
// Mediator: Coordinates multiple objects
class SmartHomeMediator {
    void onEvent(String event, Colleague sender) {
        if ("ALARM_RING".equals(event)) {
            // Coordinates multiple objects
            coffeePot.startBrewing();
            if (shouldStartSprinkler()) {
                sprinkler.start();
            }
        }
    }
}
```

**Command:**
```java
// Command: Encapsulates single operation
class TurnOnCommand implements Command {
    private Device device;
    
    void execute() {
        device.turnOn();  // Encapsulates one operation
    }
}

RemoteControl remote = new RemoteControl();
remote.setCommand(new TurnOnCommand(tv));
remote.pressButton();
```

#### Key Differences

1. **Scope**
   - **Mediator**: Coordinates **multiple objects** communicating with each other
   - **Command**: Encapsulates **single operations** or requests

2. **Purpose**
   - **Mediator**: **Reduces communication complexity** between objects
   - **Command**: Enables **queuing, logging, undo** of operations

3. **Structure**
   - **Mediator**: Mediator knows about all colleagues, contains coordination logic
   - **Command**: Command encapsulates receiver and operation, invoker executes commands

4. **Use Cases**
   - **Mediator**: GUI components, chat applications, workflow coordination
   - **Command**: Menu systems, undo/redo, macro recording, remote control systems

---

### Memento vs Command (for Undo)

#### Overview

Both patterns can be used for undo functionality, but they approach it differently: Memento stores state snapshots while Command stores operations.

| Aspect | Memento | Command |
|--------|---------|---------|
| **Intent** | Save/restore object state | Encapsulate operations as objects |
| **What's Stored** | State snapshots | Operations (commands) |
| **Undo Mechanism** | Restore previous state | Execute inverse operation |
| **Encapsulation** | State hidden in memento | Operation hidden in command |
| **Use Case** | State-based undo | Operation-based undo |

#### Code Comparison

**Memento:**
```java
// Memento: Stores state snapshots
class TextEditor {
    public Memento save() {
        return new Memento(this.content, this.cursorPosition);
    }
    
    public void restore(Memento memento) {
        this.content = memento.getContent();
        this.cursorPosition = memento.getCursorPosition();
    }
}

// Undo: Restore previous state
editor.restore(history.undo());
```

**Command:**
```java
// Command: Stores operations
class WriteCommand implements Command {
    private String text;
    private TextEditor editor;
    
    public void execute() {
        editor.write(text);
    }
    
    public void undo() {
        editor.delete(text.length());  // Inverse operation
    }
}

// Undo: Execute inverse operation
command.undo();
```

#### Key Differences

1. **What's Stored**
   - **Memento**: **State snapshots** at specific points in time
   - **Command**: **Operations** (actions) that can be executed/undone

2. **Undo Mechanism**
   - **Memento**: **Restore** previous state from snapshot
   - **Command**: **Execute inverse** operation to undo

3. **Memory Usage**
   - **Memento**: Stores full state (can be memory-intensive)
   - **Command**: Stores operation details (usually smaller)

4. **Use Cases**
   - **Memento**: Text editors, graphics applications, games (save/load)
   - **Command**: Menu systems, macro recording, transaction systems

**They can work together**: Command pattern can use Memento to store state before operations.

---

### Memento vs Prototype

#### Overview

Both patterns deal with object state, but Memento stores state for restoration while Prototype creates new objects by cloning.

| Aspect | Memento | Prototype |
|--------|---------|-----------|
| **Intent** | Save/restore object state | Create new objects by cloning |
| **Purpose** | State restoration | Object creation |
| **Access** | Opaque (only Originator) | Accessible (clients can clone) |
| **Use Case** | Undo/redo, checkpoints | Efficient object creation |

#### Code Comparison

**Memento:**
```java
// Memento: Stores state for restoration
class TextEditor {
    public Memento save() {
        return new Memento(this.content, this.cursorPosition);
    }
    
    public void restore(Memento memento) {
        this.content = memento.getContent();  // Restore previous state
        this.cursorPosition = memento.getCursorPosition();
    }
}
```

**Prototype:**
```java
// Prototype: Creates new objects by cloning
class Circle implements Cloneable {
    private int radius;
    private String color;
    
    public Circle clone() {
        return (Circle) super.clone();  // Create new object
    }
}

// Usage: Create new instances
Circle circle2 = circle1.clone();  // New object, can modify independently
```

#### Key Differences

1. **Purpose**
   - **Memento**: **Restore** object to previous state
   - **Prototype**: **Create** new object instances

2. **State Management**
   - **Memento**: Stores **past state** for restoration
   - **Prototype**: Creates **new objects** with copied state

3. **Access Control**
   - **Memento**: **Opaque** - only Originator can access
   - **Prototype**: **Accessible** - clients can clone and modify

4. **Use Cases**
   - **Memento**: Undo/redo, save/load, checkpoints
   - **Prototype**: Expensive object creation, object templates

---

### Observer vs Command

#### Overview

Both patterns deal with notifications and actions, but Observer notifies multiple objects while Command encapsulates operations.

| Aspect | Observer | Command |
|--------|----------|---------|
| **Intent** | Notify multiple observers of state changes | Encapsulate operations as objects |
| **Focus** | Event notification | Operation encapsulation |
| **Participants** | Subject + multiple observers | Command + invoker + receiver |
| **Communication** | Broadcast to all observers | Single operation execution |
| **Use Case** | Event-driven systems, MVC | Undo/redo, queuing, logging |

#### Code Comparison

**Observer:**
```java
// Observer: Notifies multiple observers
class WeatherStation implements Subject {
    void setTemperature(float temp) {
        this.temperature = temp;
        notifyObservers();  // Broadcasts to all observers
    }
}

class PhoneApp implements Observer {
    void update(float temp) {
        display(temp);  // Each observer reacts independently
    }
}
```

**Command:**
```java
// Command: Encapsulates single operation
class TurnOnCommand implements Command {
    private Device device;
    
    void execute() {
        device.turnOn();  // Single operation
    }
}

RemoteControl remote = new RemoteControl();
remote.setCommand(new TurnOnCommand(tv));
remote.pressButton();  // Executes command
```

#### Key Differences

1. **Purpose**
   - **Observer**: **Notifies** multiple objects of state changes
   - **Command**: **Encapsulates** operations for execution

2. **Communication**
   - **Observer**: **Broadcasts** to all registered observers
   - **Command**: **Executes** single operation on receiver

3. **Use Cases**
   - **Observer**: Event systems, MVC, publish-subscribe
   - **Command**: Menu systems, undo/redo, macro recording

---

### Observer vs Chain of Responsibility

#### Overview

Both patterns handle requests/events, but Observer broadcasts to all while Chain passes to one handler.

| Aspect | Observer | Chain of Responsibility |
|--------|----------|------------------------|
| **Intent** | Notify multiple observers of changes | Pass request along chain until handled |
| **Communication** | Broadcast to all observers | Sequential chain processing |
| **Handlers** | All observers receive notification | One handler processes request |
| **Use Case** | Event notification | Request handling |

#### Code Comparison

**Observer:**
```java
// Observer: Broadcasts to all
class WeatherStation implements Subject {
    void setTemperature(float temp) {
        this.temperature = temp;
        notifyObservers();  // All observers notified
    }
}

// All observers receive update
phoneApp.update(temp);
website.update(temp);
watch.update(temp);
```

**Chain of Responsibility:**
```java
// Chain: Passes to one handler
class SpamHandler extends Handler {
    void handleRequest(Email email) {
        if (isSpam(email)) {
            process(email);  // Handles and stops
        } else {
            successor.handleRequest(email);  // Passes to next
        }
    }
}
```

#### Key Differences

1. **Communication Pattern**
   - **Observer**: **Broadcasts** to all observers simultaneously
   - **Chain**: **Sequentially passes** request until one handler processes it

2. **Handler Behavior**
   - **Observer**: **All observers** receive and process notification
   - **Chain**: **One handler** processes request, then stops

3. **Use Cases**
   - **Observer**: Event systems, model-view updates
   - **Chain**: Request routing, validation pipelines, approval workflows

---

### State vs Strategy

#### Overview

Both patterns change behavior, but State changes behavior based on internal state while Strategy swaps algorithms.

| Aspect | State | Strategy |
|--------|-------|----------|
| **Intent** | Change behavior based on internal state | Swap algorithms/behaviors |
| **Focus** | State-dependent behavior | Algorithm selection |
| **State Management** | States are related and transition | Strategies are independent |
| **Transitions** | States transition between each other | Strategies are swapped |
| **Use Case** | Object behavior depends on state | Need different algorithms for same task |

#### Code Comparison

**State:**
```java
// State: Behavior changes with internal state
class VendingMachine {
    private VendingMachineState currentState;
    
    public void insertCoin() {
        currentState.insertCoin();  // Behavior depends on current state
    }
}

class NoCoinState implements VendingMachineState {
    public void insertCoin() {
        machine.setState(machine.getHasCoinState());  // Transitions to another state
    }
}
```

**Strategy:**
```java
// Strategy: Swaps algorithms
class Sorter {
    private SortStrategy strategy;
    
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;  // Swap strategy
    }
    
    public void sort(int[] array) {
        strategy.sort(array);  // Uses current strategy
    }
}

// Strategies are independent - no transitions
class QuickSort implements SortStrategy { ... }
class MergeSort implements SortStrategy { ... }
```

#### Key Differences

1. **State Management**
   - **State**: States are **related** and **transition** between each other
   - **Strategy**: Strategies are **independent** and **interchangeable**

2. **Behavior Change**
   - **State**: Behavior changes **automatically** with internal state
   - **Strategy**: Behavior changes by **explicitly swapping** strategies

3. **Use Cases**
   - **State**: Vending machines, traffic lights, game character states
   - **Strategy**: Sorting algorithms, encryption methods, payment methods

**Key Difference:**
- State = behavior changes with **internal state** (stateful, transitions)
- Strategy = behavior changes by **choosing algorithm** (stateless, no transitions)

---

### State vs Memento

#### Overview

Both patterns deal with state, but State represents current state for behavior while Memento stores past state for restoration.

| Aspect | State | Memento |
|--------|-------|---------|
| **Intent** | Change behavior based on current state | Save/restore object state |
| **Purpose** | State-dependent behavior | State restoration |
| **State Type** | Current state (active) | Past state (passive) |
| **Use Case** | Behavior changes with state | Undo/redo, checkpoints |

#### Code Comparison

**State:**
```java
// State: Current state defines behavior
class VendingMachine {
    private VendingMachineState currentState;  // Current state
    
    public void insertCoin() {
        currentState.insertCoin();  // Behavior based on current state
    }
}

class NoCoinState implements VendingMachineState {
    public void insertCoin() {
        machine.setState(machine.getHasCoinState());  // Change current state
    }
}
```

**Memento:**
```java
// Memento: Stores past state for restoration
class TextEditor {
    public Memento save() {
        return new Memento(this.content, this.cursorPosition);  // Save current state
    }
    
    public void restore(Memento memento) {
        this.content = memento.getContent();  // Restore past state
        this.cursorPosition = memento.getCursorPosition();
    }
}
```

#### Key Differences

1. **State Type**
   - **State**: Represents **current state** (active, defines behavior)
   - **Memento**: Stores **past state** (passive, for restoration)

2. **Purpose**
   - **State**: **Behavior changes** based on current state
   - **Memento**: **Restore** object to previous state

3. **Use Cases**
   - **State**: Vending machines, traffic lights, workflow states
   - **Memento**: Undo/redo, save/load, checkpoints

**Key Difference:**
- State = **current state** for behavior (active)
- Memento = **past state** for restoration (passive)

---

### State vs Command

#### Overview

State encapsulates state-specific behavior while Command encapsulates operations as objects.

| Aspect | State | Command |
|--------|-------|---------|
| **Intent** | Change behavior based on state | Encapsulate operations as objects |
| **Focus** | State-dependent behavior | Operation encapsulation |
| **Behavior** | Changes automatically with state | Executed on demand |
| **Use Case** | Object behavior depends on state | Queuing, logging, undo operations |

#### Code Comparison

**State:**
```java
// State: Behavior changes with state
class VendingMachine {
    private VendingMachineState currentState;
    
    public void pressDispense() {
        currentState.pressDispense();  // Behavior depends on current state
    }
}

class NoCoinState implements VendingMachineState {
    public void pressDispense() {
        System.out.println("Please insert coin first.");  // State-specific behavior
    }
}
```

**Command:**
```java
// Command: Encapsulates operations
class TurnOnCommand implements Command {
    private Device device;
    
    public void execute() {
        device.turnOn();  // Encapsulates operation
    }
}

RemoteControl remote = new RemoteControl();
remote.setCommand(new TurnOnCommand(tv));
remote.pressButton();  // Executes command
```

#### Key Differences

1. **Purpose**
   - **State**: Encapsulates **state-specific behavior**
   - **Command**: Encapsulates **operations** as objects

2. **Behavior Change**
   - **State**: Behavior changes **automatically** with state
   - **Command**: Operations are **executed** on demand

3. **Use Cases**
   - **State**: Vending machines, traffic lights, game states
   - **Command**: Menu systems, undo/redo, macro recording

**Key Difference:**
- State = behavior based on **current state**
- Command = **operation** encapsulation

---

### Iterator vs Visitor

#### Overview

Both patterns work with collections, but Iterator traverses elements while Visitor performs operations on elements.

| Aspect | Iterator | Visitor |
|--------|----------|---------|
| **Intent** | Traverse elements of a collection | Perform operations on elements |
| **Focus** | Accessing elements sequentially | Operating on elements |
| **Purpose** | Traversal | Processing |
| **Use Case** | Traverse collections uniformly | Apply operations to elements |

#### Code Comparison

**Iterator:**
```java
// Iterator: Traverses elements
Iterator<String> iterator = library.createIterator();
while (iterator.hasNext()) {
    String book = iterator.next();  // Just access element
    System.out.println(book);
}
```

**Visitor:**
```java
// Visitor: Performs operations on elements
class BookVisitor {
    void visit(Book book) {
        book.print();  // Perform operation
    }
}

for (Book book : library) {
    visitor.visit(book);  // Operate on element
}
```

#### Key Differences

1. **Purpose**
   - **Iterator**: **Traverses** elements of a collection
   - **Visitor**: **Performs operations** on elements

2. **Focus**
   - **Iterator**: **Accessing** elements sequentially
   - **Visitor**: **Operating** on elements

3. **Use Cases**
   - **Iterator**: Collection traversal, hiding internal structure
   - **Visitor**: Applying operations, double dispatch

**They work together**: Iterator can be used with Visitor to traverse and operate on elements.

---

### Iterator vs Composite

#### Overview

Iterator provides traversal while Composite builds tree structures. They often work together.

| Aspect | Iterator | Composite |
|--------|----------|-----------|
| **Intent** | Traverse collections | Compose objects into tree structures |
| **Focus** | Accessing elements | Building structures |
| **Purpose** | Traversal | Structure composition |
| **Use Case** | Traverse aggregate objects | Represent part-whole hierarchies |

#### Code Comparison

**Iterator:**
```java
// Iterator: Traverses collection
Iterator<String> iterator = library.createIterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

**Composite:**
```java
// Composite: Builds tree structure
class FileSystem {
    private List<FileSystem> children;
    
    void add(FileSystem component) {
        children.add(component);
    }
}

// Iterator can traverse Composite structure
Iterator<FileSystem> iterator = root.createIterator();
while (iterator.hasNext()) {
    FileSystem component = iterator.next();
    component.display();
}
```

#### Key Differences

1. **Purpose**
   - **Iterator**: **Traverses** collections
   - **Composite**: **Builds** tree structures

2. **Focus**
   - **Iterator**: **Accessing** elements
   - **Composite**: **Structuring** objects

3. **Relationship**
   - **Iterator**: Works **with** Composite to traverse tree structures
   - **Composite**: Can use Iterator to traverse its children

**They work together**: Iterator is commonly used to traverse Composite structures.

---

## Quick Reference Table

| Pattern | Intent | Key Characteristic | When to Use |
|---------|--------|-------------------|-------------|
| **Abstract Factory** | Create families of related products | Multiple related products | Need consistency across product family |
| **Factory Method** | Create one product type | Abstract creator with factory method | One product with variants |
| **Builder** | Construct complex objects | Step-by-step construction | Many optional parameters |
| **Prototype** | Clone existing instances | Copy configured object | Expensive object creation |
| **Singleton** | Single instance | One instance globally | Need single shared instance |
| **Adapter** | Convert interface | Interface translation | Incompatible interfaces |
| **Decorator** | Add behavior | Dynamic enhancement | Need to add features dynamically |
| **Bridge** | Separate abstraction from implementation | Two independent dimensions | Avoid class explosion from combinations |
| **Facade** | Simplify subsystem | Simplified interface | Complex subsystem |
| **Flyweight** | Share intrinsic state | Many objects share common data | Need memory savings with many similar objects |
| **Proxy** | Control access | Access management | Lazy loading, access control |
| **Strategy** | Swap algorithms/behaviors | Encapsulated, pluggable strategies | Need interchangeable behaviors at runtime |
| **Command** | Encapsulate requests as objects | Request/operation encapsulation | Need undo/redo, queuing, logging operations |
| **Mediator** | Centralize object communication | Centralized coordination | Complex inter-object communication |
| **Memento** | Save/restore object state | State snapshot for undo/redo | Need undo/redo, state checkpoints |
| **Observer** | Notify multiple objects of changes | One-to-many state notification | Need event-driven updates, publish-subscribe |
| **State** | Change behavior with object state | State-specific behavior encapsulation | Object behavior depends on internal state |
| **Iterator** | Traverse collection elements | Sequential access without exposing structure | Need to traverse collections uniformly |

---

## Pattern Selection Guide

### Creational Patterns

**Need to create objects?**
- **Different types?** → Factory (Simple/Factory Method/Abstract Factory)
- **Complex construction?** → Builder
- **Expensive creation?** → Prototype
- **Single instance?** → Singleton

### Structural Patterns

**Need to work with existing objects?**
- **Incompatible interface?** → Adapter
- **Add behavior?** → Decorator
- **Control access?** → Proxy
- **Simplify subsystem?** → Facade
- **Separate abstraction from implementation dimensions?** → Bridge
- **Massive number of similar objects; share intrinsic state?** → Flyweight

### Behavioral Patterns

**Need to vary algorithms/behaviors?**
- **Choose/swap algorithms at runtime?** → Strategy
- **Need to encapsulate operations/requests?** → Command
- **Need to pass requests through handlers?** → Chain of Responsibility
- **Need to coordinate multiple objects communicating?** → Mediator
- **Need to save/restore object state for undo/redo?** → Memento
- **Need to notify multiple objects of state changes?** → Observer
- **Need behavior to change with object's internal state?** → State
- **Need to traverse collections without exposing structure?** → Iterator
