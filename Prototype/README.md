## Prototype

- **Intent**: Create new objects by cloning a prototypical instance instead of calling constructors.
- **When to use**: Expensive construction; many similar objects; need to add/remove types at runtime.

### With Prototype
```java
interface Shape extends Cloneable { Shape clone(); }
class Circle implements Shape {
  int r; Circle(int r){ this.r=r; System.out.println("expensive init"); }
  public Shape clone(){ try { return (Shape) super.clone(); } catch(Exception e){ throw new RuntimeException(e); } }
}
Shape proto = new Circle(10);
Shape a = proto.clone(); Shape b = proto.clone();
```

### Without Prototype
```java
Circle a = new Circle(10); // expensive init runs again
Circle b = new Circle(10);
```

### Variants
- Shallow vs Deep clone; Copy-constructor; Registry (Map key → prototype).

### Pros
- Performance; decouples clients from concrete ctors; can add types at runtime.

### Cons
- Must implement clone carefully; deep-copy of graphs is error-prone; hidden sharing bugs.

### Notes
- Prefer deep copy for mutable nested objects; `String`/wrappers are fine with shallow.
- Registry centralizes prototypes and keeps client code simple.

### References
- Slides 1–6.pdf, Labs 1–6.pdf


