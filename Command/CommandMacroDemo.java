// Command Pattern with Macro Commands (Composite Commands)
// Demonstrates combining multiple commands into a single macro command
// Roles:
//   - Command: Interface with execute() method
//   - Concrete Commands: Individual device commands
//   - Macro Command: Composite command that executes multiple commands
//   - Invoker: RemoteControl executes commands
//   - Receivers: Various devices (TV, Stereo, Light)
//   - Client: main() demonstrates macro commands

import java.util.ArrayList;
import java.util.List;

/**
 * MACRO COMMAND INTERFACE
 * This is the basic Command interface for this example.
 * 
 * Key Concept: A macro command is itself a command, but it contains
 * multiple other commands. When executed, it executes all of them.
 * This demonstrates the Composite pattern combined with Command pattern.
 */
interface MacroCommand {
    /**
     * Executes the command. For macro commands, this will execute
     * multiple commands in sequence.
     */
    void execute();
}

/**
 * RECEIVER: MacroTV
 * A TV device that can be turned on and off.
 * This is a concrete receiver that performs actual operations.
 */
class MacroTV {
    private String name;  // Name/location of the TV
    
    /**
     * Constructor: Creates a new TV with a given name.
     * @param name The name/location of the TV
     */
    public MacroTV(String name) {
        this.name = name;
    }
    
    /**
     * Turns the TV on.
     * In a real application, this would send a signal to the TV hardware.
     */
    public void on() {
        System.out.println(name + " TV is ON");
    }
    
    /**
     * Turns the TV off.
     * In a real application, this would send a signal to the TV hardware.
     */
    public void off() {
        System.out.println(name + " TV is OFF");
    }
}

/**
 * RECEIVER: MacroStereo
 * A stereo system that can be turned on/off and have its volume adjusted.
 */
class MacroStereo {
    private String name;  // Name/location of the stereo
    
    /**
     * Constructor: Creates a new stereo with a given name.
     * @param name The name/location of the stereo
     */
    public MacroStereo(String name) {
        this.name = name;
    }
    
    /**
     * Turns the stereo on.
     */
    public void on() {
        System.out.println(name + " Stereo is ON");
    }
    
    /**
     * Turns the stereo off.
     */
    public void off() {
        System.out.println(name + " Stereo is OFF");
    }
    
    /**
     * Sets the volume to a specific level.
     * @param volume The volume level to set
     */
    public void setVolume(int volume) {
        System.out.println(name + " Stereo volume set to " + volume);
    }
}

/**
 * RECEIVER: MacroLight
 * A light that can be turned on and off.
 */
class MacroLight {
    private String name;  // Name/location of the light
    
    /**
     * Constructor: Creates a new light with a given name.
     * @param name The name/location of the light
     */
    public MacroLight(String name) {
        this.name = name;
    }
    
    /**
     * Turns the light on.
     */
    public void on() {
        System.out.println(name + " Light is ON");
    }
    
    /**
     * Turns the light off.
     */
    public void off() {
        System.out.println(name + " Light is OFF");
    }
}

/**
 * CONCRETE COMMAND: TVOnCommand
 * Command to turn a TV on.
 * This is a simple command that performs a single operation.
 */
class TVOnCommand implements MacroCommand {
    private MacroTV tv;  // The receiver (TV to control)
    
    /**
     * Constructor: Creates a command to turn on a specific TV.
     * @param tv The TV to turn on
     */
    public TVOnCommand(MacroTV tv) {
        this.tv = tv;  // Store reference to the receiver
    }
    
    /**
     * Executes the command by turning the TV on.
     */
    @Override
    public void execute() {
        tv.on();  // Call receiver's method to perform the action
    }
}

/**
 * CONCRETE COMMAND: TVOffCommand
 * Command to turn a TV off.
 */
class TVOffCommand implements MacroCommand {
    private MacroTV tv;  // The receiver (TV to control)
    
    /**
     * Constructor: Creates a command to turn off a specific TV.
     * @param tv The TV to turn off
     */
    public TVOffCommand(MacroTV tv) {
        this.tv = tv;  // Store reference to the receiver
    }
    
    /**
     * Executes the command by turning the TV off.
     */
    @Override
    public void execute() {
        tv.off();  // Call receiver's method to perform the action
    }
}

/**
 * CONCRETE COMMAND: StereoOnCommand
 * Command to turn a stereo on.
 */
class StereoOnCommand implements MacroCommand {
    private MacroStereo stereo;  // The receiver (stereo to control)
    
    /**
     * Constructor: Creates a command to turn on a specific stereo.
     * @param stereo The stereo to turn on
     */
    public StereoOnCommand(MacroStereo stereo) {
        this.stereo = stereo;  // Store reference to the receiver
    }
    
    /**
     * Executes the command by turning the stereo on.
     */
    @Override
    public void execute() {
        stereo.on();  // Call receiver's method to perform the action
    }
}

