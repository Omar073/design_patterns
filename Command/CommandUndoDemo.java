// Command Pattern with Undo/Redo Support
// Demonstrates how Command pattern enables undo functionality
// Roles:
//   - Command: Interface with execute() and undo() methods
//   - Concrete Commands: LightOnCommand, LightOffCommand with undo support
//   - Invoker: RemoteControlWithUndo holds command history
//   - Receiver: Light performs actual operations
//   - Client: main() demonstrates undo functionality

/**
 * UNDOABLE COMMAND INTERFACE
 * Extends the basic Command pattern by adding undo() functionality.
 * 
 * Key Concept: For undo to work, each command must know how to reverse itself.
 * This interface requires both execute() (do the action) and undo() (reverse it).
 * 
 * Benefits:
 * - Each command encapsulates both the action and its reverse
 * - Undo logic is co-located with the command logic
 * - Easy to maintain undo history
 */
interface UndoableCommand {
    /**
     * Executes the command, performing the action.
     */
    void execute();
    
    /**
     * Undoes the command, reversing the action performed by execute().
     * This method must reverse exactly what execute() did.
     */
    void undo();
}

/**
 * RECEIVER: Light
 * This is the object that actually performs the work.
 * It maintains its own state (isOn) to track whether it's on or off.
 * 
 * Note: The receiver doesn't know about commands or undo - it just
 * provides methods to change its state. The command knows how to
 * use these methods to implement undo.
 */
class Light {
    private String location;  // Where the light is located (e.g., "Living Room")
    private boolean isOn;     // Current state: true if on, false if off
    
    /**
     * Constructor: Creates a new light at a specific location.
     * Initially, the light is off.
     * 
     * @param location The location/name of the light
     */
    public Light(String location) {
        this.location = location;
        this.isOn = false;  // Start with light off
    }
    
    /**
     * Turns the light on.
     * This method changes the state and prints a message.
     * In a real application, this would send a signal to the actual light hardware.
     */
    public void on() {
        isOn = true;  // Update state
        System.out.println(location + " light is ON");
    }
    
    /**
     * Turns the light off.
     * This method changes the state and prints a message.
     * In a real application, this would send a signal to the actual light hardware.
     */
    public void off() {
        isOn = false;  // Update state
        System.out.println(location + " light is OFF");
    }
    
    /**
     * Returns the current state of the light.
     * @return true if light is on, false if off
     */
    public boolean isOn() {
        return isOn;
    }
}

/**
 * CONCRETE COMMAND: LightOnCommand
 * This command turns a light on.
 * 
 * Undo Strategy: To undo turning a light on, we turn it off.
 * This is a simple example - more complex commands might need to
 * store the previous state to restore it exactly.
 */
class LightOnCommand implements UndoableCommand {
    private Light light;  // The receiver (the light to control)
    
    /**
     * Constructor: Creates a command to turn on a specific light.
     * @param light The light to turn on
     */
    public LightOnCommand(Light light) {
        this.light = light;  // Store reference to the receiver
    }
    
    /**
     * Executes the command: Turns the light on.
     * This is called when the command is executed.
     */
    @Override
    public void execute() {
        light.on();  // Call the receiver's method to perform the action
    }
    
    /**
     * Undoes the command: Turns the light off.
     * This reverses what execute() did.
     * 
     * Key Point: The undo() method knows that to undo "turn on",
     * we need to "turn off". This knowledge is encapsulated in the command.
     */
    @Override
    public void undo() {
        light.off();  // Reverse the action by turning the light off
    }
}

/**
 * CONCRETE COMMAND: LightOffCommand
 * This command turns a light off.
 * 
 * Undo Strategy: To undo turning a light off, we turn it on.
 * Notice how the undo() method is the opposite of execute().
 */
class LightOffCommand implements UndoableCommand {
    private Light light;  // The receiver (the light to control)
    
    /**
     * Constructor: Creates a command to turn off a specific light.
     * @param light The light to turn off
     */
    public LightOffCommand(Light light) {
        this.light = light;  // Store reference to the receiver
    }
    
    /**
     * Executes the command: Turns the light off.
     */
    @Override
    public void execute() {
        light.off();  // Call the receiver's method to perform the action
    }
    
    /**
     * Undoes the command: Turns the light on.
     * This reverses what execute() did.
     * 
     * Notice: The undo() for LightOffCommand does the opposite
     * of what LightOnCommand's execute() does. This symmetry is
     * what makes undo work correctly.
     */
    @Override
    public void undo() {
        light.on();  // Reverse the action by turning the light on
    }
}

