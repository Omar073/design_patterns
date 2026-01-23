// Final Exam 2023 - Question 5 Solution
// Facade Pattern Implementation (ShapeMaker)

// Shape interface
interface Shape {
    void draw();
}

// Concrete shapes
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}

// Facade class
class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        this.circle = new Circle();
        this.rectangle = new Rectangle();
        this.square = new Square();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}

// Demo
public class Question5 {
    public static void main(String[] args) {
        System.out.println("=== Final Exam 2023 - Question 5: Facade Pattern ===\n");

        ShapeMaker shapeMaker = new ShapeMaker();

        System.out.println("Client using ShapeMaker facade:");
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();

        System.out.println("\n--- Pattern Explanation ---");
        System.out.println("ShapeMaker is a Facade that simplifies drawing different shapes:");
        System.out.println("- Client calls high-level methods (drawCircle, drawRectangle, drawSquare)");
        System.out.println("- Facade internally manages Circle, Rectangle, and Square objects");
    }
}

