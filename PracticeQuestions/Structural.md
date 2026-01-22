# Design Patterns Practice Questions
## Structural Patterns

---

## Question (1): Short Answer Questions

### 1. Which pattern provides a surrogate or placeholder for another object to control access to it?

### 2. Which of the following patterns dynamically attaches additional responsibilities to an object?

### 3. In which design pattern does a client interact with multiple incompatible interfaces by converting them into a unified interface?

### 4. What is the main purpose of the Facade pattern?

### 5. Which pattern best describes a pattern that reduces memory usage by sharing common state among objects?

### 6. Which pattern provides runtime modification capabilities to objects?

### 7. What type of design pattern is a pattern that provides a surrogate or placeholder to control access to an object?

### 8. Which design pattern allows you to fit more objects into memory by sharing data?

### 9. Which pattern separates an object's interface from its implementation so that the two can vary independently?

### 10. What does the Bridge pattern decouple?

### 11. Which pattern allows you to use a large number of fine-grained objects efficiently by sharing common state?

### 12. Which pattern attaches additional responsibilities to an object dynamically and provides a flexible alternative to subclassing for extending functionality?

### 13. Which design pattern is used to convert one interface of a class into another interface that clients expect?

### 14. Which pattern allows you to add new functionality to objects dynamically at runtime without modifying the original class?

### 15. You need to control access to an object. For example, you want to load a large image only when it's actually needed, or add logging before and after method calls. Which pattern provides this functionality?

### 16. Which pattern provides a simplified interface to a complex subsystem?

### 17. You're working on a graphics application where you need to separate the abstraction (shape) from its implementation (rendering API). Which pattern should you use?

### 18. You have a legacy payment system that uses a different interface than what your new code expects. You cannot modify the legacy code. Which pattern would help you integrate these systems?

### 19. Which pattern allows you to compose objects into tree structures to represent part-whole hierarchies?

### 20. You're working on a game engine where you need to render thousands of enemy units on screen. Each enemy has shared properties (sprite, animation) and unique properties (position, health). You want to minimize memory usage. Which pattern is most suitable?

### 21. You're building a word processor that needs to display thousands of letters on screen. Each letter has shared properties (typeface, size, style) and unique properties (position). You want to minimize memory usage. Which pattern should you use?

### 22. You're building a file system where directories can contain files and other directories. You want to apply operations like "getSize()" uniformly to both files and directories. Which pattern should you use?

### 23. Which pattern lets clients treat individual objects and compositions of objects uniformly?

### 24. You're building a GUI framework where containers can hold widgets and other containers. You want to apply operations like "render()" to both containers and individual widgets uniformly. Which pattern should you use?

---

## Question (2): What is the best pattern for each scenario?

### 1. A home theatre system consists of many components (TV, speakers, DVD player, etc.). The user wants to perform common tasks like "watch a movie" without dealing with the complexity of turning on each component individually.

### 2. You have an existing service that provides temperature in Celsius, but a new client wants the temperature in Fahrenheit. You don't want to modify the original class, but you need to adapt it to the new requirement.

### 3. You're building a sandwich shop application. You have a base Sandwich class, and you want to add extras (lettuce, tomato, cheese, bacon) dynamically. Each extra adds to the price and description.

### 4. You're building a graphics library where shapes can be rendered using different APIs (OpenGL, DirectX, Vulkan). You want to be able to add new shapes and new rendering APIs independently.

### 5. You're working on a game engine where you need to render thousands of enemy units on screen. Each enemy has a type (Goblin, Orc, Troll) with shared properties (sprite, animation), and unique properties (position, health). You want to minimize memory usage.

### 6. You have a third-party library that uses a different interface than what your application expects. You cannot modify the library's source code.

### 7. You're building a document editor where you can apply multiple formatting options to text (bold, italic, underline) and combine them. Each formatting option should be independent and composable.

### 8. You need to control access to an object. For example, you want to load a large image only when it's actually needed, or you want to add logging before and after method calls.

### 9. You're building a file system where directories can contain files and other directories. You want to apply operations like "getSize()" or "delete()" uniformly to both files and directories without the client needing to distinguish between them.

### 10. You're building a word processor that needs to display thousands of letters on screen. Each letter has properties like typeface, point size, and style (which are shared), and position (which is unique). You want to minimize memory usage.

