# Design Patterns Practice Questions
## All Patterns (Creational, Structural, and Behavioral)

---

## Question (1): Short Answer Questions
### 1. Which pattern provides a surrogate or placeholder for another object to control access to it?

### 2. A pattern ensures a class has only one instance and provides a global point of access to it. What pattern is this?

### 3. Which pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime?

### 4. Which of the following patterns dynamically attaches additional responsibilities to an object?

### 5. Which pattern allows an object to alter its behavior when its internal state changes?

### 6. In which design pattern does a client interact with multiple incompatible interfaces by converting them into a unified interface?

### 7. A pattern lets you copy existing objects without making your code dependent on their classes. What pattern is this?

### 8. A pattern allows an object to notify multiple dependent objects about state changes. What pattern is this?

### 9. What is the main purpose of the Facade pattern?

### 10. Which pattern defines an interface for creating an object, but lets subclasses decide which class to instantiate?

### 11. Which pattern encapsulates a request as an object, allowing you to parameterize clients with different requests, queue requests, and support undo operations?

### 12. Which pattern best describes a pattern that reduces memory usage by sharing common state among objects?

### 13. You're designing a system where you need to create objects step by step. The construction process should allow you to build different representations of the same object. Which pattern is this?

### 14. Which pattern provides runtime modification capabilities to objects?

### 15. Which pattern passes a request along a chain of handlers, where each handler decides either to process the request or pass it to the next handler?

### 16. What type of design pattern is a pattern that provides a surrogate or placeholder to control access to an object?

### 17. A pattern allows you to create families of related objects without specifying their concrete classes. What pattern is this?

### 18. Which design pattern allows you to fit more objects into memory by sharing data?

### 19. Which pattern provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation?

### 20. Which pattern separates an object's interface from its implementation so that the two can vary independently?

### 21. What does the Singleton pattern ensure?

### 22. Which pattern defines how a set of objects interact by encapsulating their communication in a mediator object?

### 23. What does the Bridge pattern decouple?

### 24. Which pattern captures and externalizes an object's internal state so that the object can be restored to this state later?

### 25. Which pattern provides a way to create objects without exposing the instantiation logic to the client?

### 26. You're building a payment system that needs to support different payment methods (Credit Card, PayPal, Bank Transfer). The payment method should be selectable at runtime. Which pattern should you use?

### 27. Which pattern allows you to use a large number of fine-grained objects efficiently by sharing common state?

### 28. You're building a weather monitoring system where multiple displays need to be updated whenever weather data changes. Which pattern should you use?

### 29. Which pattern attaches additional responsibilities to an object dynamically and provides a flexible alternative to subclassing for extending functionality?

### 30. You're building a vending machine where the behavior changes based on the current state (Idle, HasMoney, Dispensing). Which pattern should you use?

### 31. Which design pattern is used to convert one interface of a class into another interface that clients expect?

### 32. You're building a text editor that needs to support undo/redo operations. Each action should be encapsulated as an object. Which pattern should you use?

### 33. Which design pattern creates duplicate objects?

### 34. You're building a customer support system where requests can be handled by different support levels (Level 1, Level 2, Level 3). If one level can't handle it, it passes to the next. Which pattern should you use?

### 35. Which pattern allows you to add new functionality to objects dynamically at runtime without modifying the original class?

### 36. You're building a collection class and want to provide a uniform way to traverse its elements without exposing the internal structure. Which pattern should you use?

### 37. You need to control access to an object. For example, you want to load a large image only when it's actually needed, or add logging before and after method calls. Which pattern provides this functionality?

### 38. You're building a chat application where multiple users need to communicate, but you want to avoid direct references between users. Which pattern should you use?

### 39. Which pattern provides a simplified interface to a complex subsystem?

### 40. You're building a game where you need to save and restore the game state at any point. Which pattern should you use?

### 41. You're working on a graphics application where you need to separate the abstraction (shape) from its implementation (rendering API). Which pattern should you use?

