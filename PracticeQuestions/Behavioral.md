# Design Patterns Practice Questions
## Behavioral Patterns

---

## Question (1): Short Answer Questions

### 1. Which pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime?

### 2. Which pattern encapsulates a request as an object, allowing you to parameterize clients with different requests, queue requests, and support undo operations?

### 3. Which pattern passes a request along a chain of handlers, where each handler decides either to process the request or pass it to the next handler?

### 4. Which pattern defines how a set of objects interact by encapsulating their communication in a central object?

### 5. Which pattern allows an object to save and restore its previous state without exposing implementation details?

### 6. A pattern allows an object to notify multiple dependent objects about state changes. What pattern is this?

### 7. Which pattern allows an object to alter its behavior when its internal condition changes?

### 8. Which pattern reduces communication complexity between multiple objects by centralizing their interactions?

### 9. You need to implement a system where different algorithms can be swapped at runtime. Which pattern provides this flexibility?

### 10. You want to implement undo/redo functionality in your application. Which pattern allows you to encapsulate operations as objects that can be stored and executed later?

### 11. You have a request that needs to be handled by one of several handlers, but you don't know which one in advance. Which pattern allows handlers to decide if they can process the request?

### 12. You have multiple objects that need to communicate with each other, but you want to avoid tight coupling. Which pattern centralizes communication through a central coordinator?

### 13. You need to save the state of an object so you can restore it later. Which pattern captures and externalizes an object's internal state?

### 14. You want to implement a notification system where multiple dependent objects are notified when a subject's state changes. Which pattern provides this one-to-many dependency?

### 15. You have an object whose behavior changes based on its internal condition. Instead of using many if-else statements, which pattern should you use?

### 16. Which pattern lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable?

### 17. Which pattern turns a request into a stand-alone object that contains all information about the request?

### 18. You're building a help system where help requests can be handled at different levels (button level, dialog level, application level). Which pattern allows requests to be passed along a chain until handled?

### 19. You're building a chat application where users communicate through a central server rather than directly with each other. Which pattern does this represent?

### 20. You need to implement a text editor with undo/redo functionality. Which pattern allows you to capture the editor's state and restore it later?

---

## Question (2): What is the best pattern for each scenario?

### 1. You're building a text editor that needs undo/redo functionality. Users should be able to undo their last action and redo it if needed.

### 2. You're designing a payment processing system where payment requests can be handled by different processors (credit card, PayPal, bank transfer). Each processor should try to handle the request, and if it can't, pass it to the next processor.

### 3. You're building a game where characters can use different attack strategies (melee, ranged, magic). Players should be able to switch between strategies during gameplay.

### 4. You're implementing a smart home system where multiple devices (lights, thermostat, security system) need to communicate with each other. You want to avoid each device knowing about all other devices.

### 5. You're building a weather monitoring system where multiple display devices (phone app, desktop widget, email notification) need to be updated whenever weather data changes.

### 6. You're designing a vending machine where the behavior changes based on its state (idle, has money, dispensing, out of stock). Instead of using many if-else statements, you want a cleaner solution.

### 7. You're building a remote control system where each button press should be treated as an object that can be executed, queued, logged, or undone.

### 8. You're implementing a customer support system where support tickets can be handled at different levels (Level 1, Level 2, Level 3). Each level should try to handle the ticket, and if it can't, escalate to the next level.

### 9. You're designing an encryption system where users can choose between different encryption algorithms (AES, DES, RSA). The system should allow switching algorithms without changing the client code.

### 10. You're building a document editor where you need to save the document's state at various points so users can restore to previous versions.

### 11. You're implementing a stock trading system where multiple traders need to be notified immediately when stock prices change.

### 12. You're designing an air traffic control system where multiple aircraft need to communicate their positions and receive clearance. You want to avoid direct communication between aircraft.

### 13. You're building a calculator application where each operation (add, subtract, multiply) should be treated as an object that can be executed, undone, and redone.

### 14. You're implementing a file processing system where files can be processed by different handlers (text processor, image processor, video processor). Each handler checks if it can process the file, and if not, passes it to the next handler.

### 15. You're designing a media player where playback behavior changes based on state (stopped, playing, paused). You want to avoid complex conditional logic.

---

## Question (3): True/False Questions

Put T/F and correct the wrong answer.

### 1. The Strategy pattern allows you to define a family of algorithms and make them interchangeable at runtime.

