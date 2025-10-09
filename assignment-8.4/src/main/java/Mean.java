import java.util.Arrays;

/**
 * Task 1: Calculate mean of an array in functional way (no for-loops)
 */
public class Mean {

    /**
     * Calculate mean using Stream API (functional approach)
     */
    public static double calculateMean(int[] numbers) {
        return Arrays.stream(numbers)
                .average()
                .orElse(0.0);
    }


    public static void main(String[] args) {
        System.out.println("===== CALCULATE MEAN (FUNCTIONAL WAY) =====\n");

        // Test case 1: Normal array
        int[] numbers1 = {10, 20, 30, 40, 50};
        System.out.println("Test 1: Array = " + Arrays.toString(numbers1));
        System.out.println("Mean = " + calculateMean(numbers1));

        // Test case 2: Array with negative numbers
        int[] numbers2 = {-5, 10, -15, 20, 25};
        System.out.println("\nTest 2: Array = " + Arrays.toString(numbers2));
        System.out.println("Mean = " + calculateMean(numbers2));
    }
}
