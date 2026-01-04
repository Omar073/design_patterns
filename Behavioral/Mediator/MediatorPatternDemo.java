// Mediator Pattern – Smart Home Automation Example
// Reduces communication complexity between multiple objects by centralizing communication through a mediator
// Roles:
//   - Mediator: SmartHomeMediator interface defining communication contract
//   - ConcreteMediator: SmartHomeMediatorImpl coordinates all component interactions
//   - Colleague: Base interface for components (Alarm, CoffeePot, Calendar, Sprinkler)
//   - ConcreteColleague: Alarm, CoffeePot, Calendar, Sprinkler components
//   - Client: main() creates components, registers them with mediator, and triggers events

/**
 * MEDIATOR INTERFACE
 * Defines the contract for communication between colleagues.
 * The mediator knows about all colleagues and coordinates their interactions.
 * 
 * Key Benefit: Components don't need to know about each other - they only
 * communicate through the mediator, reducing coupling and complexity.
 */
interface Mediator {
    /**
     * The mediator contains all the coordination logic, deciding what actions
     * to take based on the event type and current system state.
     */
    void onEvent(String event, Colleague sender);
}

/**
 * COLLEAGUE INTERFACE
 * Base interface for all components that need to communicate with each other.
 * Colleagues don't communicate directly - they send events to the mediator,
 * which coordinates the appropriate responses.
 */
interface Colleague {
    void setMediator(Mediator mediator);

    /**
     * Instead of directly calling other components, colleagues notify
     * the mediator, which decides how to coordinate the response.
     */
    void triggerEvent(String event);
}

/**
 * CONCRETE COLLEAGUE: Alarm
 * Represents an alarm clock that can trigger morning routines.
 * When the alarm goes off, it notifies the mediator, which coordinates
 * starting the coffee pot, checking the calendar, and activating sprinklers.
 */
class Alarm implements Colleague {
    private String name;
    private Mediator mediator;

    public Alarm(String name) {
        this.name = name;
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void triggerEvent(String event) {
        System.out.println(name + " Alarm: " + event + " event triggered");
        if (mediator != null) {
            mediator.onEvent(event, this);
        }
    }

    public void ring() {
        System.out.println(name + " Alarm: Ringing!");
        triggerEvent("ALARM_RING");
    }

    public void reset() {
        System.out.println(name + " Alarm: Reset");
    }
}

/**
 * CONCRETE COLLEAGUE: CoffeePot
 * Represents a coffee maker that can brew coffee.
 * The coffee pot doesn't need to know about alarms or calendars - it just
 * notifies the mediator when events occur, and the mediator coordinates
 * the appropriate actions.
 */
class CoffeePot implements Colleague {
    private String name;
    private Mediator mediator;

    public CoffeePot(String name) {
        this.name = name;
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void triggerEvent(String event) {
        System.out.println(name + " CoffeePot: " + event + " event triggered");
        if (mediator != null) {
            mediator.onEvent(event, this);
        }
    }

    public void startBrewing() {
        System.out.println(name + " CoffeePot: Starting to brew coffee");
    }

    public void stopBrewing() {
        System.out.println(name + " CoffeePot: Stopping");
    }
}

/**
 * CONCRETE COLLEAGUE: Calendar
 * Represents a calendar system that tracks days, dates, and events.
 * The calendar can trigger events based on the day of week, special dates,
 * or scheduled events. The mediator uses this information to coordinate
 * other components (sprinklers on certain days, alarm adjustments, etc.).
 */
class Calendar implements Colleague {
    private String name;
    private Mediator mediator;
    private String dayOfWeek;
    private boolean isWeekend;
    private boolean isTrashDay;

