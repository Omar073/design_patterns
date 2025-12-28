// Command Pattern – Smart Home Remote Control Example
// Encapsulates requests as objects, allowing parameterization, queuing, logging, and undo
// Roles:
//   - Command: Interface defining execute() method
//   - Concrete Commands: TurnOnCommand, TurnOffCommand, ChangeChannelCommand, AdjustVolumeCommand
//   - Invoker: RemoteControl holds and invokes commands
//   - Receiver: Device interface and implementations (TV, Stereo) perform actual operations
//   - Client: main() creates commands, sets them in invoker, and executes

/**
 * COMMAND INTERFACE
 * This is the core of the Command pattern. It declares a single method
 * execute()
 * that all concrete commands must implement. This interface allows us to treat
 * all commands uniformly, regardless of what they do.
 * 
 * Key Benefit: The invoker (RemoteControl) doesn't need to know what specific
 * command it's executing - it just calls execute() on whatever command is set.
 */
interface Command {
    /**
     * Executes the command. Each concrete command will implement this method
     * to perform its specific action on its receiver.
     */
    void execute();
}

/**
 * RECEIVER INTERFACE
 * Defines the common interface for all devices that can receive commands.
 * Receivers are the objects that actually perform the work when a command
 * executes.
 * 
 * In this example, all devices can be turned on and off, which is why we
 * have a common Device interface. However, some devices have additional
 * methods (like changeChannel for TV or adjustVolume for Stereo).
 */
interface Device {

    void turnOn();

    void turnOff();
}

/**
 * CONCRETE RECEIVER: TV
 * This is a concrete implementation of the Device interface.
 * It represents a TV that can be turned on/off and can change channels.
 * 
 * The receiver doesn't know about commands - it just performs operations
 * when its methods are called. This separation is key to the Command pattern.
 */
class TV implements Device {
    private String name;

    public TV(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + " TV is now on");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " TV is now off");
    }

    public void changeChannel() {
        System.out.println(name + " TV channel changed");
    }
}

/**
 * CONCRETE RECEIVER: Stereo
 * Another concrete implementation of the Device interface.
 * It represents a stereo system that can be turned on/off and adjust volume.
 */
class Stereo implements Device {
    private String name;

    public Stereo(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + " Stereo is now on");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " Stereo is now off");
    }

    public void adjustVolume() {
        System.out.println(name + " Stereo volume adjusted");
    }
}

/**
 * CONCRETE COMMAND: TurnOnCommand
 * This command encapsulates the request to turn a device on.
 * 
 * Key Points:
 * 1. It holds a reference to a Device (the receiver)
 * 2. It implements the Command interface
 * 3. When execute() is called, it calls turnOn() on its receiver
 * 
 * This decouples the invoker from knowing HOW to turn on a device -
 * it just knows it can execute a command.
 */
class TurnOnCommand implements Command {
    private Device device; // The receiver that will perform the actual work

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    /**
     * Executes the command by calling turnOn() on the receiver.
     * This is where the command pattern's magic happens:
     * - The invoker calls execute() without knowing what device it's operating on
     * - The command knows which device to operate on and what operation to perform
     * - The receiver performs the actual work
     */
    @Override
    public void execute() {
        device.turnOn(); // Delegate to the receiver to perform the actual operation
    }
}

/**
 * CONCRETE COMMAND: TurnOffCommand
 * Similar to TurnOnCommand, but turns the device off instead.
 * 
 * Notice how we can create multiple commands for the same receiver,
 * each performing a different operation. This flexibility is a key
 * benefit of the Command pattern.
 */
class TurnOffCommand implements Command {
    private Device device; // The receiver that will perform the actual work

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}

/**
 * CONCRETE COMMAND: ChangeChannelCommand
 * This command works specifically with TV objects (not just any Device).
 * 
 * Notice that this command takes a TV object directly, not a Device interface.
 * This is because changeChannel() is specific to TVs and not part of the
 * Device interface. This shows that commands can work with specific receiver
 * types when needed.
 */
class ChangeChannelCommand implements Command {
    private TV tv; // Specific receiver type (TV) rather than generic Device

    public ChangeChannelCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.changeChannel();
    }
}

/**
 * CONCRETE COMMAND: AdjustVolumeCommand
 * Similar to ChangeChannelCommand, this works specifically with Stereo objects.
 * This demonstrates that commands can be tailored to specific receiver types.
 */
class AdjustVolumeCommand implements Command {
    private Stereo stereo; // Specific receiver type (Stereo)

    public AdjustVolumeCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.adjustVolume();
    }
}

