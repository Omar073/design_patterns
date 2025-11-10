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

### Solution Overview

- **Subject Interface**: `Internet` interface with `connect()` method
- **Real Subject**: `RealInternet` that provides actual internet access
- **Proxy**: `UniversityProxy` that intercepts requests, checks restrictions, and forwards allowed requests
- **Client**: Student devices that connect through the proxy

The proxy checks each request against a restricted sites list before allowing access to the real internet.

### Key Benefits

- Centralized access control
- Transparent to clients
- Easy to update restrictions
- Can log all access attempts

## Reference

For more details on the Proxy pattern, see: [../../Proxy/README.md](../../Proxy/README.md)