### 42. You have a complex object with many optional parameters. Instead of using a constructor with many parameters, you want a more flexible way to construct the object. Which pattern provides a fluent interface for building objects?

### 43. You have a legacy payment system that uses a different interface than what your new code expects. You cannot modify the legacy code. Which pattern would help you integrate these systems?

### 44. You need to create objects that are expensive to instantiate. Instead of creating new instances, you want to clone existing instances. What pattern is this?

### 45. You're working on a game engine where you need to render thousands of enemy units on screen. Each enemy has shared properties (sprite, animation) and unique properties (position, health). You want to minimize memory usage. Which pattern is most suitable?

### 46. You're building a word processor that needs to display thousands of letters on screen. Each letter has shared properties (typeface, size, style) and unique properties (position). You want to minimize memory usage. Which pattern should you use?

---

## Question (2): What is the best pattern for each scenario?

### 1. You want to implement logging in your application to track user actions across different modules. The logging module should be accessible globally and should only have one instance.

### 2. You're building a payment processing system that needs to support multiple payment strategies (Credit Card, PayPal, Cryptocurrency). The payment method should be selectable at runtime without modifying the payment processor code.

### 3. A home theatre system consists of many components (TV, speakers, DVD player, etc.). The user wants to perform common tasks like "watch a movie" without dealing with the complexity of turning on each component individually.

### 4. You're building a stock market application where multiple investors need to be notified immediately when stock prices change. You want to decouple the stock price source from the investors.

### 5. You have an existing service that provides temperature in Celsius, but a new client wants the temperature in Fahrenheit. You don't want to modify the original class, but you need to adapt it to the new requirement.

### 6. You're building a media player that behaves differently when it's in different states (Playing, Paused, Stopped). The player's buttons (Play, Pause, Stop) should have different behaviors based on the current state.

### 7. You're building a sandwich shop application. You have a base Sandwich class, and you want to add extras (lettuce, tomato, cheese, bacon) dynamically. Each extra adds to the price and description.

### 8. You're building a remote control system where you want to encapsulate operations (like turning lights on/off, adjusting volume) as objects. This will allow you to queue operations, log them, and support undo functionality.

### 9. You are designing a meal-ordering system for a restaurant where customers can customize their meals by selecting various components (main course, side dish, drink). Each order has different possible configurations.

### 10. You're building an approval workflow system where purchase requests need to go through multiple approval levels (Manager, Director, CEO). Each level can approve or pass the request to the next level.

### 11. You're building a graphics library where shapes can be rendered using different APIs (OpenGL, DirectX, Vulkan). You want to be able to add new shapes and new rendering APIs independently.

### 12. You're building a custom collection class (like a tree or graph) and want to provide a way for clients to traverse all elements without knowing the internal structure of the collection.

### 13. You're working on a game engine where you need to render thousands of enemy units on screen. Each enemy has a type (Goblin, Orc, Troll) with shared properties (sprite, animation), and unique properties (position, health). You want to minimize memory usage.

### 14. You're building an air traffic control system where multiple airplanes need to communicate with each other, but you want to avoid direct communication between airplanes to reduce coupling.

### 15. You need to ensure that only one instance of a database connection manager exists throughout your application's lifetime. The instance should be created only when it's first needed.

### 16. You're building a text editor that needs to support undo/redo functionality. You need to save the state of the document at various points so users can restore previous states.

### 17. You have a third-party library that uses a different interface than what your application expects. You cannot modify the library's source code.

### 18. You're building a game where different characters can use different attack strategies (Melee, Ranged, Magic). The attack strategy should be changeable at runtime.

### 19. You're building a document editor where you can apply multiple formatting options to text (bold, italic, underline) and combine them. Each formatting option should be independent and composable.

### 20. You're building a news application where subscribers need to be notified when new articles are published. The publisher should not need to know about specific subscribers.

### 21. You need to control access to an object. For example, you want to load a large image only when it's actually needed, or you want to add logging before and after method calls.

