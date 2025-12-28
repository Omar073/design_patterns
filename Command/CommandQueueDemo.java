// Command Pattern with Command Queue
// Demonstrates queuing commands for later execution
// Roles:
//   - Command: Interface with execute() method
//   - Concrete Commands: Various device commands
//   - Command Queue: Stores and processes commands in order
//   - Invoker: Executes commands from queue
//   - Receivers: Devices that perform operations
//   - Client: main() demonstrates command queuing

import java.util.LinkedList;
import java.util.Queue;

/**
 * QUEUE COMMAND INTERFACE
 * This is the basic Command interface for this example.
 * 
 * Key Concept: Commands can be stored in a queue and executed later.
 * This enables batch processing, scheduling, and asynchronous execution.
 */
interface QueueCommand {
    void execute();
}

/**
 * RECEIVER: Printer
 * A printer device that can print documents and cancel print jobs.
 * This represents a real-world device that might take time to process requests.
 */
class Printer {
    private String name; // Name/identifier of the printer

    public Printer(String name) {
        this.name = name;
    }

    public void print(String document) {
        System.out.println(name + " printing: " + document);
    }

    public void cancel() {
        System.out.println(name + " print job cancelled");
    }
}

/**
 * RECEIVER: EmailService
 * An email service that can send emails.
 * This represents a service that might need to queue requests.
 */
class EmailService {
    private String name; // Name/identifier of the email service

    public EmailService(String name) {
        this.name = name;
    }

    public void send(String recipient, String subject) {
        System.out.println(name + " sending email to " + recipient + ": " + subject);
    }
}

/**
 * CONCRETE COMMAND: PrintCommand
 * Command to print a document on a printer.
 * 
 * Key Point: This command encapsulates both the receiver (printer) and
 * the parameters (document name) needed for the operation.
 */
class PrintCommand implements QueueCommand {
    private Printer printer; // The receiver (printer to use)
    private String document; // The document to print (parameter)

    public PrintCommand(Printer printer, String document) {
        this.printer = printer;
        this.document = document;
    }

    @Override
    public void execute() {
        printer.print(document);
    }
}

/**
 * CONCRETE COMMAND: SendEmailCommand
 * Command to send an email through an email service.
 * 
 * This command encapsulates multiple parameters (recipient and subject)
 * along with the receiver (email service).
 */
class SendEmailCommand implements QueueCommand {
    private EmailService emailService; // The receiver (email service to use)
    private String recipient; // The email recipient (parameter)
    private String subject; // The email subject (parameter)

    public SendEmailCommand(EmailService emailService, String recipient, String subject) {
        this.emailService = emailService;
        this.recipient = recipient;
        this.subject = subject;
    }

    @Override
    public void execute() {
        emailService.send(recipient, subject);
    }
}

/**
 * COMMAND QUEUE: CommandQueue
 * This class stores commands in a queue and processes them in order.
 * 
 * Key Concepts:
 * 1. Commands are added to the queue using addCommand()
 * 2. Commands are processed in FIFO (First In, First Out) order
 * 3. All commands in the queue are executed when processCommands() is called
 * 
 * Benefits:
 * - Commands can be queued for batch processing
 * - Commands execute in the order they were added
 * - Useful for operations that need to be processed sequentially
 * - Can be extended to support scheduling, priority queues, etc.
 * 
 * Real-World Applications:
 * - Print spoolers (queue print jobs)
 * - Email servers (queue emails to send)
 * - Task schedulers (queue tasks to execute)
 * - Transaction processing (queue transactions)
 */
class CommandQueue {
    private Queue<QueueCommand> queue; // The queue storing commands (FIFO)

    public CommandQueue() {
        this.queue = new LinkedList<>();
    }

    public void addCommand(QueueCommand command) {
        // Add the command to the queue using offer (adds to the end for FIFO order).
        // offer() is a standard method in Java's Queue interface that inserts the
        // specified element into the queue,
        // returning true if successful (or false if the queue is bounded and full; with
        // LinkedList it's always successful).
        queue.offer(command);
        System.out.println("Command added to queue. Queue size: " + queue.size());
    }