### 2. The Command pattern encapsulates a request as an object, allowing you to parameterize clients with different requests.

### 3. The Chain of Responsibility pattern passes a request along a chain of handlers until one handles it.

### 4. The Mediator pattern reduces communication complexity by allowing objects to communicate directly with each other.

### 5. The Memento pattern allows an object to save and restore its previous state.

### 6. The Observer pattern defines a one-to-many dependency between objects.

### 7. The State pattern allows an object to alter its behavior when its internal state changes.

### 8. The Strategy pattern violates the Open/Closed Principle because you must modify code to add new strategies.

### 9. The Command pattern supports undo/redo operations by storing command history.

### 10. The Chain of Responsibility pattern ensures that every request is handled by exactly one handler.

### 11. The Mediator pattern promotes loose coupling between objects by centralizing their communication.

### 12. The Memento pattern exposes the internal structure of an object to allow state restoration.

### 13. The Observer pattern allows multiple observers to be notified when a subject's state changes.

### 14. The State pattern eliminates the need for large conditional statements based on object state.

### 15. The Strategy pattern is useful when you have multiple ways to perform a task and want to choose the algorithm at runtime.

---

## Answers

## Question (1): Short Answer Answers

### Answer 1
**Strategy Pattern** - The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime.

### Answer 2
**Command Pattern** - The Command pattern encapsulates a request as an object, allowing parameterization, queuing, logging, and undo operations.

### Answer 3
**Chain of Responsibility Pattern** - The Chain of Responsibility pattern passes a request along a chain of handlers, where each handler decides whether to process it or pass it along.

### Answer 4
**Mediator Pattern** - The Mediator pattern defines how objects interact by encapsulating their communication in a mediator object.

### Answer 5
**Memento Pattern** - The Memento pattern allows an object to save and restore its previous state without exposing implementation details.

### Answer 6
**Observer Pattern** - The Observer pattern allows an object to notify multiple dependent objects about state changes.

### Answer 7
**State Pattern** - The State pattern allows an object to alter its behavior when its internal state changes.

### Answer 8
**Mediator Pattern** - The Mediator pattern reduces communication complexity between multiple objects by centralizing their interactions.

### Answer 9
**Strategy Pattern** - The Strategy pattern provides flexibility to swap different algorithms at runtime.

### Answer 10
**Command Pattern** - The Command pattern allows operations to be encapsulated as objects that can be stored and executed later, enabling undo/redo functionality. (Note: For this scenario, Command Pattern is preferred because it stores operations with their inverse actions. Memento Pattern could also work but is better suited when you need to restore complete state snapshots rather than undo specific operations.)

### Answer 11
**Chain of Responsibility Pattern** - The Chain of Responsibility pattern allows handlers to decide if they can process a request, passing it along if they cannot.

### Answer 12
**Mediator Pattern** - The Mediator pattern centralizes communication through a mediator, avoiding tight coupling between objects.

### Answer 13
**Memento Pattern** - The Memento pattern captures and externalizes an object's internal state for later restoration.

### Answer 14
**Observer Pattern** - The Observer pattern provides a one-to-many dependency where multiple observers are notified when a subject's state changes.

### Answer 15
**State Pattern** - The State pattern should be used when an object's behavior changes based on its internal state, avoiding many if-else statements.

### Answer 16
**Strategy Pattern** - The Strategy pattern lets you define a family of algorithms, put each in a separate class, and make their objects interchangeable.

### Answer 17
**Command Pattern** - The Command pattern turns a request into a stand-alone object containing all information about the request.

### Answer 18
**Chain of Responsibility Pattern** - The Chain of Responsibility pattern allows requests to be passed along a chain until handled.

### Answer 19
**Mediator Pattern** - The Mediator pattern represents centralized communication, like a chat server mediating between users.

### Answer 20
**Memento Pattern** - The Memento pattern allows capturing the editor's state at various points and restoring it later for undo/redo functionality. (Note: This question specifically mentions "capture the editor's state and restore it later," which points to Memento Pattern's approach of storing state snapshots. Command Pattern could also be used for undo/redo, but it works by storing operations with inverse actions rather than state snapshots.)

---

## Question (2): Scenario-Based Answers

### Answer 1
**Command Pattern** - The Command pattern encapsulates operations as objects, allowing them to be stored in a history for undo/redo functionality. (Note: Command Pattern stores operations with their inverse actions. Memento Pattern stores state snapshots - both can implement undo, but Command is typically preferred for text editors where operations like "insert", "delete", "format" have clear inverse operations.)

