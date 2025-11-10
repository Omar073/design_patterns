# Problem 3: Notification Service

## Problem Statement

A notification service needs to send messages via SMS, Email, or Push notifications. The client should not be concerned with how each message type is created.

## Design Pattern Solution: Simple Factory

### Why Simple Factory (Not Abstract Factory)?

**Key Analysis:**
- Creates only **ONE product type**: Notification
- Each factory method creates a single notification instance
- No family of related products needed

**Abstract Factory is for:**
- Creating **families of related products** (multiple product types)
- Example: Button + Checkbox + Menu (all from same OS family)
- Each factory creates multiple related products

**Simple Factory is for:**
- Creating **one product type** with different variants
- Example: Notification (SMS, Email, Push variants)
- Single factory class with parameterized method

### Why Simple Factory?

The Simple Factory pattern is the best solution because:

1. **Single Product Type**: We only need to create Notification objects (not families of products)
2. **Simple Creation Logic**: A parameterized method is sufficient to create different notification types
3. **No Family Relationship**: SMS, Email, and Push are variants of the same product type, not a family
4. **Appropriate Complexity**: Abstract Factory would be overkill for a single product type

### Solution Overview

- **Simple Factory**: `NotificationFactory` with `createNotification(String type)` method
- **Abstract Product**: `Notification` interface with common methods
- **Concrete Products**: `SMSNotification`, `EmailNotification`, `PushNotification`

The client uses the factory to create notifications based on a string parameter, without knowing the concrete implementation.

### Key Benefits

- Client code is decoupled from notification creation logic
- Simple and easy to understand
- Easy to add new notification types (just modify the factory method)
- Centralized creation logic

### When Would Abstract Factory Be Needed?

Abstract Factory would be appropriate if:
- You needed to create families of related products
- Example: Notification + DeliveryMethod + EncryptionMethod (all from same provider family)
- You needed to ensure all products come from the same "family"

## Reference

For more details on the Simple Factory pattern, see: [../../Factory/README.md](../../Factory/README.md)
