// Bridge Pattern – Shape / Color Example
// Abstraction hierarchy: Shape -> Circle, Square
// Implementor hierarchy: Color -> Red, Blue (and can be extended)

// Implementor
interface Color {
    String applyColor();
}

// Concrete Implementors
class Red implements Color {
    @Override
    public String applyColor() {
        return "red";
    }
}

class Blue implements Color {
    @Override
    public String applyColor() {
        return "blue";
    }
}

// Abstraction
abstract class Shape {
    protected Color color;

    protected Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}

// Refined Abstractions
class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color.applyColor() + " circle");
    }
}

class Square extends Shape {

    public Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color.applyColor() + " square");
    }
}

// Client / Demo
public class BridgeShapeColorDemo {
    public static void main(String[] args) {
        // Mix and match shapes and colors at runtime
        Shape redCircle = new Circle(new Red());
        Shape blueCircle = new Circle(new Blue());
        Shape redSquare = new Square(new Red());
        Shape blueSquare = new Square(new Blue());

        redCircle.draw();
        blueCircle.draw();
        redSquare.draw();
        blueSquare.draw();
    }
}
