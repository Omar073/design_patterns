// Adapter Pattern – Charger Adapter Example
// Demonstrates adapting Micro USB charger to work with Type C interface
// Shows how adapter converts one interface to another
// Roles mapping:
//   - Target: IChargerTypeC expected by the phone
//   - Adaptee: ChargerMicroUSB provides chargeMicroUSB()
//   - Adapter: ChargerAdapter implements target and forwards to adaptee
//   - Client: main() can treat the adaptee as a Type C charger

// ChargerTypeC.java - Target interface
interface IChargerTypeC {
    void chargeTypeC();
}

// ChargerTypeC.java - Concrete Target class
class ChargerTypeC implements IChargerTypeC {
    @Override
    public void chargeTypeC() {
        System.out.println("Charging using Type C.");
    }
}

// ChargerMicroUSB.java - Adaptee class
class ChargerMicroUSB {
    public void chargeMicroUSB() {
        System.out.println("Charging using Micro USB.");
    }
}

// ChargerAdapter.java - Adapter class
class ChargerAdapter implements IChargerTypeC {
    private ChargerMicroUSB chargerMicroUSB;

    public ChargerAdapter(ChargerMicroUSB chargerMicroUSB) {
        this.chargerMicroUSB = chargerMicroUSB;
    }

    @Override
    public void chargeTypeC() {
        // Translate the Type C request into a Micro USB call
        System.out.println("Converting Type C charging to Micro USB charging.");
        chargerMicroUSB.chargeMicroUSB();
    }
}

// Main class
public class ChargerAdapterDemo {
    public static void main(String[] args) {
        System.out.println("== Adapter Pattern - Charger Example ==\n");

        // Charging using the Type C charger
        System.out.println("--- Direct Type C Charging ---");
        IChargerTypeC typeCCharger = new ChargerTypeC();
        typeCCharger.chargeTypeC();

        // Charging using the Micro USB charger with the help of the adapter
        System.out.println("\n--- Micro USB Charging via Adapter ---");
        ChargerMicroUSB microUSBCharger = new ChargerMicroUSB();
        // Wrap the Micro USB charger so the client still sees IChargerTypeC
        IChargerTypeC adapter = new ChargerAdapter(microUSBCharger);
        adapter.chargeTypeC();

        System.out.println("\n✓ Adapter converts Micro USB interface to Type C interface");
        System.out.println("✓ Client can use Micro USB charger through Type C interface");
    }
}
