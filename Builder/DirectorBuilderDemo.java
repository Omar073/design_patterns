// Builder Pattern – Director-based Builder Example
// Classic director (Waiter) building Palestine coffee/tea drinks

// Product
class PalestineCoffee {
    private String size;
    private String drink;

    public void setSize(String s) {
        this.size = s;
    }

    public void setDrink(String d) {
        this.drink = d;
    }

    public String toString() {
        return "PalestineCoffee[size=" + size + ", drink=" + drink + "]";
    }
}

// Abstract builder
abstract class PalestineCoffeeBuilder {
    protected PalestineCoffee palestineCoffee;

    public void createPalestineCoffee() {
        palestineCoffee = new PalestineCoffee();
    }

    public PalestineCoffee getPalestineCoffee() {
        return palestineCoffee;
    }

    public abstract void buildSize();

    public abstract void buildDrink();
}

// Concrete builders
class PalestineTeaBuilder extends PalestineCoffeeBuilder {
    public void buildSize() {
        palestineCoffee.setSize("large");
    }

    public void buildDrink() {
        palestineCoffee.setDrink("tea");
    }
}

class PalestineCoffeeDrinkBuilder extends PalestineCoffeeBuilder {
    public void buildSize() {
        palestineCoffee.setSize("medium");
    }

    public void buildDrink() {
        palestineCoffee.setDrink("coffee");
    }
}

// Director
class Waiter {
    private PalestineCoffeeBuilder builder;

    public void setPalestineCoffeeBuilder(PalestineCoffeeBuilder b) {
        this.builder = b;
    }

    public PalestineCoffee getDrink() {
        return builder.getPalestineCoffee();
    }

    public void construct() {
        builder.createPalestineCoffee();
        builder.buildSize();
        builder.buildDrink();
    }
}

public class DirectorBuilderDemo {
    public static void main(String[] args) {
        System.out.println("== Director-based Builder ==");
        Waiter waiter = new Waiter();
        PalestineCoffeeBuilder coffee = new PalestineCoffeeDrinkBuilder();
        waiter.setPalestineCoffeeBuilder(coffee);
        waiter.construct();
        System.out.println(waiter.getDrink());

        PalestineCoffeeBuilder tea = new PalestineTeaBuilder();
        waiter.setPalestineCoffeeBuilder(tea);
        waiter.construct();
        System.out.println(waiter.getDrink());
    }
}