/**
 * CONCRETE COMMAND: StereoOffCommand
 * Command to turn a stereo off.
 */
class StereoOffCommand implements MacroCommand {
    private MacroStereo stereo;  // The receiver (stereo to control)
    
    /**
     * Constructor: Creates a command to turn off a specific stereo.
     * @param stereo The stereo to turn off
     */
    public StereoOffCommand(MacroStereo stereo) {
        this.stereo = stereo;  // Store reference to the receiver
    }
    
    /**
     * Executes the command by turning the stereo off.
     */
    @Override
    public void execute() {
        stereo.off();  // Call receiver's method to perform the action
    }
}

/**
 * CONCRETE COMMAND: StereoVolumeCommand
 * Command to set the volume on a stereo.
 * 
 * Note: This command takes a parameter (volume level) in addition to
 * the receiver. This shows how commands can encapsulate both the
 * receiver and parameters needed for the operation.
 */
class StereoVolumeCommand implements MacroCommand {
    private MacroStereo stereo;  // The receiver (stereo to control)
    private int volume;           // The volume level to set
    
    /**
     * Constructor: Creates a command to set volume on a specific stereo.
     * @param stereo The stereo whose volume should be set
     * @param volume The volume level to set
     */
    public StereoVolumeCommand(MacroStereo stereo, int volume) {
        this.stereo = stereo;  // Store reference to the receiver
        this.volume = volume;  // Store the parameter
    }
    
    /**
     * Executes the command by setting the stereo volume.
     */
    @Override
    public void execute() {
        stereo.setVolume(volume);  // Call receiver's method with parameter
    }
}

/**
 * CONCRETE COMMAND: MacroLightOnCommand
 * Command to turn a light on.
 */
class MacroLightOnCommand implements MacroCommand {
    private MacroLight light;  // The receiver (light to control)
    
    /**
     * Constructor: Creates a command to turn on a specific light.
     * @param light The light to turn on
     */
    public MacroLightOnCommand(MacroLight light) {
        this.light = light;  // Store reference to the receiver
    }
    
    /**
     * Executes the command by turning the light on.
     */
    @Override
    public void execute() {
        light.on();  // Call receiver's method to perform the action
    }
}

/**
 * CONCRETE COMMAND: MacroLightOffCommand
 * Command to turn a light off.
 */
class MacroLightOffCommand implements MacroCommand {
    private MacroLight light;  // The receiver (light to control)
    
    /**
     * Constructor: Creates a command to turn off a specific light.
     * @param light The light to turn off
     */
    public MacroLightOffCommand(MacroLight light) {
        this.light = light;  // Store reference to the receiver
    }
    
    /**
     * Executes the command by turning the light off.
     */
    @Override
    public void execute() {
        light.off();  // Call receiver's method to perform the action
    }
}

/**
 * COMPOSITE MACRO COMMAND: CompositeMacroCommand
 * This is the key class that demonstrates macro commands.
 * 
 * Key Concept: A macro command is a command that contains other commands.
 * When executed, it executes all of its contained commands in sequence.
 * 
 * This combines the Command pattern with the Composite pattern:
 * - It implements MacroCommand (so it's a command itself)
 * - It contains a list of MacroCommand objects (so it's a composite)
 * - When execute() is called, it executes all contained commands
 * 
 * Benefits:
 * - Can combine multiple operations into one
 * - Can be treated as a single command
 * - Can be nested (macro commands can contain other macro commands)
 */
class CompositeMacroCommand implements MacroCommand {
    private List<MacroCommand> commands;  // List of commands to execute
    
    /**
     * Constructor: Creates a macro command from a list of commands.
     * 
     * @param commands The list of commands to execute when this macro is executed
     */
    public CompositeMacroCommand(List<MacroCommand> commands) {
        // Create a copy of the list to avoid external modifications
        this.commands = new ArrayList<>(commands);
    }
    
    /**
     * Executes the macro command by executing all contained commands in order.
     * 
     * Process:
     * 1. Print a message indicating macro execution started
     * 2. Iterate through all commands in the list
     * 3. Execute each command in sequence
     * 4. Print a message indicating macro execution completed
     * 
     * Key Point: The order matters - commands execute in the order they were added.
     * This allows us to control the sequence of operations.
     */
    @Override
    public void execute() {
        System.out.println("--- Executing Macro Command ---");
        // Execute each command in the list
        for (MacroCommand command : commands) {
            command.execute();  // Execute each command sequentially
        }
        System.out.println("--- Macro Command Complete ---\n");
    }
}

/**
 * INVOKER: MacroRemoteControl
 * The object that holds and executes commands.
 * 
 * This invoker works the same way as a regular remote control,
 * but it can execute macro commands (which contain multiple operations)
 * just as easily as simple commands (which contain one operation).
 * 
 * This demonstrates the power of the Command pattern: the invoker
 * doesn't need to know whether it's executing a simple command or
 * a macro command - it just calls execute().
 */
