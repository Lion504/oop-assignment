import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size;
        //handle size
        while (true) {
            System.out.print("Enter the size of the array: ");
            if (sc.hasNextInt()) {
                size = sc.nextInt();
                if (size >= 2) {
                    break;
                } else {
                    System.out.println("Size must be at least 2. Try again.");
                }
            } else {
                System.out.println("Invalid input, try again!");
                sc.next();
            }
        }

        //get input
        int counter = 0;
        int[] numlist = new int[size];
        System.out.print("Enter the integers into the array:\n");
        for (int i = 0; i < size; i++) {
            System.out.print("Enter integer " + (counter += 1) + ": ");
            while (true) {
                if (sc.hasNextInt()) {
                    numlist[i] = sc.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input, try again!");
                    sc.next();
                }
            }
        }

        //show num list
        /*System.out.print("Original list:\n");
        for (int num : numlist) {
            System.out.print(num + " ");
        }*/

        // find unique num
        System.out.print("\nThe array without duplicates:\n");
        int[] uniquelist = new int[size];
        int n = 0;
        for (int j = 0; j < numlist.length; j++) {
            boolean isDuplicate = false;

            for (int k = 0; k < n; k++) {
                if (uniquelist[k] == numlist[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            // add unique num
            if (!isDuplicate) {
                uniquelist[n] = numlist[j];
                n++;
            }
        }
        //show unique num
        for (int m = 0; m < n; m++) {
            System.out.print(uniquelist[m] + " ");
        }
    }
}
