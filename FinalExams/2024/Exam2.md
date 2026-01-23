# Final Exam 2024 - Design Patterns (Exam 2)

## Question (1): Multiple Choice Questions (30 Marks)

### 1. Which pattern allows decoupling of abstraction from implementation?
- a. Decorator
- b. Bridge
- c. Adapter
- d. Facade

### 2. The Adapter Pattern is used to:
- a. Provide a simplified interface to a complex subsystem
- b. Convert an interface into one expected by the client
- c. Control access to an object using a surrogate
- d. Build complex objects step by step

### 3. The Flyweight Pattern is most useful when:
- a. We need to create families of related objects
- b. We want to simplify access to a subsystem
- c. There are a large number of similar fine-grained objects
- d. We want to encapsulate a request as an object

### 4. In the Command Pattern, the object that actually executes the request is called:
- a. Receiver
- b. Invoker
- c. Client
- d. Context

### 5. Which of the following is NOT a creational pattern?
- a. Singleton
- b. Prototype
- c. Decorator
- d. Builder

### 6. The Decorator Pattern is used to:
- a. Provide global access to a single object
- b. Convert an interface into one expected by the client
- c. Add behaviour without modifying existing code (flexible alternative to subclassing)
- d. Simplify access to a subsystem

### 7. Proxy pattern is used to:
- a. Represent subsystems
- b. Handle undo functionality
- c. Control access to objects (surrogate / placeholder)
- d. Create objects based on conditions

### 8. The Facade Pattern provides:
- a. Multiple interfaces for different clients
- b. A unified interface to a subsystem
- c. Multiple instantiations of objects
- d. Lazy initialization

### 9. Which pattern uses the `clone()` method to create new objects?
- a. Factory Pattern
- b. Adapter Pattern
- c. Prototype Pattern
- d. Builder Pattern

### 10. In the Singleton pattern, what happens with lazy initialization?
- a. The object is created when the class is loaded
- b. The object is created only when first requested
- c. Multiple objects are created on demand
- d. The object cannot be created

---

## Question (2): Short Answer / Analysis (14 Marks)

### 1. Explain the difference between the Facade pattern and the Proxy pattern in terms of purpose, scope, and client access. (7 Marks)

### 2. Examine the following class. Does it violate any of the SOLID principles? Justify your answer. (7 Marks)

```java
class Invoice {
    public void calculateTotal() {
        // logic to calculate total
    }

    public void printInvoice() {
        // logic to print invoice
    }

    public void saveToDatabase() {
        // logic to save invoice
    }
}
```

---

## Question (3): Which Pattern Solves These Problems? (12 Marks)

### 1. A coffee shop sells different types of coffee (Espresso, Latte) and customers can add condiments (Milk, Sugar, Whipped Cream). The cost should be calculated based on the coffee type and added condiments. (4 Marks)

### 2. You're developing a text editor that needs to support different file formats (TXT, DOCX, PDF). When a user opens a file, the content should be displayed regardless of format. However, each format has a different reading mechanism. (4 Marks)

### 3. A logging system needs to write logs to multiple destinations (Console, File, Database). Each log entry should go to all configured destinations. Additionally, you might want to add timestamp or log level formatting. Which design pattern(s) would be most appropriate and why? (4 Marks)

---

## Question (4): UML Recognition (4 Marks)

Assume the following UML. What is the name of this pattern?

```
<<Interface>> Abstraction
 - implementor
 + operation()

<<Interface>> Implementor
 + implementation()

<<Stereotype>> RefinedAbstraction
 + refinedOperation()

<<Stereotype>> ConcreteImplementor
 + implementation()
```

---

## Question (5): UML → Implement the Pattern (6 Marks)

Implement the design pattern represented by the following UML diagram and write the name of the pattern.

```
Client

<<Interface>> Shape
 + display(in x1, in y1, in x2, in y2)

Rectangle

<<Adaptee>> LegacyRectangle
 + display(in x1, in y1, in w, in h)
```

---

## Question (6): Anti-Pattern Recognition (4 Marks)

Given the following UML class diagram, mention the anti-pattern illustrated in the diagram, and redraw the refactored class diagram. (You can redraw using text/ASCII if needed.)

```
Blob
  Order
    OrderID
    OrderTotal
    Date

    FindCustomer()
    CreateOrder()
    ValidatePayment()
    ProcessPayment()
    UpdateInventory()
    GenerateInvoice()

  Customer
    CustomerID
    Name
    Phone
    Address
```

