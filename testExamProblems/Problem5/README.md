# Problem 5: University Network Access Control

## Problem Statement

A university network restricts access to specific websites. Each student device connects through a central proxy that filters requests.

## Design Pattern Solution: Proxy

### Why Proxy?

The Proxy pattern is the ideal solution because:

1. **Access Control**: The proxy intercepts requests and checks if they're allowed
2. **Protection**: Acts as a gatekeeper, protecting the network from restricted sites
3. **Transparent**: Students use the internet normally, but requests go through the proxy
4. **Centralized Control**: All filtering logic is in one place (the proxy)

### The Problem: Direct Access Without Control

Without Proxy, students would access the internet directly:

```java
// Problem: Direct access - no control
class RealInternet {
    public void connect(String host) {
        System.out.println("Connecting to " + host + "...");
        // No filtering - all sites accessible!
    }
}

// Student device connects directly
class StudentDevice {
    private RealInternet internet = new RealInternet();
    
    public void browse(String website) {
        internet.connect(website);  // No restrictions!
    }
}

// Problem: Students can access any website
StudentDevice device = new StudentDevice();
device.browse("facebook.com");  // Allowed (should be blocked!)
device.browse("twitter.com");   // Allowed (should be blocked!)
```

**Problems with direct access:**
- ❌ **No access control**: All websites are accessible
- ❌ **No filtering**: Can't restrict specific sites
- ❌ **No logging**: Can't track access attempts
- ❌ **No security**: No protection against malicious sites
- ❌ **Distributed logic**: Would need to add checks in every client

### The Solution: Proxy Pattern

The Proxy pattern solves these problems by:
- ✅ **Access control**: Proxy intercepts and filters requests
- ✅ **Protection**: Blocks restricted sites before reaching real internet
- ✅ **Transparent**: Clients use the same interface, unaware of proxy
- ✅ **Centralized**: All filtering logic in one place
- ✅ **Logging**: Can log all access attempts

### Solution Overview

- **Subject Interface**: `Internet` interface with `connect()` method
- **Real Subject**: `RealInternet` that provides actual internet access
- **Proxy**: `UniversityProxy` that intercepts requests, checks restrictions, and forwards allowed requests
- **Client**: Student devices that connect through the proxy

The proxy checks each request against a restricted sites list before allowing access to the real internet. If a site is restricted, the proxy blocks it; otherwise, it forwards the request to the real internet.

### Key Benefits

- ✅ Centralized access control
- ✅ Transparent to clients (same interface)
- ✅ Easy to update restrictions
- ✅ Can log all access attempts
- ✅ Protects network from restricted sites
- ✅ Single point of control

### Comparison: Without vs With Proxy

| Aspect | Without Proxy | With Proxy |
|--------|---------------|------------|
| **Access Control** | ❌ No filtering | ✅ Filters requests |
| **Security** | ❌ All sites accessible | ✅ Blocks restricted sites |
| **Logging** | ❌ No tracking | ✅ Can log all attempts |
| **Centralization** | ❌ Logic scattered | ✅ Centralized control |
| **Transparency** | N/A | ✅ Transparent to clients |

## Reference

For more details on the Proxy pattern, see: [../../Proxy/README.md](../../Proxy/README.md)

