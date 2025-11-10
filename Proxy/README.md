## Proxy

- **Intent**: Provide a substitute that controls access to a real object (add behavior: lazy-load, security, caching, logging).
- **When to use**: Access control, resource-heavy initialization, remote proxy, monitoring.

### Protection Proxy (college internet)
```java
Internet net = new ProtectedInternetProxy();
net.connect("example.com"); // ok
net.connect("bad.com");     // blocked
```

### Virtual Proxy
```java
Image img = new ProxyImage("photo.png"); // not loaded yet
img.display(); // loads then displays
```

### Pros
- Add cross-cutting behaviors without changing real subject; defer cost; enforce policies.

### Cons
- Indirection overhead; complexity; can obscure control flow.

### Compare: Facade vs Proxy
- Proxy adds/controls behavior around one object; Facade simplifies a subsystem API.
- Client cannot directly access real subject through Proxy (by design); Facade exposes a simpler path to reachable subsystem.

### References
- Slides 1–6.pdf, Labs 1–6.pdf


