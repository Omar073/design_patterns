// Facade Pattern – Hotel Restaurant Example
// Demonstrates how HotelKeeper facade simplifies access to different restaurant types
// Client doesn't need to know about VegRestaurant, NonVegRestaurant, etc.

// Hotel interface
interface Hotel {
    Menus getMenus();
}

// Menus interface
interface Menus {
    void display();
}

// Menu implementations
class VegMenu implements Menus {
    public void display() {
        System.out.println("Vegetarian Menu");
    }
}

class NonVegMenu implements Menus {
    public void display() {
        System.out.println("Non-Vegetarian Menu");
    }
}

class Both implements Menus {
    public void display() {
        System.out.println("Both Veg and Non-Veg Menu");
    }
}

// Restaurant implementations
class VegRestaurant implements Hotel {
    public Menus getMenus() {
        VegMenu v = new VegMenu();
        return v;
    }
}

class NonVegRestaurant implements Hotel {
    public Menus getMenus() {
        NonVegMenu nv = new NonVegMenu();
        return nv;
    }
}

class VegNonBothRestaurant implements Hotel {
    public Menus getMenus() {
        Both b = new Both();
        return b;
    }
}

// Facade - HotelKeeper
class HotelKeeper {
    public VegMenu getVegMenu() {
        VegRestaurant v = new VegRestaurant();
        VegMenu vegMenu = (VegMenu) v.getMenus();
        return vegMenu;
    }

    public NonVegMenu getNonVegMenu() {
        NonVegRestaurant v = new NonVegRestaurant();
        NonVegMenu nonvegMenu = (NonVegMenu) v.getMenus();
        return nonvegMenu;
    }

    public Both getVegNonMenu() {
        VegNonBothRestaurant v = new VegNonBothRestaurant();
        Both bothMenu = (Both) v.getMenus();
        return bothMenu;
    }
}

// Client
public class HotelFacadeDemo {
    public static void main(String[] args) {
        System.out.println("== Facade Pattern - Hotel Restaurant Example ==\n");

        // Client uses facade - doesn't need to know about restaurant classes
        HotelKeeper keeper = new HotelKeeper();

        System.out.println("--- Getting Vegetarian Menu ---");
        VegMenu v = keeper.getVegMenu();
        v.display();

        System.out.println("\n--- Getting Non-Vegetarian Menu ---");
        NonVegMenu nv = keeper.getNonVegMenu();
        nv.display();

        System.out.println("\n--- Getting Both Menus ---");
        Both both = keeper.getVegNonMenu();
        both.display();

        System.out.println("\n✓ Client doesn't need to know about VegRestaurant, NonVegRestaurant, etc.");
        System.out.println("✓ Facade simplifies access to different restaurant types");
    }
}

