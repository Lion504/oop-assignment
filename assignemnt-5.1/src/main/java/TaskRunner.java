import java.util.Scanner;

/**
 * Master Runner for Assignment 5.1
 * Allows you to run any task without changing run configurations
 */
public class TaskRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Assignment 5.1 Task Runner ===");
        System.out.println("Available tasks:");
        System.out.println("1. Task1 - Multi-threaded Number Printing");
        System.out.println("2. Task2 - Parallel Summation");
        System.out.println("3. Run All Tasks");
        System.out.println("0. Exit");
        System.out.print("Select a task to run (0-3): ");
        System.out.flush(); // Force output to appear immediately

        int choice = scanner.nextInt();
        System.out.println();

        switch (choice) {
            case 1:
                System.out.println("🏃 Running Task1...");
                System.out.println("=====================================");
                try {
                    Task1 task1 = new Task1();
                    task1.startPrinting(20);
                } catch (InterruptedException e) {
                    System.err.println("Task1 was interrupted: " + e.getMessage());
                }
                break;

            case 2:
                System.out.println("🏃 Running Task2...");
                System.out.println("=====================================");
                Task2.main(new String[]{});
                break;

            case 3:
                System.out.println("🏃 Running All Tasks...");
                System.out.println("=====================================");

                // Run Task1
                System.out.println(">>> TASK 1: Multi-threaded Number Printing <<<");
                try {
                    Task1 task1 = new Task1();
                    task1.startPrinting(10); // Shorter for demo
                } catch (InterruptedException e) {
                    System.err.println("Task1 was interrupted: " + e.getMessage());
                }

                System.out.println("\n>>> TASK 2: Parallel Summation <<<");
                Task2.main(new String[]{});
                break;

            case 0:
                System.out.println("👋 Goodbye!");
                break;

            default:
                System.out.println("❌ Invalid choice. Please select 0-3.");
        }

        scanner.close();
    }
}
