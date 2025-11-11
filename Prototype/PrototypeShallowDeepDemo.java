// Prototype Pattern – Shallow vs Deep Clone Example
// Demonstrates the difference between shallow and deep cloning with nested objects

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

interface Shape extends Cloneable {
    Shape clone();

    void draw();
}

class Circle implements Shape {
    private int radius;
    private String color;
    private Point center;

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

    @Override
    public void draw() {
        System.out.println("Circle r=" + radius + " color=" + color + " center=" + center);
    }
}

public class PrototypeShallowDeepDemo {
    public static void main(String[] args) {
        System.out.println("== Shallow vs Deep clone (shared vs copied nested object) ==");
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

