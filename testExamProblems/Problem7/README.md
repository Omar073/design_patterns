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

### Solution Overview

- **Component Interface**: `IceCream` interface with `getDescription()` and `getCost()`
- **Concrete Component**: `BasicIceCream` (base ice cream)
- **Decorator Abstract Class**: `IceCreamDecorator` that wraps an `IceCream`
- **Concrete Decorators**: `ChocolateDecorator`, `NutsDecorator`, `CaramelDecorator`
- **Client**: Can compose ice cream with any combination of toppings

Instead of creating classes for every combination, we wrap the base ice cream with decorators.

### Key Benefits

- Avoids exponential subclass growth
- Dynamic composition at runtime
- Easy to add new toppings
- Each decorator has single responsibility
- Flexible pricing calculation

## Reference

For more details on the Decorator pattern, see: [../../Decorator/README.md](../../Decorator/README.md)

