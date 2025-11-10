// Prototype Pattern – Direct Cloning Example
// Demonstrates direct prototype cloning without a registry

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

    public void setRadius(int r) {
        this.radius = r;
    }

    public void setColor(String c) {
        this.color = c;
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

public class ShapeCloningDemo {
    public static void main(String[] args) {
        System.out.println("== Direct prototype cloning ==");
        Shape circleProto = new Circle(10, "red");
        Shape c1 = circleProto.clone();
        c1.draw();
        Circle c2 = (Circle) circleProto.clone();
        c2.setRadius(20);
        c2.setColor("blue");
        c2.draw();
    }
}

