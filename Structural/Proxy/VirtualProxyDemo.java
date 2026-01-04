// Proxy Pattern – Virtual Proxy Example
// Lazy-loading heavy image - only loads from disk when actually needed
// Roles:
//   - Subject: Image
//   - RealSubject: RealImage (expensive load)
//   - Proxy: ProxyImage defers creation until display() is invoked

interface Image {
    void display();
}

class RealImage implements Image {
    private final String filename;

    RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading heavy image from disk: " + filename);
    }

    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private final String filename;
    private RealImage real; // lazy

    ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (real == null) {
            real = new RealImage(filename);
        }
        real.display();
    }
}

public class VirtualProxyDemo {
    public static void main(String[] args) {
        System.out.println("== Virtual Proxy ==");
        Image img = new ProxyImage("photo.png"); // not loaded yet
        System.out.println("Image created, but not loaded from disk yet...");
        img.display(); // loads then displays
        img.display(); // cached, no reload
    }
}

