## Iterator Pattern

- **Intent**: Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
- **When to use**: When you need to traverse a collection of objects without exposing the collection's internal structure, or when you want to provide multiple ways to traverse the same collection.

---

## Pattern Structure

The Iterator pattern consists of the following components:

1. **`Iterator`** (Interface)
   - Defines an interface for accessing and traversing elements
   - Methods: `hasNext()`, `next()`, `remove()` (optional)
   - Example: `Iterator<T>` interface

2. **`ConcreteIterator`** (Class)
   - Implements the `Iterator` interface
   - Keeps track of the current position in the traversal
   - Implements the traversal algorithm
   - Example: `BookIterator`

3. **`Aggregate`** (Interface)
   - Defines an interface for creating an Iterator object
   - Method: `createIterator()`
   - Example: `Aggregate<T>` interface

4. **`ConcreteAggregate`** (Class)
   - Implements the `Aggregate` interface
   - Returns an instance of the appropriate `ConcreteIterator`
   - Example: `Library`

**Key Relationships:**
- `Aggregate` **creates** `Iterator` objects (factory method)
- `ConcreteIterator` **implements** `Iterator` interface
- `ConcreteIterator` **knows about** `ConcreteAggregate` structure (to traverse it)
- `ConcreteAggregate` **implements** `Aggregate` interface

**Pattern Flow:**
1. Client gets Iterator from Aggregate via `createIterator()`
2. Client uses Iterator's `hasNext()` to check for more elements
3. Client uses Iterator's `next()` to get next element
4. Iterator maintains position and traverses collection
5. Client can create multiple iterators for independent traversals

---

## Iterator Pattern Example

### Scenario: Book Collection Traversal

Imagine a library system that needs to traverse its collection of books. The library might use different internal data structures (array, list, tree), but clients should be able to traverse books uniformly without knowing the internal structure.

**Requirements:**
- Traverse books without exposing internal structure
- Support multiple simultaneous traversals
- Provide uniform interface for different collection types
- Easy to add new traversal algorithms

---

## Implementation

### Iterator Interface

```java
interface Iterator<T> {
    boolean hasNext();
    T next();
    void remove();  // Optional
}
```

### Concrete Iterator

```java
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
            throw new NoSuchElementException();
        }
        return books[position++];
    }
}
```

### Aggregate Interface

```java
interface Aggregate<T> {
    Iterator<T> createIterator();
}
```

### Concrete Aggregate

```java
class Library implements Aggregate<String> {
    private String[] books;
    private int count;
    
    public void addBook(String book) {
        books[count++] = book;
    }
    
    @Override
    public Iterator<String> createIterator() {
        return new BookIterator(books);
    }
}
```

### Client Usage

```java
public class IteratorPatternDemo {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("The Great Gatsby");
        library.addBook("1984");
        
        // Get iterator and traverse
        Iterator<String> iterator = library.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

---

## Why Use the Iterator Pattern?

### Code Without Iterator Pattern

Without the Iterator pattern, clients must know about the collection's internal structure:

```java
// Problem: Client must know about internal structure
class Library {
    private String[] books;
    private int count;
    
    public String[] getBooks() {
        return books;  // Exposes internal array structure
    }
    
    public int getCount() {
        return count;
    }
}

// Client code - tightly coupled to array structure
public class WithoutIteratorDemo {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("The Great Gatsby");
        library.addBook("1984");
        
        // Client must know it's an array and how to traverse it
        String[] books = library.getBooks();
        int count = library.getCount();
        
        for (int i = 0; i < count; i++) {
            System.out.println(books[i]);  // Direct array access
        }
        