### 22. You need to create objects that are expensive to instantiate. Instead of creating new instances, you want to clone existing instances.

### 23. You're building a word processor that needs to display thousands of letters on screen. Each letter has properties like typeface, point size, and style (which are shared), and position (which is unique). You want to minimize memory usage.

### 24. You want to create a UI factory that produces buttons, checkboxes, and text fields that all match a specific theme (Windows, macOS, or Linux style).

### 25. You have a complex subsystem with many classes and interfaces. Instead of making clients interact with all these classes directly, you want to provide a simple interface that handles all the complexity.

---

## Question (3): True/False Questions

Put T/F and correct the wrong answer.

### 1. Design pattern is a solution to a problem that occurs repeatedly in a variety of contexts.

### 2. The Strategy pattern allows you to swap algorithms at runtime.

### 3. Prototype pattern prevents one from creating more than one instance of a variable.

### 4. The Observer pattern allows one-to-many dependency between objects.

### 5. Decorator supports recursive composition.

### 6. The State pattern allows an object to change its behavior when its internal state changes.

### 7. It is possible to write thread-safe singleton in Java.

### 8. The Command pattern encapsulates requests as objects.

### 9. Adapter pattern is one of the structural patterns.

### 10. The Chain of Responsibility pattern passes requests along a chain of handlers.

### 11. A design pattern used to enhance the functionality of an object is decorator.

### 12. The Iterator pattern provides a way to traverse collections without exposing their internal structure.

### 13. The Bridge pattern separates abstraction from implementation.

### 14. The Mediator pattern reduces coupling by preventing objects from referring to each other directly.

### 15. The Flyweight pattern is used to reduce memory usage by sharing common state.

### 16. The Memento pattern allows you to save and restore an object's state.

### 17. The Proxy pattern can be used for lazy loading.

### 18. The Strategy pattern uses inheritance to swap algorithms.

### 19. The Builder pattern provides a fluent interface for constructing complex objects.

### 20. The Observer pattern requires tight coupling between subject and observers.

### 21. The Factory Method pattern lets subclasses decide which class to instantiate.

### 22. The Singleton pattern ensures only one instance exists throughout the application.

### 23. The Facade pattern simplifies interactions with complex subsystems.

### 24. The Adapter pattern makes incompatible interfaces work together.

### 25. The Abstract Factory pattern creates families of related objects.

---

## Answers

## Question (1): Short Answer Answers

### Answer 1
**Proxy Pattern** - The Proxy pattern provides a surrogate or placeholder to control access to an object.

### Answer 2
**Singleton Pattern** - The Singleton pattern ensures a class has only one instance and provides global access.

### Answer 3
**Strategy Pattern** - The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime.

### Answer 4
**Decorator Pattern** - The Decorator pattern dynamically attaches additional responsibilities to an object.

### Answer 5
**State Pattern** - The State pattern allows an object to alter its behavior when its internal state changes.

### Answer 6
**Adapter Pattern** - The Adapter pattern allows clients to interact with incompatible interfaces by converting them.

### Answer 7
**Prototype Pattern** - The Prototype pattern lets you copy existing objects without dependency on their classes.

### Answer 8
**Observer Pattern** - The Observer pattern allows an object to notify multiple dependent objects about state changes.

### Answer 9
**To simplify a complex system by providing a unified interface** - The Facade pattern provides a simplified interface to a complex subsystem.

### Answer 10
**Factory Method Pattern** (or **Factory** - any factory-related answer is acceptable) - The Factory Method pattern lets subclasses decide which class to instantiate.

### Answer 11
**Command Pattern** - The Command pattern encapsulates a request as an object, allowing parameterization, queuing, and undo operations.

### Answer 12
**It reduces memory usage by sharing common state among objects** - The Flyweight pattern shares intrinsic state to minimize memory usage.

### Answer 13
**Builder Pattern** - The Builder pattern constructs objects step by step with different representations.

