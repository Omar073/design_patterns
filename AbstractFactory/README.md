## Abstract Factory

- **Intent**: Provide an interface for creating families of related objects without specifying their concrete classes.
- **When to use**: The system must be configured with one of multiple families (e.g., Windows/Mac UI), and you must enforce consistency across the family.

### With Abstract Factory
```java
interface GUIFactory { Button createButton(); Checkbox createCheckbox(); }
class MacFactory implements GUIFactory { /* returns Mac widgets */ }
class WindowsFactory implements GUIFactory { /* returns Windows widgets */ }
new Application(selectFactory()).render();
```

### Without Abstract Factory
```java
boolean mac = /* runtime flag */;
Button b = mac ? new MacButton() : new WindowsButton();
Checkbox c = mac ? new MacCheckbox() : new WindowsCheckbox();
```

### Pros
- Enforces consistent families; easy to swap families; isolates creation logic; testable via factory doubles.

### Cons
- More indirection; adding new product types requires updating all factories.

### OCP note
- Prefer registering factories (or suppliers) in a map to extend without modifying creation logic.

### Compare
- **vs Builder**: Builder assembles one complex object; Abstract Factory creates multiple related objects.
- **vs Factory Method**: AF composes many factories; FM is per-product creation.

### References
- Slides 1–6.pdf, Labs 1–6.pdf