        // Problems:
        // - If Library changes to use List instead of array, client breaks
        // - Client must know about array indexing
        // - Cannot have multiple simultaneous traversals easily
        // - Cannot change traversal algorithm without modifying client
    }
}
```

**Problems:**
- ❌ **Exposes internal structure**: Client must know collection is an array
- ❌ **Tight coupling**: Client code breaks if collection structure changes
- ❌ **No abstraction**: Different collection types require different traversal code
- ❌ **Hard to support multiple traversals**: Difficult to have multiple independent traversals
- ❌ **No traversal algorithm flexibility**: Cannot easily change traversal order

### The Solution: Iterator Pattern

The Iterator pattern solves these problems by:
- ✅ **Hides internal structure**: Client doesn't know about array/list/tree
- ✅ **Loose coupling**: Collection structure can change without affecting client
- ✅ **Uniform interface**: Same traversal code works for different collection types
- ✅ **Multiple traversals**: Can create multiple independent iterators
- ✅ **Flexible algorithms**: Easy to add different traversal algorithms

---

## Pattern Participants

1. **Iterator** (Interface)
   - Defines an interface for accessing and traversing elements
   - Example: `Iterator<T>` interface

2. **ConcreteIterator** (Class)
   - Implements the `Iterator` interface
   - Keeps track of the current position in the traversal
   - Example: `BookIterator`

3. **Aggregate** (Interface)
   - Defines an interface for creating an Iterator object
   - Example: `Aggregate<T>` interface

4. **ConcreteAggregate** (Class)
   - Implements the `Aggregate` interface
   - Returns an instance of the appropriate `ConcreteIterator`
   - Example: `Library`

---

## Pros

- ✅ **Hides Internal Structure**: Client doesn't need to know collection's internal representation
- ✅ **Uniform Traversal**: Same code works for different collection types
- ✅ **Multiple Traversals**: Can have multiple iterators traversing simultaneously
- ✅ **Separation of Concerns**: Traversal logic separated from collection
- ✅ **Easy to Extend**: Can add new traversal algorithms without modifying collection
- ✅ **Simplifies Aggregate Interface**: Collection doesn't need traversal methods

---

## Cons

- ❌ **Overhead**: Iterator objects add memory overhead
- ❌ **Complexity**: Adds abstraction layer (may be overkill for simple collections)
- ⚠️ **Concurrent Modifications**: Iterator may become invalid if collection is modified during traversal
- ⚠️ **Performance**: Slight performance overhead compared to direct access

---

## When to Use Iterator Pattern

### ✅ Use Iterator Pattern When:

#### Hide Collection Structure:
- You want to hide the internal structure of a collection from clients
- You want to provide a uniform way to traverse different collection types
- Collection structure may change (array → list → tree)

#### Multiple Traversals:
- You need to support multiple simultaneous traversals of the same collection
- You want independent traversal positions

#### Traversal Algorithms:
- You want to provide different ways to traverse the same collection
- You want to add new traversal algorithms without modifying the collection

#### Additional Use Cases:
- **Collections Framework**: Java Collections use Iterator pattern
- **Tree Traversals**: Pre-order, in-order, post-order iterators
- **Filtered Iterators**: Iterate only elements matching criteria
- **Composite Structures**: Traverse tree structures uniformly

### ❌ Don't Use Iterator Pattern When:
- **Simple Collections**: Collection is simple and structure won't change
- **Direct Access Needed**: Need random access to elements
- **Performance Critical**: Iterator overhead is too high
- **Single Traversal**: Only need one traversal at a time and structure is fixed

---

## Real-World Examples

### Java Collections Framework
- **ArrayList, LinkedList, HashSet**: All implement `Iterable` and provide `Iterator`
- **Uniform Traversal**: Same code works for all collection types
- **Enhanced For Loop**: `for (String item : collection)` uses Iterator internally

### Tree Traversals
- **Binary Tree**: Pre-order, in-order, post-order iterators
- **File System**: Directory traversal iterators
- **DOM Trees**: XML/HTML tree traversal

### Database Result Sets
- **SQL ResultSet**: Iterator-like interface for traversing query results
- **Cursor Pattern**: Similar to Iterator for database records

---

## Compare with Other Patterns

### Iterator vs Visitor

**Iterator:**
- Traverses **elements** of a collection
- Focuses on **accessing** elements sequentially
- Used for **traversal** operations

**Visitor:**
- Performs **operations** on elements of a structure
- Focuses on **operations** on elements
- Used for **processing** elements

**Key Difference:**
- Iterator = **traverse** elements
- Visitor = **operate** on elements

### Iterator vs Composite

**Iterator:**
- Provides **traversal** of collections
- Works with **aggregate** objects
- Used for **accessing** elements

**Composite:**
- Composes objects into **tree structures**
- Represents part-whole hierarchies
- Used for **building** structures

**They work together**: Iterator can traverse Composite structures.

---

## Best Practices

1. **Fail-Fast Iterators**: Throw exception if collection is modified during iteration
2. **Immutable Iterators**: Consider making iterators immutable if possible
3. **Iterator Lifecycle**: Document when iterator becomes invalid
4. **Concurrent Modifications**: Handle concurrent modification exceptions
5. **Resource Management**: Close iterators if they hold resources (e.g., database connections)

---

## Implementation Notes

### Java's Built-in Iterator

Java provides Iterator pattern in `java.util.Iterator`:

```java
import java.util.Iterator;
import java.util.ArrayList;

ArrayList<String> list = new ArrayList<>();
list.add("Item 1");
list.add("Item 2");

Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

### Enhanced For Loop

Java's enhanced for loop uses Iterator internally:

```java
for (String item : list) {
    System.out.println(item);  // Uses Iterator internally
}
```

### Custom Iterator Implementations

```java
// Reverse iterator
class ReverseIterator<T> implements Iterator<T> {
    private List<T> list;
    private int position;
    
    public ReverseIterator(List<T> list) {
        this.list = list;
        this.position = list.size() - 1;
    }
    
    @Override
    public boolean hasNext() {
        return position >= 0;
    }
    
    @Override
    public T next() {
        return list.get(position--);
    }
}
```

---

## Notes

- ⚠️ **Concurrent Modifications**: Iterators may become invalid if collection is modified
- ⚠️ **Iterator State**: Iterator maintains position - be careful with shared iterators
- ⚠️ **Resource Cleanup**: Some iterators (e.g., database cursors) need explicit cleanup
- ⚠️ **Thread Safety**: Iterators are generally not thread-safe

---

**Further reading**: See the demo for a complete working example:
- [IteratorPatternDemo.java](IteratorPatternDemo.java)

