import java.util.ArrayList;
import java.util.List;

/**
 * Task 2: Collection Operations with Lambdas
 */
public class CollectionMain {

    public static void main(String[] args) {
        System.out.println("===== COLLECTION OPERATIONS WITH LAMBDAS =====\n");

        // Original list
        System.out.println("🔢 Original List:");
        List<Integer> numbers = NumberListHelper.createNumberList();
        NumberListHelper.printList(numbers);

        // Operation 1: Filter even numbers
        System.out.println("\n1️⃣ Filter Even Numbers (Keep Only Odd):");
        List<Integer> filteredList = new ArrayList<>(NumberListHelper.createNumberList());
        NumberOperations.filterEvenNumbers(filteredList);
        System.out.print("   Result: ");
        NumberListHelper.printList(filteredList);
        System.out.println("   Lambda used: num -> num % 2 == 0");

        // Operation 2: Double the odd numbers
        System.out.println("\n2️⃣ Double the Odd Numbers:");
        List<Integer> doubledList = new ArrayList<>(NumberListHelper.createNumberList());
        NumberOperations.doubleOddNumbers(doubledList);
        System.out.print("   Result: ");
        NumberListHelper.printList(doubledList);
        System.out.println("   Lambda used: num -> (num % 2 != 0) ? num * 2 : num");

        // Operation 3: Sum all numbers
        System.out.println("\n3️⃣ Sum of All Numbers:");
        List<Integer> sumList = new ArrayList<>(NumberListHelper.createNumberList());
        int total = NumberOperations.sumNumbers(sumList);
        System.out.println("   Result: " + total);
        System.out.println("   Lambda used: num -> sum[0] += num");
    }
}
