// Prototype Pattern – shallow vs deep cloning and registry usage
// This demo shows:
// - Prototype interface using Cloneable
// - Concrete prototypes: Circle, Rectangle
// - Shallow vs Deep clone difference via nested Point
// - PrototypeRegistry to manage prototypes by key

import java.util.HashMap;
import java.util.Map;

interface Shape extends Cloneable {
    Shape clone();

    void draw();
}

class Point implements Cloneable {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

class Circle implements Shape {
    private int radius;
    private String color;
    private Point center;

    Circle(int radius, String color) {
        this(radius, color, new Point(0, 0));
    }

    Circle(int radius, String color, Point center) {
        this.radius = radius;
        this.color = color;
        this.center = center;
        System.out.println("Expensive initialization for Circle...");
    }

    @Override
    public Shape clone() {
        try {
            // shallow copy (center reference reused)
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Circle deepClone() {
        try {
            Circle copy = (Circle) super.clone();
            copy.center = (Point) center.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void moveCenter(int dx, int dy) {
        center.x += dx;
        center.y += dy;
    }

    public void setRadius(int r) {
        this.radius = r;
    }

    public void setColor(String c) {
        this.color = c;
    }

    @Override
    public void draw() {
        System.out.println("Circle r=" + radius + " color=" + color + " center=" + center);
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

public class PrototypeDemo {
    public static void main(String[] args) {
        System.out.println("== Direct prototype cloning ==");
        Shape circleProto = new Circle(10, "red");
        Shape c1 = circleProto.clone();
        c1.draw();
        Circle c2 = (Circle) circleProto.clone();
        c2.setRadius(20);
        c2.setColor("blue");
        c2.draw();

        System.out.println("\n== Registry ==");
        PrototypeRegistry registry = new PrototypeRegistry();
        registry.register("circle", circleProto);
        registry.register("rect", new Rectangle(20, 30, "green"));
        registry.getClone("circle").draw();
        registry.getClone("rect").draw();

        System.out.println("\n== Shallow vs Deep clone (shared vs copied nested object) ==");
        Circle original = new Circle(5, "black", new Point(5, 5));
        Circle shallow = (Circle) original.clone();
        Circle deep = original.deepClone();
        System.out.println("same center? shallow="
                + (original == shallow ? "n/a" : "true? " + (getCenterId(original) == getCenterId(shallow))));
        System.out.println("same center? deep=" + (getCenterId(original) == getCenterId(deep)));
        shallow.moveCenter(3, 3);
        System.out.print("original after shallow moved: ");
        original.draw();
        deep.moveCenter(-2, -2);
        System.out.print("original after deep moved: ");
        original.draw();
    }

    // helper via string parsing (simple visibility workaround)
    private static int getCenterId(Circle c) {
        return System.identityHashCode(c);
    }
}