### 11. You have a complex subsystem with many classes and interfaces. Instead of making clients interact with all these classes directly, you want to provide a simple interface that handles all the complexity.

### 12. You're building a GUI framework where containers can hold widgets and other containers. You want to apply operations like "render()" or "handleEvent()" uniformly to both containers and individual widgets.

### 13. You're building an organization chart where departments can contain employees and sub-departments. You want to calculate total salary or print the hierarchy uniformly for both departments and employees.

---

## Question (3): True/False Questions

Put T/F and correct the wrong answer.

### 1. Decorator supports recursive composition.

### 2. Adapter pattern is one of the structural patterns.

### 3. A design pattern used to enhance the functionality of an object is decorator.

### 4. The Bridge pattern separates abstraction from implementation.

### 5. The Composite pattern allows you to compose objects into tree structures.

### 6. The Flyweight pattern is used to reduce memory usage by sharing common state.

### 7. The Proxy pattern can be used for lazy loading.

### 8. In the Composite pattern, leaf nodes can have children.

### 9. The Facade pattern simplifies interactions with complex subsystems.

### 10. The Adapter pattern makes incompatible interfaces work together.

### 11. The Composite pattern lets clients treat individual objects and compositions uniformly.

### 12. In the Composite pattern, the Component class typically provides default implementations that throw UnsupportedOperationException.

---

## Answers

## Question (1): Short Answer Answers

### Answer 1
**Proxy Pattern** - The Proxy pattern provides a surrogate or placeholder to control access to an object.

### Answer 2
**Decorator Pattern** - The Decorator pattern dynamically attaches additional responsibilities to an object.

### Answer 3
**Adapter Pattern** - The Adapter pattern allows clients to interact with incompatible interfaces by converting them.

### Answer 4
**To simplify a complex system by providing a unified interface** - The Facade pattern provides a simplified interface to a complex subsystem.

### Answer 5
**It reduces memory usage by sharing common state among objects** - The Flyweight pattern shares intrinsic state to minimize memory usage.

### Answer 6
**Decorator Pattern** - The Decorator pattern provides runtime modification capabilities by wrapping objects.

### Answer 7
**Structural** - The Proxy pattern is a structural design pattern.

### Answer 8
**Flyweight Pattern** - The Flyweight pattern allows fitting more objects into memory by sharing data.

### Answer 9
**Bridge Pattern** - The Bridge pattern separates an object's interface from its implementation.

### Answer 10
**Abstraction from its implementation** - The Bridge pattern decouples abstraction from implementation.

### Answer 11
**Flyweight Pattern** - The Flyweight pattern shares common state among many fine-grained objects.

### Answer 12
**Decorator Pattern** - The Decorator pattern attaches responsibilities dynamically.

### Answer 13
**Adapter Pattern** - The Adapter pattern converts one interface to another that clients expect.

### Answer 14
**Decorator Pattern** - The Decorator pattern adds functionality dynamically without modifying the original class.

### Answer 15
**Proxy Pattern** - The Proxy pattern controls access, enabling lazy loading and logging.

### Answer 16
**Facade Pattern** - The Facade pattern provides a simplified interface to a complex subsystem.

### Answer 17
**Bridge Pattern** - The Bridge pattern separates abstraction (shape) from implementation (rendering API).

### Answer 18
**Adapter Pattern** - The Adapter pattern helps integrate systems with incompatible interfaces.

### Answer 19
**Composite Pattern** - The Composite pattern allows you to compose objects into tree structures to represent part-whole hierarchies.

### Answer 20
**Flyweight Pattern** - The Flyweight pattern minimizes memory by sharing common properties.

### Answer 21
**Flyweight Pattern** - The Flyweight pattern shares common properties (typeface, size, style) to save memory.

### Answer 22
**Composite Pattern** - The Composite pattern allows applying operations uniformly to both files (leaves) and directories (composites).

### Answer 23
**Composite Pattern** - The Composite pattern lets clients treat individual objects and compositions of objects uniformly through a common interface.

### Answer 24
**Composite Pattern** - The Composite pattern allows containers (composites) and widgets (leaves) to be treated uniformly through a common interface.

---

