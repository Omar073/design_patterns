package Bridge;

// Bridge Pattern – GUI / OS API example
// Demonstrates a Dialog abstraction (SettingsDialog, AlertDialog)
// that delegates drawing to a WindowAPI implementation (WindowsAPI, LinuxAPI).
// Swapping implementations changes the look without changing dialog code.

// Implementor
interface WindowAPI {
    void drawWindow(String title);
    void drawButton(String label);
}

// Concrete Implementors
class WindowsAPI implements WindowAPI {
    @Override
    public void drawWindow(String title) {
        System.out.println("Drawing Windows-style window: " + title);
    }

    @Override
    public void drawButton(String label) {
        System.out.println("Drawing Windows-style button: " + label);
    }
}

class LinuxAPI implements WindowAPI {
    @Override
    public void drawWindow(String title) {
        System.out.println("Drawing Linux-style window: " + title);
    }

    @Override
    public void drawButton(String label) {
        System.out.println("Drawing Linux-style button: " + label);
    }
}

// Abstraction
abstract class Dialog {
    protected WindowAPI windowAPI;

    protected Dialog(WindowAPI windowAPI) {
        this.windowAPI = windowAPI;
    }

    public abstract void render(String title);
}

// Refined Abstractions
class SettingsDialog extends Dialog {
    public SettingsDialog(WindowAPI windowAPI) {
        super(windowAPI);
    }

    @Override
    public void render(String title) {
        windowAPI.drawWindow(title);
        windowAPI.drawButton("Save");
        windowAPI.drawButton("Cancel");
    }
}

class AlertDialog extends Dialog {
    public AlertDialog(WindowAPI windowAPI) {
        super(windowAPI);
    }

    @Override
    public void render(String title) {
        windowAPI.drawWindow(title);
        windowAPI.drawButton("OK");
    }
}

// Client / Demo
public class BridgeGuiApiDemo {
    public static void main(String[] args) {
        WindowAPI windowsApi = new WindowsAPI();
        WindowAPI linuxApi = new LinuxAPI();

        Dialog windowsSettings = new SettingsDialog(windowsApi);
        Dialog linuxSettings = new SettingsDialog(linuxApi);

        Dialog windowsAlert = new AlertDialog(windowsApi);
        Dialog linuxAlert = new AlertDialog(linuxApi);

        windowsSettings.render("Windows Settings");
        System.out.println("----");
        linuxSettings.render("Linux Settings");
        System.out.println("----");
        windowsAlert.render("Windows Alert");
        System.out.println("----");
        linuxAlert.render("Linux Alert");
    }
}


