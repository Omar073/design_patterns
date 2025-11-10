## Adapter

- **Intent**: Convert the interface of a class into another interface clients expect.
- **When to use**: Integrate legacy/third-party code; interface mismatch.

### With Adapter (object adapter)
```java
RoundHole hole = new RoundHole(5);
RoundPegTarget peg = new SquarePegAdapter(new SquarePeg(5));
hole.fits(peg);
```

### Without Adapter
```java
// Client would need to know how to convert SquarePeg to radius -> tight coupling
```

### Pros
- Reuse existing code; decouples client from adaptee specifics.

### Cons
- Extra indirection; only adapts interface (not semantics).

### Compare
- **vs Facade**: Facade simplifies a subsystem; Adapter changes one object’s interface.
- **vs Decorator**: Decorator preserves interface and adds behavior; Adapter changes interface.