## Question (2): Scenario-Based Answers

### Answer 1
**Facade Pattern** - The Facade pattern simplifies the complex home theatre system by providing a unified interface.

### Answer 2
**Adapter Pattern** - The Adapter pattern adapts the existing Celsius service to provide Fahrenheit without modifying the original class.

### Answer 3
**Decorator Pattern** - The Decorator pattern allows dynamic addition of extras to sandwiches without modifying the base class.

### Answer 4
**Bridge Pattern** - The Bridge pattern separates shape abstraction from rendering API implementation, allowing independent extension.

### Answer 5
**Flyweight Pattern** - The Flyweight pattern shares intrinsic state (sprite, animation) among many enemies while keeping extrinsic state (position, health) separate.

### Answer 6
**Adapter Pattern** - The Adapter pattern makes the third-party library compatible with your application's expected interface.

### Answer 7
**Decorator Pattern** - The Decorator pattern allows dynamic composition of formatting options by wrapping text objects.

### Answer 8
**Proxy Pattern** - The Proxy pattern controls access, enabling lazy loading and logging functionality.

### Answer 9
**Composite Pattern** - The Composite pattern allows files (leaves) and directories (composites) to be treated uniformly, applying operations recursively.

### Answer 10
**Flyweight Pattern** - The Flyweight pattern shares common properties (typeface, size, style) among many letters to minimize memory usage.

### Answer 11
**Facade Pattern** - The Facade pattern provides a simple interface that hides the complexity of the subsystem.

### Answer 12
**Composite Pattern** - The Composite pattern allows containers (composites) and widgets (leaves) to be treated uniformly through a common Component interface.

### Answer 13
**Composite Pattern** - The Composite pattern allows departments (composites) and employees (leaves) to be treated uniformly, enabling recursive operations on the hierarchy.

---

## Question (3): True/False Answers

### Answer 1
**True** - The Decorator pattern supports recursive composition, allowing decorators to wrap other decorators.

### Answer 2
**True** - The Adapter pattern is a structural design pattern.

### Answer 3
**True** - The Decorator pattern is used to enhance the functionality of an object dynamically.

### Answer 4
**True** - The Bridge pattern separates abstraction from implementation.

### Answer 5
**True** - The Composite pattern allows you to compose objects into tree structures to represent part-whole hierarchies.

### Answer 6
**True** - The Flyweight pattern reduces memory usage by sharing common state among objects.

### Answer 7
**True** - The Proxy pattern can be used for lazy loading (virtual proxy).

### Answer 8
**False** - In the Composite pattern, leaf nodes cannot have children. Only composite nodes can have children.

### Answer 9
**True** - The Facade pattern simplifies interactions with complex subsystems.

### Answer 10
**True** - The Adapter pattern makes incompatible interfaces work together.

### Answer 11
**True** - The Composite pattern lets clients treat individual objects (leaves) and compositions (composites) uniformly through a common Component interface.

### Answer 12
**True** - The Component class typically provides default implementations that throw UnsupportedOperationException for methods that don't make sense for all component types.

---

## Pattern Quick Reference

### Structural Patterns
- **Adapter**: Make incompatible interfaces work together
- **Bridge**: Separate abstraction from implementation
- **Composite**: Compose objects into tree structures
- **Decorator**: Add responsibilities dynamically
- **Facade**: Provide simplified interface to subsystem
- **Flyweight**: Share common state among many objects
- **Proxy**: Control access to an object

---

## Study Tips

1. **Focus on Intent**: Each pattern solves a specific problem. Understand the problem first, then the solution.

2. **Compare Similar Patterns**:
   - **Adapter vs Decorator**: Adapter changes interface; Decorator adds behavior
   - **Facade vs Adapter**: Facade simplifies subsystem; Adapter converts interface
   - **Proxy vs Decorator**: Proxy controls access; Decorator adds behavior

3. **Look for Keywords**:
   - "Incompatible interfaces" → Adapter
   - "Add behavior dynamically" → Decorator
   - "Simplify complex subsystem" → Facade
   - "Share common state" → Flyweight
   - "Control access" → Proxy
   - "Separate abstraction from implementation" → Bridge

4. **Practice Scenarios**: Think of real-world examples for each pattern to reinforce understanding.
