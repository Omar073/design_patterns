## Observer Pattern

- **Intent**: Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
- **Also Known As**: Dependents or Publish-Subscribe
- **When to use**: When you need to notify multiple objects about changes in another object's state, and you want to maintain loose coupling between the subject and its observers.

---

## Pattern Structure

The Observer pattern consists of the following components:

1. **`Subject`** (Interface/Class)
   - Maintains a list of observers
   - Provides methods to attach, detach, and notify observers
   - Example: `WeatherStation`

2. **`Observer`** (Interface)
   - Defines an interface for objects that should be notified of changes
   - Method: `update()` - Called when subject's state changes
   - Example: `Observer` interface

3. **`ConcreteSubject`** (Class)
   - Implements the `Subject` interface
   - Stores state that observers are interested in
   - Notifies observers when state changes
   - Example: `WeatherStation`

4. **`ConcreteObserver`** (Classes)
   - Implements the `Observer` interface
   - Maintains a reference to the `ConcreteSubject`
   - Updates its own state when notified
   - Examples: `PhoneApp`, `WebsiteDashboard`, `SmartWatch`, `OutdoorDisplay`

**Key Relationships:**
- `Subject` **maintains** a list of `Observer` objects (one-to-many)
- `ConcreteObserver` **implements** `Observer` interface
- `ConcreteSubject` **notifies** all observers when state changes
- Observers **subscribe** to subject by registering themselves

**Pattern Flow:**
1. Observer registers with Subject (subscribe)
2. Subject's state changes
3. Subject notifies all registered observers
4. Each observer receives update and reacts accordingly
5. Observer can unregister from Subject (unsubscribe)

---

## Observer Pattern Examples

### Example 1: Weather Station & Display Devices

**Situation:**
- A weather station monitors environmental data (temperature, humidity, pressure)
- Observers: Phone app, website dashboard, smart watch, outdoor displays
- Behavior: Whenever weather data changes, all the registered displays automatically update

**Implementation:**
```java
// Subject: Weather Station
class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature, humidity, pressure;
    
    public void setMeasurements(float temp, float hum, float press) {
        this.temperature = temp;
        this.humidity = hum;
        this.pressure = press;
        notifyObservers();  // Notify all observers automatically
    }
}

// Observer: Phone App
class PhoneApp implements Observer {
    public void update(float temp, float hum, float press) {
        // Update display with new weather data
        display(temp, hum, press);
    }
}
```

### Example 2: Stock Market Ticker

**Situation:**
- A stock market system tracks stock prices in real time
- Observers: Trading apps, news websites, analytics dashboards, alerts system
- Behavior: When a price changes, notify all observers immediately so they can show updated data

**Implementation:**
```java
// Subject: Stock Market
class StockMarket implements Subject {
    private Map<String, Double> stockPrices;
    
    public void updatePrice(String symbol, double price) {
        stockPrices.put(symbol, price);
        notifyObservers();  // Notify all trading apps, websites, etc.
    }
}

// Observer: Trading App
class TradingApp implements Observer {
    public void update(String symbol, double price) {
        // Update UI with new stock price
        updateStockDisplay(symbol, price);
    }
}
```

### Example 3: Social Media Notifications

**Situation:**
- User posts a new photo on Instagram
- Observers: Followers
- Behavior: Each follower gets a notification automatically

**Implementation:**
```java
// Subject: User Account
class UserAccount implements Subject {
    private List<Observer> followers;
    
    public void postPhoto(Photo photo) {
        // Post the photo
        photos.add(photo);
        notifyObservers();  // Notify all followers
    }
}

// Observer: Follower
class Follower implements Observer {
    public void update(Notification notification) {
        // Show notification to follower
        showNotification(notification);
    }
}
```

---

## Implementation

### Subject Interface

```java
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
```

### Observer Interface

```java
interface Observer {
    void update(float temperature, float humidity, float pressure);
}
```

