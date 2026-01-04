## State Pattern

- **Intent**: Allow an object to alter its behavior when its internal state changes. The object will appear to change its class. A State Pattern says that "the class behavior changes based on its state". In State Pattern, we create objects that represent various states and a context object whose behavior varies as its state object changes.
- **Also Known As**: Objects for States
- **When to use**: When an object's behavior depends on its state, and it must change its behavior at runtime depending on that state, or when you have many conditional statements that depend on the object's state.

---

## Pattern Structure

The State pattern consists of the following components:

1. **`State`** (Interface)
   - Defines an interface for encapsulating the behavior associated with a particular state
   - Methods represent actions that can be performed in different states
   - Example: `VendingMachineState` interface

2. **`ConcreteState`** (Classes)
   - Implements the `State` interface
   - Each class represents a specific state and defines behavior for that state
   - Knows when to transition to another state
   - Examples: `NoCoinState`, `HasCoinState`, `DispensingState`, `OutOfStockState`

3. **`Context`** (Class)
   - Maintains an instance of a `ConcreteState` subclass that defines the current state
   - Delegates state-specific behavior to the current state object
   - Example: `VendingMachine`

**Key Relationships:**
- `Context` **has a** `State` object (composition)
- `ConcreteState` classes **implement** `State` interface
- `ConcreteState` objects **know about** `Context` (to transition states)
- `Context` **delegates** behavior to current `State` object

**Pattern Flow:**
1. Context maintains current state object
2. Client performs action on Context
3. Context delegates to current state object
4. State object performs behavior and may transition to another state
5. Context's behavior changes as state changes

---

## State Pattern Examples

### Example 1: Vending Machine States

**States:**
- **No Coin**: No coin inserted, waiting for coin
- **Has Coin**: Coin inserted, ready to dispense
- **Dispensing**: Item is being dispensed
- **Out of Stock**: Machine has no items left

**Behavior:**
- Pressing "dispense" with no coin does nothing
- Pressing "dispense" when "Has Coin" dispenses item
- Pressing "dispense" when "Out of Stock" shows error
- Each state defines what actions are valid and what state comes next

### Example 2: Traffic Light System

**States:**
- **Red**: Stop - cars must stop
- **Yellow**: Caution - cars should prepare to stop
- **Green**: Go - cars can move

**Behavior:**
- Each state defines what to do and what state comes next
- When light changes to Green, behavior changes automatically: cars move, then switch to Yellow, then Red
- Traffic light = context, Colors = states

---

## Implementation

### State Interface

```java
interface VendingMachineState {
    void insertCoin();
    void pressDispense();
    void ejectCoin();
}
```

### Concrete States

```java
class NoCoinState implements VendingMachineState {
    private VendingMachine machine;
    
    public NoCoinState(VendingMachine machine) {
        this.machine = machine;
    }
    
    @Override
    public void insertCoin() {
        System.out.println("Coin inserted.");
        machine.setState(machine.getHasCoinState());  // Transition to HasCoinState
    }
    
    @Override
    public void pressDispense() {
        System.out.println("Please insert a coin first.");
    }
    
    @Override
    public void ejectCoin() {
        System.out.println("No coin to eject.");
    }
}

class HasCoinState implements VendingMachineState {
    private VendingMachine machine;
    
    public HasCoinState(VendingMachine machine) {
        this.machine = machine;
    }
    
    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted.");
    }
    
    @Override
    public void pressDispense() {
        if (machine.getStock() > 0) {
            machine.setState(machine.getDispensingState());  // Transition to DispensingState
        } else {
            machine.setState(machine.getOutOfStockState());  // Transition to OutOfStockState
        }
    }
    
    @Override
    public void ejectCoin() {
        System.out.println("Coin ejected.");
        machine.setState(machine.getNoCoinState());  // Transition back to NoCoinState
    }
}
```

### Context Class

