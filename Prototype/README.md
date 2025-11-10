## Prototype Pattern

- **Intent**: Create new objects by cloning a prototypical instance instead of calling constructors.
- **When to use**: When object creation is expensive, you need many similar objects, or you want to add/remove types at runtime.

---

## Why Use the Prototype Pattern?

### The Problem: Expensive Object Creation

When creating objects is expensive (database queries, network calls, complex calculations), creating many similar objects becomes inefficient:

```java
// Problem: Expensive initialization runs every time
class Circle {
    private int radius;
    private String color;
    
    Circle(int radius, String color) {
        this.radius = radius;
        this.color = color;
        
        // Expensive operations
        System.out.println("Loading texture from disk...");
        System.out.println("Connecting to database...");
        System.out.println("Performing complex calculations...");
        System.out.println("Expensive initialization for Circle...");
    }
    
    public void draw() {
        System.out.println("Circle r=" + radius + " color=" + color);
    }
}

// Problem: Each creation runs expensive initialization
Circle c1 = new Circle(10, "red");   // Expensive init runs
Circle c2 = new Circle(10, "red");   // Expensive init runs again (wasteful!)
Circle c3 = new Circle(10, "red");   // Expensive init runs again (wasteful!)
```

**Problems with direct construction:**
- ❌ **Expensive**: Complex initialization runs every time
- ❌ **Inefficient**: Wastes resources on duplicate initialization
- ❌ **Slow**: Performance degrades with many objects
- ❌ **Inflexible**: Can't create objects at runtime without knowing their class
- ❌ **Tight coupling**: Client code depends on concrete classes

### The Solution: Prototype Pattern

The Prototype pattern solves these problems by:
- ✅ **Performance**: Clone existing objects instead of expensive construction
- ✅ **Flexibility**: Create objects at runtime without knowing their class
- ✅ **Efficiency**: Reuse expensive initialization
- ✅ **Decoupling**: Client code doesn't depend on concrete classes
- ✅ **Runtime types**: Add/remove types at runtime via registry

---

## With Prototype Pattern

### Direct Cloning Example

```java
interface Shape extends Cloneable {
    Shape clone();
    void draw();
}

class Circle implements Shape {
    private int radius;
    private String color;
    
    Circle(int radius, String color) {
        this.radius = radius;
        this.color = color;
        System.out.println("Expensive initialization for Circle...");
    }
    
    @Override
    public Shape clone() {
        try {
            // Shallow copy - fast, no expensive initialization
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void setRadius(int r) { this.radius = r; }
    public void setColor(String c) { this.color = c; }
    
    @Override
    public void draw() {
        System.out.println("Circle r=" + radius + " color=" + color);
    }
}

// Usage - expensive init runs only once
Shape circleProto = new Circle(10, "red");  // Expensive init runs once
Shape c1 = circleProto.clone();             // Fast clone, no expensive init
Shape c2 = circleProto.clone();             // Fast clone, no expensive init
c2.setRadius(20);                           // Modify clone independently
c2.setColor("blue");
```

**Benefits:**
- ✅ **Performance**: Cloning is much faster than construction
- ✅ **Efficiency**: Expensive initialization runs only once
- ✅ **Flexibility**: Can modify clones independently
- ✅ **Fast**: Object copying is typically very fast

### Registry-Based Prototype

```java
class PrototypeRegistry {
    private final Map<String, Shape> prototypes = new HashMap<>();
    
    public void register(String key, Shape prototype) {
        prototypes.put(key, prototype);
    }
    
    public Shape getClone(String key) {
        Shape prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("No prototype for key: " + key);
        }
        return prototype.clone();  // Fast clone
    }
}

// Usage
PrototypeRegistry registry = new PrototypeRegistry();
registry.register("circle", new Circle(10, "red"));      // Expensive init once
registry.register("rect", new Rectangle(20, 30, "green")); // Expensive init once

// Fast cloning - no expensive initialization
Shape c1 = registry.getClone("circle");  // Fast
Shape c2 = registry.getClone("circle");  // Fast
Shape r1 = registry.getClone("rect");    // Fast
```

