// Prototype Pattern – Registry Example
// Demonstrates using a prototype registry to manage and clone prototypes by key
// Roles:
//   - Prototype interface: Shape with clone()
//   - Concrete prototypes: Circle, Rectangle
//   - Registry: PrototypeRegistry stores prototypes and returns clones
//   - Client: main() requests clones by key instead of keeping prototype refs

import java.util.HashMap;
import java.util.Map;

interface Shape extends Cloneable {
    Shape clone();

    void draw();
}

class Circle implements Shape {
    private int radius;
    private String color;

    Circle(int radius, String color) {
        this.radius = radius;
        this.color = color;
        System.out.println("Expensive initialization for Circle...");
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw() {
        System.out.println("Circle r=" + radius + " color=" + color);
    }
}

class Rectangle implements Shape {
    private int width, height;
    private String color;

    Rectangle(int w, int h, String c) {
        this.width = w;
        this.height = h;
        this.color = c;
        System.out.println("Expensive initialization for Rectangle...");
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw() {
        System.out.println("Rectangle w=" + width + " h=" + height + " color=" + color);
    }
}

class PrototypeRegistry {
    private final Map<String, Shape> prototypes = new HashMap<>();

    public void register(String key, Shape prototype) {
        prototypes.put(key, prototype);
    }

    public Shape getClone(String key) {
        Shape p = prototypes.get(key);
        if (p == null)
            throw new IllegalArgumentException("No prototype for key: " + key);
        return p.clone();
    }
}

public class PrototypeRegistryDemo {
    public static void main(String[] args) {
        System.out.println("== Registry ==");
        PrototypeRegistry registry = new PrototypeRegistry();
        registry.register("circle", new Circle(10, "red"));
        registry.register("rect", new Rectangle(20, 30, "green"));
        registry.getClone("circle").draw();
        registry.getClone("rect").draw();
    }
}

