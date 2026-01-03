// State Pattern – Vending Machine Example
// The class behavior changes based on its state
// Also known as: Objects for States
// Roles:
//   - State: VendingMachineState interface defining state behavior
//   - ConcreteState: NoCoinState, HasCoinState, DispensingState, OutOfStockState
//   - Context: VendingMachine class whose behavior varies as its state changes
//   - Client: main() demonstrates state transitions

/**
 * STATE INTERFACE
 * Defines the interface for encapsulating the behavior associated with
 * a particular state of the Context.
 */
interface VendingMachineState {
    /**
     * Handles inserting a coin into the vending machine.
     * Behavior depends on the current state.
     */
    void insertCoin();
    
    /**
     * Handles pressing the dispense button.
     * Behavior depends on the current state.
     */
    void pressDispense();
    
    /**
     * Handles ejecting a coin (canceling the transaction).
     * Behavior depends on the current state.
     */
    void ejectCoin();
}

/**
 * CONCRETE STATE: NoCoinState
 * Represents the state when no coin has been inserted.
 * In this state, inserting a coin transitions to HasCoinState.
 */
class NoCoinState implements VendingMachineState {
    private VendingMachine machine;
    
    public NoCoinState(VendingMachine machine) {
        this.machine = machine;
    }
    
    @Override
    public void insertCoin() {
        System.out.println("Coin inserted. You can now select an item.");
        machine.setState(machine.getHasCoinState());
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

/**
 * CONCRETE STATE: HasCoinState
 * Represents the state when a coin has been inserted.
 * In this state, pressing dispense transitions to DispensingState.
 */
class HasCoinState implements VendingMachineState {
    private VendingMachine machine;
    
    public HasCoinState(VendingMachine machine) {
        this.machine = machine;
    }
    
    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted. Press dispense to get your item.");
    }
    
    @Override
    public void pressDispense() {
        if (machine.getStock() > 0) {
            System.out.println("Dispensing item...");
            machine.setState(machine.getDispensingState());
        } else {
            System.out.println("Item out of stock. Ejecting coin.");
            machine.setState(machine.getOutOfStockState());
        }
    }
    
    @Override
    public void ejectCoin() {
        System.out.println("Coin ejected. Returning to no coin state.");
        machine.setState(machine.getNoCoinState());
    }
}

/**
 * CONCRETE STATE: DispensingState
 * Represents the state when an item is being dispensed.
 * After dispensing, transitions back to NoCoinState.
 */
class DispensingState implements VendingMachineState {
    private VendingMachine machine;
    
    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }
    
    @Override
    public void insertCoin() {
        System.out.println("Please wait, item is being dispensed.");
    }
    
    @Override
    public void pressDispense() {
        System.out.println("Item is already being dispensed. Please wait.");
    }
    
    @Override
    public void ejectCoin() {
        System.out.println("Cannot eject coin while dispensing.");
    }
    
    /**
     * Completes the dispensing process.
     * This method is called by the context after dispensing.
     */
    public void completeDispensing() {
        machine.decreaseStock();
        System.out.println("Item dispensed! Enjoy your purchase.");
        machine.setState(machine.getNoCoinState());
    }
}

/**
 * CONCRETE STATE: OutOfStockState
 * Represents the state when the machine is out of stock.
 * In this state, most operations are disabled.
 */
class OutOfStockState implements VendingMachineState {
    private VendingMachine machine;
    
    public OutOfStockState(VendingMachine machine) {
        this.machine = machine;
    }
    
    @Override
    public void insertCoin() {
        System.out.println("Machine is out of stock. Coin returned.");
    }
    
    @Override
    public void pressDispense() {
        System.out.println("Machine is out of stock. Cannot dispense.");
    }
    
    @Override
    public void ejectCoin() {
        System.out.println("No coin to eject. Machine is out of stock.");
        machine.setState(machine.getNoCoinState());
    }
}

/**
 * CONTEXT: VendingMachine
 * The object whose behavior varies as its state object changes.
 * Maintains an instance of a ConcreteState subclass that defines
 * the current state.
 */
class VendingMachine {
    private VendingMachineState noCoinState;
    private VendingMachineState hasCoinState;
    private VendingMachineState dispensingState;
    private VendingMachineState outOfStockState;
    
    private VendingMachineState currentState;
    private int stock;
    
    public VendingMachine(int initialStock) {
        this.stock = initialStock;
        
        // Create state objects
        this.noCoinState = new NoCoinState(this);
        this.hasCoinState = new HasCoinState(this);
        this.dispensingState = new DispensingState(this);
        this.outOfStockState = new OutOfStockState(this);
        
        // Set initial state
        this.currentState = noCoinState;
    }
    
    /**
     * Sets the current state of the machine.
     * This changes the behavior of the machine.
     */
    public void setState(VendingMachineState state) {
        this.currentState = state;
    }
    
