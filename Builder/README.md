## Builder

- **Intent**: Separate construction of a complex object from its representation.
- **When to use**: Many optional params; need readable, safe construction; immutable products.

### With Builder
```java
Car car = new Car.Builder("V8", 4).color("black").sunroof(true).gps(true).build();
```

### Without Builder (telescoping)
```java
CarTelescoping c = new CarTelescoping("V8", 4, "black", true, true);
```

### Pros
- Readable, safe, immutable products; easy defaults; validation in `build()`.

### Cons
- Extra types; overkill for simple objects.

### Variants
- Fluent inner builder; Director-based (classic) for fixed assembly steps.

### Compare
- **vs Factory**: AF creates families; Builder assembles one complex object.
- **vs Facade**: Facade simplifies a subsystem; Builder hides construction steps.