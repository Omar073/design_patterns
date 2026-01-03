// Mediator Pattern – Air Traffic Control Example
// Simplifies communication between multiple objects by centralizing their interactions
// Roles:
//   - Mediator: AirTrafficControlTower interface defining communication contract
//   - ConcreteMediator: AirportControlTower class coordinating airplane interactions
//   - Colleague: Airplane interface for components that communicate via mediator
//   - ConcreteColleague: CommercialAirplane class implementing airplane behavior

/**
 * COLLEAGUE INTERFACE: Airplane
 * Defines the interface for components that need to communicate with each other.
 * Airplanes communicate through the mediator, not directly with each other.
 */
interface Airplane {
    /**
     * Requests takeoff clearance from air traffic control.
     */
    void requestTakeoff();
    
    /**
     * Requests landing clearance from air traffic control.
     */
    void requestLanding();
    
    /**
     * Receives notifications from air traffic control.
     * 
     * @param message The message from air traffic control
     */
    void notifyAirTrafficControl(String message);
}

/**
 * CONCRETE COLLEAGUE: CommercialAirplane
 * Represents a commercial airplane that needs to coordinate with other airplanes.
 * It communicates only through the mediator, not directly with other airplanes.
 */
class CommercialAirplane implements Airplane {
    private AirTrafficControlTower mediator;
    private String flightNumber;
    
    public CommercialAirplane(AirTrafficControlTower mediator, String flightNumber) {
        this.mediator = mediator;
        this.flightNumber = flightNumber;
    }
    
    @Override
    public void requestTakeoff() {
        System.out.println("Flight " + flightNumber + ": Requesting takeoff clearance.");
        mediator.requestTakeoff(this);
    }
    
    @Override
    public void requestLanding() {
        System.out.println("Flight " + flightNumber + ": Requesting landing clearance.");
        mediator.requestLanding(this);
    }
    
    @Override
    public void notifyAirTrafficControl(String message) {
        System.out.println("Flight " + flightNumber + ": " + message);
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }
}

/**
 * MEDIATOR INTERFACE: AirTrafficControlTower
 * Defines the communication contract for coordinating airplane interactions.
 * This interface declares methods that concrete mediators should implement
 * to facilitate interactions among colleagues (airplanes).
 */
interface AirTrafficControlTower {
    /**
     * Handles takeoff request from an airplane.
     * Coordinates with other airplanes to ensure safe takeoff.
     * 
     * @param airplane The airplane requesting takeoff
     */
    void requestTakeoff(Airplane airplane);
    
    /**
     * Handles landing request from an airplane.
     * Coordinates with other airplanes to ensure safe landing.
     * 
     * @param airplane The airplane requesting landing
     */
    void requestLanding(Airplane airplane);
}

/**
 * CONCRETE MEDIATOR: AirportControlTower
 * Coordinates communication between airplanes.
 * Manages the complex coordination logic to prevent collisions
 * and ensure safe takeoffs and landings.
 */
class AirportControlTower implements AirTrafficControlTower {
    private java.util.List<Airplane> airplanes;
    private java.util.List<Airplane> runwayQueue;
    
    public AirportControlTower() {
        this.airplanes = new java.util.ArrayList<>();
        this.runwayQueue = new java.util.ArrayList<>();
    }
    
    /**
     * Registers an airplane with the control tower.
     */
    public void registerAirplane(Airplane airplane) {
        airplanes.add(airplane);
        System.out.println("Control Tower: Airplane registered.");
    }
    
    @Override
    public void requestTakeoff(Airplane airplane) {
        // Check if runway is available
        if (runwayQueue.isEmpty()) {
            runwayQueue.add(airplane);
            airplane.notifyAirTrafficControl("Takeoff clearance granted. Proceed to runway.");
            
            // Simulate takeoff completion
            System.out.println("Control Tower: " + getAirplaneName(airplane) + " has taken off.");
            runwayQueue.remove(airplane);
        } else {
            airplane.notifyAirTrafficControl("Runway busy. Please wait for clearance.");
            runwayQueue.add(airplane);
        }
    }
    
    @Override
    public void requestLanding(Airplane airplane) {
        // Check if runway is available
        if (runwayQueue.isEmpty()) {
            runwayQueue.add(airplane);
            airplane.notifyAirTrafficControl("Landing clearance granted. Proceed to approach.");
            
            // Simulate landing completion
            System.out.println("Control Tower: " + getAirplaneName(airplane) + " has landed.");
            runwayQueue.remove(airplane);
        } else {
            airplane.notifyAirTrafficControl("Runway busy. Please circle and wait for clearance.");
            runwayQueue.add(airplane);
        }
    }
    
    /**
     * Helper method to get airplane identifier for logging.
     */
    private String getAirplaneName(Airplane airplane) {
        if (airplane instanceof CommercialAirplane) {
            return "Flight " + ((CommercialAirplane) airplane).getFlightNumber();
        }
        return "Airplane";
    }
}

/**
 * CLIENT: MediatorAirplaneDemo
 * Demonstrates the Mediator pattern in action.
 * 
 * The client's role:
 * 1. Creates the Concrete Mediator (AirportControlTower)
 * 2. Creates Concrete Colleagues (CommercialAirplane instances)
 * 3. Registers airplanes with the mediator
 * 4. Airplanes communicate through the mediator, not directly
 * 
 * Notice how airplanes don't know about each other directly -
 * they only communicate through the control tower (mediator).
 */
public class MediatorAirplaneDemo {
    public static void main(String[] args) {
        System.out.println("=== Mediator Pattern Demo: Air Traffic Control ===\n");
        
        // Create Concrete Mediator
        AirportControlTower controlTower = new AirportControlTower();
        
        // Create Concrete Colleagues (Commercial Airplanes)
        Airplane airplane1 = new CommercialAirplane(controlTower, "AA101");
        Airplane airplane2 = new CommercialAirplane(controlTower, "UA202");
        Airplane airplane3 = new CommercialAirplane(controlTower, "DL303");
        
        // Register airplanes with the mediator
        controlTower.registerAirplane(airplane1);
        controlTower.registerAirplane(airplane2);
        controlTower.registerAirplane(airplane3);
        
        System.out.println("\n--- Airplane Operations ---");
        
        // Airplanes communicate through the mediator
        airplane1.requestTakeoff();
        System.out.println();
        
        airplane2.requestLanding();
        System.out.println();
        
        airplane3.requestTakeoff();
        System.out.println();
        
        // Try another operation while runway is busy
        airplane1.requestLanding();
        
        System.out.println("\n--- Mediator Pattern Benefits ---");
        System.out.println("✓ Centralized control: All communication goes through control tower");
        System.out.println("✓ Loose coupling: Airplanes don't know about each other");
        System.out.println("✓ Collision avoidance: Mediator coordinates safe operations");
        System.out.println("✓ Easy to extend: Add new airplanes without modifying existing ones");
        System.out.println("✓ Organized communication: Prevents chaos from direct interactions");
    }
}

