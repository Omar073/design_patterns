// Builder Pattern – Director-based Builder Example
// Classic director (Waiter) building Palestine coffee/tea drinks

// Product
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

// Abstract builder
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

// Concrete builders
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

// Director
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
        Waiter waiter = new Waiter();
        PalestineDrinkBuilder coffee = new PalestineCoffeeBuilder();
        waiter.setPalestineDrinkBuilder(coffee);
        waiter.construct();
        System.out.println(waiter.getDrink());

        PalestineDrinkBuilder tea = new PalestineTeaBuilder();
        waiter.setPalestineDrinkBuilder(tea);
        waiter.construct();
        System.out.println(waiter.getDrink());
    }
}