class MacroRemoteControl {
    private MacroCommand command;  // The command currently set (can be simple or macro)
    
    public void setCommand(MacroCommand command) {
        this.command = command;
    }
    
    public void pressButton() {
        if (command != null) {
            command.execute();
        }
    }
}

/**
 * CLIENT: CommandMacroDemo
 * Demonstrates the Command pattern with macro commands.
 * 
 * This example shows:
 * 1. How to create individual commands
 * 2. How to combine multiple commands into a macro command
 * 3. How to execute macro commands as if they were single commands
 * 4. How macro commands enable complex operations with a single button press
 */
public class CommandMacroDemo {
    public static void main(String[] args) {
        System.out.println("=== Command Pattern with Macro Commands Demo ===\n");
        
        // STEP 1: Create receivers (the objects that will perform actual work)
        // These are the devices we want to control
        MacroTV tv = new MacroTV("Living Room");
        MacroStereo stereo = new MacroStereo("Music System");
        MacroLight light = new MacroLight("Living Room");
        
        // STEP 2: Create individual commands (simple commands)
        // Each command performs a single operation on a single device
        MacroCommand tvOn = new TVOnCommand(tv);                    // Turn TV on
        MacroCommand stereoOn = new StereoOnCommand(stereo);         // Turn stereo on
        MacroCommand stereoVolume = new StereoVolumeCommand(stereo, 11);  // Set stereo volume to 11
        MacroCommand lightOn = new MacroLightOnCommand(light);      // Turn light on
        
        MacroCommand tvOff = new TVOffCommand(tv);                   // Turn TV off
        MacroCommand stereoOff = new StereoOffCommand(stereo);      // Turn stereo off
        MacroCommand lightOff = new MacroLightOffCommand(light);     // Turn light off
        
        // STEP 3: Create macro commands (composite commands)
        // A macro command combines multiple simple commands into one
        
        // Create "Party Mode On" macro: turns on light, TV, stereo, and sets volume
        // This demonstrates how we can combine multiple operations into one command
        List<MacroCommand> partyModeOn = new ArrayList<>();
        partyModeOn.add(lightOn);        // First: Turn light on
        partyModeOn.add(tvOn);           // Second: Turn TV on
        partyModeOn.add(stereoOn);       // Third: Turn stereo on
        partyModeOn.add(stereoVolume);   // Fourth: Set volume to 11
        // Create the macro command from the list
        MacroCommand partyModeOnMacro = new CompositeMacroCommand(partyModeOn);
        
        // Create "Party Mode Off" macro: turns off light, TV, and stereo
        // This shows how we can create a reverse macro command
        List<MacroCommand> partyModeOff = new ArrayList<>();
        partyModeOff.add(lightOff);      // First: Turn light off
        partyModeOff.add(tvOff);         // Second: Turn TV off
        partyModeOff.add(stereoOff);     // Third: Turn stereo off
        // Create the macro command from the list
        MacroCommand partyModeOffMacro = new CompositeMacroCommand(partyModeOff);
        
        // STEP 4: Create the invoker (remote control)
        // The remote control can execute both simple and macro commands
        MacroRemoteControl remote = new MacroRemoteControl();
        
        // STEP 5: Execute macro commands
        // Notice how we execute a macro command exactly like a simple command
        
        // Execute "Party Mode On" macro
        // This single command execution will:
        // 1. Turn on the light
        // 2. Turn on the TV
        // 3. Turn on the stereo
        // 4. Set the stereo volume to 11
        System.out.println("--- Activating Party Mode ---");
        remote.setCommand(partyModeOnMacro);  // Set the macro command
        remote.pressButton();                 // Execute it (all operations happen)
        
        // Execute "Party Mode Off" macro
        // This single command execution will:
        // 1. Turn off the light
        // 2. Turn off the TV
        // 3. Turn off the stereo
        System.out.println("--- Deactivating Party Mode ---");
        remote.setCommand(partyModeOffMacro);  // Set a different macro command
        remote.pressButton();                    // Execute it (all operations happen)
        
        // SUMMARY: What we've demonstrated
        System.out.println("--- Macro Command Benefits ---");
        System.out.println("✓ Combine multiple commands into one");
        System.out.println("✓ Execute complex operations with single button press");
        System.out.println("✓ Commands can be easily reordered or modified");
        System.out.println("✓ Supports both individual and composite commands");
        
        // KEY TAKEAWAYS:
        // 1. Macro commands are commands that contain other commands
        // 2. They can be treated exactly like simple commands
        // 3. They enable complex operations with a single button press
        // 4. The order of commands in a macro matters
        // 5. Macros can be nested (a macro can contain other macros)
        // 6. This combines Command pattern with Composite pattern
    }
}