### Answer 2
**Chain of Responsibility Pattern** - The Chain of Responsibility pattern allows payment processors to try handling requests and pass them along if they cannot process them.

### Answer 3
**Strategy Pattern** - The Strategy pattern allows different attack strategies to be swapped at runtime during gameplay.

### Answer 4
**Mediator Pattern** - The Mediator pattern centralizes communication between devices, avoiding tight coupling where each device knows about all others.

### Answer 5
**Observer Pattern** - The Observer pattern allows multiple display devices to be notified whenever weather data changes.

### Answer 6
**State Pattern** - The State pattern allows behavior to change based on the vending machine's state, eliminating complex if-else statements.

### Answer 7
**Command Pattern** - The Command pattern treats button presses as command objects that can be executed, queued, logged, or undone.

### Answer 8
**Chain of Responsibility Pattern** - The Chain of Responsibility pattern allows support tickets to be handled at different levels, with escalation to the next level if needed.

### Answer 9
**Strategy Pattern** - The Strategy pattern allows switching between encryption algorithms without changing client code.

### Answer 10
**Memento Pattern** - The Memento pattern allows saving document state at various points for restoration to previous versions.

### Answer 11
**Observer Pattern** - The Observer pattern allows multiple traders to be notified immediately when stock prices change.

### Answer 12
**Mediator Pattern** - The Mediator pattern allows aircraft to communicate through a central air traffic control mediator, avoiding direct communication.

### Answer 13
**Command Pattern** - The Command pattern treats operations as objects that can be executed, undone, and redone.

### Answer 14
**Chain of Responsibility Pattern** - The Chain of Responsibility pattern allows file handlers to check if they can process a file and pass it along if they cannot.

### Answer 15
**State Pattern** - The State pattern allows playback behavior to change based on state (stopped, playing, paused), avoiding complex conditional logic.

---

## Question (3): True/False Answers

### Answer 1
**True** - The Strategy pattern allows you to define a family of algorithms and make them interchangeable at runtime.

### Answer 2
**True** - The Command pattern encapsulates a request as an object, allowing parameterization of clients with different requests.

### Answer 3
**True** - The Chain of Responsibility pattern passes a request along a chain of handlers until one handles it.

### Answer 4
**False** - The Mediator pattern reduces communication complexity by centralizing communication through a mediator, NOT by allowing direct communication. Objects communicate via the mediator, not directly with each other.

### Answer 5
**True** - The Memento pattern allows an object to save and restore its previous state.

### Answer 6
**True** - The Observer pattern defines a one-to-many dependency between objects.

### Answer 7
**True** - The State pattern allows an object to alter its behavior when its internal state changes.

### Answer 8
**False** - The Strategy pattern FOLLOWS the Open/Closed Principle. You can add new strategies by creating new classes without modifying existing code.

### Answer 9
**True** - The Command pattern supports undo/redo operations by storing command history.

### Answer 10
**False** - The Chain of Responsibility pattern does NOT ensure every request is handled. A request might pass through the entire chain without being handled if no handler can process it.

### Answer 11
**True** - The Mediator pattern promotes loose coupling between objects by centralizing their communication.

### Answer 12
**False** - The Memento pattern does NOT expose the internal structure. It encapsulates the state and only the originator can access it, maintaining encapsulation.

### Answer 13
**True** - The Observer pattern allows multiple observers to be notified when a subject's state changes.

### Answer 14
**True** - The State pattern eliminates the need for large conditional statements based on object state.

### Answer 15
**True** - The Strategy pattern is useful when you have multiple ways to perform a task and want to choose the algorithm at runtime.

---

## Pattern Quick Reference

### Behavioral Patterns Covered
- **Chain of Responsibility**: Pass requests along a chain of handlers
- **Command**: Encapsulate requests as objects
- **Mediator**: Define how objects interact (Note: ASU course did not require code structure study)
- **Memento**: Capture and restore object state
- **Observer**: Notify dependents of state changes
- **State**: Allow object behavior to change with state
- **Strategy**: Define a family of algorithms and make them interchangeable

### Note on Code Structure Study
**Mediator Pattern**: 📝 In the ASU university course lecture, no code example was given for this pattern. Therefore, code structure details are not required for exam preparation, but understanding the pattern's intent and use cases is important.

---

## Study Tips

