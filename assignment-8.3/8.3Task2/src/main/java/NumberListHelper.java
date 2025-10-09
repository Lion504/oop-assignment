import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for creating and printing number lists
 */
public class NumberListHelper {

    /**
     * Create a list of integers: [10, 5, 8, 20, 15, 3, 12]
     */
    public static List<Integer> createNumberList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(5);
        numbers.add(8);
        numbers.add(20);
        numbers.add(15);
        numbers.add(3);
        numbers.add(12);
        return numbers;
    }

    /**
     * Print numbers in format: [num1, num2, num3, ...]
     */
    public static void printList(List<Integer> numbers) {
        System.out.print("[");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i));
            if (i < numbers.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
