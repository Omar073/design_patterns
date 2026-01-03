## Memento Pattern

- **Intent**: Without violating encapsulation, capture and externalize an object's internal state so that the object can be restored to this state later. Use the Memento Pattern when you need to be able to return an object to one of its previous states; for instance, if your user requests an "undo."
- **When to use**: When you need to save and restore an object's state, implement undo/redo functionality, or provide checkpoints for rollback operations.

---

## Pattern Structure

The Memento pattern consists of the following components:

1. **`Memento`** (Class)
   - Stores the internal state of the Originator
   - Provides no public methods - state is only accessible to Originator
   - Example: `TextEditorMemento`

2. **`Originator`** (Class)
   - Creates a memento containing a snapshot of its current state
   - Uses a memento to restore its internal state
   - Example: `TextEditor`

3. **`Caretaker`** (Class)
   - Stores mementos but never operates on or examines their contents
   - Manages the history of mementos (undo/redo stacks)
   - Example: `History`

**Key Relationships:**
- `Originator` **creates** `Memento` objects (composition)
- `Originator` **uses** `Memento` objects to restore state
- `Caretaker` **stores** `Memento` objects but cannot access their contents
- `Caretaker` **does not know** the internal structure of `Memento`

**Pattern Flow:**
1. Originator creates a memento containing its current state
2. Caretaker stores the memento (without knowing its contents)
3. When needed, Caretaker returns a memento to Originator
4. Originator restores its state from the memento

---

## Memento Pattern Example

### Scenario: Text Editor with Undo/Redo

Imagine a text editor that needs to support undo/redo functionality. Users should be able to:
- Type text and have it saved
- Undo their actions to return to previous states
- Redo actions that were undone
- Have the editor's state (content and cursor position) preserved

**Requirements:**
- Save editor state at various points
- Restore to any saved state
- Implement undo/redo functionality
- Maintain encapsulation (state details hidden from history manager)

---

## Implementation

### Memento Class

```java
class TextEditorMemento {
    private final String content;
    private final int cursorPosition;
    
    // Package-private constructor - only Originator can create
    TextEditorMemento(String content, int cursorPosition) {
        this.content = content;
        this.cursorPosition = cursorPosition;
    }
    
    // Package-private getters - only accessible by Originator
    String getContent() {
        return content;
    }
    
    int getCursorPosition() {
        return cursorPosition;
    }
}
```

**Key Points:**
- Memento is **opaque** to Caretaker - it cannot access internal fields
- Only Originator can create and access memento contents (package-private)
- Memento is **immutable** - state cannot be changed after creation

### Originator Class

```java
class TextEditor {
    private String content;
    private int cursorPosition;
    
    // Creates a memento containing current state
    public TextEditorMemento save() {
        return new TextEditorMemento(content, cursorPosition);
    }
    
    // Restores state from a memento
    public void restore(TextEditorMemento memento) {
        if (memento != null) {
            this.content = memento.getContent();
            this.cursorPosition = memento.getCursorPosition();
        }
    }
    
    // Other methods that modify state...
    public void write(String text) {
        // Modify content and cursor position
    }
}
```

**Key Points:**
- Originator **creates** mementos via `save()` method
- Originator **restores** state via `restore()` method
- Only Originator knows how to create and use mementos

### Caretaker Class

```java
class History {
    private Stack<TextEditorMemento> undoStack;
    private Stack<TextEditorMemento> redoStack;
    
    public void saveState(TextEditorMemento memento) {
        undoStack.push(memento);
        redoStack.clear();
    }
    
    public TextEditorMemento undo() {
        if (undoStack.isEmpty()) return null;
        TextEditorMemento current = undoStack.pop();
        if (!undoStack.isEmpty()) {
            redoStack.push(current);
            return undoStack.peek();
        }
        undoStack.push(current);
        return null;
    }
    
    public TextEditorMemento redo() {
        if (redoStack.isEmpty()) return null;
        TextEditorMemento memento = redoStack.pop();
        undoStack.push(memento);
        return memento;
    }
}
```

**Key Points:**
- Caretaker **stores** mementos but cannot access their contents
- Caretaker manages the **history** (undo/redo stacks)
- Caretaker doesn't know what's inside mementos - just stores and retrieves them

### Client Usage

```java
public class MementoPatternDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();
        
        // Save initial state
        history.saveState(editor.save());
        
        // Perform operations
        editor.write("Hello");
        history.saveState(editor.save());
        
        editor.write(" World");
        history.saveState(editor.save());
        
        // Undo
        editor.restore(history.undo());
        
        // Redo
        editor.restore(history.redo());
    }
}
```

---

## Why Use the Memento Pattern?

### Code Without Memento Pattern

Without the Memento pattern, you must expose the object's internal state to save/restore it:

