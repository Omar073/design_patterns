// Command Pattern – Smart Home Remote Control Example
// Encapsulates requests as objects, allowing parameterization, queuing, logging, and undo
// Roles:
//   - Command: Interface defining execute() method
//   - Concrete Commands: TurnOnCommand, TurnOffCommand, ChangeChannelCommand, AdjustVolumeCommand
//   - Invoker: RemoteControl holds and invokes commands
//   - Receiver: Device class performs actual operations
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
    void execute();
}

/**
 * CONCRETE RECEIVER: Device
 * Represents a device that can be turned on/off, change channels, and adjust volume.
 * 
 * The receiver doesn't know about commands - it just performs operations
 * when its methods are called. This separation is key to the Command pattern.
 */
class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + " is now on");
    }

    public void turnOff() {
        System.out.println(name + " is now off");
    }

    public void changeChannel() {
        System.out.println(name + " channel changed");
    }

    public void adjustVolume() {
        System.out.println(name + " volume adjusted");
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
     * This is where the command pattern's magic happens:
     * - The invoker calls execute() without knowing what device it's operating on
     * - The command knows which device to operate on and what operation to perform
     * - The receiver performs the actual work
     */
    @Override
    public void execute() {
        device.turnOn();
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
 * This command works with Device objects that support channel changing.
 */
class ChangeChannelCommand implements Command {
    private Device device; // Device receiver

    public ChangeChannelCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.changeChannel();
    }
}

/**
 * CONCRETE COMMAND: AdjustVolumeCommand
 * This command works with Device objects that support volume adjustment.
 */
class AdjustVolumeCommand implements Command {
    private Device device; // Device receiver

    public AdjustVolumeCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.adjustVolume();
    }
}

/**
 * INVOKER: RemoteControl
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

    public void setCommand(Command command) {
        this.command = command;
    }

    /**
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
        Device livingRoomTV = new Device("Living Room TV");
        Device musicSystem = new Device("Music System");

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
        // 1. The RemoteControl (invoker) never directly calls methods on Device
        // 2. Commands encapsulate the "what" (operation) and "who" (receiver)
        // 3. The same remote can control any device through commands
        // 4. New commands can be added without changing RemoteControl or Device classes
    }
}