---

## Answers (Write these at the end)

### Question (1): Multiple Choice Answer Key
1. b
2. b
3. c
4. a
5. c
6. c
7. c
8. b
9. c
10. b

---

### Question (2): Suggested Answers

1) Facade vs Proxy
- Facade: simplifies a subsystem by providing a single, high-level interface; scope is typically a whole subsystem; client uses the facade for convenience.
- Proxy: controls access to a specific object (the “real subject”); scope is usually one object/service; client uses the proxy as a substitute that can add access control, caching, lazy loading, logging, etc.

2) SOLID violations in `Invoice`
- Violates SRP (Single Responsibility Principle): the class has multiple responsibilities (calculation, printing, persistence).
- Typical refactor: split into classes like `InvoiceCalculator`, `InvoicePrinter`, `InvoiceRepository` (or persistence service), while keeping `Invoice` as a data/model object.

---

### Question (3): Suggested Answers

1) Decorator Pattern
- Base coffee = component; condiments = decorators; total cost computed by wrapping.

2) Strategy Pattern (often used with a Factory to choose the right strategy)
- Define a `FileReader` interface; implement `TxtReader`, `DocxReader`, `PdfReader`; select based on file type at runtime.

3) **Composite + Decorator** (NOT Observer)

**Why Composite + Decorator is correct:**

**Composite Pattern:**
- **Structure:** All loggers (Console, File, Database) implement the same `Logger` interface with `log()` method
- **Intent:** A `CompositeLogger` contains multiple `Logger` objects and when `log()` is called, it forwards the call to all child loggers
- **Why Composite fits:** The problem states destinations are distinct types of Loggers. You want to treat a "Group of Loggers" exactly the same as a "Single Logger" - this is the textbook definition of Composite
- **Fan-out behavior:** The same log entry must go to multiple destinations simultaneously (fan-out), not event-driven notifications

**Decorator Pattern:**
- **Purpose:** Add formatting (timestamp, log level) without modifying existing destination code
- **Structure:** Formatting decorators wrap loggers and add behavior before/after delegating to the wrapped logger
- **Flexibility:** Can stack decorators (e.g., `TimestampDecorator(LogLevelDecorator(CompositeLogger))`)

**Why Observer is WRONG:**
- **Observer Pattern:** Designed for one-to-many dependencies where subjects notify observers of events
- **Structure:** Subject (`log()`) and Observer (`update()`) usually have **different interfaces**
- **Intent:** "I have changed state (new message). I don't care what you do with it, but here is the info."
- **Why it doesn't fit:** Observer implies loose coupling and event-driven notifications. Here, logging to file and database are fundamentally the same operation (writing data) via different mediums, so structuring them as a Composite set of `Logger` objects is cleaner

**Example Structure:**
```java
// Composite: Multiple destinations
Logger compositeLogger = new CompositeLogger(
    new ConsoleLogger(),
    new FileLogger(),
    new DatabaseLogger()
);

// Decorator: Add formatting
Logger formattedLogger = new TimestampDecorator(
    new LogLevelDecorator(compositeLogger)
);

// Usage: Single call sends to all destinations with formatting
formattedLogger.log("Error occurred");
```

**Key Distinction:**
- **Observer:** Primary goal is **decoupling** logic - Subject doesn't know what Observers do
- **Composite:** Primary goal is **aggregation** - Composite specifically executes the same command on a collection of objects sharing the same interface
- Composite (or “fan-out” logger): send the same log message to multiple destinations (console/file/db).
- Decorator: add formatting like timestamp / log level without modifying existing destination code.

---

### Question (4): UML Recognition Answer
Bridge Pattern

---

### Question (5): UML → Pattern + Implementation Answer
Adapter Pattern

Implementation idea:
- Target: `Shape.display(x1, y1, x2, y2)`
- Adaptee: `LegacyRectangle.display(x1, y1, w, h)`
- Adapter: `RectangleAdapter implements Shape`, wraps a `LegacyRectangle`, converts \((x2, y2)\) into \((w, h)\) where \(w = x2 - x1\), \(h = y2 - y1\).

---

### Question (6): Anti-Pattern Answer
Anti-pattern: God Object / Blob

Refactor idea (example split):
- `OrderService`: create order, coordinate workflow
- `PaymentService`: validate/process payment
- `InventoryService`: update inventory
- `InvoiceService` (or `InvoiceGenerator`): generate invoice
- `CustomerService` or `CustomerRepository`: find customer

