// Builder Pattern – Fluent Builder Example
// Demonstrates a fluent inner static builder for immutable Car objects

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

        public Builder(String engine, int wheels) {
            this.engine = engine;
            this.wheels = wheels;
        }

        public Builder color(String c) {
            this.color = c;
            return this;
        }

        public Builder sunroof(boolean s) {
            this.sunroof = s;
            return this;
        }

        public Builder gps(boolean g) {
            this.gps = g;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

public class FluentBuilderDemo {
    public static void main(String[] args) {
        System.out.println("== Fluent Builder ==");
        Car basic = new Car.Builder("V6", 4).build();
        Car luxury = new Car.Builder("V8", 4).color("black").sunroof(true).gps(true).build();
        System.out.println(basic);
        System.out.println(luxury);
    }
}

