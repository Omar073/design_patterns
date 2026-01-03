// Iterator Pattern – Book Collection Example
// Provides a way to access the elements of an aggregate object sequentially
// without exposing its underlying representation
// Roles:
//   - Iterator: Iterator interface defining traversal methods
//   - ConcreteIterator: BookIterator class implementing traversal logic
//   - Aggregate: BookCollection interface defining iterator creation method
//   - ConcreteAggregate: Library class implementing collection and iterator creation
//   - Client: main() uses iterator to traverse collection

/**
 * ITERATOR INTERFACE
 * Defines the interface for accessing and traversing elements.
 * Provides methods to check if more elements exist and to get the next element.
 */
interface Iterator<T> {
    /**
     * Returns true if the iteration has more elements.
     * 
     * @return true if there are more elements, false otherwise
     */
    boolean hasNext();
    
    /**
     * Returns the next element in the iteration.
     * 
     * @return the next element
     * @throws java.util.NoSuchElementException if no more elements exist
     */
    T next();
    
    /**
     * Removes the last element returned by next() (optional operation).
     */
    void remove();
}

/**
 * AGGREGATE INTERFACE
 * Defines the interface for creating an Iterator object.
 */
interface Aggregate<T> {
    /**
     * Creates an iterator for traversing the collection.
     * 
     * @return an Iterator for this collection
     */
    Iterator<T> createIterator();
}

/**
 * CONCRETE ITERATOR: BookIterator
 * Implements the Iterator interface for traversing a book collection.
 * Maintains the current position in the traversal.
 */
class BookIterator implements Iterator<String> {
    private String[] books;
    private int position;
    
    public BookIterator(String[] books) {
        this.books = books;
        this.position = 0;
    }
    
    @Override
    public boolean hasNext() {
        return position < books.length && books[position] != null;
    }
    
    @Override
    public String next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("No more books");
        }
        return books[position++];
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation not supported");
    }
}

/**
 * CONCRETE AGGREGATE: Library
 * Implements the Aggregate interface and provides a way to create
 * an iterator for its collection of books.
 */
class Library implements Aggregate<String> {
    private String[] books;
    private int count;
    private static final int MAX_BOOKS = 10;
    
    public Library() {
        this.books = new String[MAX_BOOKS];
        this.count = 0;
    }
    
    /**
     * Adds a book to the library.
     */
    public void addBook(String book) {
        if (count < MAX_BOOKS) {
            books[count++] = book;
            System.out.println("Added book: " + book);
        } else {
            System.out.println("Library is full. Cannot add: " + book);
        }
    }
    
    /**
     * Gets the number of books in the library.
     */
    public int getCount() {
        return count;
    }
    
    /**
     * Creates an iterator for traversing the books.
     */
    @Override
    public Iterator<String> createIterator() {
        return new BookIterator(books);
    }
}

/**
 * CLIENT: IteratorPatternDemo
 * Demonstrates the Iterator pattern in action.
 * 
 * The client's role:
 * 1. Creates the Aggregate (Library)
 * 2. Adds elements to the collection
 * 3. Gets an Iterator from the Aggregate
 * 4. Uses the Iterator to traverse the collection
 * 
 * Notice how the client doesn't need to know about the internal
 * structure of the collection (array) - it just uses the iterator.
 */
public class IteratorPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Iterator Pattern Demo: Book Collection ===\n");
        
        // Create Aggregate
        Library library = new Library();
        
        // Add books to collection
        System.out.println("--- Adding Books to Library ---");
        library.addBook("The Great Gatsby");
        library.addBook("1984");
        library.addBook("To Kill a Mockingbird");
        library.addBook("Pride and Prejudice");
        library.addBook("The Catcher in the Rye");
        
        // Get Iterator from Aggregate
        Iterator<String> iterator = library.createIterator();
        
        // Traverse collection using Iterator
        System.out.println("\n--- Traversing Books Using Iterator ---");
        while (iterator.hasNext()) {
            String book = iterator.next();
            System.out.println("Reading: " + book);
        }
        
        // Create new iterator for another traversal
        System.out.println("\n--- Second Traversal (New Iterator) ---");
        Iterator<String> iterator2 = library.createIterator();
        int bookNumber = 1;
        while (iterator2.hasNext()) {
            System.out.println(bookNumber + ". " + iterator2.next());
            bookNumber++;
        }
        
        // Demonstrate multiple independent iterators
        System.out.println("\n--- Multiple Independent Iterators ---");
        Iterator<String> iter1 = library.createIterator();
        Iterator<String> iter2 = library.createIterator();
        
        System.out.println("Iterator 1: " + iter1.next());
        System.out.println("Iterator 2: " + iter2.next());
        System.out.println("Iterator 1: " + iter1.next());
        System.out.println("Iterator 2: " + iter2.next());
        System.out.println("Iterator 1: " + iter1.next());
        
        System.out.println("\n--- Iterator Pattern Benefits ---");
        System.out.println("✓ Provides uniform way to traverse different collections");
        System.out.println("✓ Hides internal structure of collection from client");
        System.out.println("✓ Supports multiple traversals simultaneously");
        System.out.println("✓ Easy to add new traversal algorithms");
        System.out.println("✓ Simplifies collection interface");
    }
}

