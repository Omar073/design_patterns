// Abstract Factory – create families of related objects without specifying concrete classes
// Demo: Windows/Mac GUI widgets (Button, Checkbox) with runtime factory selection.
// Also shows a direct-instantiation variant (without factory) for contrast.

interface Button { void paint(); }
interface Checkbox { void check(); }

// Windows widgets
class WindowsButton implements Button { public void paint() { System.out.println("Windows button painted"); } }
class WindowsCheckbox implements Checkbox { public void check() { System.out.println("Windows checkbox checked"); } }

// Mac widgets
class MacButton implements Button { public void paint() { System.out.println("Mac button painted"); } }
class MacCheckbox implements Checkbox { public void check() { System.out.println("Mac checkbox checked"); } }

// Abstract factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete factories
class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}
class MacFactory implements GUIFactory {
    public Button createButton() { return new MacButton(); }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}

// Client uses only abstractions
class Application {
    private final Button button;
    private final Checkbox checkbox;
    Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }
    void render() { button.paint(); checkbox.check(); }
}

// Variant without factory (tighter coupling)
class DirectApp {
    private final Button button;
    private final Checkbox checkbox;
    DirectApp(boolean mac) {
        if (mac) { button = new MacButton(); checkbox = new MacCheckbox(); }
        else     { button = new WindowsButton(); checkbox = new WindowsCheckbox(); }
    }
    void render() { button.paint(); checkbox.check(); }
}

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        boolean isMac = System.getProperty("os.name").toLowerCase().contains("mac");
        GUIFactory factory = isMac ? new MacFactory() : new WindowsFactory();
        System.out.println("== With Abstract Factory ==");
        new Application(factory).render();

        System.out.println("\n== Without Abstract Factory (direct) ==");
        new DirectApp(isMac).render();
    }
}