```java
class VendingMachine {
    private VendingMachineState currentState;
    private VendingMachineState noCoinState;
    private VendingMachineState hasCoinState;
    private VendingMachineState dispensingState;
    private VendingMachineState outOfStockState;
    
    public VendingMachine() {
        // Create state objects
        this.noCoinState = new NoCoinState(this);
        this.hasCoinState = new HasCoinState(this);
        this.dispensingState = new DispensingState(this);
        this.outOfStockState = new OutOfStockState(this);
        
        // Set initial state
        this.currentState = noCoinState;
    }
    
    public void setState(VendingMachineState state) {
        this.currentState = state;
    }
    
    // Delegate to current state
    public void insertCoin() {
        currentState.insertCoin();
    }
    
    public void pressDispense() {
        currentState.pressDispense();
    }
    
    public void ejectCoin() {
        currentState.ejectCoin();
    }
}
```

### Client Usage

```java
public class StatePatternDemo {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        
        // Behavior changes based on state
        machine.pressDispense();  // Does nothing (NoCoinState)
        machine.insertCoin();     // Transitions to HasCoinState
        machine.pressDispense();  // Dispenses item (HasCoinState)
    }
}
```

---

## Why Use the State Pattern?

### Code Without State Pattern

Without the State pattern, you would use large if/else or switch statements based on state:

```java
// Problem: Large if/else statements based on state
class VendingMachine {
    private String state;  // "NO_COIN", "HAS_COIN", "DISPENSING", "OUT_OF_STOCK"
    private int stock;
    
    public VendingMachine() {
        this.state = "NO_COIN";
        this.stock = 5;
    }
    
    public void insertCoin() {
        if (state.equals("NO_COIN")) {
            state = "HAS_COIN";
            System.out.println("Coin inserted.");
        } else if (state.equals("HAS_COIN")) {
            System.out.println("Coin already inserted.");
        } else if (state.equals("DISPENSING")) {
            System.out.println("Please wait, item is being dispensed.");
        } else if (state.equals("OUT_OF_STOCK")) {
            System.out.println("Machine is out of stock. Coin returned.");
        }
    }
    
    public void pressDispense() {
        if (state.equals("NO_COIN")) {
            System.out.println("Please insert a coin first.");
        } else if (state.equals("HAS_COIN")) {
            if (stock > 0) {
                state = "DISPENSING";
                System.out.println("Dispensing item...");
                // Dispense logic
                stock--;
                state = "NO_COIN";
            } else {
                state = "OUT_OF_STOCK";
                System.out.println("Item out of stock.");
            }
        } else if (state.equals("DISPENSING")) {
            System.out.println("Item is already being dispensed.");
        } else if (state.equals("OUT_OF_STOCK")) {
            System.out.println("Machine is out of stock. Cannot dispense.");
        }
    }
    
    public void ejectCoin() {
        if (state.equals("NO_COIN")) {
            System.out.println("No coin to eject.");
        } else if (state.equals("HAS_COIN")) {
            state = "NO_COIN";
            System.out.println("Coin ejected.");
        } else if (state.equals("DISPENSING")) {
            System.out.println("Cannot eject coin while dispensing.");
        } else if (state.equals("OUT_OF_STOCK")) {
            System.out.println("No coin to eject.");
            state = "NO_COIN";
        }
    }
    
    // Adding a new state requires modifying ALL methods!
    // public void newAction() {
    //     if (state.equals("NO_COIN")) {
    //         // handle NO_COIN
    //     } else if (state.equals("HAS_COIN")) {
    //         // handle HAS_COIN
    //     } else if (state.equals("DISPENSING")) {
    //         // handle DISPENSING
    //     } else if (state.equals("OUT_OF_STOCK")) {
    //         // handle OUT_OF_STOCK
    //     } else if (state.equals("NEW_STATE")) {  // Must add to every method!
    //         // handle NEW_STATE
    //     }
    // }
}
```

