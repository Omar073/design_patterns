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
     * Handles an event from a colleague component.
     * The mediator contains all the coordination logic, deciding what actions
     * to take based on the event type and current system state.
     * 
     * @param event The type of event that occurred
     * @param sender The component that triggered the event
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
    /**
     * Sets the mediator for this colleague.
     * This allows the colleague to send events to the mediator.
     * 
     * @param mediator The mediator that will coordinate communications
     */
    void setMediator(Mediator mediator);
    
    /**
     * Triggers an event that will be handled by the mediator.
     * Instead of directly calling other components, colleagues notify
     * the mediator, which decides how to coordinate the response.
     * 
     * @param event The type of event to trigger
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
    
    // Simulated state for demonstration
    private boolean isShowerRunning = false;
    private int temperature = 72;
    private boolean isRaining = false;
    
    /**
     * Registers all components with the mediator.
     * This establishes bidirectional communication: components can send events
     * to mediator, and mediator can command components.
     */
    public void registerComponents(Alarm alarm, CoffeePot coffeePot, 
                                   Calendar calendar, Sprinkler sprinkler) {
        this.alarm = alarm;
        this.coffeePot = coffeePot;
        this.calendar = calendar;
        this.sprinkler = sprinkler;
        
        // Set mediator reference in each component
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
        System.out.println("\n--- Mediator Processing: " + event + " from " + 
                          sender.getClass().getSimpleName() + " ---");
        
        // Handle alarm events
        if ("ALARM_RING".equals(event)) {
            handleAlarmEvent();
        }
        // Handle calendar events
        else if ("DAY_CHANGED".equals(event)) {
            handleDayChangedEvent();
        }
        else if ("TRASH_DAY".equals(event)) {
            handleTrashDayEvent();
        }
        // Handle sprinkler events
        else if ("SPRINKLER_REQUEST".equals(event)) {
            handleSprinklerRequestEvent();
        }
        // Handle coffee pot events
        else if ("COFFEE_REQUEST".equals(event)) {
            handleCoffeeRequestEvent();
        }
    }
    
    /**
     * Handles alarm ring events.
     * When alarm rings, mediator checks calendar and coordinates starting
     * coffee pot and sprinkler based on conditions.
     */
    private void handleAlarmEvent() {
        System.out.println("Mediator: Alarm rang - checking calendar...");
        
        // Check if it's a weekend (might want different behavior)
        if (calendar != null && calendar.isWeekend()) {
            System.out.println("Mediator: It's a weekend - checking weather before starting sprinkler");
            if (!isRaining && temperature > 65) {
                if (sprinkler != null) {
                    sprinkler.start();
                }
            }
        } else {
            // Weekday routine
            if (sprinkler != null && !isRaining && temperature > 70) {
                System.out.println("Mediator: Starting sprinkler (weekday routine)");
                sprinkler.start();
            }
        }
        
        // Always start coffee when alarm rings
        if (coffeePot != null) {
            System.out.println("Mediator: Starting coffee pot");
            coffeePot.startBrewing();
        }
    }
    
    /**
     * Handles day changed events from calendar.
     * When day changes, mediator adjusts schedules and routines accordingly.
     */
    private void handleDayChangedEvent() {
        System.out.println("Mediator: Day changed to " + 
                          (calendar != null ? calendar.getDayOfWeek() : "unknown"));
        
        // Adjust alarm based on day of week
        if (calendar != null && calendar.isWeekend()) {
            System.out.println("Mediator: Weekend detected - no early alarm needed");
        } else {
            System.out.println("Mediator: Weekday - alarm will ring as scheduled");
        }
        
        // Check if sprinkler should run based on day
        if (calendar != null && !calendar.isWeekend() && !isRaining) {
            System.out.println("Mediator: Checking if sprinkler should run today");
            // Sprinkler logic would go here
        }
    }
    
    /**
     * Handles trash day events.
     * On trash day, mediator might adjust alarm time or other routines.
     */
    private void handleTrashDayEvent() {
        System.out.println("Mediator: Trash day detected");
        if (alarm != null) {
            System.out.println("Mediator: Resetting alarm for trash day schedule");
            alarm.reset();
        }
    }
    
    /**
     * Handles sprinkler request events.
     * Before starting sprinkler, mediator checks multiple conditions:
     * calendar (day of week), weather (rain), temperature, and shower status.
     */
    private void handleSprinklerRequestEvent() {
        System.out.println("Mediator: Sprinkler requested - checking conditions...");
        
        // Check if shower is running (don't run sprinkler during shower)
        if (isShowerRunning) {
            System.out.println("Mediator: Shower is running - deferring sprinkler");
            return;
        }
        
        // Check calendar
        if (calendar != null && calendar.isWeekend()) {
            System.out.println("Mediator: Weekend - checking weather before starting");
            if (isRaining) {
                System.out.println("Mediator: It's raining - skipping sprinkler");
                return;
            }
        }
        
        // Check temperature
        if (temperature < 60) {
            System.out.println("Mediator: Temperature too low (" + temperature + "°F) - skipping sprinkler");
            return;
        }
        
        // Check weather
        if (isRaining) {
            System.out.println("Mediator: It's raining - skipping sprinkler");
            return;
        }
        
        // All conditions met - start sprinkler
        if (sprinkler != null) {
            System.out.println("Mediator: All conditions met - starting sprinkler");
            sprinkler.start();
        }
    }
    
    /**
     * Handles coffee request events.
     * Mediator checks calendar and alarm status before starting coffee.
     */
    private void handleCoffeeRequestEvent() {
        System.out.println("Mediator: Coffee requested - checking conditions...");
        
        // Check if it's too early (example logic)
        if (calendar != null && calendar.isWeekend()) {
            System.out.println("Mediator: Weekend - allowing coffee anytime");
        }
        
        // Start coffee
        if (coffeePot != null) {
            System.out.println("Mediator: Starting coffee pot");
            coffeePot.startBrewing();
        }
    }
    
    // Helper methods to simulate state changes
    public void setShowerRunning(boolean running) {
        this.isShowerRunning = running;
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
 * communication goes through the mediator, which contains the coordination logic.
 */
public class MediatorPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Mediator Pattern Demo: Smart Home Automation ===\n");
        
        // STEP 1: Create components
        // These components don't know about each other - they only know about the mediator
        Alarm alarm = new Alarm("Morning");
        CoffeePot coffeePot = new CoffeePot("Kitchen");
        Calendar calendar = new Calendar("Home");
        Sprinkler sprinkler = new Sprinkler("Garden");
        
        // STEP 2: Create mediator
        // The mediator will coordinate all communication between components
        SmartHomeMediatorImpl mediator = new SmartHomeMediatorImpl();
        
        // STEP 3: Register components with mediator
        // This establishes the communication channel: components -> mediator -> components
        mediator.registerComponents(alarm, coffeePot, calendar, sprinkler);
        
        // STEP 4: Demonstrate various scenarios
        
        System.out.println("\n========== Scenario 1: Alarm Rings (Weekday) ==========");
        calendar.setDayOfWeek("Monday");
        mediator.setTemperature(75);
        mediator.setRaining(false);
        alarm.ring();
        
        System.out.println("\n========== Scenario 2: Day Changes to Weekend ==========");
        calendar.setDayOfWeek("Saturday");
        
        System.out.println("\n========== Scenario 3: Alarm Rings (Weekend) ==========");
        mediator.setTemperature(68);
        mediator.setRaining(false);
        alarm.ring();
        
        System.out.println("\n========== Scenario 4: Sprinkler Request (Conditions Met) ==========");
        mediator.setTemperature(75);
        mediator.setRaining(false);
        mediator.setShowerRunning(false);
        sprinkler.triggerEvent("SPRINKLER_REQUEST");
        
        System.out.println("\n========== Scenario 5: Sprinkler Request (Raining) ==========");
        mediator.setRaining(true);
        sprinkler.triggerEvent("SPRINKLER_REQUEST");
        
        System.out.println("\n========== Scenario 6: Trash Day ==========");
        calendar.setTrashDay(true);
        
        System.out.println("\n========== Scenario 7: Sprinkler Request (Shower Running) ==========");
        mediator.setRaining(false);
        mediator.setShowerRunning(true);
        sprinkler.triggerEvent("SPRINKLER_REQUEST");
        
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

