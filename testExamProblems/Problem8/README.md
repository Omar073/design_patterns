# Problem 8: Game Enemy Character Creation

## Problem Statement

A game requires multiple enemy characters with similar attributes. Creating each one from scratch is expensive because initialization involves heavy resource loading (e.g., 3D models, textures).

## Design Pattern Solution: Prototype

### Why Prototype?

The Prototype pattern is the perfect solution because:

1. **Expensive Initialization**: Loading 3D models and textures is costly
2. **Similar Objects**: Enemies share similar attributes with minor variations
3. **Performance**: Cloning is much faster than creating from scratch
4. **Flexibility**: Can clone and then modify specific attributes
5. **Resource Efficiency**: Load resources once, clone many times

### Solution Overview

- **Prototype Interface**: `Enemy` interface with `clone()` method
- **Concrete Prototypes**: `Goblin`, `Orc`, `Dragon` classes that implement cloning
- **Registry (optional)**: Can store pre-initialized prototypes for quick access
- **Client**: Clones prototypes instead of creating new instances

Instead of `new Goblin()` (which loads 3D models), we create one prototype and clone it multiple times.

### Key Benefits

- Avoids expensive initialization on every creation
- Fast object creation through cloning
- Can customize clones after creation
- Efficient resource usage
- Supports prototype registry for common enemy types

## Reference

For more details on the Prototype pattern, see: [../../Prototype/README.md](../../Prototype/README.md)

