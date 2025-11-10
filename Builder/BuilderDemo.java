// Builder Pattern – fluent builder and classic director-based variant
// This demo shows:
// - Fluent inner static builder for immutable Car
// - Classic director (Waiter) building Starbucks drinks
// - Telescoping constructor variant for contrast

// Fluent builder product
class Car {
    private final String engine;
    private final int wheels;
    private final String color;
    private final boolean sunroof;
    private final boolean gps;

    private Car(Builder b) {
        this.engine = b.engine;
        this.wheels = b.wheels;
        this.color = b.color;
        this.sunroof = b.sunroof;
        this.gps = b.gps;
    }
    public String toString() {
        return "Car{engine=" + engine + ", wheels=" + wheels + ", color=" + color +
               ", sunroof=" + sunroof + ", gps=" + gps + "}";
    }
    public static class Builder {
        private final String engine;
        private final int wheels;
        private String color = "white";
        private boolean sunroof = false;
        private boolean gps = false;
        public Builder(String engine, int wheels) { this.engine = engine; this.wheels = wheels; }
        public Builder color(String c) { this.color = c; return this; }
        public Builder sunroof(boolean s) { this.sunroof = s; return this; }
        public Builder gps(boolean g) { this.gps = g; return this; }
        public Car build() { return new Car(this); }
    }
}

// Classic director variant
class Starbucks {
    private String size;
    private String drink;
    public void setSize(String s) { this.size = s; }
    public void setDrink(String d) { this.drink = d; }
    public String toString() { return "Starbucks[size=" + size + ", drink=" + drink + "]"; }
}

abstract class StarbucksBuilder {
    protected Starbucks starbucks;
    public void createStarbucks() { starbucks = new Starbucks(); }
    public Starbucks getStarbucks() { return starbucks; }
    public abstract void buildSize();
    public abstract void buildDrink();
}

class TeaBuilder extends StarbucksBuilder {
    public void buildSize() { starbucks.setSize("large"); }
    public void buildDrink() { starbucks.setDrink("tea"); }
}

class CoffeeBuilder extends StarbucksBuilder {
    public void buildSize() { starbucks.setSize("medium"); }
    public void buildDrink() { starbucks.setDrink("coffee"); }
}

class Waiter {
    private StarbucksBuilder builder;
    public void setStarbucksBuilder(StarbucksBuilder b) { this.builder = b; }
    public Starbucks getDrink() { return builder.getStarbucks(); }
    public void construct() {
        builder.createStarbucks();
        builder.buildSize();
        builder.buildDrink();
    }
}

// Telescoping contrast (less readable)
class CarTelescoping {
    private final String engine; private final int wheels;
    private final String color; private final boolean sunroof; private final boolean gps;
    public CarTelescoping(String engine, int wheels) { this(engine, wheels, "white", false, false); }
    public CarTelescoping(String engine, int wheels, String color) { this(engine, wheels, color, false, false); }
    public CarTelescoping(String engine, int wheels, String color, boolean sunroof) { this(engine, wheels, color, sunroof, false); }
    public CarTelescoping(String engine, int wheels, String color, boolean sunroof, boolean gps) {
        this.engine = engine; this.wheels = wheels; this.color = color; this.sunroof = sunroof; this.gps = gps;
    }
    public String toString() {
        return "CarTelescoping{engine=" + engine + ", wheels=" + wheels + ", color=" + color +
               ", sunroof=" + sunroof + ", gps=" + gps + "}";
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        System.out.println("== Fluent Builder ==");
        Car basic = new Car.Builder("V6", 4).build();
        Car luxury = new Car.Builder("V8", 4).color("black").sunroof(true).gps(true).build();
        System.out.println(basic);
        System.out.println(luxury);

        System.out.println("\n== Director-based Builder ==");
        Waiter waiter = new Waiter();
        StarbucksBuilder coffee = new CoffeeBuilder();
        waiter.setStarbucksBuilder(coffee);
        waiter.construct();
        System.out.println(waiter.getDrink());

        System.out.println("\n== Telescoping Constructors (contrast) ==");
        System.out.println(new CarTelescoping("V6", 4));
        System.out.println(new CarTelescoping("V8", 4, "black", true, true));
    }
}