### Answer 14
**Decorator Pattern** - The Decorator pattern provides runtime modification capabilities by wrapping objects.

### Answer 15
**Chain of Responsibility Pattern** - The Chain of Responsibility pattern passes a request along a chain of handlers.

### Answer 16
**Structural** - The Proxy pattern is a structural design pattern.

### Answer 17
**Abstract Factory Pattern** (or **Factory** - any factory-related answer is acceptable) - The Abstract Factory pattern creates families of related objects.

### Answer 18
**Flyweight Pattern** - The Flyweight pattern allows fitting more objects into memory by sharing data.

### Answer 19
**Iterator Pattern** - The Iterator pattern provides sequential access to elements of an aggregate without exposing its structure.

### Answer 20
**Bridge Pattern** - The Bridge pattern separates an object's interface from its implementation.

### Answer 21
**A class has only one instance** - The Singleton pattern ensures only one instance exists.

### Answer 22
**Mediator Pattern** - The Mediator pattern defines how objects interact by encapsulating their communication.

### Answer 23
**Abstraction from its implementation** - The Bridge pattern decouples abstraction from implementation.

### Answer 24
**Memento Pattern** - The Memento pattern captures and externalizes an object's internal state for later restoration.

### Answer 25
**Factory Pattern** (or **Factory Method**, **Abstract Factory**, or any factory-related pattern - all are acceptable) - All factory-related patterns create objects without exposing instantiation logic.

### Answer 26
**Strategy Pattern** - The Strategy pattern allows selecting payment methods at runtime without modifying the payment processor.

### Answer 27
**Flyweight Pattern** - The Flyweight pattern shares common state among many fine-grained objects.

### Answer 28
**Observer Pattern** - The Observer pattern allows multiple displays to be notified when weather data changes.

### Answer 29
**Decorator Pattern** - The Decorator pattern attaches responsibilities dynamically.

### Answer 30
**State Pattern** - The State pattern allows the vending machine to change behavior based on its current state.

### Answer 31
**Adapter Pattern** - The Adapter pattern converts one interface to another that clients expect.

### Answer 32
**Command Pattern** - The Command pattern encapsulates actions as objects, enabling undo/redo functionality.

### Answer 33
**Prototype Pattern** - The Prototype pattern creates duplicate objects by cloning.

### Answer 34
**Chain of Responsibility Pattern** - The Chain of Responsibility pattern allows requests to pass through multiple approval levels.

### Answer 35
**Decorator Pattern** - The Decorator pattern adds functionality dynamically without modifying the original class.

### Answer 36
**Iterator Pattern** - The Iterator pattern provides uniform traversal without exposing collection structure.

### Answer 37
**Proxy Pattern** - The Proxy pattern controls access, enabling lazy loading and logging.

### Answer 38
**Mediator Pattern** - The Mediator pattern allows communication between users without direct references.

### Answer 39
**Facade Pattern** - The Facade pattern provides a simplified interface to a complex subsystem.

### Answer 40
**Memento Pattern** - The Memento pattern allows saving and restoring game state at any point.

### Answer 41
**Bridge Pattern** - The Bridge pattern separates abstraction (shape) from implementation (rendering API).

### Answer 42
**Builder Pattern** - The Builder pattern provides a fluent interface for constructing complex objects.

### Answer 43
**Adapter Pattern** - The Adapter pattern helps integrate systems with incompatible interfaces.

### Answer 44
**Prototype Pattern** - The Prototype pattern clones existing instances instead of creating new ones.

### Answer 45
**Flyweight Pattern** - The Flyweight pattern minimizes memory by sharing common properties.

### Answer 46
**Flyweight Pattern** - The Flyweight pattern shares common properties (typeface, size, style) to save memory.

---

## Question (2): Scenario-Based Answers

### Answer 1
**Singleton Pattern** - The logging module needs to be globally accessible with only one instance.

### Answer 2
**Strategy Pattern** - The Strategy pattern allows selecting payment methods at runtime without modifying the payment processor code.

