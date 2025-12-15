// Decorator Pattern – Shape Decorator Example
// Demonstrates adding border color functionality to shapes using decorators
// Shows how decorators can add visual enhancements to shapes
// Roles:
//   - Component: Shape
//   - Concrete components: Circle, Rectangle
//   - Decorator base: ShapeDecorator delegates to a Shape
//   - Concrete decorator: RedShapeDecorator adds border behavior

// Interface named Shape
interface Shape {
    // Method inside interface
    void draw();
}

// Concrete component - Circle
class Circle implements Shape {
    // Overriding the method
    @Override
    public void draw() {
        // Print statement to execute when
        // draw() method of this class is called
        // later on in the main() method
        System.out.println("Shape: Circle");
    }
}

// Concrete component - Rectangle
class Rectangle implements Shape {
    // Overriding the method
    @Override
    public void draw() {
        // Print statement to execute when
        // draw() method of this class is called
        // later on in the main() method
        System.out.println("Shape: Rectangle");
    }
}

// Abstract class
abstract class ShapeDecorator implements Shape {
    // Protected variable
    protected Shape decoratedShape;

    // Method 1
    // Abstract class constructor
    public ShapeDecorator(Shape decoratedShape) {
        // This keyword refers to current object itself
        this.decoratedShape = decoratedShape;
    }

    // Method 2 - draw()
    // Concrete implementation of interface method
    public void draw() {
        decoratedShape.draw();
    }
}

// Concrete class extending the abstract class
class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape) {
        // Display message whenever function is called
        System.out.println("Border Color: Red");
    }
}

// Main class
public class ShapeDecoratorDemo {
    // Main driver method
    public static void main(String[] args) {
        // Creating an object of Shape interface
        // inside the main() method
        Shape circle = new Circle();
        Shape redCircle = new RedShapeDecorator(new Circle());
        Shape redRectangle = new RedShapeDecorator(new Rectangle());

        // Display message
        System.out.println("Circle with normal border");
        // Calling the draw method over the
        // object calls as created in
        // above classes
        // Call 1
        circle.draw();

        // Display message
        System.out.println("\nCircle of red border");
        // Call 2
        redCircle.draw();

        // Display message
        System.out.println("\nRectangle of red border");
        // Call 3
        redRectangle.draw();
    }
}

