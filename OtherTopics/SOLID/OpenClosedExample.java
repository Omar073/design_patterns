// SOLID Principle: Open/Closed Principle (OCP)
// Open for extension, closed for modification

// ❌ BAD: Must modify AreaCalculator to add new shapes
class AreaCalculatorBad {
    public double calculateArea(Object shape) {
        if (shape instanceof RectangleBad) {
            RectangleBad rect = (RectangleBad) shape;
            return rect.width * rect.height;
        } else if (shape instanceof CircleBad) {
            CircleBad circle = (CircleBad) shape;
            return Math.PI * circle.radius * circle.radius;
        }
        // To add Triangle, we must modify this class!
        return 0;
    }
}

class RectangleBad {
    double width;
    double height;
    
    RectangleBad(double width, double height) {
        this.width = width;
        this.height = height;
    }
}

class CircleBad {
    double radius;
    
    CircleBad(double radius) {
        this.radius = radius;
    }
}

// ✅ GOOD: Open for extension, closed for modification
abstract class Shape {
    public abstract double calculateArea();
}

class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Can add Triangle without modifying AreaCalculator!
class Triangle extends Shape {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

// AreaCalculator doesn't need modification for new shapes
class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();  // Works for any Shape!
    }
}

// Demo
public class OpenClosedExample {
    public static void main(String[] args) {
        System.out.println("=== Open/Closed Principle (OCP) ===\n");
        
        System.out.println("❌ BAD: Must modify AreaCalculator to add new shapes");
        AreaCalculatorBad badCalc = new AreaCalculatorBad();
        System.out.println("Rectangle area: " + badCalc.calculateArea(new RectangleBad(5, 4)));
        System.out.println("Circle area: " + badCalc.calculateArea(new CircleBad(3)));
        System.out.println("To add Triangle, must modify AreaCalculatorBad class!");
        
        System.out.println("\n✅ GOOD: Can add new shapes without modifying AreaCalculator");
        AreaCalculator calc = new AreaCalculator();
        
        Rectangle rect = new Rectangle(5, 4);
        Circle circle = new Circle(3);
        Triangle triangle = new Triangle(4, 6);  // New shape added!
        
        System.out.println("Rectangle area: " + calc.calculateArea(rect));
        System.out.println("Circle area: " + calc.calculateArea(circle));
        System.out.println("Triangle area: " + calc.calculateArea(triangle));
        
        System.out.println("\n--- Key Benefits ---");
        System.out.println("✓ Can add new shapes without modifying existing code");
        System.out.println("✓ Existing code remains unchanged and stable");
        System.out.println("✓ Reduces risk of introducing bugs");
        System.out.println("✓ Follows OCP - open for extension, closed for modification");
    }
}