### Concrete Subject

```java
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
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
    
    public void setMeasurements(float temp, float hum, float press) {
        this.temperature = temp;
        this.humidity = hum;
        this.pressure = press;
        notifyObservers();  // Automatically notify all observers
    }
}
```

### Concrete Observers

```java
class PhoneApp implements Observer {
    private float temperature, humidity, pressure;
    
    @Override
    public void update(float temp, float hum, float press) {
        this.temperature = temp;
        this.humidity = hum;
        this.pressure = press;
        display();  // Update display
    }
    
    private void display() {
        System.out.println("Temperature: " + temperature);
        System.out.println("Humidity: " + humidity);
        System.out.println("Pressure: " + pressure);
    }
}

class WebsiteDashboard implements Observer {
    // Similar implementation
    @Override
    public void update(float temp, float hum, float press) {
        // Update website display
    }
}
```

### Client Usage

```java
public class ObserverPatternDemo {
    public static void main(String[] args) {
        // Create subject
        WeatherStation weatherStation = new WeatherStation();
        
        // Create observers
        PhoneApp phoneApp = new PhoneApp("WeatherPro");
        WebsiteDashboard website = new WebsiteDashboard("Weather.com");
        
        // Register observers
        weatherStation.registerObserver(phoneApp);
        weatherStation.registerObserver(website);
        
        // Update weather data - observers are automatically notified!
        weatherStation.setMeasurements(25.5f, 65.0f, 1013.25f);
    }
}
```

---

## Why Use the Observer Pattern?

### Code Without Observer Pattern

Without the Observer pattern, the subject must directly know about and update each observer:

```java
// Problem: Tight coupling - WeatherStation knows about all concrete observers
class WeatherStation {
    private PhoneApp phoneApp;
    private WebsiteDashboard website;
    private SmartWatch smartWatch;
    private float temperature;
    private float humidity;
    private float pressure;
    
    public WeatherStation(PhoneApp phoneApp, WebsiteDashboard website, SmartWatch smartWatch) {
        this.phoneApp = phoneApp;
        this.website = website;
        this.smartWatch = smartWatch;
    }
    
    public void setMeasurements(float temp, float hum, float press) {
        this.temperature = temp;
        this.humidity = hum;
        this.pressure = press;
        
        // Must manually update each observer - easy to forget one!
        phoneApp.updateTemperature(temp);
        phoneApp.updateHumidity(hum);
        phoneApp.updatePressure(press);
        
        website.updateTemperature(temp);
        website.updateHumidity(hum);
        website.updatePressure(press);
        
        smartWatch.updateTemperature(temp);
        smartWatch.updateHumidity(hum);
        smartWatch.updatePressure(press);
    }
    
    // To add a new observer, must modify this class!
    // public void setMeasurements(...) {
    //     ... existing code ...
    //     newObserver.updateTemperature(temp);  // Must add this
    //     newObserver.updateHumidity(hum);       // Must add this
    //     newObserver.updatePressure(press);     // Must add this
    // }
}

// Client code - tightly coupled
public class WithoutObserverDemo {
    public static void main(String[] args) {
        PhoneApp phoneApp = new PhoneApp("WeatherPro");
        WebsiteDashboard website = new WebsiteDashboard("Weather.com");
        SmartWatch smartWatch = new SmartWatch("FitWatch");
        
        // WeatherStation must know about all observers at construction
        WeatherStation station = new WeatherStation(phoneApp, website, smartWatch);
        
        // If we want to add/remove observers, we need a new WeatherStation instance
        // or modify the existing one - no dynamic registration!
    }
}
```

**Problems:**
- ❌ **Tight coupling**: Subject knows about all concrete observer classes
- ❌ **Hard to extend**: Adding new observer requires modifying subject class
- ❌ **Violates Open/Closed Principle**: Must modify subject to add observers
- ❌ **Manual updates**: Must remember to update each observer manually
- ❌ **No dynamic registration**: Cannot add/remove observers at runtime
- ❌ **Error-prone**: Easy to forget updating an observer

