// Final Exam 2026 - Question 5 Solution
// Adapter Pattern Implementation

// Target interface (what Client expects)
interface Shape {
    void display(int x1, int y1, int x2, int y2);
}

// Adaptee (legacy class with incompatible interface)
class LegacyRectangle {
    public void display(int x1, int y1, int w, int h) {
        System.out.println("LegacyRectangle: Displaying rectangle at (" + x1 + ", " + y1 +
                ") with width=" + w + ", height=" + h);
    }
}

// Adapter: Adapts LegacyRectangle to Shape interface
class RectangleAdapter implements Shape {
    private LegacyRectangle legacyRectangle;

    public RectangleAdapter(LegacyRectangle legacyRectangle) {
        this.legacyRectangle = legacyRectangle;
    }

    @Override
    public void display(int x1, int y1, int x2, int y2) {
        // Convert (x1, y1, x2, y2) coordinates to (x1, y1, w, h) format
        int w = x2 - x1; // width = x2 - x1
        int h = y2 - y1; // height = y2 - y1

        System.out.println("Adapter: Converting coordinates (" + x1 + ", " + y1 +
                ", " + x2 + ", " + y2 + ") to (" + x1 + ", " + y1 +
                ", w=" + w + ", h=" + h + ")");

        // Delegate to legacy rectangle
        legacyRectangle.display(x1, y1, w, h);
    }
}

// Client
class Client {
    public void drawShape(Shape shape) {
        // Client uses Shape interface with (x1, y1, x2, y2) coordinates
        shape.display(10, 20, 50, 80);
    }
}

// Demo
public class Question5 {
    public static void main(String[] args) {
        System.out.println("=== Final Exam 2026 - Question 5: Adapter Pattern ===\n");

        // Create legacy rectangle (incompatible with Shape interface)
        LegacyRectangle legacyRect = new LegacyRectangle();

        // Adapt it to Shape interface
        Shape adapter = new RectangleAdapter(legacyRect);

        // Client can now use it
        Client client = new Client();

        System.out.println("Client calling display() with Shape interface:");
        client.drawShape(adapter);

        System.out.println("\n--- Pattern Explanation ---");
        System.out.println("Adapter Pattern converts LegacyRectangle interface to Shape interface:");
        System.out.println("- Shape.display(x1, y1, x2, y2) → LegacyRectangle.display(x1, y1, w, h)");
        System.out.println("- Conversion: w = x2 - x1, h = y2 - y1");
    }
}
