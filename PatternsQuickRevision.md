## Design Patterns – Quick Revision Summary

This is a concise revision sheet for all the design patterns in this project.  
Each section gives:

- **Intent & When to use**
- **Key structure idea (with existing diagram image)**
- **How it feels in code**
- **Brief comparison with related patterns**

---

## Quick Navigation

### Creational Patterns
- [Singleton](#singleton)
- [Factory Family](#factory-family-simple-factory-factory-method-abstract-factory)
- [Builder](#builder)
- [Prototype](#prototype)

### Structural Patterns
- [Adapter](#adapter)
- [Bridge](#bridge)
- [Composite](#composite)
- [Decorator](#decorator)
- [Facade](#facade)
- [Flyweight](#flyweight)
- [Proxy](#proxy)

### Behavioral Patterns
- [Strategy](#strategy)
- [Chain of Responsibility](#chain-of-responsibility)
- [Command](#command)
- [Iterator](#iterator)
- [Mediator](#mediator)
- [Memento](#memento)
- [Observer](#observer)
- [State](#state)

---

## Pattern Classification Overview

The table below summarizes the patterns in this codebase using the classic **GoF classification** by **purpose** (Creational, Structural, Behavioral) and **scope** (Class vs Object).

| Scope  | Purpose     | Patterns in this repo                                  |
|--------|-------------|--------------------------------------------------------|
| Class  | Creational  | Factory Method (part of Factory family)               |
| Object | Creational  | Abstract Factory, Builder, Prototype, Singleton, Simple Factory |
| Class  | Structural  | Adapter (class form – shown conceptually)             |
| Object | Structural  | Adapter (object form), Bridge, Composite, Decorator, Facade, Flyweight, Proxy |
| Object | Behavioral  | Strategy, Chain of Responsibility, Command, Iterator, Mediator, Memento, Observer, State                     |

---

## Singleton

- **Intent**: Ensure a class has only one instance and provide a global access point to it.
- **When to use**:
  - Exactly one shared object should exist (e.g., configuration, logger, cache, connection pool).
- **Structure**:
  - No diagram image, but see examples in `Singleton/README.md`.
  - Pattern:
    - Private constructor.
    - Static field to hold single instance.
    - Static `getInstance()` method.
- **Code feel**:
  - `Logger logger = Logger.getInstance();`
-  - All code uses the same instance.
- **Similar to / compared with**:
  - **Flyweight**: Flyweight allows many shared instances (one per intrinsic key); Singleton enforces exactly one instance.
  - **Static class**: Static classes have no instance and cannot implement interfaces; Singleton is an object and can be passed around.

- **Further reading**: [Singleton README](Singleton/README.md), demos: [EagerSingletonDemo](Singleton/EagerSingletonDemo.java), [LazySingletonDemo](Singleton/LazySingletonDemo.java), [SynchronizedSingletonDemo](Singleton/SynchronizedSingletonDemo.java)

---

## Factory Family (Simple Factory, Factory Method, Abstract Factory)

- **Intent**: Encapsulate object creation so client code depends on abstractions, not concrete classes.
- **When to use**:
  - You want to centralize creation logic.
  - You want to vary which concrete classes are instantiated without changing client code.
- **Structure**:
  - Diagram:
    - ![Factory Diagram](Factory/factory_diagram.jpeg)  
      *Shows the base product interface, its concrete products, and a factory class responsible for creating them for the client.*
  - Key variants:
    - **Simple Factory**: one class with a method that uses `if/switch` to return different products.
    - **Factory Method**: superclass declares factory method; subclasses decide which concrete product to create.
    - **Abstract Factory**: interface for creating families of related products (e.g., button + checkbox).
- **Code feel**:

```java
class ShapeFactory {
    Shape create(String type) {
        if ("circle".equals(type)) return new Circle();
        if ("square".equals(type)) return new Square();
        throw new IllegalArgumentException();
    }
}
```
- **Similar to / compared with**:
  - **Builder**: Builder focuses on step-by-step construction; factories just pick which class to instantiate.
  - **Prototype**: Prototype clones existing instances; factories build new ones from scratch.
  - **Singleton**: Singleton controls *how many* instances exist; factories control *which type* of instance you get.

- **Further reading**: [Factory README](Factory/README.md), demos: [SimpleFactoryDemo](Factory/SimpleFactoryDemo.java), [ShapeFactoryDemo](Factory/ShapeFactoryDemo.java), [FactoryMethodDemo](Factory/FactoryMethodDemo.java), [AbstractFactoryDemo](Factory/AbstractFactoryDemo.java)

---

## Builder

- **Intent**: Separate the construction of a complex object from its representation so the same construction process can create different representations.
- **When to use**:
  - Object construction has many optional/stepwise parameters.
-  - You want readable, step-by-step creation instead of a telescoping constructor.
- **Structure**:
  - Diagrams:
    - ![Builder Diagram 1](Builder/builder_diagram_1.jpeg)  
      *Shows the core Builder pattern class diagram: Product, abstract Builder, ConcreteBuilder, and an optional Director coordinating construction.*
    - ![Builder Diagram 2](Builder/builder_diagram_2.jpeg)  
      *Shows a concrete usage example of the Builder pattern, where a client assembles an object step‑by‑step via builder methods and then calls `build()`.*
  - Roles:
    - **Builder**: defines steps to build parts.
    - **Concrete Builder**: implements steps for a specific product.
    - **Director**: runs building steps in a particular order.
    - **Product**: complex object being constructed.
- **Code feel**:

```java
Car car = new CarBuilder()
        .withEngine("V8")
        .withColor("Red")
        .build();
```
- **Similar to / compared with**:
  - **Factory**: Factory returns a fully-built object in one call; Builder focuses on step-by-step construction.
  - **Prototype**: Prototype clones an existing configured object; Builder constructs from scratch via steps.

- **Further reading**: [Builder README](Builder/README.md), demos: [CarBuilderDemo](Builder/CarBuilderDemo.java), [DirectorBuilderDemo](Builder/DirectorBuilderDemo.java), [DocumentBuilderDemo](Builder/DocumentBuilderDemo.java), [TelescopingConstructorDemo](Builder/TelescopingConstructorDemo.java)

---

## Prototype

- **Intent**: Create new objects by copying (cloning) existing prototype instances instead of instantiating classes directly.
- **When to use**:
  - Object creation is expensive or complex.
  - You need many similar objects configured at runtime.
- **Structure**:
  - Diagram:
    - ![Prototype Diagram](Prototype/prototype_diagram.jpeg)  
      *Shows the Prototype interface, concrete prototype subclasses, and the client cloning them instead of using `new`.*
  - Roles:
    - **Prototype**: declares clone operation.
    - **Concrete Prototype**: implements cloning (shallow/deep).
    - **Client**: clones via Prototype interface instead of `new`.
- **Code feel**:

```java
Shape prototype = new Circle(10, "red");
Shape copy = prototype.clone();
copy.setColor("blue");
```
- **Shallow vs Deep cloning (conceptually)**:
  - *Shallow clone*: copies primitive fields and **references** as-is; original and clone still share referenced objects.
  - *Deep clone*: copies primitive fields and also **creates new copies** of referenced objects so original and clone are fully independent.
- **Similar to / compared with**:
  - **Factory**: Factory chooses a class to instantiate; Prototype duplicates existing configured objects.
  - **Flyweight**: Prototype makes copies; Flyweight shares intrinsic state among many logical objects.

- **Further reading**: [Prototype README](Prototype/README.md), demos: [PrototypeDirectDemo](Prototype/PrototypeDirectDemo.java), [PrototypeRegistryDemo](Prototype/PrototypeRegistryDemo.java), [PrototypeShallowDeepDemo](Prototype/PrototypeShallowDeepDemo.java)

---

## Strategy

- **Intent**: Define a family of algorithms, encapsulate each one, and make them interchangeable at runtime without changing the client code.
- **When to use**:
  - You have several ways to perform the same task (different algorithms or behaviors).
  - Behavior should be chosen at runtime (config, user choice, context) instead of via big `if/else` chains.
  - You want to avoid subclass explosion when **what varies is behavior**, not the data structure of the class.
- **Structure**:
  - Diagrams:
    - `Startegy/diagram1.png`–`diagram3.png`: show the **naive Duck inheritance** and the problems of putting `fly()` and `quack()` directly in the superclass (rubber and decoy ducks that shouldn’t fly or quack).
    - `Startegy/diagram4.png`–`diagram5.png`: extract fly/quack into **behavior interfaces** (`FlyBehavior`, `QuackBehavior`) with concrete implementations like `FlyWithWings`, `FlyNoWay`, `Quack`, `Squeak`, `MuteQuack`.
    - `Startegy/diagram6.png`–`diagram7.png`: final design where `Duck` has `FlyBehavior flyBehavior` and `QuackBehavior quackBehavior`, and the client works with ducks while behaviors are encapsulated and swappable.
  - Roles:
    - **Strategy**: interface representing an algorithm (`FlyBehavior`, `QuackBehavior`, `EncryptionStrategy`).
    - **Concrete Strategies**: implementations of the algorithm (`FlyWithWings`, `FlyNoWay`, `Quack`, `Squeak`, `MuteQuack`, `AesEncryption`, `RsaEncryption`, `EccEncryption`).
    - **Context**: class that **has a** strategy and delegates to it (`Duck`, `SecureMessenger`).
    - **Client**: picks which strategy instance the context should use.
- **Code feel**:

```java
// Duck context with pluggable behaviors
Duck mallard = new MallardDuck();          // FlyWithWings + Quack by default
mallard.performFly();                      // Delegates to FlyBehavior
mallard.performQuack();                    // Delegates to QuackBehavior

Duck rubber = new RubberDuck();            // FlyNoWay + Squeak
rubber.setFlyBehavior(new RocketFly());    // Swap strategy at runtime
rubber.performFly();                       // Now rocket-powered
```

```java
// Encryption strategies in a messenger context
SecureMessenger messenger = new SecureMessenger(new AesEncryption());
messenger.send("Alice", "Hi Alice!");            // Standard security

messenger.setStrategy(new RsaEncryption());      // Higher security
messenger.send("Bob", "Confidential meeting");   // Uses RSA now
```
- **Similar to / compared with**:
  - **State**: same class diagram shape; Strategy focuses on interchangeable algorithms, State focuses on transitions between object states.
  - **Bridge**: Bridge splits abstraction and implementation into two hierarchies; Strategy swaps algorithms used by a single context class.
  - **Decorator**: Decorator wraps objects to add responsibilities; Strategy swaps out the core behavior implementation without wrapping.

- **Further reading**: [Strategy README](Startegy/README.md), demos: `StrategyDuckDemo.java`, `StrategyEncryptionDemo.java`

---

## Chain of Responsibility

- **Intent**: Pass a request along a chain of handlers. Each handler decides whether to process the request or pass it to the next handler in the chain.
- **When to use**:
  - Multiple objects can handle a request, and the handler isn't known a priori.
  - You want to issue a request to one of several objects without specifying the receiver explicitly.
  - The set of handlers should be specified dynamically.
- **Structure**:
  - Diagrams:
    - ![Chain of Responsibility Flow](ChainOfResponsibility/diagram1.png)  
      *Shows client sending request through handler chain where each handler either processes or forwards the request.*
    - ![Chain of Responsibility UML](ChainOfResponsibility/diagram2.png)  
      *Shows Handler interface with successor reference, and ConcreteHandlerA/B implementing it.*
    - ![Email Handler Chain](ChainOfResponsibility/diagram 3.png)  
      *Shows email processing chain: Spam → Fan → Complaint → NewLoc handlers.*
    - ![Email Handlers UML](ChainOfResponsibility/diagram4.png)  
      *Shows Handler with SpamHandler, FanHandler, ComplaintHandler, NewLocHandler subclasses.*
  - Roles:
    - **Handler**: defines interface for handling requests and optionally implements successor link.
    - **ConcreteHandler**: handles requests it's responsible for; can access successor; forwards requests it can't handle.
    - **Client**: initiates request to first handler in chain.
- **Code feel**:

```java
// Email handling chain
Handler spamHandler = new SpamHandler();
Handler fanHandler = new FanHandler();
Handler complaintHandler = new ComplaintHandler();

spamHandler.setSuccessor(fanHandler);
fanHandler.setSuccessor(complaintHandler);

Email email = new Email("COMPLAINT", "Product broken", "customer@example.com");
spamHandler.handleRequest(email);  // Passes through chain until ComplaintHandler handles it
```

```java
// Purchase approval chain
Approver teamLead = new TeamLead("Alice");
Approver manager = new Manager("Bob");
teamLead.setSuccessor(manager);

PurchaseRequest req = new PurchaseRequest(2500, "Equipment", "John");
teamLead.handleRequest(req);  // TeamLead forwards to Manager who approves
```
- **Similar to / compared with**:
  - **Command**: Chain of Responsibility can use Command pattern to represent requests as objects.
  - **Composite**: Chain of Responsibility often used with Composite, where parent component acts as successor.
  - **Decorator**: Both use recursive composition; Decorator adds responsibilities, Chain of Responsibility passes requests along.
  - **Strategy**: Strategy chooses an algorithm; Chain of Responsibility finds which handler can process a request.

- **Further reading**: [Chain of Responsibility README](ChainOfResponsibility/README.md), demos: `ChainOfResponsibilityEmailDemo.java`, `ChainOfResponsibilityApprovalDemo.java`

---

## Command

- **Intent**: Encapsulate a request under an object as a command and pass it to invoker object. Invoker object looks for the appropriate object which can handle this command and pass the command to the corresponding object and that object executes the command.
- **Also Known As**: Action or Transaction
- **When to use**:
  - You need to parameterize objects with operations
  - You need to queue operations, schedule them, or execute them remotely
  - You need to support undo/redo functionality
  - You need to log operations for auditing or debugging
  - You need to support macro commands (combining multiple commands)
- **Structure**:
  - Diagrams:
    - ![Command Diagram 1](Command/diagram1.png)  
      *Shows the general Command pattern structure with Command interface, Invoker, Concrete Commands, and Receivers.*
    - ![Command Diagram 2](Command/diagram2.png)  
      *Shows the specific implementation with RemoteControl as Invoker, TurnOnCommand/ChangeChannelCommand as Concrete Commands, and TV/Stereo as Receivers.*
  - Roles:
    - **Command**: Interface defining `execute()` method
    - **Concrete Commands**: Implement Command interface, encapsulate receiver and operation
    - **Invoker**: Holds and invokes commands (e.g., RemoteControl)
    - **Receiver**: Performs actual operations (e.g., TV, Stereo)
    - **Client**: Creates commands, sets them in invoker, executes
- **Code feel**:

```java
// Create receiver and command
TV tv = new TV("Living Room");
Command turnOnTV = new TurnOnCommand(tv);

// Create invoker and set command
RemoteControl remote = new RemoteControl();
remote.setCommand(turnOnTV);
remote.pressButton();  // Executes command
```

```java
// Macro command (combining multiple commands)
List<Command> partyMode = Arrays.asList(
    new TurnOnCommand(tv),
    new TurnOnCommand(stereo),
    new AdjustVolumeCommand(stereo)
);
Command macro = new MacroCommand(partyMode);
remote.setCommand(macro);
remote.pressButton();  // Executes all commands
```

- **Similar to / compared with**:
  - **vs Strategy**: Strategy encapsulates algorithms; Command encapsulates requests/operations
  - **vs Memento**: Memento stores state for undo; Command stores operations for undo
  - **vs Chain of Responsibility**: Chain passes requests along a chain; Command encapsulates requests
  - **vs Observer**: Observer notifies multiple observers; Command encapsulates a single operation

- **Further reading**: [Command README](Command/README.md), demos: [CommandPatternDemo](Command/CommandPatternDemo.java), [CommandUndoDemo](Command/CommandUndoDemo.java), [CommandMacroDemo](Command/CommandMacroDemo.java), [CommandQueueDemo](Command/CommandQueueDemo.java)

---

## Iterator

- **Intent**: Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
- **When to use**:
  - You need to traverse a collection without exposing its internal structure
  - You want to provide a uniform way to traverse different collection types
  - You need to support multiple simultaneous traversals
  - Collection structure may change (array → list → tree)
- **Structure**:
  - Roles:
    - **Iterator**: Interface defining traversal methods (hasNext(), next())
    - **ConcreteIterator**: Implements Iterator, maintains position, implements traversal
    - **Aggregate**: Interface defining iterator creation method (createIterator())
    - **ConcreteAggregate**: Implements Aggregate, creates appropriate iterator
- **Code feel**:

```java
// Get Iterator from Aggregate
Library library = new Library();
library.addBook("The Great Gatsby");
library.addBook("1984");

Iterator<String> iterator = library.createIterator();

// Traverse using Iterator
while (iterator.hasNext()) {
    String book = iterator.next();
    System.out.println(book);
}
```

```java
// Iterator hides internal structure
class BookIterator implements Iterator<String> {
    private String[] books;
    private int position;
    
    public boolean hasNext() {
        return position < books.length;
    }
    
    public String next() {
        return books[position++];
    }
}
```

- **Similar to / compared with**:
  - **vs Visitor**: Iterator traverses elements; Visitor performs operations on elements
  - **vs Composite**: Iterator traverses collections; Composite builds tree structures

- **Further reading**: [Iterator README](Iterator/README.md), demo: [IteratorPatternDemo](Iterator/IteratorPatternDemo.java)

---

## Mediator

- **Intent**: Reduce communication complexity between multiple objects or classes by providing a mediator class that handles all communications between different classes, supporting easy maintainability through loose coupling. The Mediator Design Pattern simplifies communication between multiple objects in a system by centralizing their interactions through a mediator. Instead of objects interacting directly, they communicate via a mediator, reducing dependencies and making the system easier to manage.
- **When to use**:
  - You have many objects communicating with each other in complex ways
  - You want to reduce dependencies between objects by centralizing their interactions
  - Communication logic is scattered across multiple classes
  - You need centralized coordination of component interactions
  - Complex communication: Your system involves a set of objects that need to communicate with each other in a complex manner
  - Loose coupling: You want to promote loose coupling between objects
  - Centralized control: You need a centralized mechanism to coordinate interactions
- **Structure**:
  - Diagrams:
    - ![Mediator Diagram 1](Mediator/diagram1.png)  
      *Shows the general Mediator pattern structure with Mediator interface, Concrete Mediator, Colleague interface, and Concrete Colleague classes.*
    - ![Mediator Diagram 2](Mediator/diagram2.png)  
      *Shows the Air Traffic Control example with AirTrafficControlTower as Mediator, AirportControlTower as Concrete Mediator, Airplane as Colleague, and CommercialAirplane as Concrete Colleague.*
  - Roles:
    - **Mediator**: Interface defining communication contract (`onEvent()` method or `requestTakeoff()`, `requestLanding()`)
    - **ConcreteMediator**: Implements Mediator, knows about all colleagues, contains coordination logic
    - **Colleague**: Interface for components that need to communicate (`setMediator()`, `triggerEvent()` or `requestTakeoff()`, `requestLanding()`)
    - **ConcreteColleague**: Implements Colleague, communicates only through mediator (e.g., Alarm, CoffeePot, Calendar, Sprinkler or CommercialAirplane)
    - **Client**: Creates components and mediator, registers components with mediator
- **Code feel**:

```java
// Create components and mediator
Alarm alarm = new Alarm("Morning");
CoffeePot coffeePot = new CoffeePot("Kitchen");
Calendar calendar = new Calendar("Home");
Sprinkler sprinkler = new Sprinkler("Garden");

SmartHomeMediatorImpl mediator = new SmartHomeMediatorImpl();
mediator.registerComponents(alarm, coffeePot, calendar, sprinkler);

// Components communicate through mediator
alarm.ring();  // Mediator coordinates: checks calendar, starts sprinkler if needed, starts coffee
calendar.setDayOfWeek("Saturday");  // Mediator adjusts routines based on day
```

```java
// Mediator contains all coordination logic
public void onEvent(String event, Colleague sender) {
    if ("ALARM_RING".equals(event)) {
        if (calendar.isWeekend()) {
            if (!isRaining && temperature > 65) {
                sprinkler.start();
            }
        } else {
            if (!isRaining && temperature > 70) {
                sprinkler.start();
            }
        }
        coffeePot.startBrewing();
    }
}
```

```java
// Air Traffic Control Example - Airplanes communicate through mediator
AirTrafficControlTower controlTower = new AirportControlTower();
Airplane airplane1 = new CommercialAirplane(controlTower, "AA101");
Airplane airplane2 = new CommercialAirplane(controlTower, "UA202");

// Airplanes communicate through mediator, not directly
airplane1.requestTakeoff();  // Goes through control tower
airplane2.requestLanding();  // Goes through control tower
```

- **Similar to / compared with**:
  - **vs Facade**: Mediator coordinates peer objects; Facade simplifies subsystem interface
  - **vs Observer**: Mediator centralizes communication; Observer distributes notifications
  - **vs Command**: Mediator coordinates multiple objects; Command encapsulates single operations

- **Further reading**: [Mediator README](Mediator/README.md), demos: [MediatorPatternDemo](Mediator/MediatorPatternDemo.java), [MediatorAirplaneDemo](Mediator/MediatorAirplaneDemo.java)

---

## Memento

- **Intent**: Without violating encapsulation, capture and externalize an object's internal state so that the object can be restored to this state later. Use the Memento Pattern when you need to be able to return an object to one of its previous states; for instance, if your user requests an "undo."
- **When to use**:
  - You need to implement undo/redo functionality
  - You need to save and restore an object's state
  - You need to provide checkpoints for rollback operations
  - You need to maintain encapsulation while saving state
- **Structure**:
  - Roles:
    - **Memento**: Stores the internal state of the Originator (opaque to Caretaker)
    - **Originator**: Creates mementos containing snapshots of its current state and uses mementos to restore state
    - **Caretaker**: Stores mementos but never operates on or examines their contents
- **Code feel**:

```java
// Create Originator and Caretaker
TextEditor editor = new TextEditor();
History history = new History();

// Save initial state
history.saveState(editor.save());

// Perform operations
editor.write("Hello");
history.saveState(editor.save());

editor.write(" World");
history.saveState(editor.save());

// Undo - restore previous state
editor.restore(history.undo());

// Redo - restore undone state
editor.restore(history.redo());
```

```java
// Memento is opaque - Caretaker cannot access its contents
class TextEditorMemento {
    private final String content;  // Private - only Originator can access
    private final int cursorPosition;
    
    TextEditorMemento(String content, int cursorPosition) {
        this.content = content;
        this.cursorPosition = cursorPosition;
    }
    
    // Package-private getters - only Originator can call these
    String getContent() { return content; }
    int getCursorPosition() { return cursorPosition; }
}
```

- **Similar to / compared with**:
  - **vs Command**: Memento stores state snapshots; Command stores operations for undo
  - **vs Prototype**: Memento stores past state for restoration; Prototype creates new objects by cloning
  - **vs State**: Memento represents past state (for restoration); State represents current state (for behavior)

- **Further reading**: [Memento README](Memento/README.md), demo: [MementoPatternDemo](Memento/MementoPatternDemo.java)

---

## Observer

- **Intent**: Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
- **Also Known As**: Dependents or Publish-Subscribe
- **When to use**:
  - You need to notify multiple objects when one object's state changes
  - You want to maintain loose coupling between subject and observers
  - You need to implement event-driven systems or publish-subscribe functionality
  - Multiple views need to stay synchronized with model data
- **Structure**:
  - Roles:
    - **Subject**: Maintains list of observers, provides attach/detach/notify methods
    - **Observer**: Interface defining update() method for receiving notifications
    - **ConcreteSubject**: Implements Subject, stores state, notifies observers when state changes
    - **ConcreteObserver**: Implements Observer, updates its own state when notified
- **Code feel**:

```java
// Create Subject and Observers
WeatherStation weatherStation = new WeatherStation();
PhoneApp phoneApp = new PhoneApp("WeatherPro");
WebsiteDashboard website = new WebsiteDashboard("Weather.com");

// Register observers
weatherStation.registerObserver(phoneApp);
weatherStation.registerObserver(website);

// Update weather data - observers are automatically notified!
weatherStation.setMeasurements(25.5f, 65.0f, 1013.25f);
```

```java
// Subject notifies all observers automatically
public void setMeasurements(float temp, float hum, float press) {
    this.temperature = temp;
    this.humidity = hum;
    this.pressure = press;
    notifyObservers();  // Automatically notify all registered observers
}

// Observer receives update
public void update(float temp, float hum, float press) {
    this.temperature = temp;
    this.humidity = hum;
    this.pressure = press;
    display();  // Update display
}
```

- **Similar to / compared with**:
  - **vs Mediator**: Observer distributes notifications; Mediator centralizes coordination
  - **vs Command**: Observer notifies multiple observers; Command encapsulates single operations
  - **vs Chain of Responsibility**: Observer broadcasts to all; Chain passes to one handler

- **Further reading**: [Observer README](Observer/README.md), demo: [ObserverPatternDemo](Observer/ObserverPatternDemo.java)

---

## State

- **Intent**: Allow an object to alter its behavior when its internal state changes. The object will appear to change its class. A State Pattern says that "the class behavior changes based on its state". In State Pattern, we create objects that represent various states and a context object whose behavior varies as its state object changes.
- **Also Known As**: Objects for States
- **When to use**:
  - An object's behavior depends on its state, and it must change behavior at runtime
  - You have many conditional statements that depend on the object's state
  - State transitions are complex or numerous
  - You want to eliminate large if/else or switch statements based on state
- **Structure**:
  - Roles:
    - **State**: Interface defining behavior for states
    - **ConcreteState**: Implements State, represents specific state and defines behavior
    - **Context**: Maintains current state object, delegates behavior to current state
- **Code feel**:

```java
// Create Context
VendingMachine machine = new VendingMachine();

// Behavior changes based on state automatically
machine.pressDispense();  // Does nothing (NoCoinState)
machine.insertCoin();     // Transitions to HasCoinState
machine.pressDispense();  // Dispenses item (HasCoinState)
```

```java
// Each state defines its own behavior
class NoCoinState implements VendingMachineState {
    public void insertCoin() {
        machine.setState(machine.getHasCoinState());  // Transition
    }
    
    public void pressDispense() {
        System.out.println("Please insert a coin first.");
    }
}

class HasCoinState implements VendingMachineState {
    public void pressDispense() {
        machine.setState(machine.getDispensingState());  // Transition
    }
}
```

- **Similar to / compared with**:
  - **vs Strategy**: State changes behavior with internal state; Strategy swaps algorithms
  - **vs Memento**: State represents current state for behavior; Memento stores past state for restoration
  - **vs Command**: State encapsulates state-specific behavior; Command encapsulates operations

- **Further reading**: [State README](State/README.md), demo: [StatePatternDemo](State/StatePatternDemo.java)

---

## Adapter

- **Intent**: Convert the interface of a class into another interface clients expect so that incompatible classes can work together.
- **When to use**:
  - You have existing code (legacy or third-party) with an interface that does not match what your client code expects.
  - You want to reuse an existing class without modifying it.
- **Structure**:
  - Diagrams:
    - ![Adapter Diagram 1](Adapter/adapter_diagram_1.jpeg)  
      *Shows a client using a Target interface while an Adapter wraps an Adaptee to translate calls.*
    - ![Adapter Diagram 2](Adapter/adapter_diagram_2.jpeg)  
      *Shows the Movable speed example converting MPH to KMPH via an object adapter.*
  - Roles:
    - **Target**: interface that the client expects.
    - **Adaptee**: existing class with incompatible interface.
    - **Adapter**: implements Target, wraps Adaptee, converts calls.
    - **Client**: talks only to Target.
- **Code feel**:

```java
RoundHole hole = new RoundHole(5);
SquarePeg square = new SquarePeg(5);
RoundPegTarget adapter = new SquarePegAdapter(square);
hole.fits(adapter);
```
- **Similar to / compared with**:
  - **Bridge**: Bridge is a design from the start to separate abstraction and implementation; Adapter is usually added later to fix an incompatibility.
  - **Facade**: Facade provides a new simpler interface to a whole subsystem; Adapter converts one existing class’s interface to another.

- **Further reading**: [Adapter README](Adapter/README.md), demos: [AdapterDemo](Adapter/AdapterDemo.java), [ChargerAdapterDemo](Adapter/ChargerAdapterDemo.java)

---

## Bridge

- **Intent**: Decouple an abstraction from its implementation so that the two can vary independently.
- **When to use**:
  - You have two independent dimensions that can both change (e.g., transport type vs engine type, GUI vs OS API).
  - You want to avoid combinatorial explosion like `GasCar`, `ElectricCar`, `GasPlane`, `ElectricPlane`, etc.
- **Structure**:
  - Diagrams:
    - ![Bridge Structure – Generic](Bridge/bridge_structure_uml.jpeg)  
      *Shows the general Bridge layout: Abstraction holding an Implementor, with RefinedAbstractions and ConcreteImplementors on each side.*
    - ![Bridge Example – Transport/Engine](Bridge/bridge_example_uml.jpeg)  
      *Shows the Transport/Engine example where Car and Plane use GasEngine or ElectricEngine via the Bridge composition.*
  - Roles:
    - **Abstraction** + **Refined Abstractions**: high-level interface and its variations (e.g., `Transport`, `Car`, `Plane`).
    - **Implementor** + **Concrete Implementors**: platform- or detail-specific behaviors (e.g., `Engine`, `GasEngine`, `ElectricEngine`).
    - **Client**: uses Abstraction; doesn't know which Implementor is used.
- **Code feel**:

```java
Transport car = new Car(new GasEngine());
Transport plane = new Plane(new ElectricEngine());
car.drive();
plane.drive();
```
- **Similar to / compared with**:
  - **Adapter**: Adapter usually wraps one existing class to fix an incompatible interface; Bridge is a design choice from the start to separate two hierarchies.
  - **Strategy**: Strategy swaps algorithms; Bridge splits class responsibilities between abstraction and implementation.
  - **Decorator**: Decorator stacks behaviors; Bridge separates two dimensions (type vs implementation).

- **Further reading**: [Bridge README](Bridge/README.md), demos: [BridgeTransportDemo](Bridge/BridgeTransportDemo.java), [BridgeGuiApiDemo](Bridge/BridgeGuiApiDemo.java)

---

## Composite

- **Intent**: Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly.
- **When to use**:
  - You want to represent part-whole hierarchies of objects
  - You want clients to be able to ignore the difference between compositions of objects and individual objects
  - You want to treat all objects in the composition uniformly
- **Structure**:
  - Roles:
    - **Component**: Declares interface for objects in composition and for accessing/managing child components
    - **Leaf**: Represents leaf objects in the composition (has no children)
    - **Composite**: Defines behavior for components having children and stores child components
    - **Client**: Manipulates objects in the composition through the Component interface
- **Code feel**:

```java
// Treat individual and composite objects uniformly
Component file = new File("document.txt");
Component folder = new Folder("Documents");
folder.add(file);
folder.add(new File("readme.txt"));

folder.display();  // Displays folder and all contents
```

- **Similar to / compared with**:
  - **vs Decorator**: Composite builds tree structures; Decorator adds behavior to objects
  - **vs Iterator**: Composite structures can be traversed using Iterator pattern

- **Further reading**: [Composite README](Composite/README.md), demo: [CompositePatternDemo](Composite/CompositePatternDemo.java)

---

## Decorator

- **Intent**: Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.
- **When to use**:
  - Many optional features around a core type (e.g., condiments around coffee, visual effects around a window).
  - You want to add/remove behavior at runtime and avoid subclass explosion.
- **Structure**:
  - Diagram:
    - ![Decorator Diagram](Decorator/decorator_diagram.jpeg)  
      *Shows the Component hierarchy with Decorator subclasses that wrap components to add responsibilities dynamically.*
  - Roles:
    - **Component**: base interface or abstract class.
    - **Concrete Component**: core implementation.
    - **Decorator**: implements Component and wraps another Component.
    - **Concrete Decorators**: add extra behavior before/after delegating to wrapped component.
- **Code feel**:

```java
Coffee c = new SimpleCoffee();
c = new Milk(c);
c = new Sugar(c);
```
- **Similar to / compared with**:
  - **Proxy**: Proxy controls access; Decorator adds behavior while preserving the same interface.
  - **Adapter**: Adapter changes the interface; Decorator keeps the same interface and adds responsibilities.

- **Further reading**: [Decorator README](Decorator/README.md), demos: [DecoratorDemo](Decorator/DecoratorDemo.java), [ShapeDecoratorDemo](Decorator/ShapeDecoratorDemo.java), [FileDecoratorDemo](Decorator/FileDecoratorDemo.java), [FileDecoratorChainingDemo](Decorator/FileDecoratorChainingDemo.java), [FileDecoratorPatternDemo](Decorator/FileDecoratorPatternDemo.java)

---

## Facade

- **Intent**: Provide a unified, simple interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.
- **When to use**:
  - A subsystem is complex and you want to simplify the typical use cases.
  - You want to decouple client code from many subsystem classes.
- **Structure**:
  - Diagram:
    - ![Facade Diagram](Facade/facade_diagram.jpeg)  
      *Shows a single Facade class delegating work to multiple complex subsystem classes behind the scenes.*
  - Roles:
    - **Facade**: offers simple high-level operations.
    - **Subsystem classes**: many classes that implement the actual work.
    - **Client**: uses only the Facade.
- **Code feel**:

```java
HotelFacade hotel = new HotelFacade();
hotel.bookStayWithBreakfast();
```
- **Similar to / compared with**:
  - **Adapter**: Adapter changes one class's interface; Facade groups many classes behind a simpler interface.
  - **Bridge**: Bridge decouples abstraction from implementation for two hierarchies; Facade is just a convenient front-end to an existing subsystem.
  - **Proxy**: Proxy controls access or adds lazy loading/security around one object; Facade only simplifies usage of many classes without access control semantics.

- **Further reading**: [Facade README](Facade/README.md), demos: [WithoutFacadeDemo](Facade/WithoutFacadeDemo.java), [WithFacadeDemo](Facade/WithFacadeDemo.java), [HotelFacadeDemo](Facade/HotelFacadeDemo.java)

---

## Flyweight

- **Intent**: Fit more objects into memory by sharing common (intrinsic) state between multiple objects and externalizing varying (extrinsic) state.
- **When to use**:
  - You have *huge* numbers of similar objects (characters, trees, tiles) and memory use is a concern.
- **Structure**:
  - Diagrams:
    - ![Flyweight Diagram 1](Flyweight/Flyweight_uml_structure.jpeg)  
      *Shows the Flyweight interface, ConcreteFlyweight, FlyweightFactory, and Client that supplies extrinsic state.*
    - ![Flyweight Diagram 2](Flyweight/Flyweight_example_uml.jpeg)  
      *Shows the Forest example with Tree holding position (extrinsic) and TreeType as the shared flyweight for intrinsic state.*
  - Roles:
    - **Flyweight** / **ConcreteFlyweight**: store intrinsic state (shared).
    - **FlyweightFactory**: creates and caches flyweights.
    - **Client / Context**: stores extrinsic state and supplies it to flyweights.
- **Code feel**:

```java
TreeType oak = TreeFactory.getTreeType("Oak", Color.GREEN);
forest.plantTree(10, 20, "Oak", Color.GREEN); // shares same TreeType
```
- **Similar to / compared with**:
  - **Prototype**: Prototype clones whole objects; Flyweight avoids copying by sharing intrinsic state.
  - **Singleton**: Singleton has exactly one global instance; Flyweight has one instance *per intrinsic configuration* (e.g., per tree type or character).
  - **Caching / Pools**: Pools reuse heavy objects over time; Flyweight shares immutable intrinsic state among many logical objects at the same time.

- **Further reading**: [Flyweight README](Flyweight/README.md), demos: [FlyweightForestDemo](Flyweight/FlyweightForestDemo.java), [FlyweightTextEditorDemo](Flyweight/FlyweightTextEditorDemo.java)

---

## Proxy

- **Intent**: Provide a surrogate or placeholder for another object to control access to it.
- **When to use**:
  - You want lazy loading, access control, logging, caching, or remote access while keeping the same interface.
- **Structure**:
  - Diagram:
    - ![Proxy Diagram](Proxy/diagram.png)  
      *Shows the Subject interface, RealSubject, and Proxy which forwards calls while adding access control or other logic.*
  - Roles:
    - **Subject**: common interface for RealSubject and Proxy.
    - **RealSubject**: does the real work.
    - **Proxy**: holds reference to RealSubject, controls access, may add behavior.
- **Code feel**:

```java
Subject service = new LoggingProxy(new RealService());
service.request();
```
- **Similar to / compared with**:
  - **Decorator**: both wrap another object with same interface; Decorator emphasizes adding features, Proxy emphasizes controlling access/lifecycle.
  - **Facade**: Facade simplifies a subsystem; Proxy stands in for a single object.

- **Further reading**: [Proxy README](Proxy/README.md), demos: [LoggingProxyDemo](Proxy/LoggingProxyDemo.java), [ProtectionProxyDemo](Proxy/ProtectionProxyDemo.java), [VirtualProxyDemo](Proxy/VirtualProxyDemo.java)

---

## High-Level Comparison Cheat Sheet

- **Interface / access focused**:
  - **Adapter**: change interface of one class to another.
  - **Facade**: offer a simpler interface to a whole subsystem.
  - **Proxy**: same interface, but controls access (lazy, secure, remote, logging).

- **Object creation focused**:
  - **Factory (Simple / Method / Abstract)**: centralize and vary which class gets instantiated.
  - **Builder**: construct complex objects step-by-step.
  - **Prototype**: copy existing configured objects.
  - **Singleton**: restrict to a single instance.

- **Structure / variation focused**:
  - **Bridge**: separate abstraction from implementation (two dimensions).
  - **Decorator**: wrap objects to add behavior dynamically.
  - **Flyweight**: share common intrinsic state across many small objects.


