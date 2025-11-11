# Problem 2: Web-Based Logging System

## Problem Statement

A web-based logging system must maintain a single log file across the entire application. Multiple components try to create their own log instances, leading to inconsistent file writes.

## Design Pattern Solution: Singleton

### Why Singleton?

The Singleton pattern is the perfect solution because:

1. **Single Instance Requirement**: We need exactly one logger instance across the entire application
2. **Global Access**: All components need access to the same logger instance
3. **Resource Management**: Prevents multiple file handles and ensures consistent logging
4. **Thread Safety**: In a web application, multiple threads must safely access the same logger

### The Problem: Multiple Instances Cause Inconsistency

Without Singleton, each component creates its own logger instance:

```java
// Problem: Multiple logger instances
class Logger {
    private FileWriter fileWriter;
    
    public Logger() {
        try {
            fileWriter = new FileWriter("application.log", true);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger");
        }
    }
    
    public void log(String message) {
        try {
            fileWriter.write(message + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            System.err.println("Failed to write log");
        }
    }
}

// Different components create their own instances
UserService userService = new UserService();
OrderService orderService = new OrderService();
PaymentService paymentService = new PaymentService();

// Problem: Each service has its own logger instance
// - Multiple file handles to the same file
// - Potential file write conflicts
// - Inconsistent logging order
// - Resource waste
```

**Problems with multiple instances:**
- ❌ **File write conflicts**: Multiple file handles writing to the same file
- ❌ **Inconsistent logging**: Logs may be interleaved or lost
- ❌ **Resource waste**: Unnecessary file handles
- ❌ **Thread safety issues**: Concurrent writes can corrupt the file
- ❌ **No global state**: Can't track application-wide logging statistics

### The Solution: Singleton Pattern

The Singleton pattern ensures:
- ✅ **Single instance**: Only one logger instance exists
- ✅ **Global access**: All components use the same instance
- ✅ **Consistent logging**: All logs go to the same file in order
- ✅ **Thread-safe**: Synchronized access prevents conflicts
- ✅ **Resource efficient**: Single file handle

### Solution Overview

- **Singleton Logger Class**: Ensures only one instance exists
- **Thread-Safe Implementation**: Uses `synchronized` to handle concurrent access
- **Lazy Initialization**: Creates the instance only when first needed
- **Global Access Point**: Provides a static `getInstance()` method
- **Private Constructor**: Prevents external instantiation

All components use `Logger.getInstance()` to get the same logger instance, ensuring all log entries go to the same file in the correct order.

### Key Benefits

- ✅ Guarantees a single logger instance
- ✅ Prevents file write conflicts
- ✅ Provides global access point
- ✅ Thread-safe logging operations
- ✅ Consistent log ordering
- ✅ Resource efficient (single file handle)

### Comparison: Without vs With Singleton

| Aspect | Without Singleton | With Singleton |
|--------|------------------|----------------|
| **Instances** | Multiple logger instances | Single instance |
| **File Handles** | Multiple (one per instance) | Single file handle |
| **Thread Safety** | ❌ Race conditions possible | ✅ Synchronized access |
| **Log Consistency** | ❌ Logs may be interleaved | ✅ Ordered logging |
| **Resource Usage** | ❌ High (multiple handles) | ✅ Low (single handle) |
| **Global State** | ❌ No shared state | ✅ Shared state |

## Reference

For more details on the Singleton pattern, see: [../../Singleton/README.md](../../Singleton/README.md)

