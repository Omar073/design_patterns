# Final Exam 2025 - Design Patterns

## Question (1): Multiple Choice Questions (40 Marks)

### 1. Which pattern provides a surrogate or placeholder for another object to control access to it?
- a. Decorator  
- b. Proxy  
- c. Adapter  
- d. Composite  

### 2. Which of the following patterns dynamically attaches additional responsibilities to an object?
- a. Decorator  
- b. Proxy  
- c. Adapter  
- d. Bridge  

### 3. In which design pattern does a client interact with multiple incompatible interfaces by converting them into a unified interface?
- a. Adapter  
- b. Proxy  
- c. Façade  
- d. Bridge  

### 4. The Façade pattern is used to:
- a. Add responsibilities to an object at runtime  
- b. Control access to an object  
- c. Convert one interface into another expected by the client  
- d. Simplify a complex system by providing a unified interface  

### 5. Which of the following best describes the Flyweight pattern?
- a. It adds functionality to an object at runtime  
- b. It reduces memory usage by sharing common state among objects  
- c. It controls access to objects  
- d. It simplifies interactions with complex systems  

### 6. The Composite pattern allows:
- a. Objects to be treated as individual objects or compositions of objects  
- b. A client to control access to a single object  
- c. Multiple incompatible interfaces to be adapted  
- d. Objects to be created without exposing instantiation logic  

### 7. What does the Bridge pattern decouple?
- a. The front-end from the back-end  
- b. A client from the server  
- c. Abstraction from its implementation  
- d. The user interface from business logic  

### 8. Which pattern provides runtime modification capabilities to objects?
- a. Proxy  
- b. Decorator  
- c. Composite  
- d. Adapter  

### 9. What type of design pattern is the Proxy pattern?
- a. Creational  
- b. Structural  
- c. Behavioural  
- d. Factory  

### 10. Which design pattern allows you to fit more objects into memory by sharing data?
- a. Flyweight  
- b. Bridge  
- c. Composite  
- d. Decorator  

### 11. Which of the following patterns is used to decouple algorithm implementations at runtime?
- a. Strategy  
- b. State  
- c. Command  
- d. Memento  

### 12. The Command pattern encapsulates:
- a. Multiple incompatible interfaces  
- b. A request as an object  
- c. The behaviour of an algorithm  
- d. The state of an object  

### 13. In the Chain of Responsibility pattern, how is the request typically processed?
- a. By passing the request to the next object in the chain  
- b. By delegating the request to multiple objects simultaneously  
- c. By storing the request as a memento  
- d. By selecting a strategy at runtime  

### 14. What does the Memento pattern do?
- a. Encapsulates a request as an object  
- b. Provides a way to capture and restore an object's internal state  
- c. Defines a one-to-many dependency between objects  
- d. Allows an object to change behaviour at runtime  

### 15. In the Iterator pattern, the main purpose is to:
- a. Traverse elements of a collection without exposing the underlying representation  
- b. Capture and externalize the internal state of an object  
- c. Change an object's behaviour when its state changes  
- d. Dynamically add functionality to an object  

### 16. Which pattern defines a family of algorithms and makes them interchangeable?
- a. Strategy  
- b. State  
- c. Command  
- d. Memento  

### 17. Which pattern centralizes communication between multiple objects?
- a. Mediator  
- b. Observer  
- c. Chain of Responsibility  
- d. Iterator  

### 18. The Singleton pattern ensures that:
- a. A class can have multiple instances  
- b. A class has only one instance  
- c. A class provides many global points of access  
- d. Objects are created without exposing the instantiation logic  

### 19. Which of the following is NOT represented in a UML class diagram?
- a. Class names  
- b. Attributes  
- c. Methods  
- d. Details of how classes interact at runtime  

### 20. In UML, what is indicated by a hollow diamond in a class diagram?
- a. Inheritance / Generalization  
- b. Composition  
- c. Aggregation  
- d. Dependency  

---

## Question (2): What is the best pattern for each scenario? (25 Marks)

### 1. You want to implement logging in your application to track user actions across different modules (authentication, file management, etc.). The logging module should be accessible globally and should only have one instance. (5 Marks)

### 2. A home theatre system consists of many components (TV, speakers, DVD player, etc.). The user wants to perform common tasks like "watch a movie" without dealing with the complexity of turning on each component individually. (5 Marks)

### 3. You have an existing service that provides temperature in Celsius, but a new client wants the temperature in Fahrenheit. You don't want to modify the original class, but you need to adapt it to the new requirement. (5 Marks)

### 4. In your e-commerce application, users can choose different payment methods (e.g., PayPal, Credit Card, Bank Transfer). The system should allow switching between these methods without modifying the core payment logic. (5 Marks)

### 5. You are designing a meal-ordering system for a restaurant where customers can customize their meals by selecting various components (main course, side dish, drink). Each order has different possible configurations. (5 Marks)

---

## Question (3): UML Recognition and Implementation (15 Marks)

Assume the following UML. What is the name of this pattern? Implement this pattern using Java.

```
PatternDemo

Pattern           <<Interface>>
 + doOperation(): int

OperationAdd        implements Pattern
 + doOperation(): int

OperationSubtract   implements Pattern
 + doOperation(): int

OperationMultiply   implements Pattern
 + doOperation(): int

Context
 - pattern: Pattern
 + executePattern(): int   // uses / asks Pattern
```

---

## Answers (Write these at the end)

### Question (1): Multiple Choice Answer Key
1. b (Proxy)  
2. a (Decorator)  
3. a (Adapter)  
4. d (Façade)  
5. b (Reduces memory by sharing state)  
6. a (Treat individual objects and compositions uniformly)  
7. c (Abstraction from its implementation)  
8. b (Decorator)  
9. b (Structural)  
10. a (Flyweight)  
11. a (Strategy)  
12. b (A request as an object)  
13. a (Pass request to next in chain)  
14. b (Capture and restore internal state)  
15. a (Traverse collection without exposing representation)  
16. a (Strategy)  
17. a (Mediator)  
18. b (Only one instance)  
19. d (Runtime interaction details)  
20. c (Aggregation – shared ownership)  

---

### Question (2): Suggested Answers

1) Logging module with a single global instance  
**Pattern:** Singleton  
- Ensures only one instance of the logger.  
- Provides a global access point (e.g., `Logger.getInstance()`).  

2) Home theatre "watch a movie" operation  
**Pattern:** Facade  
- Provides a simple high-level interface (e.g., `watchMovie()`) that internally coordinates TV, speakers, DVD player, etc.  

3) Celsius service to Fahrenheit client  
**Pattern:** Adapter  
- Wraps the Celsius service and converts its output to Fahrenheit for the new client.  

4) Multiple payment methods (PayPal, Credit Card, Bank Transfer)  
**Pattern:** Strategy  
- Each payment method is a concrete strategy; the context (payment processor) delegates to the selected strategy.  

5) Meal-ordering with configurable components  
**Pattern:** Builder  
- Separates construction of a complex object (meal) from its representation; allows step-by-step configuration.  

---

### Question (3): UML Recognition and Implementation Answer

**Pattern name:** Strategy Pattern  

**Implementation idea (see `FinalExams/2025/Question3.java`):**
- `Pattern` is the strategy interface with `doOperation()`.  
- `OperationAdd`, `OperationSubtract`, `OperationMultiply` are concrete strategies implementing `Pattern`.  
- `Context` has a reference to `Pattern` and exposes `executePattern()` that calls `pattern.doOperation()`.  
- `PatternDemo` (main) creates different `Pattern` implementations and passes them to `Context` to switch behaviour at runtime.  

