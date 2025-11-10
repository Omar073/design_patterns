// Builder Pattern – Telescoping Constructor Example (Contrast)
// Demonstrates the problem with telescoping constructors (less readable alternative to Builder)

// Telescoping contrast (less readable)
class CarTelescoping {
    private final String engine;
    private final int wheels;
    private final String color;
    private final boolean sunroof;
    private final boolean gps;

    public CarTelescoping(String engine, int wheels) {
        this(engine, wheels, "white", false, false);
    }

    public CarTelescoping(String engine, int wheels, String color) {
        this(engine, wheels, color, false, false);
    }

    public CarTelescoping(String engine, int wheels, String color, boolean sunroof) {
        this(engine, wheels, color, sunroof, false);
    }

    public CarTelescoping(String engine, int wheels, String color, boolean sunroof, boolean gps) {
        this.engine = engine;
        this.wheels = wheels;
        this.color = color;
        this.sunroof = sunroof;
        this.gps = gps;
    }

    public String toString() {
        return "CarTelescoping{engine=" + engine + ", wheels=" + wheels + ", color=" + color +
                ", sunroof=" + sunroof + ", gps=" + gps + "}";
    }
}

public class TelescopingConstructorDemo {
    public static void main(String[] args) {
        System.out.println("== Telescoping Constructors (contrast) ==");
        System.out.println(new CarTelescoping("V6", 4));
        System.out.println(new CarTelescoping("V8", 4, "black", true, true));
        System.out.println("\nNote: This approach is less readable and harder to maintain than using Builder pattern.");
    }
}

