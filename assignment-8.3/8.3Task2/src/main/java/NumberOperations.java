import java.util.List;

/**
 * Lambda-based operations on number lists
 */
public class NumberOperations {

    // Filter even numbers (keeps only odd)
    public static void filterEvenNumbers(List<Integer> numbers) {
        numbers.removeIf(num -> num % 2 == 0);
    }

    // Double the value of odd numbers
    public static void doubleOddNumbers(List<Integer> numbers) {
        numbers.replaceAll(num -> (num % 2 != 0) ? num * 2 : num);
    }

    // Calculate sum of all numbers
    public static int sumNumbers(List<Integer> numbers) {
        final int[] sum = {0};
        numbers.forEach(num -> sum[0] += num);
        return sum[0];
    }
}