### Answer 3
**Facade Pattern** - The Facade pattern simplifies the complex home theatre system by providing a unified interface.

### Answer 4
**Observer Pattern** - The Observer pattern decouples the stock price source from investors, allowing multiple investors to be notified of changes.

### Answer 5
**Adapter Pattern** - The Adapter pattern adapts the existing Celsius service to provide Fahrenheit without modifying the original class.

### Answer 6
**State Pattern** - The State pattern allows the media player to change button behavior based on its current state.

### Answer 7
**Decorator Pattern** - The Decorator pattern allows dynamic addition of extras to sandwiches without modifying the base class.

### Answer 8
**Command Pattern** - The Command pattern encapsulates operations as objects, enabling queuing, logging, and undo functionality.

### Answer 9
**Builder Pattern** - The Builder pattern allows step-by-step construction of meals with different configurations.

### Answer 10
**Chain of Responsibility Pattern** - The Chain of Responsibility pattern allows purchase requests to pass through multiple approval levels.

### Answer 11
**Bridge Pattern** - The Bridge pattern separates shape abstraction from rendering API implementation, allowing independent extension.

### Answer 12
**Iterator Pattern** - The Iterator pattern provides a way to traverse custom collections without exposing their internal structure.

### Answer 13
**Flyweight Pattern** - The Flyweight pattern shares intrinsic state (sprite, animation) among many enemies while keeping extrinsic state (position, health) separate.

### Answer 14
**Mediator Pattern** - The Mediator pattern allows airplanes to communicate through a central mediator, reducing coupling.

### Answer 15
**Singleton Pattern** - The Singleton pattern ensures only one instance of the database connection manager exists with lazy initialization.

### Answer 16
**Memento Pattern** - The Memento pattern allows saving document states at various points for undo/redo functionality.

### Answer 17
**Adapter Pattern** - The Adapter pattern makes the third-party library compatible with your application's expected interface.

### Answer 18
**Strategy Pattern** - The Strategy pattern allows characters to use different attack strategies that can be changed at runtime.

### Answer 19
**Decorator Pattern** - The Decorator pattern allows dynamic composition of formatting options by wrapping text objects.

### Answer 20
**Observer Pattern** - The Observer pattern allows subscribers to be notified of new articles without the publisher knowing about specific subscribers.

### Answer 21
**Proxy Pattern** - The Proxy pattern controls access, enabling lazy loading and logging functionality.

### Answer 22
**Prototype Pattern** - The Prototype pattern clones existing instances instead of creating expensive new ones.

### Answer 23
**Flyweight Pattern** - The Flyweight pattern shares common properties (typeface, size, style) among many letters to minimize memory usage.

### Answer 24
**Abstract Factory Pattern** - The Abstract Factory pattern creates families of related UI components that match a specific theme.

### Answer 25
**Facade Pattern** - The Facade pattern provides a simple interface that hides the complexity of the subsystem.

---

## Question (3): True/False Answers

### Answer 1
**True** - Design patterns are reusable solutions to common problems in software design.

### Answer 2
**True** - The Strategy pattern allows you to swap algorithms at runtime.

### Answer 3
**False** - The Prototype pattern creates duplicate objects by cloning. The Singleton pattern prevents creating more than one instance.

### Answer 4
**True** - The Observer pattern allows one-to-many dependency between objects.

### Answer 5
**True** - The Decorator pattern supports recursive composition, allowing decorators to wrap other decorators.

### Answer 6
**True** - The State pattern allows an object to change its behavior when its internal state changes.

### Answer 7
**True** - It is possible to write thread-safe singleton in Java using synchronized blocks or other synchronization mechanisms.

### Answer 8
**True** - The Command pattern encapsulates requests as objects.

### Answer 9
**True** - The Adapter pattern is a structural design pattern.

### Answer 10
**True** - The Chain of Responsibility pattern passes requests along a chain of handlers.

