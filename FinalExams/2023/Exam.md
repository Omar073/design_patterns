# Final Exam 2023 - Design Patterns

## Question (1): True / False (15 Marks)

For each statement, write **T** for true or **F** for false, and correct the statement if it is false.

1. Design pattern is a solution to a problem that occurs repeatedly in a variety of contexts.  
2. Prototype pattern prevents one from creating more than one instance of a variable.  
3. Decorator supports recursive composition.  
4. Chain of Responsibility pattern is one of the behavioural patterns.  
5. It is possible to write a thread-safe Singleton in Java.  
6. Bridge makes things work after they're designed; Adapter makes them work before they are.  
7. A design pattern used to enhance the functionality of an object is Decorator.  

---

## Question (2): Short Answer Questions (10 Marks)

Answer the following:

a. Explain the benefits of using design patterns in Java.  
b. What is the meaning of SOLID principles?  
c. Explain the advantage of the Chain of Responsibility pattern and when it is used.  
d. How is the Bridge pattern different from the Adapter pattern?  
e. What are the drawbacks of using the Singleton design pattern?  

---

## Question (3): Multiple Choice Questions (5 Marks)

### a. Which design pattern creates duplicate objects?
- 1) Prototype pattern  
- 2) Factory pattern  
- 3) Facade pattern  
- 4) Builder pattern  

### b. Patterns are important because:
- 1) They capture the design of other experts  
- 2) They capture expert design knowledge  
- 3) None of these  
- 4) All of these  

### c. Which of the following are concerned with communication between objects?
- 1) Design Patterns  
- 2) Creational Design Patterns  
- 3) Behavioural Design Patterns  
- 4) Structural Design Patterns  

### d. Attach additional responsibilities to an object dynamically. It provides a flexible alternative to subclassing for extending functionality.
- a. Chain of Responsibility  
- b. Decorator  
- c. Adapter  
- d. Composite  

### e. Define an interface for creating an object, but let the subclasses decide which class to instantiate. It lets the instantiation defer to subclasses.
- a. Factory Method  
- b. Builder  
- c. Abstract Factory  
- d. Prototype  

---

## Question (4): UML Pattern Implementation (10 Marks)

Consider the following UML diagram. Implement this pattern in Java.

```
Strategy              <<Interface>>
 + doOperation(): int

OperationAdd        implements Strategy
 + doOperation(): int

OperationSubtract   implements Strategy
 + doOperation(): int

OperationMultiply   implements Strategy
 + doOperation(): int

Context
 - strategy: Strategy
 + executeStrategy(): int   // uses Strategy

StrategyPatternDemo
 + main(): void   // asks Context
```

---

## Question (5): UML Pattern Implementation (10 Marks)

Consider the following UML diagram. Implement this diagram using Java. What is the appropriate design pattern for it?

```
Shape          <<Interface>>
 + draw(): void

Circle       implements Shape
 + draw(): void

Rectangle    implements Shape
 + draw(): void

Square       implements Shape
 + draw(): void

ShapeMaker
 - circle: Shape
 - rectangle: Shape
 - square: Shape
 + ShapeMaker()
 + drawCircle(): void
 + drawRectangle(): void
 + drawSquare(): void

PatternDemo
 + main(): void   // uses ShapeMaker
```

---

## Answers (Write these at the end)

### Question (1): True / False Answer Key

1. T – Correct as stated.  
2. F – This describes Singleton; Prototype creates new objects by cloning, it does not restrict the number of instances.  
3. T – Decorator supports recursive composition by wrapping objects in other decorators.  
4. T – Chain of Responsibility is a behavioural pattern.  
5. T – It is possible to implement a thread-safe Singleton in Java (e.g., using synchronized, double-checked locking, or enum).  
6. F – Adapter makes things work **after** they are designed (adapts existing interfaces); Bridge is used **before** they are designed to separate abstraction from implementation.  
7. T – Decorator enhances functionality by adding responsibilities to an object dynamically.  

---

### Question (2): Suggested Answers

a) Benefits of design patterns in Java  
- Provide reusable solutions to common design problems.  
- Improve code readability and maintainability by giving a shared vocabulary (names like Singleton, Strategy, Facade).  
- Encourage best practices such as loose coupling, high cohesion, and separation of concerns.  
- Help new developers understand and extend existing systems more easily.  

b) Meaning of SOLID principles  
- **S**ingle Responsibility: a class should have one reason to change.  
- **O**pen/Closed: open for extension, closed for modification.  
- **L**iskov Substitution: subclasses should be substitutable for their base classes.  
- **I**nterface Segregation: many specific interfaces are better than one general-purpose interface.  
- **D**ependency Inversion: depend on abstractions, not on concrete implementations.  

c) Advantage and use of Chain of Responsibility  
- Allows multiple handlers to process a request without the sender knowing which handler will handle it.  
- Promotes loose coupling between sender and receiver.  
- Used when you have a chain of processing steps (e.g., logging, validation, authorization) where each handler can handle or pass the request along the chain.  

d) Bridge vs Adapter  
- **Bridge:** Designed up-front to separate abstraction from implementation so both can vary independently. It’s part of the system design.  
- **Adapter:** Applied later to make existing, incompatible interfaces work together without changing them. It’s a retrofitting solution.  

e) Drawbacks of Singleton  
- Can introduce global state, making code harder to test and reason about.  
- Can hide dependencies (clients call `getInstance()` instead of receiving dependencies explicitly).  
- Can lead to tight coupling and potential concurrency issues if not implemented correctly.  

---

### Question (3): Multiple Choice Answer Key

a. 1) Prototype pattern  
b. 4) All of these  
c. 3) Behavioural Design Patterns  
d. b) Decorator  
e. a) Factory Method  

---

### Question (4): UML Pattern Implementation Answer

**Pattern name:** Strategy Pattern  

Implementation idea (see `FinalExams/2023/Question4.java`):  
- `Strategy` is the interface with `doOperation(int a, int b)`.  
- `OperationAdd`, `OperationSubtract`, `OperationMultiply` implement `Strategy`.  
- `Context` has a `Strategy` field and `executeStrategy(a, b)` that delegates to the current strategy.  
- `StrategyPatternDemo` (main) selects different strategies at runtime and calls `executeStrategy`.  

---

### Question (5): UML Pattern Implementation Answer

**Pattern name:** Facade Pattern  

Implementation idea (see `FinalExams/2023/Question5.java`):  
- `Shape` is the interface with `draw()`.  
- `Circle`, `Rectangle`, and `Square` implement `Shape`.  
- `ShapeMaker` is the Facade that knows how to create and use the different shapes; it exposes simple methods `drawCircle()`, `drawRectangle()`, `drawSquare()`.  
- `PatternDemo` (main) uses only `ShapeMaker` to draw shapes, without dealing with individual shape classes directly.  