/**
 * NO COMMAND (Null Object Pattern)
 * This is a special command that does nothing.
 * 
 * Purpose: Instead of using null to represent "no command",
 * we use this object. This avoids null checks and makes the
 * code more robust.
 * 
 * Benefits:
 * - No need to check for null before calling execute() or undo()
 * - Can be used to initialize command slots
 * - Follows the Null Object design pattern
 */
class NoCommand implements UndoableCommand {
    /**
     * Does nothing when executed.
     * This is useful for initializing command slots or representing
     * "no operation" states.
     */
    @Override
    public void execute() {
        // Do nothing - this is intentional
    }
    
    /**
     * Does nothing when undone.
     * Since execute() does nothing, undo() also does nothing.
     */
    @Override
    public void undo() {
        // Do nothing - this is intentional
    }
}

/**
 * INVOKER WITH UNDO SUPPORT: RemoteControlWithUndo
 * This extends the basic invoker by maintaining a history of commands.
 * 
 * Key Features:
 * 1. Stores the current command (for execution)
 * 2. Stores the last command (for undo)
 * 3. Provides pressUndo() method to undo the last command
 * 
 * Note: This is a simple undo implementation. A more advanced version
 * could maintain a stack of commands for multiple levels of undo.
 */
class RemoteControlWithUndo {
    private UndoableCommand command;      // Current command to execute
    private UndoableCommand lastCommand;   // Last executed command (for undo)
    
    public void setCommand(UndoableCommand command) {
        this.command = command;
    }
    
    public void pressButton() {
        if (command != null) {
            command.execute();
            lastCommand = command;
        }
    }
    
    public void pressUndo() {
        if (lastCommand != null) {
            System.out.println("--- Undoing last command ---");
            lastCommand.undo();
            lastCommand = null;
        } else {
            System.out.println("No command to undo");
        }
    }
}

/**
 * CLIENT: CommandUndoDemo
 * Demonstrates the Command pattern with undo functionality.
 * 
 * This example shows:
 * 1. How to create commands with undo support
 * 2. How to execute commands
 * 3. How to undo commands
 * 4. How commands encapsulate both action and reverse action
 */
public class CommandUndoDemo {
    public static void main(String[] args) {
        System.out.println("=== Command Pattern with Undo/Redo Demo ===\n");
        
        // STEP 1: Create the receiver (the object that performs actual work)
        // This is the light we want to control
        Light livingRoomLight = new Light("Living Room");
        
        // STEP 2: Create commands (encapsulate requests as objects with undo support)
        // Each command knows how to both execute and undo its action
        UndoableCommand lightOn = new LightOnCommand(livingRoomLight);   // Command to turn light on
        UndoableCommand lightOff = new LightOffCommand(livingRoomLight);  // Command to turn light off
        
        // STEP 3: Create the invoker (remote control with undo support)
        // The remote control can execute commands and undo them
        RemoteControlWithUndo remote = new RemoteControlWithUndo();
        
        // STEP 4: Execute commands and demonstrate undo
        System.out.println("--- Executing Commands ---");
        
        // Execute: Turn light on
        remote.setCommand(lightOn);   // Set the command
        remote.pressButton();         // Execute it (light turns ON)
        
        // Execute: Turn light off
        remote.setCommand(lightOff);  // Set a different command
        remote.pressButton();         // Execute it (light turns OFF)
        
        // STEP 5: Demonstrate undo functionality
        System.out.println("\n--- Undo Functionality ---");
        // Undo the last command (turning light off)
        // This should turn the light back on
        remote.pressUndo();           // Undo last command (light turns ON again)
        
        // STEP 6: Execute another command and undo it
        System.out.println("\n--- Executing Another Command ---");
        remote.setCommand(lightOn);   // Set command to turn light on
        remote.pressButton();         // Execute it (light turns ON)
        
        System.out.println("\n--- Undo Again ---");
        remote.pressUndo();           // Undo last command (light turns OFF)
        
        // SUMMARY: What we've demonstrated
        System.out.println("\n--- Undo Pattern Benefits ---");
        System.out.println("✓ Each command knows how to undo itself");
        System.out.println("✓ Undo history can be maintained");
        System.out.println("✓ Easy to implement redo functionality");
        
        // KEY TAKEAWAYS:
        // 1. Each command encapsulates both the action and its reverse
        // 2. The invoker maintains a history of executed commands
        // 3. Undo is achieved by calling undo() on the last command
        // 4. This pattern can be extended to support multiple levels of undo/redo
        //    by maintaining a stack of commands instead of just one
    }
}
