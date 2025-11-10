// Proxy Pattern – protection, virtual, and logging proxies
// 1) Protection proxy: college internet blocks some hosts
// 2) Virtual proxy: lazy-load heavy image
// 3) Logging proxy: dynamic proxy adding cross-cutting logging

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

// 1) Protection proxy
interface Internet { void connect(String host); }
class RealInternet implements Internet {
    public void connect(String host) { System.out.println("Connecting to " + host); }
}
class ProtectedInternetProxy implements Internet {
    private final Internet real = new RealInternet();
    private static final Set<String> banned = new HashSet<>();
    static {
        banned.add("bad.com");
        banned.add("games.example");
    }
    public void connect(String host) {
        if (banned.contains(host)) {
            System.out.println("Access denied to " + host);
        } else {
            real.connect(host);
        }
    }
}

// 2) Virtual proxy
interface Image { void display(); }
class RealImage implements Image {
    private final String filename;
    RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    private void loadFromDisk() {
        System.out.println("Loading heavy image from disk: " + filename);
    }
    public void display() { System.out.println("Displaying " + filename); }
}
class ProxyImage implements Image {
    private final String filename;
    private RealImage real; // lazy
    ProxyImage(String filename) { this.filename = filename; }
    public void display() {
        if (real == null) real = new RealImage(filename);
        real.display();
    }
}

// 3) Logging proxy via JDK dynamic proxies (requires interfaces)
interface Service { void doWork(); String say(String name); }
class RealService implements Service {
    public void doWork() { System.out.println("Doing work..."); }
    public String say(String name) { return "Hello, " + name; }
}
class LoggingHandler implements InvocationHandler {
    private final Object target;
    LoggingHandler(Object target) { this.target = target; }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[LOG] Calling " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("[LOG] Done " + method.getName());
        return result;
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        System.out.println("== Protection Proxy ==");
        Internet internet = new ProtectedInternetProxy();
        internet.connect("example.com");
        internet.connect("bad.com"); // blocked

        System.out.println("\n== Virtual Proxy ==");
        Image img = new ProxyImage("photo.png"); // not loaded yet
        img.display(); // loads then displays
        img.display(); // cached

        System.out.println("\n== Logging (Dynamic) Proxy ==");
        Service real = new RealService();
        Service logged = (Service) Proxy.newProxyInstance(
                Service.class.getClassLoader(),
                new Class<?>[]{Service.class},
                new LoggingHandler(real));
        logged.doWork();
        System.out.println(logged.say("Omar"));
    }
}