    public void processCommands() {
        System.out.println("\n--- Processing Command Queue ---");
        while (!queue.isEmpty()) {
            // Retrieve and remove the next command from the front of the queue (FIFO
            // order).
            // The poll() method returns the element at the head of the queue, or null if
            // the queue is empty.
            // In this context, since we checked !queue.isEmpty(), poll() will always return
            // a valid command.
            QueueCommand command = queue.poll();
            command.execute();
        }
        System.out.println("--- Queue Processing Complete ---\n");
    }

    public int getQueueSize() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

/**
 * CLIENT: CommandQueueDemo
 * Demonstrates the Command pattern with command queuing.
 * 
 * This example shows:
 * 1. How to create commands
 * 2. How to add commands to a queue
 * 3. How commands are executed in order (FIFO)
 * 4. How commands can be queued for batch processing
 * 5. How this pattern enables asynchronous and scheduled execution
 */
public class CommandQueueDemo {
    public static void main(String[] args) {
        System.out.println("=== Command Pattern with Command Queue Demo ===\n");

        // STEP 1: Create receivers (the objects that will perform actual work)
        // These are the services/devices we want to use
        Printer officePrinter = new Printer("Office Printer");
        EmailService emailService = new EmailService("Email Service");

        // STEP 2: Create a command queue
        // This queue will store commands and execute them in order
        CommandQueue commandQueue = new CommandQueue();

        // STEP 3: Add commands to the queue
        // Commands are added but not executed immediately
        // They will be executed later when processCommands() is called
        System.out.println("--- Adding Commands to Queue ---");

        // Add multiple commands to the queue
        // Notice how we create commands and add them immediately
        commandQueue.addCommand(new PrintCommand(officePrinter, "Report.pdf"));
        commandQueue.addCommand(new SendEmailCommand(emailService, "manager@company.com", "Weekly Report"));
        commandQueue.addCommand(new PrintCommand(officePrinter, "Invoice.pdf"));
        commandQueue.addCommand(new SendEmailCommand(emailService, "client@company.com", "Invoice"));
        commandQueue.addCommand(new PrintCommand(officePrinter, "Presentation.pptx"));

        // At this point, we have 5 commands in the queue, but none have been executed
        // yet

        // STEP 4: Process all commands in the queue
        // This will execute all commands in the order they were added (FIFO)
        // The queue will be emptied after processing
        commandQueue.processCommands();

        // STEP 5: Add more commands and process again
        // This demonstrates that we can add commands at different times
        // and process them in batches
        System.out.println("--- Adding More Commands ---");
        commandQueue.addCommand(new SendEmailCommand(emailService, "team@company.com", "Meeting Reminder"));
        commandQueue.addCommand(new PrintCommand(officePrinter, "Agenda.pdf"));

        // Process the new batch of commands
        commandQueue.processCommands();

        // SUMMARY: What we've demonstrated
        System.out.println("--- Command Queue Benefits ---");
        System.out.println("✓ Commands can be queued for later execution");
        System.out.println("✓ Commands execute in order (FIFO)");
        System.out.println("✓ Useful for batch processing");
        System.out.println("✓ Commands can be scheduled or executed asynchronously");
        System.out.println("✓ Supports logging and auditing of operations");

        // KEY TAKEAWAYS:
        // 1. Commands can be stored in a queue before execution
        // 2. Commands execute in the order they were added (FIFO)
        // 3. This enables batch processing of operations
        // 4. Commands can be added at different times and processed together
        // 5. This pattern is useful for:
        // - Print spoolers (queue print jobs)
        // - Email servers (queue emails to send)
        // - Task schedulers (queue tasks)
        // - Transaction processing (queue transactions)
        // - Asynchronous operations (queue for background processing)
    }
}