**Problems:**
- ❌ **Large conditional statements**: Every method has if/else chains checking state
- ❌ **Hard to maintain**: Adding new state requires modifying all methods
- ❌ **Violates Open/Closed Principle**: Must modify existing code to add states
- ❌ **Error-prone**: Easy to forget handling a state in a method
- ❌ **State transitions scattered**: Transition logic spread across methods
- ❌ **Hard to test**: Difficult to test individual state behaviors
- ❌ **Code duplication**: Similar logic repeated in multiple methods

### The Solution: State Pattern

The State pattern solves these problems by:
- ✅ **Encapsulates state behavior**: Each state is a separate class
- ✅ **Easy to extend**: Add new states without modifying existing code
- ✅ **Clear state transitions**: Transitions are explicit in state classes
- ✅ **Eliminates conditionals**: No large if/else statements
- ✅ **Single Responsibility**: Each state class handles one state's behavior
- ✅ **Easy to test**: Each state can be tested independently

---

## Pattern Participants

1. **State** (Interface)
   - Defines an interface for encapsulating the behavior associated with a particular state
   - Example: `VendingMachineState` interface

2. **ConcreteState** (Classes)
   - Implements the `State` interface
   - Each class represents a specific state
   - Defines behavior for that state and knows when to transition
   - Examples: `NoCoinState`, `HasCoinState`, `DispensingState`, `OutOfStockState`

3. **Context** (Class)
   - Maintains an instance of a `ConcreteState` subclass that defines the current state
   - Delegates state-specific behavior to the current state object
   - Example: `VendingMachine`

---

## Pros

- ✅ **Encapsulates State Behavior**: Each state's behavior is encapsulated in its own class
- ✅ **Easy to Add States**: Add new states without modifying existing code
- ✅ **Eliminates Conditionals**: No large if/else or switch statements
- ✅ **Clear State Transitions**: Transitions are explicit and easy to understand
- ✅ **Single Responsibility**: Each state class has one responsibility
- ✅ **Easy to Test**: Each state can be tested independently
- ✅ **Open/Closed Principle**: Open for extension (new states), closed for modification

---

## Cons

- ❌ **More Classes**: Increases number of classes in the system
- ❌ **State Objects**: Each state is an object (memory overhead)
- ⚠️ **State Transitions**: Must be careful about transition logic to avoid invalid states
- ⚠️ **Context Coupling**: States need reference to context for transitions

---

## When to Use State Pattern

### ✅ Use State Pattern When:

#### Object Behavior Depends on State:
- An object's behavior depends on its state, and it must change behavior at runtime
- You have many conditional statements that depend on the object's state
- State transitions are complex or numerous

#### State-Specific Behavior:
- Different states require different behavior for the same operations
- States have clear, distinct behaviors
- State transitions are well-defined

#### Eliminate Conditionals:
- You want to eliminate large if/else or switch statements based on state
- State logic is scattered across multiple methods
- You want to make state transitions explicit

#### Additional Use Cases:
- **Vending Machines**: Different behavior based on coin/stock state
- **Traffic Lights**: Different behavior for Red/Yellow/Green
- **Game Characters**: Different behavior for Idle/Running/Attacking states
- **Document Workflow**: Draft/Review/Approved/Published states
- **Network Connections**: Connected/Disconnected/Connecting states

### ❌ Don't Use State Pattern When:
- **Simple State**: Object has only one or two states with simple behavior
- **No State-Specific Behavior**: States don't change behavior significantly
- **Performance Critical**: Object creation overhead is too high
- **State Transitions Unclear**: State transitions are not well-defined

---

## Real-World Examples

### Vending Machines
- **States**: No Coin, Has Coin, Dispensing, Out of Stock
- **Behavior**: Insert coin, dispense, eject coin behave differently in each state
- **Transitions**: No Coin → Has Coin → Dispensing → No Coin

### Traffic Light Systems
- **States**: Red, Yellow, Green
- **Behavior**: Each color has different meaning and next state
- **Transitions**: Red → Green → Yellow → Red