**Benefits:**
- ✅ **Centralized**: All prototypes in one place
- ✅ **Runtime types**: Add/remove types at runtime
- ✅ **Simple client code**: Client doesn't need to know concrete classes
- ✅ **Flexible**: Can change prototypes without changing client code

---

## Without Prototype Pattern

### Direct Construction (Expensive)

```java
class Circle {
    private int radius;
    private String color;
    
    Circle(int radius, String color) {
        this.radius = radius;
        this.color = color;
        
        // Expensive operations run every time
        System.out.println("Loading texture from disk...");
        System.out.println("Connecting to database...");
        System.out.println("Performing complex calculations...");
        System.out.println("Expensive initialization for Circle...");
    }
    
    public void draw() {
        System.out.println("Circle r=" + radius + " color=" + color);
    }
}

// Problem: Expensive initialization runs every time
Circle c1 = new Circle(10, "red");   // Expensive init
Circle c2 = new Circle(10, "red");   // Expensive init again (wasteful!)
Circle c3 = new Circle(10, "red");   // Expensive init again (wasteful!)
```

**Problems:**
- ❌ **Expensive**: Complex initialization runs every time
- ❌ **Inefficient**: Wastes CPU, memory, network resources
- ❌ **Slow**: Performance degrades with many objects
- ❌ **Tight coupling**: Client must know concrete class

### Factory Pattern Alternative

```java
class ShapeFactory {
    public Shape createShape(String type, int... params) {
        if (type.equals("circle")) {
            return new Circle(params[0], "red");  // Expensive init every time
        } else if (type.equals("rect")) {
            return new Rectangle(params[0], params[1], "green");  // Expensive init
        }
        throw new IllegalArgumentException();
    }
}

// Usage - still runs expensive initialization
ShapeFactory factory = new ShapeFactory();
Shape c1 = factory.createShape("circle", 10);  // Expensive init
Shape c2 = factory.createShape("circle", 10);  // Expensive init again
```

**Problems:**
- ❌ **Still expensive**: Construction runs every time
- ❌ **Less flexible**: Can't modify existing objects
- ❌ **No reuse**: Can't reuse expensive initialization

---

## Comparison: Prototype vs Alternatives

| Aspect | Direct Construction | Factory Pattern | Prototype Pattern |
|--------|-------------------|-----------------|-------------------|
| **Performance** | ❌ Slow (expensive init) | ❌ Slow (expensive init) | ✅ Fast (clone) |
| **Initialization** | ❌ Runs every time | ❌ Runs every time | ✅ Runs once |
| **Flexibility** | ⚠️ Medium | ⚠️ Medium | ✅ High (runtime types) |
| **Memory** | ⚠️ Medium | ⚠️ Medium | ✅ Efficient (reuse) |
| **Runtime Types** | ❌ No | ❌ No | ✅ Yes (registry) |
| **Modification** | ❌ Must reconstruct | ❌ Must reconstruct | ✅ Modify clone |

---

## Shallow vs Deep Clone

### Shallow Clone (Default)

```java
class Circle implements Shape {
    private int radius;
    private Point center;  // Reference type
    
    @Override
    public Shape clone() {
        try {
            // Shallow copy - center reference is shared
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

// Problem: Both circles share the same Point object
Circle original = new Circle(5, new Point(5, 5));
Circle shallow = (Circle) original.clone();
shallow.center.x = 10;  // Modifies original's center too!
```

**Problems:**
- ❌ **Shared references**: Nested objects are shared
- ❌ **Side effects**: Modifying clone affects original
- ❌ **Not independent**: Clones aren't truly independent

### Deep Clone (Solution)