1. **Focus on Intent**: Each pattern solves a specific problem. Understand the problem first, then the solution.

2. **Compare Similar Patterns**:
   - **Strategy vs State**: Strategy swaps algorithms; State changes behavior based on state
   - **Observer vs Mediator**: Observer notifies multiple objects; Mediator coordinates interactions
   - **Command vs Strategy**: Command encapsulates requests; Strategy encapsulates algorithms
   - **Command vs Memento (Undo Functionality)**: See detailed explanation below

3. **Look for Keywords**:
   - "Chain of handlers" → Chain of Responsibility
   - "Encapsulate request" → Command
   - "Notify dependents" → Observer
   - "Change behavior with state" → State
   - "Interchangeable algorithms" → Strategy

4. **Practice Scenarios**: Think of real-world examples for each pattern to reinforce understanding.

---

## Differentiating Command and Memento Patterns for Undo Functionality

Both **Command Pattern** and **Memento Pattern** can be used to implement undo/redo functionality, but they work in fundamentally different ways:

### Command Pattern for Undo

**How it works:**
- Stores **operations/commands** as objects in a history
- Each command knows how to **execute** and **undo** itself
- Undo is achieved by calling the command's `undo()` method
- Commands store the **inverse operation** or **reverse action**

**Example:**
```java
// Command stores the operation
class AddCommand implements Command {
    private int value;
    private Calculator calc;
    
    void execute() { calc.add(value); }
    void undo() { calc.subtract(value); }  // Inverse operation
}

// History stores commands
history.push(new AddCommand(5));
history.push(new MultiplyCommand(2));

// Undo: execute inverse operation
history.pop().undo();  // Undoes multiply
```

**Key Characteristics:**
- ✅ Stores **what was done** (the operation)
- ✅ Each command knows its own undo logic
- ✅ Can undo specific operations in sequence
- ✅ Good for operations that have clear inverse operations

### Memento Pattern for Undo

**How it works:**
- Stores **snapshots of object state** at various points
- Undo is achieved by **restoring a previous state snapshot**
- The originator (object) creates mementos (snapshots) before changes
- To undo, restore the previous memento

**Example:**
```java
// Memento stores state snapshot
class TextMemento {
    private String text;  // Snapshot of text state
}

class TextEditor {
    private String text;
    
    TextMemento save() { return new TextMemento(text); }
    void restore(TextMemento m) { this.text = m.getText(); }
}

// History stores state snapshots
history.push(editor.save());  // Save state before change
editor.append("Hello");
history.push(editor.save());  // Save state after change

// Undo: restore previous state
editor.restore(history.pop());  // Restores to "Hello"
```

**Key Characteristics:**
- ✅ Stores **how things were** (the state)
- ✅ Captures complete state at a point in time
- ✅ Can restore to any previous state snapshot
- ✅ Good for complex objects where inverse operations are difficult

### When to Use Which?

**Use Command Pattern when:**
- Operations have clear inverse operations (add/subtract, insert/delete)
- You need fine-grained control over what gets undone
- Operations are discrete and well-defined
- Example: Text editor with operations like "insert text", "delete character", "format bold"

**Use Memento Pattern when:**
- Object state is complex and hard to reverse
- You need to restore to any previous state (not just sequential undo)
- State changes don't have simple inverse operations
- Example: Game save system, complex document state, configuration settings

### Combined Approach

In practice, many applications use **both patterns together**:
- **Command Pattern**: For operation history and undo/redo queue
- **Memento Pattern**: For saving/restoring complete state snapshots

**Example:**
```java
// Command uses Memento internally
class SaveStateCommand implements Command {
    private Memento beforeState;
    private Memento afterState;
    
    void execute() {
        beforeState = editor.save();  // Memento: save state
        editor.makeChange();
        afterState = editor.save();   // Memento: save new state
    }
    
    void undo() {
        editor.restore(beforeState);  // Memento: restore previous state
    }
}
```

### Quick Comparison Table

| Aspect | Command Pattern | Memento Pattern |
|--------|----------------|-----------------|
| **What is stored** | Operations/Commands | State Snapshots |
| **Undo mechanism** | Execute inverse operation | Restore previous state |
| **Storage** | Command history | State history |
| **Best for** | Operations with clear inverses | Complex state restoration |
| **Granularity** | Per-operation | Per-state-snapshot |
| **Memory** | Stores operations (usually smaller) | Stores complete state (can be larger) |
