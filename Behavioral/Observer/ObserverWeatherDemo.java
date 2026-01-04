// Observer Pattern – Weather Station Example (Class Diagram Implementation)
// Defines a one-to-many dependency so that when one object changes state,
// all its dependents are notified and updated automatically
// Also known as: Dependents or Publish-Subscribe
// This implementation matches the class diagram (diagram2.png)
// Roles:
//   - WeatherSubject Interface: Defines contract for managing observers
//   - WeatherObserver Interface: Defines contract for receiving updates
//   - ConcreteSubject: SimpleWeatherStation class that maintains observers and notifies them
//   - ConcreteObserver: PhoneDisplay, TVDisplay classes that display weather data
//   - Client: ObserverWeatherDemo main() creates subject, observers, and demonstrates notifications

import java.util.ArrayList;
import java.util.List;

/**
 * OBSERVER INTERFACE
 * Defines the contract for objects that want to be notified about changes
 * in the subject's state.
 */
interface WeatherObserver {
    void update(String weather);
}

/**
 * SUBJECT INTERFACE
 * Defines the contract for objects that can have observers.
 * Outlines operations a subject (like WeatherStation) should support.
 */
interface WeatherSubject {
    void addObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
}

/**
 * CONCRETE SUBJECT: SimpleWeatherStation
 * The concrete subject implementing the WeatherSubject interface.
 * Maintains a list of observers and provides methods to manage this list.
 * When weather changes, it notifies all registered observers.
 */
class SimpleWeatherStation implements WeatherSubject {
    private List<WeatherObserver> observers = new ArrayList<>();
    private String weather;

    @Override
    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(weather);
        }
    }

    /**
     * Updates the weather and notifies all observers of the change.
     */
    public void setWeather(String newWeather) {
        this.weather = newWeather;
        notifyObservers();
    }
}

/**
 * CONCRETE OBSERVER: PhoneDisplay
 * A concrete observer implementing the WeatherObserver interface.
 * Displays weather updates on a phone device.
 */
class PhoneDisplay implements WeatherObserver {
    private String weather;

    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }

    private void display() {
        System.out.println("Phone Display: Weather updated - " + weather);
    }
}

/**
 * CONCRETE OBSERVER: TVDisplay
 * Another concrete observer implementing the WeatherObserver interface.
 * Displays weather updates on a TV device.
 */
class TVDisplay implements WeatherObserver {
    private String weather;

    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }

    private void display() {
        System.out.println("TV Display: Weather updated - " + weather);
    }
}

/**
 * CLIENT: WeatherApp
 * Demonstrates the Observer pattern in action.
 */
public class ObserverWeatherDemo {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Demo: Weather Station ===\n");

        SimpleWeatherStation weatherStation = new SimpleWeatherStation();

        WeatherObserver phoneDisplay = new PhoneDisplay();
        WeatherObserver tvDisplay = new TVDisplay();

        // Register observers
        System.out.println("--- Registering Observers ---");
        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(tvDisplay);
        System.out.println("PhoneDisplay and TVDisplay registered\n");

        // Simulating weather changes
        System.out.println("--- Simulating Weather Changes ---");
        weatherStation.setWeather("Sunny");
        System.out.println();

        weatherStation.setWeather("Rainy");
        System.out.println();

        weatherStation.setWeather("Cloudy");
        System.out.println();

        // Remove one observer
        System.out.println("--- Removing TVDisplay Observer ---");
        weatherStation.removeObserver(tvDisplay);
        System.out.println("TVDisplay unregistered\n");

        // Notify remaining observer
        System.out.println("--- Weather Change After Removal ---");
        weatherStation.setWeather("Windy");

        System.out.println("\n--- Observer Pattern Benefits ---");
        System.out.println("✓ Loose coupling between Subject and Observers");
        System.out.println("✓ Subject doesn't need to know concrete observer classes");
        System.out.println("✓ Observers can be added/removed dynamically");
        System.out.println("✓ One-to-many dependency: one change notifies all observers");
    }
}