/**
 * INVOKER: RemoteControl
 * This is the object that holds and invokes commands.
 * 
 * Key Points:
 * 1. It doesn't know what specific command it's holding
 * 2. It doesn't know what receiver the command operates on
 * 3. It just calls execute() when asked to
 * 
 * This decoupling is the main benefit of the Command pattern:
 * - The invoker and receiver are completely decoupled
 * - Commands can be swapped at runtime
 * - New commands can be added without modifying the invoker
 */
class RemoteControl {
    private Command command; // The command currently set in the remote

    /**
     * Sets the command that should be executed when pressButton() is called.
     * This method allows us to change what the remote does without
     * modifying the RemoteControl class itself.
     * 
     * @param command The command to execute (can be any Command implementation)
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Executes the currently set command.
     * 
     * This method demonstrates the power of the Command pattern:
     * - The remote doesn't need to know what the command does
     * - It just calls execute() and lets the command handle the details
     * - This allows the same remote to control any device through commands
     */
    public void pressButton() {
        if (command != null) {
            // Execute the command - the command knows what to do
            command.execute();
        } else {
            // Safety check: don't execute if no command is set
            System.out.println("No command set");
        }
    }
}

/**
 * CLIENT: CommandPatternDemo
 * This is the main class that demonstrates the Command pattern in action.
 * 
 * The client's role is to:
 * 1. Create receivers (devices)
 * 2. Create commands (wrapping receivers)
 * 3. Create invoker (remote control)
 * 4. Set commands in the invoker
 * 5. Execute commands through the invoker
 * 
 * The client knows about all the pieces, but the invoker and receivers
 * don't know about each other - they're connected only through commands.
 */
public class CommandPatternDemo {
    public static void main(String[] args) {
        System.out.println(
                "=== Command Pattern Demo: Smart Home Remote Control ===\n");

        // STEP 1: Create receivers (the objects that will perform actual work)
        // These are the devices we want to control
        TV livingRoomTV = new TV("Living Room");
        Stereo musicSystem = new Stereo("Music System");

        // STEP 2: Create commands (encapsulate requests as objects)
        // Each command wraps a receiver and knows what operation to perform
        // Notice how we can create multiple commands for the same receiver
        Command turnOnTV = new TurnOnCommand(livingRoomTV); // Command to turn TV on
        Command turnOffTV = new TurnOffCommand(livingRoomTV); // Command to turn TV off
        Command changeChannel = new ChangeChannelCommand(livingRoomTV); // Command to change channel

        Command turnOnStereo = new TurnOnCommand(musicSystem); // Command to turn stereo on
        Command adjustVolume = new AdjustVolumeCommand(musicSystem); // Command to adjust volume
        Command turnOffStereo = new TurnOffCommand(musicSystem); // Command to turn stereo off

        // STEP 3: Create invoker (the object that will execute commands)
        // The remote control doesn't know about TVs or Stereos - it just knows about
        // commands
        RemoteControl remote = new RemoteControl();

        // STEP 4: Execute commands through the invoker
        // The remote control can execute any command without knowing what it does
        System.out.println("--- Executing Commands ---");

        // Set command and execute: Turn TV on
        remote.setCommand(turnOnTV); // Set the command
        remote.pressButton(); // Execute it (TV turns on)

        // Set command and execute: Change TV channel
        remote.setCommand(changeChannel); // Change what the remote does
        remote.pressButton(); // Execute it (channel changes)

        // Set command and execute: Turn stereo on
        remote.setCommand(turnOnStereo); // Now control a different device
        remote.pressButton(); // Execute it (stereo turns on)

        // Set command and execute: Adjust stereo volume
        remote.setCommand(adjustVolume); // Different operation on same device
        remote.pressButton(); // Execute it (volume adjusts)

        // Set command and execute: Turn TV off
        remote.setCommand(turnOffTV); // Back to TV, but different operation
        remote.pressButton(); // Execute it (TV turns off)

        // Set command and execute: Turn stereo off
        remote.setCommand(turnOffStereo); // Back to stereo
        remote.pressButton(); // Execute it (stereo turns off)

        // SUMMARY: What we've demonstrated
        System.out.println("\n--- Command Pattern Benefits ---");
        System.out.println("✓ Commands are objects that can be stored and reused");
        System.out.println("✓ Invoker (RemoteControl) is decoupled from receivers (Devices)");
        System.out.println("✓ Easy to add new commands without modifying existing code");
        System.out.println("✓ Commands can be queued, logged, or undone");

        // KEY TAKEAWAYS:
        // 1. The RemoteControl (invoker) never directly calls methods on TV or Stereo
        // 2. Commands encapsulate the "what" (operation) and "who" (receiver)
        // 3. The same remote can control any device through commands
        // 4. New commands can be added without changing RemoteControl or Device classes
    }
}