```java
class Circle implements Shape {
    private int radius;
    private Point center;
    
    public Circle deepClone() {
        try {
            Circle copy = (Circle) super.clone();
            copy.center = (Point) center.clone();  // Clone nested object
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

// Solution: Each circle has its own Point object
Circle original = new Circle(5, new Point(5, 5));
Circle deep = original.deepClone();
deep.center.x = 10;  // Only affects deep clone, not original
```

**Benefits:**
- ✅ **Independent**: Clones are truly independent
- ✅ **No side effects**: Modifying clone doesn't affect original
- ✅ **Complete copy**: All nested objects are cloned

---

## Pros

- ✅ **Performance**: Cloning is much faster than expensive construction
- ✅ **Efficiency**: Expensive initialization runs only once
- ✅ **Flexibility**: Can create objects at runtime without knowing their class
- ✅ **Decoupling**: Client code doesn't depend on concrete classes
- ✅ **Runtime types**: Can add/remove types at runtime via registry
- ✅ **Memory efficient**: Reuses expensive initialization
- ✅ **Independent clones**: Can modify clones without affecting original (with deep clone)

---

## Cons

- ❌ **Clone complexity**: Must implement `clone()` carefully
- ❌ **Deep copy complexity**: Deep copying object graphs is error-prone
- ❌ **Hidden sharing**: Shallow clones can share references (bugs)
- ❌ **Cloneable interface**: Java's `Cloneable` interface is awkward
- ❌ **Type safety**: Cloning can lose type information
- ❌ **Circular references**: Deep cloning circular references is complex

---

## Variants

### 1. Direct Cloning
- Clone prototype directly
- Simple and straightforward
- Example: `ShapeCloningDemo.java`

### 2. Registry-Based
- Centralized prototype storage
- Runtime type management
- Example: `ShapeRegistryDemo.java`

### 3. Shallow Clone
- Default `clone()` behavior
- Fast but shares references
- Use for immutable nested objects

### 4. Deep Clone
- Clones nested objects too
- Truly independent copies
- Use for mutable nested objects
- Example: `CircleShallowDeepCloneDemo.java`

### 5. Copy Constructor
- Alternative to `clone()`
- More explicit and type-safe
- Example: `new Circle(original)`

---

## When to Use Prototype Pattern

### ✅ Use Prototype When:
- Object creation is **expensive** (database, network, complex calculations)
- You need **many similar objects**
- You want to **add/remove types at runtime**
- Objects have **few differences** (can clone and modify)
- You need **independent copies** of objects
- **Performance** is critical

### ❌ Don't Use Prototype When:
- Object creation is **cheap** (simple objects)
- Objects are **very different** (no benefit from cloning)
- You need **few objects** (overhead not worth it)
- **Deep copying** is too complex (circular references)
- Objects have **many dependencies** (hard to clone)

---

## Best Practices

1. **Prefer Deep Clone**: For mutable nested objects, use deep clone
2. **Use Registry**: For runtime type management, use prototype registry
3. **Consider Copy Constructor**: More explicit than `clone()`
4. **Document Clone Behavior**: Clearly document shallow vs deep
5. **Handle Circular References**: Be careful with circular object graphs

---

## Compare with Other Patterns

- **vs Factory**: Factory creates new instances; Prototype clones existing instances
- **vs Builder**: Builder constructs step-by-step; Prototype copies existing object
- **vs Singleton**: Singleton ensures one instance; Prototype creates many copies
- **vs Copy Constructor**: Copy constructor is more explicit; Prototype uses `clone()`

---

## File Examples

- **`ShapeCloningDemo.java`**: Direct cloning example
- **`ShapeRegistryDemo.java`**: Registry-based prototype management
- **`CircleShallowDeepCloneDemo.java`**: Shallow vs deep clone comparison

---

## Notes

- ⚠️ **Prefer deep copy** for mutable nested objects to avoid shared references
- ⚠️ **Use registry** to centralize prototypes and simplify client code
- ⚠️ **Consider copy constructor** as an alternative to `clone()` (more explicit)
- ⚠️ **Be careful with circular references** in deep cloning scenarios
