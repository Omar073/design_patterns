## Cross-Pattern Comparisons (Quick Reference)

- **Facade vs Builder/Factory**
  - Facade: hides subsystem usage complexity (sequence of calls).
  - Builder/Factory: hide construction; Builder assembles one complex object; Abstract Factory creates families.

- **Facade vs Proxy**
  - Proxy: represents a single object and adds behavior (security, lazy, logging).
  - Facade: represents a subsystem and simplifies its interface.
  - Proxy can block direct access; Facade does not block access to subsystem.

- **Adapter vs Decorator vs Facade**
  - Adapter: changes interface to be compatible.
  - Decorator: preserves interface, adds responsibilities.
  - Facade: provides a simpler interface to many things.

- **Prototype vs Builder**
  - Prototype: copy an existing configured instance.
  - Builder: construct step-by-step with explicit options.

- **Abstract Factory vs Factory Method**
  - Abstract Factory: families (multiple related products).
  - Factory Method: one product creation per hierarchy.

References: Slides 1–6.pdf, Labs 1–6.pdf


