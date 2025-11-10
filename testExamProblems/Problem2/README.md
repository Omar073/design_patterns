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

### Solution Overview

- **Singleton Logger Class**: Ensures only one instance exists
- **Thread-Safe Implementation**: Uses synchronization or enum to handle concurrent access
- **Lazy Initialization**: Creates the instance only when first needed
- **Global Access Point**: Provides a static method to get the single instance

All components use the same logger instance, ensuring all log entries go to the same file in the correct order.

### Key Benefits

- Guarantees a single logger instance
- Prevents file write conflicts
- Provides global access point
- Thread-safe logging operations

## Reference

For more details on the Singleton pattern, see: [../../Singleton/README.md](../../Singleton/README.md)