```java
// Problem: Exposing internal state - breaks encapsulation
class TextEditor {
    // Public fields - anyone can access and modify directly!
    public String content;
    public int cursorPosition;
    
    public TextEditor() {
        this.content = "";
        this.cursorPosition = 0;
    }
    
    public void write(String text) {
        content = content.substring(0, cursorPosition) + text + 
                  content.substring(cursorPosition);
        cursorPosition += text.length();
    }
}

class History {
    // Must store editor's internal state directly
    private java.util.Stack<String> contentHistory;
    private java.util.Stack<Integer> cursorHistory;
    
    public History() {
        this.contentHistory = new java.util.Stack<>();
        this.cursorHistory = new java.util.Stack<>();
    }
    
    public void saveState(TextEditor editor) {
        // Direct access to internal fields - breaks encapsulation!
        contentHistory.push(editor.content);
        cursorHistory.push(editor.cursorPosition);
    }
    
    public void undo(TextEditor editor) {
        if (!contentHistory.isEmpty()) {
            // Direct modification of internal fields - dangerous!
            editor.content = contentHistory.pop();
            editor.cursorPosition = cursorHistory.pop();
        }
    }
}

// Client code - can accidentally corrupt editor state
public class WithoutMementoDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();
        
        editor.write("Hello");
        history.saveState(editor);
        
        // Problem: Anyone can directly modify editor state!
        editor.content = "Hacked!";  // Direct access - no protection
        editor.cursorPosition = -1;   // Invalid value - no validation
        
        // History can also directly modify state
        history.undo(editor);  // Directly sets editor.content and cursorPosition
    }
}
```

**Problems:**
- ❌ **Breaks encapsulation**: Internal state must be public to save/restore
- ❌ **Tight coupling**: History knows about editor's internal structure (field names)
- ❌ **Hard to maintain**: Changes to editor fields break history class
- ❌ **No protection**: Anyone can modify state directly (no validation)
- ❌ **No abstraction**: History must know exact field names and types
- ❌ **Error-prone**: Easy to corrupt state with invalid values

### The Solution: Memento Pattern

The Memento pattern solves these problems by:
- ✅ **Encapsulation**: State is hidden in mementos
- ✅ **Loose coupling**: Caretaker doesn't know memento contents
- ✅ **Easy to maintain**: Changes to Originator don't affect Caretaker
- ✅ **Protected state**: Only Originator can access memento contents

---

## Pattern Participants

1. **Memento** (Class)
   - Stores the internal state of the Originator
   - Provides no public methods for accessing state
   - State is only accessible to Originator
   - Example: `TextEditorMemento`

2. **Originator** (Class)
   - Creates a memento containing a snapshot of its current state
   - Uses a memento to restore its internal state
   - Example: `TextEditor`

3. **Caretaker** (Class)
   - Is responsible for the memento's safekeeping
   - Never operates on or examines the contents of a memento
   - Manages the history of mementos
   - Example: `History`

---

## Pros

- ✅ **Encapsulation**: Originator's state is protected - only it can access memento contents
- ✅ **Separation of Concerns**: Caretaker manages history without knowing state details
- ✅ **Undo/Redo Support**: Easy to implement undo/redo functionality
- ✅ **Checkpoint System**: Can save state at any point for rollback
- ✅ **Loose Coupling**: Caretaker doesn't depend on Originator's internal structure

---

## Cons

- ❌ **Memory Overhead**: Storing many mementos can consume significant memory
- ❌ **Performance**: Creating and restoring mementos may be expensive for large objects
- ⚠️ **Caretaker Responsibility**: Caretaker must manage memento lifecycle (when to delete old ones)
- ⚠️ **Deep vs Shallow Copy**: Need to decide if mementos should store deep or shallow copies

---

## When to Use Memento Pattern

### ✅ Use Memento Pattern When:

#### Undo/Redo Functionality Required:
- You need to implement undo/redo operations in your application
- Users need to revert changes and restore previous states
- State history needs to be maintained

#### State Checkpointing Needed:
- You need to save object state at specific points
- You need to rollback to previous states
- You need to implement save/load functionality

#### Encapsulation Must Be Maintained:
- You want to save state without exposing internal structure
- You want Caretaker to manage history without knowing state details
- You want to protect object's internal state from external access

#### Additional Use Cases:
- **Text Editors**: Undo/redo text changes
- **Graphics Editors**: Undo/redo drawing operations
- **Games**: Save/load game state
- **Configuration Management**: Rollback to previous configurations
- **Transaction Systems**: Rollback failed transactions

### ❌ Don't Use Memento Pattern When:
- **Simple State**: Object state is trivial and doesn't need encapsulation
- **Memory Constraints**: Cannot afford to store multiple state snapshots
- **Performance Critical**: Creating mementos is too expensive
- **Public State Acceptable**: Exposing internal state is not a concern

