## Singleton

- **Intent**: Ensure a class has only one instance and provide a global access point.
- **When to use**: Shared configuration, logging, device drivers, caches.
- **Variants**: Eager, Lazy (synchronized), Double-checked locking (DCL), Holder, Enum.
- **Pitfalls**: Hidden global state; testing hard; reflection/serialization can break non-enum singletons.

### With Singleton (example)
```java
class HolderSingleton {
  private HolderSingleton() {}
  private static class Holder { static final HolderSingleton I = new HolderSingleton(); }
  public static HolderSingleton getInstance() { return Holder.I; }
}
```

### Without Singleton
```java
class Logger { /* state */ }
Logger a = new Logger();
Logger b = new Logger(); // divergent instances cause inconsistency
```

### Pros
- Single point of truth; controlled access; lazy or eager as needed; enum is robust.

### Cons
- Global state; tight coupling; order-of-init issues; difficult to test/mock.

### Notes
- Prefer dependency injection where feasible.
- Use `enum` to resist reflection/serialization attacks.