package Bridge;

// Bridge Pattern – Transport/Engine example
// Demonstrates separating the Transport abstraction hierarchy (Car, Plane)
// from the Engine implementation hierarchy (GasEngine, ElectricEngine).
// This avoids creating classes like GasCar, ElectricCar, GasPlane, ElectricPlane.
// Roles:
//   - Implementor: Engine (GasEngine, ElectricEngine)
//   - Abstraction: Transport with refined variants Car, Plane
//   - Client: main() freely composes any Transport with any Engine

// Implementor
interface Engine {
    void start();
    void move();
}

// Concrete Implementors
class GasEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Gas engine started");
    }

    @Override
    public void move() {
        System.out.println("Gas engine spinning");
    }
}

class ElectricEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Electric engine started");
    }

    @Override
    public void move() {
        System.out.println("Electric engine spinning");
    }
}

// Abstraction
abstract class Transport {
    protected Engine engine;

    protected Transport(Engine engine) {
        this.engine = engine;
        this.engine.start(); // initialization delegated to implementation
    }

    public abstract void drive();
}

// Refined Abstractions
class Car extends Transport {
    public Car(Engine engine) {
        super(engine);
    }

    @Override
    public void drive() {
        engine.move(); // use implementor behavior
        System.out.println("Car is being driven.");
    }
}

class Plane extends Transport {
    public Plane(Engine engine) {
        super(engine);
    }

    @Override
    public void drive() {
        engine.move();
        System.out.println("Plane is flying.");
    }
}

// Client / Demo
public class BridgeTransportDemo {
    public static void main(String[] args) {
        Engine gasEngine = new GasEngine();
        Engine electricEngine = new ElectricEngine();

        // Mix and match abstractions with implementations
        Transport carWithGasEngine = new Car(gasEngine);
        carWithGasEngine.drive();

        System.out.println("----");

        Transport carWithElectricEngine = new Car(electricEngine);
        carWithElectricEngine.drive();

        System.out.println("----");

        Transport planeWithGasEngine = new Plane(gasEngine);
        planeWithGasEngine.drive();

        System.out.println("----");

        Transport planeWithElectricEngine = new Plane(electricEngine);
        planeWithElectricEngine.drive();
    }
}


