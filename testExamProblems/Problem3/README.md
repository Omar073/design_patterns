# Problem 3: Notification Service

## Problem Statement

A notification service needs to send messages via SMS, Email, or Push notifications. The client should not be concerned with how each message type is created.

## Design Pattern Solution: Abstract Factory

### Why Abstract Factory?

The Abstract Factory pattern is ideal because:

1. **Family of Related Objects**: Different notification types (SMS, Email, Push) form a family that can be created consistently
2. **Encapsulation**: Hides the complexity of creating different notification types from the client
3. **Extensibility**: Easy to add new notification types (WhatsApp, Slack) without changing client code
4. **Consistency**: Ensures all notifications follow the same creation pattern

### Solution Overview

- **Abstract Factory Interface**: `NotificationFactory` with method to create notifications
- **Concrete Factories**: `SMSNotificationFactory`, `EmailNotificationFactory`, `PushNotificationFactory`
- **Abstract Product**: `Notification` interface with common methods
- **Concrete Products**: `SMSNotification`, `EmailNotification`, `PushNotification`

The client works with the abstract `Notification` interface and factory, never knowing the concrete implementation details.

### Key Benefits

- Client code is decoupled from notification creation logic
- Easy to add new notification types
- Consistent interface for all notification types
- Centralized creation logic

## Reference

For more details on the Abstract Factory pattern, see: [../../AbstractFactory/README.md](../../AbstractFactory/README.md)

