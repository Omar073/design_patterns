// Composite Pattern – Restaurant Menu Example
// Composes objects into tree structures to represent part-whole hierarchies
// Roles:
//   - Component: MenuComponent abstract class defining common interface
//   - Leaf: MenuItem represents individual menu items
//   - Composite: Menu represents menus that can contain MenuItems or other Menus
//   - Client: Waitress uses MenuComponent interface uniformly

import java.util.ArrayList;
import java.util.Iterator;

/**
 * COMPONENT (Abstract Class)
 * Defines the interface for all objects in the composition (both composite and leaf nodes).
 * Provides default implementations that throw UnsupportedOperationException.
 * 
 * Key Point: Some methods only make sense for MenuItems (like getPrice()),
 * and some only make sense for Menus (like add()). The default implementation
 * throws an exception, allowing each subclass to override only what makes sense.
 */
abstract class MenuComponent {
    // Composite methods - for managing children
    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    // Operation methods - for leaf nodes
    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    // Operation method for both Menu and MenuItem
    public void print() {
        throw new UnsupportedOperationException();
    }
}

/**
 * LEAF: MenuItem
 * Represents individual menu items that have no children.
 * 
 * Key Points:
 * 1. Overrides methods that make sense (getName, getDescription, getPrice, isVegetarian, print)
 * 2. Inherits add(), remove(), getChild() which don't make sense - they'll throw exceptions
 * 3. This is a leaf node - it cannot have children
 */
class MenuItem extends MenuComponent {
    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void print() {
        System.out.print("  " + getName());
        if (isVegetarian()) {
            System.out.print(" (v)");
        }
        System.out.println(", " + getPrice());
        System.out.println("     -- " + getDescription());
    }
}

/**
 * COMPOSITE: Menu
 * Represents menus that can contain MenuItems or other Menus (submenus).
 * 
 * Key Points:
 * 1. Stores a collection of MenuComponent objects (can be MenuItem or Menu)
 * 2. Implements add(), remove(), getChild() to manage children
 * 3. Implements getName(), getDescription() for menu information
 * 4. Implements print() which recursively calls print() on all children
 * 5. Does NOT override getPrice() or isVegetarian() - they don't make sense for menus
 */
class Menu extends MenuComponent {
    ArrayList<MenuComponent> menuComponents = new ArrayList<>();
    String name;
    String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void print() {
        System.out.print("\n" + getName());
        System.out.println(", " + getDescription());
        System.out.println("---------------------");

        Iterator<MenuComponent> iterator = menuComponents.iterator();
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            menuComponent.print();  // Recursive call - works for Menu or MenuItem
        }
    }
}

/**
 * CLIENT: Waitress
 * 
 * Key Points:
 * 1. Uses MenuComponent interface to manipulate objects
 * 2. Doesn't need to know if it's dealing with Menu or MenuItem
 * 3. Treats individual objects and compositions uniformly
 * 4. Can print entire menu hierarchy with a single call
 */
class Waitress {
    MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        allMenus.print();  // Works for entire menu hierarchy
    }
}

/**
 * CLIENT: CompositeMenuDemo
 * 
 * Demonstrates the Composite pattern by:
 * 1. Creating menu items (leaves)
 * 2. Creating menus (composites)
 * 3. Building a tree structure (menus containing items and other menus)
 * 4. Using Waitress to print entire hierarchy uniformly
 */
public class CompositeMenuDemo {
    public static void main(String[] args) {
        System.out.println("=== Composite Pattern Demo: Restaurant Menu System ===\n");

        // Create menus (composites)
        MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
        MenuComponent dinerMenu = new Menu("DINER MENU", "Lunch");
        MenuComponent cafeMenu = new Menu("CAFE MENU", "Dinner");
        MenuComponent dessertMenu = new Menu("DESSERT MENU", "Dessert of course!");

        // Create top-level menu
        MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");

        // Add menus to top-level menu
        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);

        // Add menu items to Pancake House Menu
        pancakeHouseMenu.add(new MenuItem(
            "K&B's Pancake Breakfast",
            "Pancakes with scrambled eggs, and toast",
            true,
            2.99));

        pancakeHouseMenu.add(new MenuItem(
            "Regular Pancake Breakfast",
            "Pancakes with fried eggs, sausage",
            false,
            2.99));

        pancakeHouseMenu.add(new MenuItem(
            "Blueberry Pancakes",
            "Pancakes made with fresh blueberries",
            true,
            3.49));

        // Add menu items to Diner Menu
        dinerMenu.add(new MenuItem(
            "Vegetarian BLT",
            "(Fakin') Bacon with lettuce & tomato on whole wheat",
            true,
            2.99));

        dinerMenu.add(new MenuItem(
            "BLT",
            "Bacon with lettuce & tomato on whole wheat",
            false,
            2.99));

        dinerMenu.add(new MenuItem(
            "Soup of the day",
            "Soup of the day, with a side of potato salad",
            false,
            3.29));

        dinerMenu.add(new MenuItem(
            "Hotdog",
            "A hot dog, with saurkraut, relish, onions, topped with cheese",
            false,
            3.05));

        // Add dessert menu to diner menu (menu containing menu!)
        dinerMenu.add(dessertMenu);

        // Add items to dessert menu
        dessertMenu.add(new MenuItem(
            "Apple Pie",
            "Apple pie with a flakey crust, topped with vanilla icecream",
            true,
            1.59));

        dessertMenu.add(new MenuItem(
            "Cheesecake",
            "Creamy New York cheesecake, with a chocolate graham crust",
            true,
            1.99));

        dessertMenu.add(new MenuItem(
            "Sorbet",
            "A scoop of raspberry and a scoop of lime",
            true,
            1.89));

        // Add menu items to Cafe Menu
        cafeMenu.add(new MenuItem(
            "Veggie Burger and Air Fries",
            "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
            true,
            3.99));

        cafeMenu.add(new MenuItem(
            "Soup of the day",
            "A cup of the soup of the day, with a side salad",
            false,
            3.69));

        cafeMenu.add(new MenuItem(
            "Burrito",
            "A large burrito, with whole pinto beans, salsa, guacamole",
            true,
            4.29));

        // Create waitress and print entire menu hierarchy
        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();

        // SUMMARY: What we've demonstrated
        System.out.println("\n--- Composite Pattern Benefits ---");
        System.out.println("✓ Individual objects (MenuItem) and compositions (Menu) treated uniformly");
        System.out.println("✓ Menus can contain other menus (recursive composition)");
        System.out.println("✓ Client (Waitress) doesn't need to know about Menu vs MenuItem");
        System.out.println("✓ Operations work recursively on entire tree structure");
        System.out.println("✓ Easy to add new menu items or menus without changing client code");

        // KEY TAKEAWAYS:
        System.out.println("\n--- Key Takeaways ---");
        System.out.println("1. MenuComponent provides uniform interface for Menu and MenuItem");
        System.out.println("2. Menu can contain MenuItems OR other Menus (submenus)");
        System.out.println("3. Waitress.printMenu() works on entire hierarchy with one call");
        System.out.println("4. Default implementations throw UnsupportedOperationException");
        System.out.println("5. Each class overrides only methods that make sense");
    }
}
