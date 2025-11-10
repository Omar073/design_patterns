## Proxy Pattern

- **Intent**: Provide a substitute or placeholder for another object to control access to it.
- **When to use**: When you need to control access to an object, add behavior before/after method calls, or defer expensive operations.

---

## Why Use the Proxy Pattern?

### The Problem: Direct Access Issues

When you need to add behavior around object access (security, lazy loading, logging), direct access doesn't allow this:

```java
// Problem: Direct access - no control
class RealImage {
    private String filename;
    
    RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();  // Expensive operation runs immediately
    }
    
    private void loadFromDisk() {
        System.out.println("Loading heavy image from disk: " + filename);
        // Simulate expensive disk I/O
    }
    
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

// Problem: Image loaded even if never displayed
RealImage img = new RealImage("large-photo.jpg");  // Expensive load happens immediately
// What if we never call img.display()? We wasted resources!
```

**Problems with direct access:**
- ❌ **No access control**: Can't restrict or filter access
- ❌ **No lazy loading**: Expensive operations run immediately
- ❌ **No cross-cutting concerns**: Can't add logging, caching, security
- ❌ **Tight coupling**: Client directly depends on real object
- ❌ **Resource waste**: Expensive operations run even if not needed

### The Solution: Proxy Pattern

The Proxy pattern solves these problems by:
- ✅ **Access control**: Can restrict or filter access to real object
- ✅ **Lazy loading**: Defer expensive operations until needed
- ✅ **Cross-cutting concerns**: Add logging, caching, security transparently
- ✅ **Decoupling**: Client doesn't need to know about real object
- ✅ **Resource efficiency**: Expensive operations only when needed

---

## With Proxy Pattern

### Virtual Proxy (Lazy Loading)

```java
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    
    RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();  // Expensive operation
    }
    
    private void loadFromDisk() {
        System.out.println("Loading heavy image from disk: " + filename);
    }
    
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private String filename;
    private RealImage real;  // Lazy - not created yet
    
    ProxyImage(String filename) {
        this.filename = filename;
        // No expensive load here!
    }
    
    public void display() {
        if (real == null) {
            real = new RealImage(filename);  // Load only when needed
        }
        real.display();
    }
}

// Usage - image not loaded until display() is called
Image img = new ProxyImage("large-photo.jpg");  // Fast - no load
// ... do other work ...
img.display();  // Now loads and displays
```

**Benefits:**
- ✅ **Lazy loading**: Expensive operation only when needed
- ✅ **Resource efficient**: No waste if object never used
- ✅ **Transparent**: Client doesn't know about lazy loading
- ✅ **Performance**: Faster initialization

### Protection Proxy (Access Control)

```java
interface Internet {
    void connect(String host);
}

class RealInternet implements Internet {
    public void connect(String host) {
        System.out.println("Connecting to " + host);
    }
}

class ProtectedInternetProxy implements Internet {
    private final Internet real = new RealInternet();
    private static final Set<String> banned = new HashSet<>();
    
    static {
        banned.add("bad.com");
        banned.add("games.example");
    }
    
    public void connect(String host) {
        if (banned.contains(host)) {
            System.out.println("Access denied to " + host);
        } else {
            real.connect(host);  // Forward to real object
        }
    }
}

// Usage - access control transparent to client
Internet internet = new ProtectedInternetProxy();
internet.connect("example.com");  // Allowed
internet.connect("bad.com");     // Blocked
```

**Benefits:**
- ✅ **Access control**: Can restrict access transparently
- ✅ **Security**: Enforces policies without changing real object
- ✅ **Transparent**: Client doesn't know about restrictions
- ✅ **Flexible**: Can change policies without changing client code

### Logging Proxy (Cross-Cutting Concerns)

```java
interface Service {
    void doWork();
    String say(String name);
}

class RealService implements Service {
    public void doWork() {
        System.out.println("Doing work...");
    }
    
    public String say(String name) {
        return "Hello, " + name;
    }
}

class LoggingProxy implements Service {
    private final Service real = new RealService();
    
    public void doWork() {
        System.out.println("[LOG] Calling doWork()");
        real.doWork();
        System.out.println("[LOG] Done doWork()");
    }
    
    public String say(String name) {
        System.out.println("[LOG] Calling say() with: " + name);
        String result = real.say(name);
        System.out.println("[LOG] Done say(), result: " + result);
        return result;
    }
}

// Usage - logging added transparently
Service service = new LoggingProxy();
service.doWork();  // Automatically logged
```

**Benefits:**
- ✅ **Cross-cutting concerns**: Add logging, caching, timing transparently
- ✅ **Separation of concerns**: Real object doesn't need logging code
- ✅ **Reusable**: Can add same behavior to multiple objects
- ✅ **Transparent**: Client doesn't know about added behavior

---

## Without Proxy Pattern

### Direct Access (No Control)

```java
class RealImage {
    private String filename;
    
    RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();  // Expensive - runs immediately
    }
    
    private void loadFromDisk() {
        System.out.println("Loading heavy image from disk: " + filename);
    }
    
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

// Problem: Image loaded even if never displayed
RealImage img = new RealImage("large-photo.jpg");  // Expensive load
// What if we never call img.display()? Waste!
```

