## Adapter Pattern

- **Intent**: Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.
- **When to use**: When you need to integrate legacy code, third-party libraries, or classes with incompatible interfaces.

---

## Why Use the Adapter Pattern?

### The Problem: Incompatible Interfaces

When you have existing code that expects a specific interface, but you need to use a class with a different interface:

```java
// Client expects this interface
interface RoundPegTarget {
    double getRadius();
}

class RoundHole {
    private double radius;
    
    RoundHole(double radius) {
        this.radius = radius;
    }
    
    public boolean fits(RoundPegTarget peg) {
        return peg.getRadius() <= radius;
    }
}

// But you have this class (incompatible interface)
class SquarePeg {
    private double width;
    
    SquarePeg(double width) {
        this.width = width;
    }
    
    public double getWidth() {  // Different method name!
        return width;
    }
}

// Problem: Can't use SquarePeg with RoundHole
RoundHole hole = new RoundHole(5);
SquarePeg square = new SquarePeg(5);
// hole.fits(square);  // ERROR: Incompatible types!
```

**Problems with incompatible interfaces:**
- ❌ **Can't integrate**: Can't use existing classes together
- ❌ **Code duplication**: Might need to rewrite code
- ❌ **Tight coupling**: Client depends on specific interface
- ❌ **No reuse**: Can't reuse existing code
- ❌ **Maintenance**: Need to maintain multiple versions

### The Solution: Adapter Pattern

The Adapter pattern solves these problems by:
- ✅ **Interface conversion**: Converts one interface to another
- ✅ **Reuse existing code**: Can use existing classes without modification
- ✅ **Decoupling**: Client doesn't need to know about adaptee
- ✅ **Flexibility**: Can adapt multiple classes to same interface
- ✅ **Integration**: Makes incompatible classes work together

---

## With Adapter Pattern

### Object Adapter Example

```java
// Target interface (what client expects)
interface RoundPegTarget {
    double getRadius();
}

// Client code
class RoundHole {
    private double radius;
    
    RoundHole(double radius) {
        this.radius = radius;
    }
    
    public boolean fits(RoundPegTarget peg) {
        return peg.getRadius() <= radius;
    }
}

// Adaptee (existing class with incompatible interface)
class SquarePeg {
    private double width;
    
    SquarePeg(double width) {
        this.width = width;
    }
    
    public double getWidth() {
        return width;
    }
}

// Adapter (converts SquarePeg to RoundPegTarget)
class SquarePegAdapter implements RoundPegTarget {
    private final SquarePeg squarePeg;
    
    SquarePegAdapter(SquarePeg peg) {
        this.squarePeg = peg;
    }
    
    public double getRadius() {
        // Convert square width to circle radius
        // Minimal circle that fits the square
        return (squarePeg.getWidth() * Math.sqrt(2) / 2.0);
    }
}

// Usage - adapter makes incompatible classes work together
RoundHole hole = new RoundHole(5);
SquarePeg square = new SquarePeg(5);

// Adapter converts SquarePeg to RoundPegTarget
RoundPegTarget adapter = new SquarePegAdapter(square);
boolean fits = hole.fits(adapter);  // Works!
```

**Benefits:**
- ✅ **Reuse existing code**: SquarePeg doesn't need to change
- ✅ **Interface conversion**: Adapter converts interface
- ✅ **Transparent**: Client doesn't know about adaptee
- ✅ **Flexible**: Can adapt multiple classes

### Class Adapter (Conceptual)

```java
// Class adapter uses inheritance (limited in Java due to single inheritance)
class SquarePegClassAdapter extends SquarePeg implements RoundPegTarget {
    SquarePegClassAdapter(double width) {
        super(width);
    }
    
    public double getRadius() {
        return (getWidth() * Math.sqrt(2) / 2.0);
    }
}

// Usage
RoundPegTarget adapter = new SquarePegClassAdapter(5);
```

**Note**: Class adapter uses inheritance, but Java's single inheritance limits this approach. Object adapter (composition) is preferred.

---

## Without Adapter Pattern

### Direct Modification (Not Always Possible)

```java
// Problem: Need to modify existing class
class SquarePeg {
    private double width;
    
    SquarePeg(double width) {
        this.width = width;
    }
    
    public double getWidth() {
        return width;
    }
    
    // Problem: Adding method to match interface
    public double getRadius() {
        return (width * Math.sqrt(2) / 2.0);
    }
}

// Usage
RoundHole hole = new RoundHole(5);
SquarePeg square = new SquarePeg(5);
// Can't use directly - still incompatible!
```

**Problems:**
- ❌ **Can't modify**: Third-party or legacy code can't be modified
- ❌ **Violates Open/Closed**: Modifies existing class
- ❌ **Tight coupling**: Client depends on modified class
- ❌ **Maintenance**: Need to maintain modified version

### Code Duplication

```java
// Problem: Rewrite code to match interface
class SquarePegWrapper {
    private double width;
    
    SquarePegWrapper(double width) {
        this.width = width;
    }
    
    public double getRadius() {
        return (width * Math.sqrt(2) / 2.0);
    }
}

// Problem: Duplicate code and logic
```

