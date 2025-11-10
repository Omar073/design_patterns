// Builder Pattern – Fluent Builder Example
// Demonstrates a fluent inner static builder for immutable Car objects

// Fluent builder product
class Car {
    private final String engine;
    private final int wheels;
    private final String color;
    private final boolean hasSunroof;
    private final boolean hasGPS;

    private Car(Builder b) {
        this.engine = b.engine;
        this.wheels = b.wheels;
        this.color = b.color;
        this.hasSunroof = b.hasSunroof;
        this.hasGPS = b.hasGPS;
    }

    public String toString() {
        return "Car{engine=" + engine + ", wheels=" + wheels + ", color=" + color +
                ", hasSunroof=" + hasSunroof + ", hasGPS=" + hasGPS + "}";
    }

    public static class Builder {
        // Required parameters
        private final String engine;
        private final int wheels;

        // Optional parameters (with defaults)
        private String color = "white";
        private boolean hasSunroof = false;
        private boolean hasGPS = false;

        // Constructor with required parameters
        public Builder(String engine, int wheels) {
            this.engine = engine;
            this.wheels = wheels;
        }

        public Builder color(String c) {
            this.color = c;
            return this;
        }

        public Builder hasSunroof(boolean s) {
            this.hasSunroof = s;
            return this;
        }

        public Builder hasGPS(boolean g) {
            this.hasGPS = g;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

public class CarBuilderDemo {
    public static void main(String[] args) {
        System.out.println("== Fluent Builder ==");

        // Basic car with required parameters only (uses defaults for optional)
        Car basic = new Car.Builder("V6", 4).build();
        System.out.println("Basic: " + basic);

        // Custom car with all parameters
        Car luxury = new Car.Builder("V8", 4)
                .color("black")
                .hasSunroof(true)
                .hasGPS(true)
                .build();
        System.out.println("Luxury: " + luxury);

        // Car with some optional parameters
        Car sporty = new Car.Builder("V8", 4)
                .color("red")
                .hasGPS(true)
                .build();
        System.out.println("Sporty: " + sporty);
    }
}
