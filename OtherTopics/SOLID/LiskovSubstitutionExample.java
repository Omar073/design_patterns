// SOLID Principle: Liskov Substitution Principle (LSP)
// Subclasses should be substitutable for their base classes

// ❌ BAD: Square violates LSP - changes expected behavior
class RectangleBad {
    protected int width;
    protected int height;
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getArea() {
        return width * height;
    }
}

// Square violates LSP - changes expected behavior
class SquareBad extends RectangleBad {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;  // Changes height too!
    }
    
    @Override
    public void setHeight(int height) {
        this.width = height;  // Changes width too!
        this.height = height;
    }
}

// ✅ GOOD: Proper substitution - both maintain Shape contract
abstract class Shape {
    public abstract int getArea();
}

class Rectangle extends Shape {
    private int width;
    private int height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public int getArea() {
        return width * height;
    }
}

class Square extends Shape {
    private int side;
    
    public Square(int side) {
        this.side = side;
    }
    
    public void setSide(int side) {
        this.side = side;
    }
    
    @Override
    public int getArea() {
        return side * side;
    }
}

// Demo
public class LiskovSubstitutionExample {
    public static void main(String[] args) {
        System.out.println("=== Liskov Substitution Principle (LSP) ===\n");
        
        System.out.println("❌ BAD: Square changes expected behavior");
        RectangleBad rect = new RectangleBad();
        rect.setWidth(5);
        rect.setHeight(4);
        System.out.println("Rectangle area: " + rect.getArea());  // 20
        
        SquareBad square = new SquareBad();
        square.setWidth(5);
        square.setHeight(4);  // This also changes width to 4!
        System.out.println("Square area: " + square.getArea());  // 16, not 20!
        System.out.println("Problem: Square doesn't behave like Rectangle!");
        
        System.out.println("\n✅ GOOD: Both maintain Shape contract");
        Rectangle rectangle = new Rectangle(5, 4);
        Square squareGood = new Square(5);
        
        System.out.println("Rectangle area: " + rectangle.getArea());  // 20
        System.out.println("Square area: " + squareGood.getArea());  // 25
        
        // Both can be used through Shape interface
        Shape shape1 = rectangle;
        Shape shape2 = squareGood;
        System.out.println("Shape1 (Rectangle) area: " + shape1.getArea());
        System.out.println("Shape2 (Square) area: " + shape2.getArea());
        
        System.out.println("\n--- Key Benefits ---");
        System.out.println("✓ Subclasses maintain expected behavior");
        System.out.println("✓ Can substitute subclasses without breaking code");
        System.out.println("✓ Polymorphism works correctly");
        System.out.println("✓ Follows LSP - substitutable");
    }
}