**Problems:**
- ❌ **Code duplication**: Duplicate logic and code
- ❌ **Maintenance**: Need to maintain duplicate code
- ❌ **No reuse**: Can't reuse existing SquarePeg
- ❌ **Inconsistency**: Two versions might diverge

---

## Comparison: Adapter vs Alternatives

| Aspect | Direct Modification | Code Duplication | Adapter Pattern |
|--------|-------------------|------------------|-----------------|
| **Reuse** | ⚠️ Modified | ❌ No | ✅ Yes |
| **Code Duplication** | ✅ No | ❌ Yes | ✅ No |
| **Flexibility** | ❌ Low | ⚠️ Medium | ✅ High |
| **Maintenance** | ⚠️ Medium | ❌ High | ✅ Low |
| **Third-Party** | ❌ Can't modify | ⚠️ Possible | ✅ Yes |

---

## Adapter Types

### 1. Object Adapter (Composition)
- **Structure**: Adapter contains adaptee (composition)
- **Advantages**: More flexible, can adapt multiple classes
- **Disadvantages**: Slightly more complex
- **Example**: `AdapterDemo.java` (SquarePegAdapter)

### 2. Class Adapter (Inheritance)
- **Structure**: Adapter extends adaptee (inheritance)
- **Advantages**: Simpler, direct access to adaptee
- **Disadvantages**: Limited by single inheritance in Java
- **Note**: Less common in Java

---

## Pros

- ✅ **Reuse existing code**: Can use existing classes without modification
- ✅ **Interface conversion**: Converts incompatible interfaces
- ✅ **Decoupling**: Client doesn't need to know about adaptee
- ✅ **Flexibility**: Can adapt multiple classes to same interface
- ✅ **Integration**: Makes incompatible classes work together
- ✅ **Open/Closed**: Open for extension (new adapters), closed for modification

---

## Cons

- ❌ **Extra indirection**: Additional layer of abstraction
- **Complexity**: More classes and indirection
- ⚠️ **Only adapts interface**: Doesn't change semantics (might not work correctly)
- ⚠️ **Performance**: Slight overhead from adapter layer
- ⚠️ **Type safety**: Need to maintain interface compatibility

---

## When to Use Adapter Pattern

### ✅ Use Adapter When:
- You need to **integrate legacy code** or third-party libraries
- You have **incompatible interfaces** that need to work together
- You want to **reuse existing code** without modification
- You need to **adapt multiple classes** to same interface
- You want to **decouple client** from adaptee

### ❌ Don't Use Adapter When:
- **Simple cases**: Can modify code directly
- **Performance critical**: Extra indirection hurts performance
- **Semantic mismatch**: Adapter can't fix semantic incompatibility
- **Too complex**: Simpler solution exists

---

## Best Practices

1. **Use Object Adapter**: Prefer composition over inheritance
2. **Maintain Interface**: Adapter must implement target interface correctly
3. **Document Conversion**: Clearly document how adapter converts interface
4. **Consider Semantics**: Ensure adapter works correctly, not just compiles
5. **Reuse Existing**: Don't duplicate code - adapt existing classes

---

## Real-World Examples

### Legacy System Integration

```java
// Legacy system uses different interface
class LegacyPayment {
    void processPayment(String account, double amount) { /* ... */ }
}

// New system expects different interface
interface PaymentProcessor {
    void pay(String account, double amount);
}

// Adapter makes legacy system work with new interface
class LegacyPaymentAdapter implements PaymentProcessor {
    private LegacyPayment legacy = new LegacyPayment();
    
    public void pay(String account, double amount) {
        legacy.processPayment(account, amount);
    }
}
```

### Third-Party Library Integration

```java
// Third-party library
class ThirdPartyLogger {
    void logMessage(String msg) { /* ... */ }
}

// Your system expects
interface Logger {
    void log(String message);
}

// Adapter integrates third-party library
class ThirdPartyLoggerAdapter implements Logger {
    private ThirdPartyLogger thirdParty = new ThirdPartyLogger();
    
    public void log(String message) {
        thirdParty.logMessage(message);
    }
}
```

---

## Compare with Other Patterns

- **vs Decorator**: Decorator adds behavior; Adapter changes interface
- **vs Facade**: Facade simplifies subsystem; Adapter converts one interface
- **vs Proxy**: Proxy controls access; Adapter converts interface
- **vs Bridge**: Bridge separates abstraction from implementation; Adapter converts interface

---

## File Examples

- **`AdapterDemo.java`**: Object adapter example (SquarePeg to RoundPegTarget)

---

## Notes

- ⚠️ **Object adapter preferred**: Use composition over inheritance in Java
- ⚠️ **Semantic compatibility**: Ensure adapter works correctly, not just compiles
- ⚠️ **Document conversion**: Clearly document how adapter converts interface
- ⚠️ **Don't overuse**: Only use when truly needed for incompatible interfaces
