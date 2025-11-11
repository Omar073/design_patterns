# Problem 7: Ice-Cream Ordering System

## Problem Statement

An online ice-cream ordering system allows customers to add toppings (nuts, chocolate, caramel) dynamically to their orders. Creating a subclass for every combination (e.g., "ChocolateWithNuts") would explode in number.

## Design Pattern Solution: Decorator

### Why Decorator?

The Decorator pattern is the ideal solution because:

1. **Dynamic Composition**: Toppings can be added/removed at runtime
2. **Avoids Subclass Explosion**: Instead of IceCreamWithChocolate, IceCreamWithNuts, IceCreamWithChocolateAndNuts, etc., we compose decorators
3. **Flexibility**: Any combination of toppings is possible
4. **Single Responsibility**: Each decorator adds one topping
5. **Open/Closed Principle**: Can add new toppings without modifying existing code

### The Problem: Subclass Explosion

Without Decorator, you need a class for every combination:

```java
// Problem: Subclass explosion
class BasicIceCream {
    public double getCost() { return 3.0; }
}

// Need classes for every combination!
class IceCreamWithChocolate extends BasicIceCream {
    public double getCost() { return super.getCost() + 0.5; }
}

class IceCreamWithNuts extends BasicIceCream {
    public double getCost() { return super.getCost() + 0.75; }
}

class IceCreamWithCaramel extends BasicIceCream {
    public double getCost() { return super.getCost() + 0.6; }
}

// Now combinations...
class IceCreamWithChocolateAndNuts extends BasicIceCream {
    public double getCost() { return super.getCost() + 0.5 + 0.75; }
}

class IceCreamWithChocolateAndCaramel extends BasicIceCream {
    public double getCost() { return super.getCost() + 0.5 + 0.6; }
}

class IceCreamWithNutsAndCaramel extends BasicIceCream {
    public double getCost() { return super.getCost() + 0.75 + 0.6; }
}

class IceCreamWithChocolateAndNutsAndCaramel extends BasicIceCream {
    public double getCost() { return super.getCost() + 0.5 + 0.75 + 0.6; }
}

// For 3 toppings: 2^3 = 8 classes needed!
// For 5 toppings: 2^5 = 32 classes needed!
// For 10 toppings: 2^10 = 1024 classes needed! 😱
```

**Problems with subclass explosion:**
- ❌ **Exponential growth**: 2^n classes for n toppings
- ❌ **Unmaintainable**: Too many classes to manage
- ❌ **Rigid**: Can't combine toppings at runtime
- ❌ **Code duplication**: Similar logic repeated in each class
- ❌ **Hard to extend**: Adding one topping doubles the number of classes

### The Solution: Decorator Pattern

The Decorator pattern solves these problems by:
- ✅ **Composition over inheritance**: Compose decorators at runtime
- ✅ **Linear growth**: n decorators for n toppings (not 2^n classes)
- ✅ **Dynamic**: Can combine any toppings at runtime
- ✅ **Single responsibility**: Each decorator adds one feature
- ✅ **Open/Closed**: Add new decorators without modifying existing code

### Solution Overview

- **Component Interface**: `IceCream` interface with `getDescription()` and `getCost()`
- **Concrete Component**: `BasicIceCream` (base ice cream)
- **Decorator Abstract Class**: `IceCreamDecorator` that wraps an `IceCream`
- **Concrete Decorators**: `ChocolateDecorator`, `NutsDecorator`, `CaramelDecorator`
- **Client**: Can compose ice cream with any combination of toppings

Instead of creating classes for every combination, we wrap the base ice cream with decorators:

```java
// Compose dynamically at runtime!
IceCream order = new CaramelDecorator(
    new NutsDecorator(
        new ChocolateDecorator(
            new BasicIceCream("Vanilla")
        )
    )
);
// Any combination is possible!
```

### Key Benefits

- ✅ Avoids exponential subclass growth (2^n → n)
- ✅ Dynamic composition at runtime
- ✅ Easy to add new toppings (just add a decorator)
- ✅ Each decorator has single responsibility
- ✅ Flexible pricing calculation
- ✅ Open/Closed Principle: Open for extension, closed for modification

### Comparison: Without vs With Decorator

| Aspect | Without Decorator | With Decorator |
|--------|-------------------|----------------|
| **Classes Needed** | 2^n (exponential) | n (linear) |
| **Flexibility** | ❌ Fixed combinations | ✅ Any combination at runtime |
| **Maintainability** | ❌ Unmaintainable | ✅ Easy to maintain |
| **Extensibility** | ❌ Adding topping doubles classes | ✅ Just add one decorator |
| **Code Duplication** | ❌ High | ✅ Low (reusable decorators) |

## Reference

For more details on the Decorator pattern, see: [../../Decorator/README.md](../../Decorator/README.md)

