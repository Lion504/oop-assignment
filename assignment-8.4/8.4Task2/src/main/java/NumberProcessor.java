import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BinaryOperator;

/**
 * Task 2: Filtering and Transforming a List of Numbers
 *
 * Demonstrates:
 * - Lambda expressions
 * - Streams
 * - Functional interfaces
 */
public class NumberProcessor {

    // Functional Interface: Predicate to filter odd numbers
    private static final Predicate<Integer> isOdd = n -> n % 2 != 0;

    // Functional Interface: Function to double a number
    private static final Function<Integer, Integer> doubleValue = n -> n * 2;

    // Functional Interface: BinaryOperator to sum two numbers
    private static final BinaryOperator<Integer> sum = (a, b) -> a + b;

    /**
     * Process numbers using functional interfaces
     */
    public static int processNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(isOdd)           // Filter using Predicate
                .map(doubleValue)        // Transform using Function
                .reduce(0, sum);         // Reduce using BinaryOperator
    }

    /**
     * Alternative: Using inline lambda expressions
     */
    public static int processNumbersInline(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 != 0)      // Lambda: Predicate
                .map(n -> n * 2)               // Lambda: Function
                .reduce(0, (a, b) -> a + b);   // Lambda: BinaryOperator
    }

    public static void main(String[] args) {
        System.out.println("===== FUNCTIONAL PROGRAMMING DEMONSTRATION =====\n");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("Original List: " + numbers);
        System.out.println();

        // Method 1: Using Functional Interfaces
        System.out.println("Method 1: Using Functional Interfaces");
        System.out.println("Result: " + processNumbers(numbers));
        System.out.println();

        // Method 2: Using Inline Lambda Expressions
        System.out.println("Method 2: Using Inline Lambda Expressions");
        System.out.println("Result: " + processNumbersInline(numbers));
        System.out.println();

    }
}
