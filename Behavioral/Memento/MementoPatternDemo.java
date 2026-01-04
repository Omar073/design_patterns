// Memento Pattern – Document Example
// Allows an object to return to one of its previous states without exposing implementation details
// Roles:
//   - Originator: Document class that creates and restores mementos
//   - Memento: DocumentMemento class that stores the state
//   - Caretaker: History class that manages mementos without knowing their internal structure
//   - Client: main() demonstrates state saving and restoration

import java.util.ArrayList;
import java.util.List;

/**
 * ORIGINATOR CLASS
 * The object whose state needs to be saved and restored.
 * Creates mementos containing snapshots of its current state,
 * and uses mementos to restore itself to a previous state.
 */
class Document {
    private String content;

    public Document(String content) {
        this.content = content;
    }

    public void write(String text) {
        this.content += text;
    }

    public String getContent() {
        return this.content;
    }

    public DocumentMemento createMemento() {
        return new DocumentMemento(this.content);
    }

    public void restoreFromMemento(DocumentMemento memento) {
        this.content = memento.getSavedContent();
    }
}

/**
 * MEMENTO CLASS
 * Stores the internal state of the Originator object.
 * The memento is opaque to the Caretaker - it doesn't know what's inside.
 */
class DocumentMemento {
    private String content;

    public DocumentMemento(String content) {
        this.content = content;
    }

    public String getSavedContent() {
        return this.content;
    }
}

/**
 * CARETAKER CLASS
 * Manages mementos without knowing their internal structure.
 * The Caretaker can store mementos, retrieve them, but cannot
 * access or modify their contents - only the Originator can do that.
 * Supports undo/redo functionality by tracking current position in history.
 */
class History {
    private List<DocumentMemento> mementos;
    private int currentIndex;

    public History() {
        this.mementos = new ArrayList<>();
        this.currentIndex = -1;
    }

    public void addMemento(DocumentMemento memento) {
        // Remove any mementos after current index (when new state is added after undo)
        while (mementos.size() > currentIndex + 1) {
            mementos.remove(mementos.size() - 1);
        }
        mementos.add(memento);
        currentIndex = mementos.size() - 1;
    }

    public DocumentMemento getMemento(int index) {
        if (index >= 0 && index < mementos.size()) {
            return mementos.get(index);
        }
        return null;
    }

    public DocumentMemento undo() {
        if (currentIndex > 0) {
            currentIndex--;
            return mementos.get(currentIndex);
        }
        return null;
    }

    public DocumentMemento redo() {
        if (currentIndex < mementos.size() - 1) {
            currentIndex++;
            return mementos.get(currentIndex);
        }
        return null;
    }

    public boolean canUndo() {
        return currentIndex > 0;
    }

    public boolean canRedo() {
        return currentIndex < mementos.size() - 1;
    }
}

/**
 * CLIENT: MementoPatternDemo
 * Demonstrates the Memento pattern in action.
 */
public class MementoPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Memento Pattern Demo: Document with State Management ===\n");

        Document document = new Document("Initial content\n");
        History history = new History();

        // Save initial stateu
        history.addMemento(document.createMemento());
        System.out.println("Initial state saved");
        System.out.println("Content: " + document.getContent());

        // Write some content
        System.out.println("\n--- Writing Additional Content ---");
        document.write("Additional content\n");
        history.addMemento(document.createMemento());
        System.out.println("State saved");
        System.out.println("Content: " + document.getContent());

        // Write more content
        System.out.println("\n--- Writing More Content ---");
        document.write("More content\n");
        history.addMemento(document.createMemento());
        System.out.println("State saved");
        System.out.println("Content: " + document.getContent());

        // Undo operations
        System.out.println("\n--- Undo Operations ---");
        DocumentMemento undoMemento = history.undo();
        if (undoMemento != null) {
            document.restoreFromMemento(undoMemento);
            System.out.println("Undo successful");
            System.out.println("Content: " + document.getContent());
        } else {
            System.out.println("Nothing to undo");
        }

        undoMemento = history.undo();
        if (undoMemento != null) {
            document.restoreFromMemento(undoMemento);
            System.out.println("Undo successful");
            System.out.println("Content: " + document.getContent());
        } else {
            System.out.println("Nothing to undo");
        }

        // Redo operations
        System.out.println("\n--- Redo Operations ---");
        DocumentMemento redoMemento = history.redo();
        if (redoMemento != null) {
            document.restoreFromMemento(redoMemento);
            System.out.println("Redo successful");
            System.out.println("Content: " + document.getContent());
        } else {
            System.out.println("Nothing to redo");
        }

        redoMemento = history.redo();
        if (redoMemento != null) {
            document.restoreFromMemento(redoMemento);
            System.out.println("Redo successful");
            System.out.println("Content: " + document.getContent());
        } else {
            System.out.println("Nothing to redo");
        }

        // Write new content after undo/redo (should clear redo history)
        System.out.println("\n--- Writing New Content After Undo/Redo ---");
        document.write("New content after undo/redo\n");
        history.addMemento(document.createMemento());
        System.out.println("State saved (redo history cleared)");
        System.out.println("Content: " + document.getContent());

        System.out.println("\n--- Memento Pattern Benefits ---");
        System.out.println("✓ State can be saved and restored without exposing internal structure");
        System.out.println("✓ Caretaker manages mementos without knowing their contents");
        System.out.println("✓ Enables undo/redo functionality");
        System.out.println("✓ Supports checkpoint/rollback operations");
    }
}