    public Calendar(String name) {
        this.name = name;
        this.dayOfWeek = "Monday";
        this.isWeekend = false;
        this.isTrashDay = false;
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void triggerEvent(String event) {
        System.out.println(name + " Calendar: " + event + " event triggered");
        if (mediator != null) {
            mediator.onEvent(event, this);
        }
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public boolean isTrashDay() {
        return isTrashDay;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        this.isWeekend = "Saturday".equals(dayOfWeek) || "Sunday".equals(dayOfWeek);
        triggerEvent("DAY_CHANGED");
    }

    public void setTrashDay(boolean isTrashDay) {
        this.isTrashDay = isTrashDay;
        if (isTrashDay) {
            triggerEvent("TRASH_DAY");
        }
    }
}

/**
 * CONCRETE COLLEAGUE: Sprinkler
 * Represents a sprinkler system for watering the garden.
 * The sprinkler doesn't decide when to run - it responds to commands from
 * the mediator, which checks calendar, weather, and other conditions before
 * activating the sprinkler.
 */
class Sprinkler implements Colleague {
    private String name;
    private Mediator mediator;
    private boolean isRunning;

    public Sprinkler(String name) {
        this.name = name;
        this.isRunning = false;
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void triggerEvent(String event) {
        System.out.println(name + " Sprinkler: " + event + " event triggered");
        if (mediator != null) {
            mediator.onEvent(event, this);
        }
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            System.out.println(name + " Sprinkler: Started watering");
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            System.out.println(name + " Sprinkler: Stopped watering");
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}

/**
 * CONCRETE MEDIATOR: SmartHomeMediatorImpl
 * This is the heart of the Mediator pattern. It coordinates all communication
 * between components, containing all the business logic for when to trigger
 * actions based on events.
 * 
 * Key Benefits:
 * 1. All coordination logic is in one place (easy to maintain)
 * 2. Components don't need to know about each other (loose coupling)
 * 3. Easy to modify behavior by changing mediator logic
 * 4. Components can be added/removed without affecting others
 */
class SmartHomeMediatorImpl implements Mediator {
    private Alarm alarm;
    private CoffeePot coffeePot;
    private Calendar calendar;
    private Sprinkler sprinkler;

    private int temperature = 22;
    private boolean isRaining = false;

    public void registerComponents(Alarm alarm, CoffeePot coffeePot,
            Calendar calendar, Sprinkler sprinkler) {
        this.alarm = alarm;
        this.coffeePot = coffeePot;
        this.calendar = calendar;
        this.sprinkler = sprinkler;

        alarm.setMediator(this);
        coffeePot.setMediator(this);
        calendar.setMediator(this);
        sprinkler.setMediator(this);
    }

    /**
     * Central coordination method - all events flow through here.
     * The mediator contains all the logic for deciding what actions to take
     * based on events and current system state. This is where the complexity
     * of inter-component communication is centralized.
     */
    @Override
    public void onEvent(String event, Colleague sender) {
        System.out.println("Mediator: Processing " + event + " from " + sender.getClass().getSimpleName());

        switch (event) {
            case "ALARM_RING":
                handleAlarmEvent();
                break;
            case "DAY_CHANGED":
                handleDayChangedEvent();
                break;
            case "TRASH_DAY":
                handleTrashDayEvent();
                break;
            case "SPRINKLER_REQUEST":
                handleSprinklerRequestEvent();
                break;
            case "COFFEE_REQUEST":
                handleCoffeeRequestEvent();
                break;
        }
    }

    private void handleAlarmEvent() {
        System.out.println("Mediator: Alarm rang - coordinating response...");
        if (coffeePot != null) {
            coffeePot.startBrewing();
        }
        if (sprinkler != null && !isRaining && temperature > 20) {
            sprinkler.start();
        }
    }

    private void handleDayChangedEvent() {
        System.out.println("Mediator: Day changed - adjusting schedules");
        if (calendar != null && calendar.isWeekend()) {
            System.out.println("Mediator: Weekend mode activated");
        }
    }

    private void handleTrashDayEvent() {
        System.out.println("Mediator: Trash day - adjusting routines");
        if (alarm != null) {
            alarm.reset();
        }
    }

    private void handleSprinklerRequestEvent() {
        if (isRaining || temperature < 16) {
            System.out.println("Mediator: Conditions not met - skipping sprinkler");
            return;
        }
        if (sprinkler != null) {
            sprinkler.start();
        }
    }

    private void handleCoffeeRequestEvent() {
        if (coffeePot != null) {
            coffeePot.startBrewing();
        }
    }

    public void setTemperature(int temp) {
        this.temperature = temp;
    }

    public void setRaining(boolean raining) {
        this.isRaining = raining;
    }
}

/**
 * CLIENT: MediatorPatternDemo
 * Demonstrates the Mediator pattern in action.
 * 
 * The client's role:
 * 1. Creates all components (Alarm, CoffeePot, Calendar, Sprinkler)
 * 2. Creates the mediator
 * 3. Registers components with the mediator
 * 4. Triggers events through components
 * 
 * Notice how components don't directly interact with each other - all
 * communication goes through the mediator, which contains the coordination
 * logic.
 */
public class MediatorPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Mediator Pattern Demo: Smart Home Automation ===\n");

        // Create components
        Alarm alarm = new Alarm("Morning");
        CoffeePot coffeePot = new CoffeePot("Kitchen");
        Calendar calendar = new Calendar("Home");
        Sprinkler sprinkler = new Sprinkler("Garden");

        // Create mediator and register components
        SmartHomeMediatorImpl mediator = new SmartHomeMediatorImpl();
        mediator.registerComponents(alarm, coffeePot, calendar, sprinkler);

        // Demonstrate scenarios

        System.out.println("\n--- Scenario 1: Alarm Rings ---");
        mediator.setTemperature(24);
        mediator.setRaining(false);
        alarm.ring();

        System.out.println("\n--- Scenario 2: Sprinkler Request ---");
        mediator.setTemperature(24);
        mediator.setRaining(false);
        sprinkler.triggerEvent("SPRINKLER_REQUEST");

        System.out.println("\n--- Scenario 3: Sprinkler Request (Raining) ---");
        mediator.setRaining(true);
        sprinkler.triggerEvent("SPRINKLER_REQUEST");

        System.out.println("\n--- Scenario 4: Day Changes ---");
        calendar.setDayOfWeek("Saturday");

        System.out.println("\n--- Mediator Pattern Benefits ---");
        System.out.println("✓ Components are loosely coupled - they don't know about each other");
        System.out.println("✓ All coordination logic is centralized in the mediator");
        System.out.println("✓ Easy to modify behavior by changing mediator logic");
        System.out.println("✓ Components can be added/removed without affecting others");
        System.out.println("✓ Reduces communication complexity from O(n²) to O(n)");

        System.out.println("\n--- Key Takeaway ---");
        System.out.println("Without Mediator: Each component would need to know about all others");
        System.out.println("With Mediator: Components only know about the mediator, which knows about all");
    }
}
