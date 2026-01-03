// Observer Pattern – Weather Station Example
// Defines a one-to-many dependency so that when one object changes state,
// all its dependents are notified and updated automatically
// Also known as: Dependents or Publish-Subscribe
// Roles:
//   - Subject: WeatherStation class that maintains observers and notifies them
//   - Observer: Observer interface defining update() method
//   - ConcreteObserver: PhoneApp, WebsiteDashboard classes that display weather data
//   - Client: main() creates subject, observers, and demonstrates notifications

/**
 * OBSERVER INTERFACE
 * Defines the contract for objects that should be notified of changes
 * in the Subject's state.
 */
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

/**
 * SUBJECT INTERFACE
 * Defines the contract for objects that can have observers.
 */
interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

/**
 * CONCRETE SUBJECT: WeatherStation
 * The object being observed. When its state changes, it notifies
 * all registered observers automatically.
 */
class WeatherStation implements Subject {
    private java.util.List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        this.observers = new java.util.ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    /**
     * Updates the weather measurements and notifies all observers.
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers(); // Automatically notify all observers
    }
}

/**
 * CONCRETE OBSERVER: PhoneApp
 * A mobile phone application that displays weather data.
 */
class PhoneApp implements Observer {
    private String appName;
    private float temperature;
    private float humidity;
    private float pressure;

    public PhoneApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("📱 " + appName + " Phone App:");
        System.out.println("   Temperature: " + temperature + "°C");
        System.out.println("   Humidity: " + humidity + "%");
        System.out.println("   Pressure: " + pressure + " hPa");
    }
}

/**
 * CONCRETE OBSERVER: WebsiteDashboard
 * A website dashboard that displays weather data.
 */
class WebsiteDashboard implements Observer {
    private String websiteName;
    private float temperature;
    private float humidity;
    private float pressure;

    public WebsiteDashboard(String websiteName) {
        this.websiteName = websiteName;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("🌐 " + websiteName + " Website Dashboard:");
        System.out.println("   Temperature: " + temperature + "°C");
        System.out.println("   Humidity: " + humidity + "%");
        System.out.println("   Pressure: " + pressure + " hPa");
    }
}

/**
 * CLIENT: ObserverPatternDemo
 * Demonstrates the Observer pattern in action.
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Demo: Weather Station ===\n");

        // Create Subject and Observers
        WeatherStation weatherStation = new WeatherStation();
        PhoneApp phoneApp = new PhoneApp("WeatherPro");
        WebsiteDashboard website = new WebsiteDashboard("Weather.com");

        // Register observers
        weatherStation.registerObserver(phoneApp);
        weatherStation.registerObserver(website);

        // Update weather data - observers are automatically notified!
        System.out.println("--- Weather Update 1 ---");
        weatherStation.setMeasurements(25.5f, 65.0f, 1013.25f);

        System.out.println("\n--- Weather Update 2 ---");
        weatherStation.setMeasurements(28.0f, 70.0f, 1015.50f);

        // Remove an observer
        System.out.println("\n--- Removing Observer ---");
        weatherStation.removeObserver(phoneApp);

        // Update again - only remaining observer is notified
        System.out.println("\n--- Weather Update 3 ---");
        weatherStation.setMeasurements(30.5f, 75.0f, 1017.75f);

        System.out.println("\n--- Observer Pattern Benefits ---");
        System.out.println("✓ Loose coupling: Subject doesn't know observer implementations");
        System.out.println("✓ Dynamic relationships: Observers can be added/removed at runtime");
        System.out.println("✓ Automatic updates: Observers are notified automatically");
    }
}
