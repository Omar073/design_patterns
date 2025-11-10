## Facade

- **Intent**: Provide a unified interface to a set of interfaces in a subsystem; makes the subsystem easier to use.
- **When to use**: Complex subsystem; common usage sequences; reduce coupling from clients to internals.

### With Facade
```java
HomeTheaterFacade f = new HomeTheaterFacade(amp, tuner, screen, dvd);
f.watchMovie("Inception"); f.endMovie();
```

### Without Facade
```java
screen.down(); amp.on(); amp.setVolume(7); tuner.on(); dvd.on(); dvd.play("Inception"); /* ... */
```

### Pros
- Simplifies usage; isolates clients from subsystem changes; improves readability.

### Cons
- Can become a god-object; risk of hiding useful capabilities if too minimal.

### Facade vs Builder/Factory
- Facade hides subsystem complexity (usage); Builder/Factory hide object construction details.

### Facade vs Proxy
- Proxy adds behavior to access a single object; Facade simplifies access to a subsystem of objects.

### References
- Slides 1–6.pdf, Labs 1–6.pdf


