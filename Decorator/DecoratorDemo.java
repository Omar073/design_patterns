// Decorator Pattern – compose responsibilities dynamically
// Coffee example with condiments; parallels Java I/O decorators

interface Coffee {
    double cost();

    String description();
}

class SimpleCoffee implements Coffee {
    public double cost() {
        return 2.0;
    }

    public String description() {
        return "Simple coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected final Coffee delegate;

    protected CoffeeDecorator(Coffee coffee) {
        this.delegate = coffee;
    }
}

class Milk extends CoffeeDecorator {
    public Milk(Coffee c) {
        super(c);
    }

    public double cost() {
        return delegate.cost() + 0.5;
    }

    public String description() {
        return delegate.description() + ", milk";
    }
}

class Sugar extends CoffeeDecorator {
    public Sugar(Coffee c) {
        super(c);
    }

    public double cost() {
        return delegate.cost() + 0.2;
    }

    public String description() {
        return delegate.description() + ", sugar";
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        System.out.println("== Decorator ==");
        Coffee coffee = new SimpleCoffee();
        coffee = new Milk(coffee);
        coffee = new Sugar(coffee);
        System.out.println(coffee.description() + " -> $" + coffee.cost());

        System.out.println("\n== Java I/O analogy ==");
        System.out.println("new BufferedInputStream(new FileInputStream(path)) // decorators stack");
    }
}