### The Solution: Observer Pattern

The Observer pattern solves these problems by:
- ✅ **Loose coupling**: Subject only knows about Observer interface
- ✅ **Easy to extend**: Add new observers without modifying subject
- ✅ **Automatic updates**: Observers are notified automatically
- ✅ **Dynamic relationships**: Observers can be added/removed at runtime

---

## Pattern Participants

1. **Subject** (Interface/Class)
   - Knows its observers and provides an interface for attaching/detaching observers
   - Example: `Subject` interface, `WeatherStation` class

2. **Observer** (Interface)
   - Defines an interface for objects that should be notified of changes
   - Example: `Observer` interface with `update()` method

3. **ConcreteSubject** (Class)
   - Stores state that observers are interested in
   - Sends notification to observers when state changes
   - Example: `WeatherStation`

4. **ConcreteObserver** (Classes)
   - Implements the `Observer` interface
   - Maintains reference to `ConcreteSubject` (optional)
   - Updates its own state when notified
   - Examples: `PhoneApp`, `WebsiteDashboard`, `SmartWatch`

---

## Pros

- ✅ **Loose Coupling**: Subject and observers are loosely coupled - subject doesn't know concrete observer types
- ✅ **Dynamic Relationships**: Observers can be added/removed at runtime
- ✅ **Open/Closed Principle**: Can add new observers without modifying subject
- ✅ **Automatic Updates**: Observers are notified automatically when state changes
- ✅ **One-to-Many**: One subject can notify many observers efficiently
- ✅ **Broadcast Communication**: Subject can notify all observers with one call

---

## Cons

- ❌ **Unexpected Updates**: Observers may receive updates they don't care about
- ❌ **Update Order**: No guarantee of order in which observers are notified
- ❌ **Memory Leaks**: Observers must be properly unregistered to avoid memory leaks
- ⚠️ **Performance**: Notifying many observers can be slow if observers do heavy work
- ⚠️ **Circular Dependencies**: Can create circular dependencies if not careful

---

## When to Use Observer Pattern

### ✅ Use Observer Pattern When:

#### One-to-Many Dependency:
- You need to notify multiple objects when one object's state changes
- Changes to one object require updating multiple dependent objects
- You want to maintain loose coupling between subject and observers

#### Event-Driven Systems:
- You need to implement event handling systems
- You need to decouple event sources from event handlers
- You need publish-subscribe functionality

#### Model-View Architecture:
- You need to separate model (data) from views (displays)
- Multiple views need to stay synchronized with model
- You want views to update automatically when model changes

#### Additional Use Cases:
- **GUI Frameworks**: Buttons, menus notify listeners of events
- **Distributed Systems**: Publish-subscribe messaging systems
- **Reactive Programming**: Event streams and reactive data flows
- **Game Development**: Game events notify multiple systems

### ❌ Don't Use Observer Pattern When:
- **Simple Updates**: Direct method calls are sufficient
- **Performance Critical**: Overhead of notification is too high
- **Tight Coupling Acceptable**: Subject and observers are naturally tightly coupled
- **Update Order Matters**: You need guaranteed order of updates

---

## Real-World Examples

### GUI Frameworks
- **Button Click Events**: Button (subject) notifies listeners (observers) when clicked
- **Window Events**: Window notifies listeners of resize, close, focus events
- **Model-View-Controller**: Model (subject) notifies views (observers) of data changes

### Event Systems
- **JavaScript Events**: DOM elements notify event listeners
- **Java AWT/Swing**: Components notify action listeners
- **C# Events**: Events notify subscribers

### Reactive Programming
- **RxJava/RxJS**: Observable streams notify subscribers
- **ReactiveX**: Data streams notify observers of changes
- **Event Buses**: Central event bus notifies subscribers

