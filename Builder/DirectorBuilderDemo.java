// Builder Pattern – Director-based Builder Example
// Classic Director (Waiter) building different Palestine drinks (coffee / tea)
// This example matches the textbook Builder structure:
//   - Product:       PalestineDrink
//   - Builder:       PalestineDrinkBuilder
//   - ConcreteBuilder: PalestineTeaBuilder / PalestineCoffeeBuilder
//   - Director:      Waiter orchestrates the building steps

// Product: the complex object being built step‑by‑step
class PalestineDrink {
    private String size;
    private String drink;

    public void setSize(String s) {
        this.size = s;
    }

    public void setDrink(String d) {
        this.drink = d;
    }

    public String toString() {
        return "PalestineDrink[size=" + size + ", drink=" + drink + "]";
    }
}

// Abstract Builder: declares building steps for the product
abstract class PalestineDrinkBuilder {
    protected PalestineDrink palestineDrink;

    public void createPalestineDrink() {
        palestineDrink = new PalestineDrink();
    }

    public PalestineDrink getPalestineDrink() {
        return palestineDrink;
    }

    public abstract void buildSize();

    public abstract void buildDrink();
}

// Concrete Builders: provide specific implementations of the steps
class PalestineTeaBuilder extends PalestineDrinkBuilder {
    public void buildSize() {
        palestineDrink.setSize("large");
    }

    public void buildDrink() {
        palestineDrink.setDrink("tea");
    }
}

class PalestineCoffeeBuilder extends PalestineDrinkBuilder {
    public void buildSize() {
        palestineDrink.setSize("medium");
    }

    public void buildDrink() {
        palestineDrink.setDrink("coffee");
    }
}

// Director: knows *how* to build a drink (the sequence of steps),
// but not *which* concrete drink it is – that depends on the builder.
class Waiter {
    private PalestineDrinkBuilder builder;

    public void setPalestineDrinkBuilder(PalestineDrinkBuilder b) {
        this.builder = b;
    }

    public PalestineDrink getDrink() {
        return builder.getPalestineDrink();
    }

    public void construct() {
        builder.createPalestineDrink();
        builder.buildSize();
        builder.buildDrink();
    }
}

public class DirectorBuilderDemo {
    public static void main(String[] args) {
        System.out.println("== Director-based Builder ==");

        // Build a coffee using the coffee builder
        Waiter waiter = new Waiter();
        PalestineDrinkBuilder coffeeBuilder = new PalestineCoffeeBuilder();
        waiter.setPalestineDrinkBuilder(coffeeBuilder);
        waiter.construct();
        System.out.println(waiter.getDrink());

        // Reuse the same Director with a different builder to build tea
        PalestineDrinkBuilder teaBuilder = new PalestineTeaBuilder();
        waiter.setPalestineDrinkBuilder(teaBuilder);
        waiter.construct();
        System.out.println(waiter.getDrink());
    }
}
