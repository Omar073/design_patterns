# Design Patterns Practice Questions
## Creational Patterns

---

## Question (1): Short Answer Questions

### 1. A pattern ensures a class has only one instance and provides a global point of access to it. What pattern is this?

### 2. Which pattern defines an interface for creating an object, but lets subclasses decide which class to instantiate?

### 3. A pattern allows you to create families of related objects without specifying their concrete classes. What pattern is this?

### 4. You're designing a system where you need to create objects step by step. The construction process should allow you to build different representations of the same object. Which pattern is this?

### 5. A pattern lets you copy existing objects without making your code dependent on their classes. What pattern is this?

### 6. Which pattern provides a way to create objects without exposing the instantiation logic to the client?

### 7. What does the Singleton pattern ensure?

### 8. Which design pattern creates duplicate objects?

### 9. You have a complex object with many optional parameters. Instead of using a constructor with many parameters, you want a more flexible way to construct the object. Which pattern provides a fluent interface for building objects?

### 10. You need to create objects that are expensive to instantiate. Instead of creating new instances, you want to clone existing instances. What pattern is this?

---

## Question (2): What is the best pattern for each scenario?

### 1. You want to implement logging in your application to track user actions across different modules. The logging module should be accessible globally and should only have one instance.

### 2. You are designing a meal-ordering system for a restaurant where customers can customize their meals by selecting various components (main course, side dish, drink). Each order has different possible configurations.

### 3. You need to ensure that only one instance of a database connection manager exists throughout your application's lifetime. The instance should be created only when it's first needed.

### 4. You need to create objects that are expensive to instantiate. Instead of creating new instances, you want to clone existing instances.

### 5. You want to create a UI factory that produces buttons, checkboxes, and text fields that all match a specific theme (Windows, macOS, or Linux style).

---

## Question (3): True/False Questions

Put T/F and correct the wrong answer.

### 1. Prototype pattern prevents one from creating more than one instance of a variable.

### 2. It is possible to write thread-safe singleton in Java.

### 3. The Builder pattern provides a fluent interface for constructing complex objects.

### 4. The Factory Method pattern lets subclasses decide which class to instantiate.

### 5. The Singleton pattern ensures only one instance exists throughout the application.

### 6. The Abstract Factory pattern creates families of related objects.

---

## Answers

## Question (1): Short Answer Answers

### Answer 1
**Singleton Pattern** - The Singleton pattern ensures a class has only one instance and provides global access.

### Answer 2
**Factory Method Pattern** (or **Factory** - any factory-related answer is acceptable) - The Factory Method pattern lets subclasses decide which class to instantiate.

### Answer 3
**Abstract Factory Pattern** (or **Factory** - any factory-related answer is acceptable) - The Abstract Factory pattern creates families of related objects.

### Answer 4
**Builder Pattern** - The Builder pattern constructs objects step by step with different representations.

### Answer 5
**Prototype Pattern** - The Prototype pattern lets you copy existing objects without dependency on their classes.

### Answer 6
**Factory Pattern** (or **Factory Method**, **Abstract Factory**, or any factory-related pattern - all are acceptable) - All factory-related patterns create objects without exposing instantiation logic.

### Answer 7
**A class has only one instance** - The Singleton pattern ensures only one instance exists.

### Answer 8
**Prototype Pattern** - The Prototype pattern creates duplicate objects by cloning.

### Answer 9
**Builder Pattern** - The Builder pattern provides a fluent interface for constructing complex objects.

### Answer 10
**Prototype Pattern** - The Prototype pattern clones existing instances instead of creating new ones.

---

## Question (2): Scenario-Based Answers

### Answer 1
**Singleton Pattern** - The logging module needs to be globally accessible with only one instance.

### Answer 2
**Builder Pattern** - The Builder pattern allows step-by-step construction of meals with different configurations.

### Answer 3
**Singleton Pattern** - The Singleton pattern ensures only one instance of the database connection manager exists with lazy initialization.

### Answer 4
**Prototype Pattern** - The Prototype pattern clones existing instances instead of creating expensive new ones.

### Answer 5
**Abstract Factory Pattern** - The Abstract Factory pattern creates families of related UI components that match a specific theme.

---

## Question (3): True/False Answers

### Answer 1
**False** - The Prototype pattern creates duplicate objects by cloning. The Singleton pattern prevents creating more than one instance.

### Answer 2
**True** - It is possible to write thread-safe singleton in Java using synchronized blocks or other synchronization mechanisms.

### Answer 3
**True** - The Builder pattern provides a fluent interface for constructing complex objects.

### Answer 4
**True** - The Factory Method pattern lets subclasses decide which class to instantiate.

### Answer 5
**True** - The Singleton pattern ensures only one instance exists throughout the application.

### Answer 6
**True** - The Abstract Factory pattern creates families of related objects.

---

## Pattern Quick Reference

### Creational Patterns
- **Singleton**: Ensure only one instance exists
- **Factory Method**: Create objects through subclasses
- **Abstract Factory**: Create families of related objects
- **Builder**: Construct complex objects step by step
- **Prototype**: Clone existing objects

---

## Study Tips

1. **Focus on Intent**: Each pattern solves a specific problem. Understand the problem first, then the solution.

2. **Compare Similar Patterns**:
   - **Factory Method vs Abstract Factory**: Factory Method creates one product; Abstract Factory creates families
   - **Prototype vs Singleton**: Prototype creates duplicates; Singleton ensures only one instance

3. **Look for Keywords**:
   - "Only one instance" → Singleton
   - "Step by step construction" → Builder
   - "Clone existing object" → Prototype
   - "Create families of objects" → Abstract Factory
   - "Subclasses decide instantiation" → Factory Method

4. **Practice Scenarios**: Think of real-world examples for each pattern to reinforce understanding.