    /**
     * Delegates to the current state's insertCoin method.
     */
    public void insertCoin() {
        currentState.insertCoin();
    }
    
    /**
     * Delegates to the current state's pressDispense method.
     */
    public void pressDispense() {
        currentState.pressDispense();
        
        // If we're in dispensing state, complete the dispensing
        if (currentState instanceof DispensingState) {
            ((DispensingState) currentState).completeDispensing();
        }
    }
    
    /**
     * Delegates to the current state's ejectCoin method.
     */
    public void ejectCoin() {
        currentState.ejectCoin();
    }
    
    /**
     * Gets the current stock count.
     */
    public int getStock() {
        return stock;
    }
    
    /**
     * Decreases stock by 1.
     */
    public void decreaseStock() {
        if (stock > 0) {
            stock--;
        }
        
        // If stock reaches 0, transition to out of stock state
        if (stock == 0 && currentState != dispensingState) {
            setState(outOfStockState);
        }
    }
    
    /**
     * Restocks the machine.
     */
    public void restock(int amount) {
        stock += amount;
        System.out.println("Machine restocked. Stock: " + stock);
        
        // If we were out of stock, transition to no coin state
        if (currentState == outOfStockState) {
            setState(noCoinState);
        }
    }
    
    // Getters for state objects
    public VendingMachineState getNoCoinState() {
        return noCoinState;
    }
    
    public VendingMachineState getHasCoinState() {
        return hasCoinState;
    }
    
    public VendingMachineState getDispensingState() {
        return dispensingState;
    }
    
    public VendingMachineState getOutOfStockState() {
        return outOfStockState;
    }
    
    /**
     * Gets the name of the current state for display.
     */
    public String getCurrentStateName() {
        if (currentState == noCoinState) return "No Coin";
        if (currentState == hasCoinState) return "Has Coin";
        if (currentState == dispensingState) return "Dispensing";
        if (currentState == outOfStockState) return "Out of Stock";
        return "Unknown";
    }
}

/**
 * CLIENT: StatePatternDemo
 * Demonstrates the State pattern in action.
 * 
 * The client's role:
 * 1. Creates the Context (VendingMachine)
 * 2. Performs actions that trigger state transitions
 * 3. Observes how behavior changes based on state
 * 
 * Notice how the same action (pressDispense) behaves differently
 * depending on the current state of the machine.
 */
public class StatePatternDemo {
    public static void main(String[] args) {
        System.out.println("=== State Pattern Demo: Vending Machine ===\n");
        
        // Create Context with initial stock
        VendingMachine machine = new VendingMachine(2);
        
        System.out.println("--- Initial State: " + machine.getCurrentStateName() + " ---");
        
        // Try to dispense without coin - should fail
        System.out.println("\n--- Attempting to dispense without coin ---");
        machine.pressDispense();
        
        // Insert coin - transitions to HasCoinState
        System.out.println("\n--- Inserting coin ---");
        machine.insertCoin();
        System.out.println("Current State: " + machine.getCurrentStateName());
        
        // Try to insert another coin - should be ignored
        System.out.println("\n--- Attempting to insert another coin ---");
        machine.insertCoin();
        
        // Press dispense - transitions to DispensingState, then back to NoCoinState
        System.out.println("\n--- Pressing dispense button ---");
        machine.pressDispense();
        System.out.println("Current State: " + machine.getCurrentStateName());
        System.out.println("Stock remaining: " + machine.getStock());
        
        // Insert coin and dispense again
        System.out.println("\n--- Second purchase ---");
        machine.insertCoin();
        machine.pressDispense();
        System.out.println("Current State: " + machine.getCurrentStateName());
        System.out.println("Stock remaining: " + machine.getStock());
        
        // Try to purchase when out of stock
        System.out.println("\n--- Attempting purchase when out of stock ---");
        machine.insertCoin();
        System.out.println("Current State: " + machine.getCurrentStateName());
        machine.pressDispense();
        System.out.println("Current State: " + machine.getCurrentStateName());
        
        // Try operations in out of stock state
        System.out.println("\n--- Operations in Out of Stock state ---");
        machine.insertCoin();
        machine.pressDispense();
        machine.ejectCoin();
        
        // Restock the machine
        System.out.println("\n--- Restocking machine ---");
        machine.restock(1);
        System.out.println("Current State: " + machine.getCurrentStateName());
        
        // Eject coin without inserting - should do nothing
        System.out.println("\n--- Attempting to eject coin without inserting ---");
        machine.ejectCoin();
        
        System.out.println("\n--- State Pattern Benefits ---");
        System.out.println("✓ Behavior changes based on state automatically");
        System.out.println("✓ State transitions are encapsulated in state objects");
        System.out.println("✓ Easy to add new states without modifying existing code");
        System.out.println("✓ Eliminates large if/else or switch statements");
        System.out.println("✓ Each state knows its own transitions");
    }
}

