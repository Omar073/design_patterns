/**
 * Problem 5: University Network Access Control
 * Solution: Proxy Pattern
 * 
 * This demo shows how Proxy filters internet access, blocking
 * restricted websites while allowing access to others.
 */

import java.util.ArrayList;
import java.util.List;

// Subject interface
interface Internet {
    void connect(String host);
}

// Real subject - actual internet connection
class RealInternet implements Internet {
    public void connect(String host) {
        System.out.println("Connecting to " + host + "...");
        System.out.println("✓ Connected successfully!");
    }
}

// Proxy - filters access based on restrictions
class UniversityProxy implements Internet {
    private Internet realInternet;
    private List<String> restrictedSites;
    
    public UniversityProxy() {
        this.realInternet = new RealInternet();
        this.restrictedSites = new ArrayList<>();
        // Add restricted sites
        restrictedSites.add("facebook.com");
        restrictedSites.add("twitter.com");
        restrictedSites.add("instagram.com");
    }
    
    public void connect(String host) {
        // Check if site is restricted
        if (isRestricted(host)) {
            System.out.println("Access Denied: " + host + " is blocked by university policy.");
            return;
        }
        
        // Allow access to non-restricted sites
        System.out.println("Proxy checking: " + host);
        realInternet.connect(host);
    }
    
    private boolean isRestricted(String host) {
        for (String restricted : restrictedSites) {
            if (host.toLowerCase().contains(restricted.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}

// Client - student device
class StudentDevice {
    private Internet internet;
    
    public StudentDevice(Internet internet) {
        this.internet = internet;
    }
    
    public void browse(String website) {
        System.out.println("\nStudent trying to access: " + website);
        internet.connect(website);
    }
}

// Demo
public class Problem5Demo {
    public static void main(String[] args) {
        System.out.println("=== Problem 5: University Network Access Control ===\n");
        
        // Student device connects through university proxy
        UniversityProxy proxy = new UniversityProxy();
        StudentDevice device = new StudentDevice(proxy);
        
        // Try to access different sites
        device.browse("google.com");
        device.browse("github.com");
        device.browse("facebook.com");
        device.browse("stackoverflow.com");
        device.browse("twitter.com");
        
        System.out.println("\n✓ Proxy successfully filters access!");
    }
}

