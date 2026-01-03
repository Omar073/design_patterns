// Memento Pattern – Text Editor Undo/Redo Example
// Allows an object to return to one of its previous states without exposing implementation details
// Roles:
//   - Originator: TextEditor class that creates and restores mementos
//   - Memento: TextEditorMemento class that stores the state
//   - Caretaker: History class that manages mementos without knowing their internal structure
//   - Client: main() demonstrates undo/redo functionality

/**
 * MEMENTO CLASS
 * Stores the internal state of the Originator object.
 * The memento is opaque to the Caretaker - it doesn't know what's inside.
 */
class TextEditorMemento {
    private final String content;
    private final int cursorPosition;
    
    // Package-private constructor - only Originator can create mementos
    TextEditorMemento(String content, int cursorPosition) {
        this.content = content;
        this.cursorPosition = cursorPosition;
    }
    
    // Package-private getters - only accessible by TextEditor (same package)
    String getContent() {
        return content;
    }
    
    int getCursorPosition() {
        return cursorPosition;
    }
}

/**
 * ORIGINATOR CLASS
 * The object whose state needs to be saved and restored.
 * Creates mementos containing snapshots of its current state,
 * and uses mementos to restore itself to a previous state.
 */
class TextEditor {
    private String content;
    private int cursorPosition;
    
    public TextEditor() {
        this.content = "";
        this.cursorPosition = 0;
    }
    
    public void write(String text) {
        content = content.substring(0, cursorPosition) + text + 
                  content.substring(cursorPosition);
        cursorPosition += text.length();
        System.out.println("Written: \"" + content + "\"");
    }
    
    /**
     * Creates a memento containing the current state.
     */
    public TextEditorMemento save() {
        return new TextEditorMemento(content, cursorPosition);
    }
    
    /**
     * Restores the editor to a state stored in a memento.
     */
    public void restore(TextEditorMemento memento) {
        if (memento != null) {
            this.content = memento.getContent();
            this.cursorPosition = memento.getCursorPosition();
            System.out.println("Restored: \"" + content + "\"");
        }
    }
    
    public void display() {
        System.out.println("Editor: \"" + content + "\" | Cursor: " + cursorPosition);
    }
}

/**
 * CARETAKER CLASS
 * Manages mementos without knowing their internal structure.
 * The Caretaker can store mementos, retrieve them, but cannot
 * access or modify their contents - only the Originator can do that.
 */
class History {
    private java.util.Stack<TextEditorMemento> undoStack;
    private java.util.Stack<TextEditorMemento> redoStack;
    
    public History() {
        this.undoStack = new java.util.Stack<>();
        this.redoStack = new java.util.Stack<>();
    }
    
    public void saveState(TextEditorMemento memento) {
        undoStack.push(memento);
        redoStack.clear();
    }
    
    public TextEditorMemento undo() {
        if (undoStack.size() <= 1) {
            System.out.println("Nothing to undo");
            return null;
        }
        
        TextEditorMemento current = undoStack.pop();
        redoStack.push(current);
        return undoStack.peek();
    }
    
    public TextEditorMemento redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo");
            return null;
        }
        
        TextEditorMemento memento = redoStack.pop();
        undoStack.push(memento);
        return memento;
    }
}

/**
 * CLIENT: MementoPatternDemo
 * Demonstrates the Memento pattern in action.
 */
public class MementoPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Memento Pattern Demo: Text Editor with Undo/Redo ===\n");
        
        // Create Originator and Caretaker
        TextEditor editor = new TextEditor();
        History history = new History();
        
        // Initial state
        history.saveState(editor.save());
        editor.display();
        
        // Perform operations and save states
        System.out.println("\n--- Performing Operations ---");
        editor.write("Hello");
        history.saveState(editor.save());
        
        editor.write(" World");
        history.saveState(editor.save());
        
        editor.write("!");
        history.saveState(editor.save());
        
        System.out.println("\n--- Current State ---");
        editor.display();
        
        // Undo operations
        System.out.println("\n--- Undo Operations ---");
        editor.restore(history.undo());
        editor.display();
        
        editor.restore(history.undo());
        editor.display();
        
        // Redo operations
        System.out.println("\n--- Redo Operations ---");
        editor.restore(history.redo());
        editor.display();
        
        System.out.println("\n--- Memento Pattern Benefits ---");
        System.out.println("✓ State can be saved and restored without exposing internal structure");
        System.out.println("✓ Caretaker manages mementos without knowing their contents");
        System.out.println("✓ Enables undo/redo functionality");
    }
}