**Problems:**
- ❌ **No lazy loading**: Expensive operations run immediately
- ❌ **Resource waste**: Operations run even if not needed
- ❌ **No access control**: Can't restrict access
- ❌ **No cross-cutting**: Can't add logging, caching transparently

### Direct Access with Manual Checks

```java
class Internet {
    private static final Set<String> banned = new HashSet<>();
    
    static {
        banned.add("bad.com");
    }
    
    public void connect(String host) {
        if (banned.contains(host)) {  // Check in real object
            System.out.println("Access denied");
            return;
        }
        System.out.println("Connecting to " + host);
    }
}

// Problem: Real object has access control logic mixed in
Internet internet = new Internet();
internet.connect("bad.com");
```

**Problems:**
- ❌ **Mixed concerns**: Access control mixed with real functionality
- ❌ **Less flexible**: Can't change access control without changing real object
- ❌ **Tight coupling**: Client depends on real object with access control
- ❌ **Hard to test**: Can't test real object without access control

---

## Comparison: Proxy vs Alternatives

| Aspect | Direct Access | Manual Checks | Proxy Pattern |
|--------|--------------|---------------|---------------|
| **Lazy Loading** | ❌ No | ❌ No | ✅ Yes |
| **Access Control** | ❌ No | ⚠️ Mixed in | ✅ Separate |
| **Cross-Cutting** | ❌ No | ❌ No | ✅ Yes |
| **Separation** | ⚠️ Medium | ❌ Poor | ✅ Excellent |
| **Flexibility** | ❌ Low | ⚠️ Medium | ✅ High |
| **Transparency** | N/A | ⚠️ Medium | ✅ High |

---

## Proxy Types

### 1. Virtual Proxy
- **Purpose**: Lazy loading of expensive objects
- **Use case**: Defer expensive operations until needed
- **Example**: `VirtualProxyDemo.java`

### 2. Protection Proxy
- **Purpose**: Access control and security
- **Use case**: Restrict access to real object
- **Example**: `ProtectionProxyDemo.java`

### 3. Logging Proxy
- **Purpose**: Add cross-cutting concerns (logging, caching)
- **Use case**: Add behavior without changing real object
- **Example**: `LoggingProxyDemo.java`

### 4. Remote Proxy
- **Purpose**: Represent object in different address space
- **Use case**: Network communication, RPC

### 5. Smart Reference Proxy
- **Purpose**: Additional operations when accessing object
- **Use case**: Reference counting, loading on access

---

## Pros

- ✅ **Access control**: Can restrict or filter access transparently
- ✅ **Lazy loading**: Defer expensive operations until needed
- ✅ **Cross-cutting concerns**: Add logging, caching, security without changing real object
- ✅ **Separation of concerns**: Real object doesn't need access control/logging code
- ✅ **Transparency**: Client doesn't know about proxy
- ✅ **Resource efficiency**: Expensive operations only when needed
- ✅ **Flexibility**: Can change behavior without changing real object

---

## Cons

- ❌ **Indirection overhead**: Extra method call layer
- ❌ **Complexity**: More classes and indirection
- ❌ **Can obscure control flow**: Harder to debug
- ❌ **Performance**: Slight overhead from proxy layer
- ❌ **Type safety**: Need to maintain same interface

---

## When to Use Proxy Pattern

### ✅ Use Proxy When:
- You need **lazy loading** of expensive objects
- You need **access control** or security
- You need **cross-cutting concerns** (logging, caching, timing)
- You want to **defer expensive operations**
- You need **remote access** to objects
- You want to **add behavior** without changing real object

### ❌ Don't Use Proxy When:
- **Simple objects**: Overhead not worth it
- **Performance critical**: Extra indirection hurts performance
- **Direct access sufficient**: No need for control or lazy loading
- **Too complex**: Simpler solution exists

---

## Best Practices

1. **Maintain Interface**: Proxy must implement same interface as real object
2. **Transparency**: Client shouldn't know it's using a proxy
3. **Lazy Loading**: Use virtual proxy for expensive objects
4. **Access Control**: Use protection proxy for security
5. **Cross-Cutting**: Use proxy for logging, caching, timing

---

## Compare with Other Patterns

- **vs Decorator**: Decorator adds behavior; Proxy controls access
- **vs Facade**: Facade simplifies subsystem; Proxy controls single object
- **vs Adapter**: Adapter changes interface; Proxy preserves interface
- **vs Wrapper**: Proxy controls access; Wrapper just wraps

---

## File Examples

- **`ProtectionProxyDemo.java`**: Protection proxy for access control
- **`VirtualProxyDemo.java`**: Virtual proxy for lazy loading
- **`LoggingProxyDemo.java`**: Logging proxy for cross-cutting concerns

---

## Notes

- ⚠️ **Maintain same interface**: Proxy must implement same interface as real object
- ⚠️ **Transparency**: Client shouldn't know it's using a proxy
- ⚠️ **Performance**: Consider overhead of extra indirection
- ⚠️ **Use appropriately**: Don't overuse - simpler solutions might be better