---

## Real-World Examples

### Text Editors
- **Undo/Redo**: Save editor state (content, cursor position, selection) and restore on undo/redo
- **Multiple Undo Levels**: Maintain a stack of mementos for multiple undo operations

### Graphics Applications
- **Drawing Operations**: Save canvas state before each operation for undo
- **Layer Management**: Save layer states for undo/redo

### Games
- **Save/Load**: Save game state (player position, inventory, world state) to memento
- **Checkpoint System**: Save game state at checkpoints for respawning

### Configuration Management
- **Settings Rollback**: Save configuration states and restore previous settings
- **System Restore**: Save system state for rollback to previous configuration

---

## Compare with Other Patterns

### Memento vs Command (for Undo)

**Memento:**
- Stores **state snapshots** of the object
- Originator creates and restores mementos
- Caretaker manages mementos without knowing contents
- Used when you need to save/restore object state

**Command:**
- Stores **operations** (commands) that can be executed/undone
- Command objects encapsulate operations and their inverse
- Invoker executes commands without knowing details
- Used when you need to queue, log, or undo operations

**When to use which:**
- Use **Memento** when you need to save/restore object state
- Use **Command** when you need to undo operations (actions)

**They can work together**: Command pattern can use Memento to store state before operations.

### Memento vs Prototype

**Memento:**
- Stores **state** of an object at a specific point in time
- Used for **restoring** previous states
- Memento is opaque - only Originator can access it

**Prototype:**
- Creates **new objects** by cloning existing ones
- Used for **creating** new instances efficiently
- Prototype is accessible - clients can clone and modify

**Key Difference:**
- Memento = **state snapshot** for restoration
- Prototype = **object template** for creation

### Memento vs State

**Memento:**
- Stores **snapshots** of object state
- Used for **restoring** previous states
- External to the object (managed by Caretaker)

**State:**
- Represents **current state** of an object
- Used for **state transitions** and behavior changes
- Internal to the object (part of its structure)

**Key Difference:**
- Memento = **past state** (for restoration)
- State = **current state** (for behavior)

---

## Best Practices

1. **Encapsulate Memento**: Use package-private or nested classes to prevent external access
2. **Immutable Mementos**: Make mementos immutable once created
3. **Limit Memento Size**: Store only necessary state to reduce memory usage
4. **Manage Memento Lifecycle**: Caretaker should limit history size to prevent memory issues
5. **Deep vs Shallow Copy**: Decide whether mementos should store deep or shallow copies
6. **Originator Control**: Only Originator should create and access memento contents

---

## Implementation Notes

### Encapsulation Techniques

**Package-Private Memento:**
```java
// Memento in same package as Originator
class TextEditorMemento {
    private final String content;  // Private fields
    // Package-private constructor and getters
    TextEditorMemento(String content) { ... }
    String getContent() { ... }  // Only accessible in same package
}
```

**Nested Class Memento:**
```java
class TextEditor {
    // Memento as nested class - only TextEditor can access
    private static class Memento {
        private final String content;
        private Memento(String content) { this.content = content; }
    }
    
    public Memento save() {
        return new Memento(this.content);
    }
}
```

### Undo/Redo Implementation

```java
class History {
    private Stack<Memento> undoStack = new Stack<>();
    private Stack<Memento> redoStack = new Stack<>();
    
    public void saveState(Memento memento) {
        undoStack.push(memento);
        redoStack.clear();  // Clear redo when new action performed
    }
    
    public Memento undo() {
        if (undoStack.size() <= 1) return null;
        Memento current = undoStack.pop();
        redoStack.push(current);
        return undoStack.peek();  // Return previous state
    }
    
    public Memento redo() {
        if (redoStack.isEmpty()) return null;
        Memento memento = redoStack.pop();
        undoStack.push(memento);
        return memento;
    }
}
```

### Memory Management

```java
class History {
    private static final int MAX_HISTORY = 50;
    private Stack<Memento> undoStack = new Stack<>();
    
    public void saveState(Memento memento) {
        undoStack.push(memento);
        // Limit history size to prevent memory issues
        if (undoStack.size() > MAX_HISTORY) {
            undoStack.remove(0);  // Remove oldest
        }
    }
}
```

---

## Notes

- ⚠️ **Memento Size**: Large objects can create memory issues - consider storing only changed fields
- ⚠️ **Memento Lifecycle**: Caretaker must manage when to delete old mementos
- ⚠️ **Deep Copy**: If object contains references, decide if memento should store deep copies
- ⚠️ **Thread Safety**: If multiple threads access mementos, ensure thread safety
- ⚠️ **Serialization**: Mementos can be serialized for persistent storage (save to disk)

---

**Further reading**: See the demo for a complete working example:
- [MementoPatternDemo.java](MementoPatternDemo.java)