### Answer 11
**True** - The Decorator pattern is used to enhance the functionality of an object dynamically.

### Answer 12
**True** - The Iterator pattern provides a way to traverse collections without exposing their internal structure.

### Answer 13
**True** - The Bridge pattern separates abstraction from implementation.

### Answer 14
**True** - The Mediator pattern reduces coupling by preventing objects from referring to each other directly.

### Answer 15
**True** - The Flyweight pattern reduces memory usage by sharing common state among objects.

### Answer 16
**True** - The Memento pattern allows you to save and restore an object's state.

### Answer 17
**True** - The Proxy pattern can be used for lazy loading (virtual proxy).

### Answer 18
**False** - The Strategy pattern uses composition (has-a), not inheritance (is-a), to swap algorithms.

### Answer 19
**True** - The Builder pattern provides a fluent interface for constructing complex objects.

### Answer 20
**False** - The Observer pattern promotes loose coupling between subject and observers, not tight coupling.

### Answer 21
**True** - The Factory Method pattern lets subclasses decide which class to instantiate.

### Answer 22
**True** - The Singleton pattern ensures only one instance exists throughout the application.

### Answer 23
**True** - The Facade pattern simplifies interactions with complex subsystems.

### Answer 24
**True** - The Adapter pattern makes incompatible interfaces work together.

### Answer 25
**True** - The Abstract Factory pattern creates families of related objects.

---

## Pattern Quick Reference

### Creational Patterns
- **Singleton**: Ensure only one instance exists
- **Factory Method**: Create objects through subclasses
- **Abstract Factory**: Create families of related objects
- **Builder**: Construct complex objects step by step
- **Prototype**: Clone existing objects

### Structural Patterns
- **Adapter**: Make incompatible interfaces work together
- **Bridge**: Separate abstraction from implementation
- **Decorator**: Add responsibilities dynamically
- **Facade**: Provide simplified interface to subsystem
- **Flyweight**: Share common state among many objects
- **Proxy**: Control access to an object

### Behavioral Patterns
- **Chain of Responsibility**: Pass requests along a chain of handlers
- **Command**: Encapsulate requests as objects
- **Iterator**: Provide sequential access to elements
- **Mediator**: Define how objects interact
- **Memento**: Capture and restore object state
- **Observer**: Notify dependents of state changes
- **State**: Allow behavior to change with state
- **Strategy**: Define interchangeable algorithms

---

## Study Tips

1. **Focus on Intent**: Each pattern solves a specific problem. Understand the problem first, then the solution.

2. **Compare Similar Patterns**:
   - **Adapter vs Decorator**: Adapter changes interface; Decorator adds behavior
   - **Facade vs Adapter**: Facade simplifies subsystem; Adapter converts interface
   - **Proxy vs Decorator**: Proxy controls access; Decorator adds behavior
   - **Factory Method vs Abstract Factory**: Factory Method creates one product; Abstract Factory creates families
   - **Strategy vs State**: Strategy swaps algorithms; State changes behavior based on state
   - **Observer vs Mediator**: Observer notifies multiple objects; Mediator coordinates interactions
   - **Command vs Strategy**: Command encapsulates requests; Strategy encapsulates algorithms

3. **Look for Keywords**:
   - "Only one instance" → Singleton
   - "Incompatible interfaces" → Adapter
   - "Add behavior dynamically" → Decorator
   - "Simplify complex subsystem" → Facade
   - "Share common state" → Flyweight
   - "Control access" → Proxy
   - "Step by step construction" → Builder
   - "Clone existing object" → Prototype
   - "Interchangeable algorithms" → Strategy
   - "Notify dependents" → Observer
   - "Change behavior with state" → State
   - "Encapsulate request" → Command
   - "Chain of handlers" → Chain of Responsibility
   - "Traverse collection" → Iterator
   - "Coordinate interactions" → Mediator
   - "Save/restore state" → Memento

4. **Practice Scenarios**: Think of real-world examples for each pattern to reinforce understanding.
