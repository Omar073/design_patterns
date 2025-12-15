// Proxy Pattern – Protection Proxy Example
// College internet blocks access to certain banned hosts
// Roles:
//   - Subject: Internet
//   - RealSubject: RealInternet
//   - Proxy: ProtectedInternetProxy filters calls based on banned list

import java.util.HashSet;
import java.util.Set;

interface Internet {
    void connect(String host);
}

class RealInternet implements Internet {
    public void connect(String host) {
        System.out.println("Connecting to " + host);
    }
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

public class ProtectionProxyDemo {
    public static void main(String[] args) {
        System.out.println("== Protection Proxy ==");
        Internet internet = new ProtectedInternetProxy();
        internet.connect("example.com");
        internet.connect("bad.com"); // blocked
        internet.connect("games.example"); // blocked
    }
}

