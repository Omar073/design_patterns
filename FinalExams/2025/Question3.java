// Final Exam 2025 - Question 3 Solution
// Strategy Pattern Implementation based on UML

// Strategy interface
interface Pattern {
    int doOperation(int a, int b);
}

// Concrete Strategies
class OperationAdd implements Pattern {
    @Override
    public int doOperation(int a, int b) {
        return a + b;
    }
}

class OperationSubtract implements Pattern {
    @Override
    public int doOperation(int a, int b) {
        return a - b;
    }
}

class OperationMultiply implements Pattern {
    @Override
    public int doOperation(int a, int b) {
        return a * b;
    }
}

// Context class
class Context {
    private Pattern pattern;

    public Context(Pattern pattern) {
        this.pattern = pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public int executePattern(int a, int b) {
        return pattern.doOperation(a, b);
    }
}

// Demo / Client
public class Question3 {
    public static void main(String[] args) {
        System.out.println("=== Final Exam 2025 - Question 3: Strategy Pattern ===\n");

        Context context = new Context(new OperationAdd());
        System.out.println("Using OperationAdd strategy: 5 + 3 = " + context.executePattern(5, 3));

        context.setPattern(new OperationSubtract());
        System.out.println("Using OperationSubtract strategy: 5 - 3 = " + context.executePattern(5, 3));

        context.setPattern(new OperationMultiply());
        System.out.println("Using OperationMultiply strategy: 5 * 3 = " + context.executePattern(5, 3));

        System.out.println("\n--- Pattern Explanation ---");
        System.out.println("Strategy Pattern decouples algorithms from the context:");
        System.out.println("- Pattern: strategy interface with doOperation(a, b)");
        System.out.println("- OperationAdd / OperationSubtract / OperationMultiply: concrete strategies");
        System.out.println("- Context: uses a Pattern and can switch strategies at runtime");
    }
}

