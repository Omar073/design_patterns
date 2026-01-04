// Factory Method – define an interface for creating an object, but let subclasses decide which class to instantiate
// Demo: Different types of dialogs (Windows/Mac) that create platform-specific buttons.
// The factory method is implemented in subclasses to create the appropriate button type.
// Roles:
//   - Product: Button (WindowsButton, MacButton)
//   - Creator: Dialog declares factory method createButton()
//   - Concrete creators: WindowsDialog, MacDialog override createButton()
//   - Template method render() uses the factory method so subclasses decide the product
// Note: DirectDialog shows the if/else branching that Factory Method removes.

// Product interface
interface Button {
    void paint();
    void onClick();
}

// Concrete products
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering Windows-style button");
    }

    public void onClick() {
        System.out.println("Windows button clicked");
    }
}

class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering Mac-style button");
    }

    public void onClick() {
        System.out.println("Mac button clicked");
    }
}

// Creator abstract class with factory method
abstract class Dialog {
    // Factory method - subclasses will implement this
    abstract Button createButton();

    // Template method that uses the factory method
    void render() {
        System.out.println("Rendering dialog...");
        Button button = createButton();  // Factory method call
        button.paint();
        System.out.println("Dialog rendered with button");
    }

    void simulateClick() {
        Button button = createButton(); // each call can return proper subtype
        button.onClick();
    }
}

// Concrete creators that implement the factory method
class WindowsDialog extends Dialog {
    // Factory method implementation
    Button createButton() {
        return new WindowsButton();
    }
}

class MacDialog extends Dialog {
    // Factory method implementation
    Button createButton() {
        return new MacButton();
    }
}

// Variant without factory method (tighter coupling)
class DirectDialog {
    private Button button;

    DirectDialog(boolean isMac) {
        // Direct instantiation - violates Open/Closed Principle
        if (isMac) {
            button = new MacButton();
        } else {
            button = new WindowsButton();
        }
    }

    void render() {
        System.out.println("Rendering dialog...");
        button.paint();
        System.out.println("Dialog rendered with button");
    }
}

public class FactoryMethodDemo {
    public static void main(String[] args) {
        boolean isMac = System.getProperty("os.name").toLowerCase().contains("mac");

        System.out.println("== With Factory Method ==");
        Dialog dialog = isMac ? new MacDialog() : new WindowsDialog();
        dialog.render();
        dialog.simulateClick();

        System.out.println("\n== Without Factory Method (direct) ==");
        DirectDialog directDialog = new DirectDialog(isMac);
        directDialog.render();
    }
}

