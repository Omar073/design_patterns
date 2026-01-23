// Final Exam 2023 - Question 4 Solution
// Strategy Pattern Implementation

// Strategy interface
interface Strategy {
    int doOperation(int a, int b);
}

// Concrete Strategies
class OperationAdd implements Strategy {
    @Override
    public int doOperation(int a, int b) {
        return a + b;
    }
}

class OperationSubtract implements Strategy {
    @Override
    public int doOperation(int a, int b) {
        return a - b;
    }
}

class OperationMultiply implements Strategy {
    @Override
    public int doOperation(int a, int b) {
        return a * b;
    }
}

// Context class
class StrategyContext {
    private Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b) {
        return strategy.doOperation(a, b);
    }
}

// Demo
public class Question4 {
    public static void main(String[] args) {
        System.out.println("=== Final Exam 2023 - Question 4: Strategy Pattern ===\n");

        StrategyContext context = new StrategyContext(new OperationAdd());
        System.out.println("Add: 10 + 5 = " + context.executeStrategy(10, 5));

        context.setStrategy(new OperationSubtract());
        System.out.println("Subtract: 10 - 5 = " + context.executeStrategy(10, 5));

        context.setStrategy(new OperationMultiply());
        System.out.println("Multiply: 10 * 5 = " + context.executeStrategy(10, 5));

        System.out.println("\n--- Pattern Explanation ---");
        System.out.println("Strategy Pattern:");
        System.out.println("- Strategy: interface with doOperation(a, b)");
        System.out.println("- OperationAdd / OperationSubtract / OperationMultiply: concrete strategies");
        System.out.println("- StrategyContext: holds a Strategy and delegates executeStrategy() to it");
    }
}