### Distributed Systems
- **Message Queues**: Publishers notify subscribers via message queues
- **Pub/Sub Systems**: Redis Pub/Sub, RabbitMQ notify subscribers
- **Microservices**: Service events notify other services

---

## Compare with Other Patterns

### Observer vs Mediator

**Observer:**
- **Distributed** communication through observers
- Subject notifies multiple observers directly
- Observers are independent
- Used when you need one-to-many notification

**Mediator:**
- **Centralized** communication through one mediator
- Mediator actively coordinates colleagues
- Colleagues don't know about each other
- Used when you need centralized coordination

**When to use which:**
- Use **Observer** when you need one-to-many notifications (event systems)
- Use **Mediator** when you need centralized coordination (GUI dialogs, workflow systems)

### Observer vs Command

**Observer:**
- Notifies **multiple observers** of state changes
- Used for **event notification**
- Observers react to changes independently

**Command:**
- Encapsulates **single operations** as objects
- Used for **request queuing** and undo/redo
- Commands can be stored and executed later

**Key Difference:**
- Observer = **notify many** about changes
- Command = **encapsulate operations** for execution

### Observer vs Chain of Responsibility

**Observer:**
- **Broadcasts** to all observers
- All observers receive the same notification
- Used for **event notification**

**Chain of Responsibility:**
- **Passes request** along a chain
- Only one handler processes the request
- Used for **request handling**

**Key Difference:**
- Observer = **notify all** observers
- Chain = **pass to one** handler

---

## Best Practices

1. **Use Interfaces**: Define clear `Subject` and `Observer` interfaces
2. **Manage Lifecycle**: Always unregister observers to prevent memory leaks
3. **Consider Thread Safety**: If subject and observers run in different threads, ensure thread safety
4. **Limit Update Frequency**: Consider throttling or debouncing frequent updates
5. **Use Pull vs Push**: Decide if observers pull data or subject pushes data
6. **Handle Exceptions**: Don't let one observer's exception break notification to others

---

## Implementation Notes

### Push vs Pull Model

**Push Model (Current Implementation):**
```java
// Subject pushes data to observers
public void notifyObservers() {
    for (Observer observer : observers) {
        observer.update(temperature, humidity, pressure);  // Push data
    }
}
```

**Pull Model:**
```java
// Observers pull data from subject
public void notifyObservers() {
    for (Observer observer : observers) {
        observer.update();  // Observer pulls data from subject
    }
}

// Observer pulls data when needed
public void update() {
    float temp = weatherStation.getTemperature();  // Pull data
    float hum = weatherStation.getHumidity();
    // Use data...
}
```

### Thread Safety

```java
class WeatherStation implements Subject {
    private List<Observer> observers = Collections.synchronizedList(new ArrayList<>());
    
    public void notifyObservers() {
        // Create copy to avoid concurrent modification
        List<Observer> observersCopy;
        synchronized (observers) {
            observersCopy = new ArrayList<>(observers);
        }
        
        for (Observer observer : observersCopy) {
            observer.update(temperature, humidity, pressure);
        }
    }
}
```

### Preventing Memory Leaks

```java
// Always unregister observers when done
public void cleanup() {
    weatherStation.removeObserver(phoneApp);
    weatherStation.removeObserver(website);
    // Allow garbage collection
    phoneApp = null;
    website = null;
}
```

---

## Notes

- ⚠️ **Memory Leaks**: Always unregister observers to prevent memory leaks
- ⚠️ **Update Order**: No guarantee of notification order - don't depend on it
- ⚠️ **Exception Handling**: Handle exceptions in observers to prevent breaking notification chain
- ⚠️ **Performance**: Consider lazy evaluation or batching for frequent updates
- ⚠️ **Circular Updates**: Avoid observers updating subject (can cause infinite loops)

---

**Further reading**: See the demo for a complete working example:
- [ObserverPatternDemo.java](ObserverPatternDemo.java)

