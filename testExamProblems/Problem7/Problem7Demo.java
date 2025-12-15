/**
 * Problem 7: Ice-Cream Ordering System
 * Solution: Decorator Pattern
 * 
 * This demo shows how Decorator avoids subclass explosion by
 * dynamically composing ice cream with toppings at runtime.
 *
 * Roles:
 *  - Component: IceCream
 *  - Concrete component: BasicIceCream
 *  - Decorators: ChocolateDecorator, NutsDecorator, CaramelDecorator
 *  - Client: main() stacks decorators to build combinations
 */

// Component interface
interface IceCream {
    String getDescription();
    double getCost();
}

// Concrete component - base ice cream
class BasicIceCream implements IceCream {
    private String flavor;
    
    public BasicIceCream(String flavor) {
        this.flavor = flavor;
    }
    
    public String getDescription() {
        return flavor + " Ice Cream";
    }
    
    public double getCost() {
        return 3.0; // Base price
    }
}

// Decorator abstract class
abstract class IceCreamDecorator implements IceCream {
    protected IceCream iceCream;
    
    public IceCreamDecorator(IceCream iceCream) {
        this.iceCream = iceCream;
    }
    
    public String getDescription() {
        return iceCream.getDescription();
    }
    
    public double getCost() {
        return iceCream.getCost();
    }
}

// Concrete decorators
class ChocolateDecorator extends IceCreamDecorator {
    public ChocolateDecorator(IceCream iceCream) {
        super(iceCream);
    }
    
    public String getDescription() {
        return iceCream.getDescription() + " + Chocolate";
    }
    
    public double getCost() {
        return iceCream.getCost() + 0.5;
    }
}

class NutsDecorator extends IceCreamDecorator {
    public NutsDecorator(IceCream iceCream) {
        super(iceCream);
    }
    
    public String getDescription() {
        return iceCream.getDescription() + " + Nuts";
    }
    
    public double getCost() {
        return iceCream.getCost() + 0.75;
    }
}

class CaramelDecorator extends IceCreamDecorator {
    public CaramelDecorator(IceCream iceCream) {
        super(iceCream);
    }
    
    public String getDescription() {
        return iceCream.getDescription() + " + Caramel";
    }
    
    public double getCost() {
        return iceCream.getCost() + 0.6;
    }
}

// Demo
public class Problem7Demo {
    public static void main(String[] args) {
        System.out.println("=== Problem 7: Ice-Cream Ordering System ===\n");
        
        // Without Decorator: Would need classes like:
        // IceCreamWithChocolate, IceCreamWithNuts, IceCreamWithChocolateAndNuts, etc.
        // That's 2^n combinations! (2^3 = 8 classes for 3 toppings)
        
        // With Decorator: Compose dynamically!
        System.out.println("--- Order 1: Vanilla with Chocolate ---");
        IceCream order1 = new ChocolateDecorator(
            new BasicIceCream("Vanilla")
        );
        System.out.println(order1.getDescription());
        System.out.println("Cost: $" + order1.getCost());
        
        System.out.println("\n--- Order 2: Chocolate with Nuts and Caramel ---");
        IceCream order2 = new CaramelDecorator(
            new NutsDecorator(
                new BasicIceCream("Chocolate")
            )
        );
        System.out.println(order2.getDescription());
        System.out.println("Cost: $" + order2.getCost());
        
        System.out.println("\n--- Order 3: Strawberry with All Toppings ---");
        IceCream order3 = new CaramelDecorator(
            new NutsDecorator(
                new ChocolateDecorator(
                    new BasicIceCream("Strawberry")
                )
            )
        );
        System.out.println(order3.getDescription());
        System.out.println("Cost: $" + order3.getCost());
        
        System.out.println("\n✓ Decorator allows any combination without subclass explosion!");
    }
}