### Game Development
- **Character States**: Idle, Running, Jumping, Attacking, Dead
- **Behavior**: Movement, actions behave differently in each state
- **Transitions**: Idle → Running → Jumping → Idle

### Document Management
- **Document States**: Draft, Review, Approved, Published, Archived
- **Behavior**: Edit, approve, publish behave differently in each state
- **Transitions**: Draft → Review → Approved → Published

---

## Compare with Other Patterns

### State vs Strategy

**State:**
- **Changes behavior** based on **internal state**
- States are **related** and **transition** between each other
- Used when **object's behavior changes** with state
- Example: Vending machine behavior changes with coin/stock state

**Strategy:**
- **Changes behavior** by **swapping algorithms**
- Strategies are **independent** and **interchangeable**
- Used when you need **different algorithms** for the same task
- Example: Different sorting algorithms (QuickSort, MergeSort)

**Key Difference:**
- State = behavior changes with **internal state** (stateful)
- Strategy = behavior changes by **choosing algorithm** (stateless)

### State vs Memento

**State:**
- Represents **current state** of an object
- Used for **state transitions** and behavior changes
- States are **active** and define behavior

**Memento:**
- Stores **snapshots** of object state
- Used for **restoring** previous states
- Mementos are **passive** and just store data

**Key Difference:**
- State = **current state** for behavior (active)
- Memento = **past state** for restoration (passive)

### State vs Command

**State:**
- Encapsulates **state-specific behavior**
- Behavior changes **automatically** with state
- Used for **state-dependent** operations

**Command:**
- Encapsulates **operations** as objects
- Operations are **executed** on demand
- Used for **queuing, logging, undo** operations

**Key Difference:**
- State = behavior based on **current state**
- Command = **operation** encapsulation

---

## Best Practices

1. **State Objects**: Consider making state objects singletons if they don't have instance-specific data
2. **State Transitions**: Keep transition logic in state classes, not in context
3. **Initial State**: Always set a valid initial state in the constructor
4. **Invalid Transitions**: Handle invalid state transitions gracefully
5. **State Entry/Exit**: Consider adding `enter()` and `exit()` methods for state setup/cleanup
6. **State Enumeration**: Use enums or constants for state identification if needed

---

## Implementation Notes

### State Objects as Singletons

If state objects don't have instance-specific data, make them singletons:

```java
class NoCoinState implements VendingMachineState {
    private static NoCoinState instance;
    
    public static NoCoinState getInstance() {
        if (instance == null) {
            instance = new NoCoinState();
        }
        return instance;
    }
    
    // State objects don't need reference to context if transitions are handled differently
}
```

### State Entry and Exit Actions

```java
interface VendingMachineState {
    void enter();  // Called when entering this state
    void exit();   // Called when exiting this state
    void insertCoin();
    void pressDispense();
    void ejectCoin();
}

class HasCoinState implements VendingMachineState {
    @Override
    public void enter() {
        System.out.println("Machine ready. Select an item.");
    }
    
    @Override
    public void exit() {
        System.out.println("Leaving HasCoinState.");
    }
}
```

### State Machine Pattern

For complex state machines, consider using a state machine framework or table-driven approach:

```java
// State transition table
Map<State, Map<Event, State>> transitions = new HashMap<>();
transitions.put(NO_COIN, Map.of(INSERT_COIN, HAS_COIN));
transitions.put(HAS_COIN, Map.of(PRESS_DISPENSE, DISPENSING, EJECT_COIN, NO_COIN));
```

---

## Notes

- ⚠️ **State Objects**: Each state is an object - consider memory implications
- ⚠️ **State Transitions**: Ensure transitions are valid and don't create invalid states
- ⚠️ **Context Reference**: States often need reference to context for transitions
- ⚠️ **Thread Safety**: If context is accessed by multiple threads, ensure state transitions are thread-safe
- ⚠️ **State Persistence**: Consider how to persist and restore state if needed

---

**Further reading**: See the demo for a complete working example:
- [StatePatternDemo.java](StatePatternDemo.java)

