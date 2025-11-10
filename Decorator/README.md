## Decorator

- **Intent**: Attach additional responsibilities to an object dynamically; alternative to subclass explosion.
- **When to use**: Many optional features; need to combine features at runtime.

### With Decorator
```java
Coffee c = new Sugar(new Milk(new SimpleCoffee()));
```

### Without Decorator (subclasses blow-up)
```java
// SimpleCoffeeWithMilkAndSugar extends Coffee { ... } // combinatorial growth
```

### Pros
- Composable; adheres to Open/Closed; avoids subclass explosion.

### Cons
- Many small objects; debugging chain can be harder; order matters.

### Compare
- **vs Adapter**: Adapter changes interface; Decorator preserves interface and adds behavior.
- **vs Facade**: Facade simplifies; Decorator augments one object’s responsibilities.

### References
- Slides 1–6.pdf, Labs 1–6.pdf


