import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
// handle array size
        int size;
        while (true) {
            System.out.print("How many numbers of integer you wish in array: ");
            if (input.hasNextInt()){
                size = input.nextInt();
                if (size >= 2) {
                    break;
                } else {
                    System.out.println("Size must be at least 2. Try again.");
                }
            }  else {
                System.out.println("Must be an integer. Try again.");
                input.next();//from Scanner class, used to consume previous invalid input, prevent infinite loop
            }
        }
// handle array value
        int[] intList = new int[size];
        int counter = 1;
        for (int i = 0; i < size; i++) {
            while (true) {
                System.out.print("Enter integer " + counter + ": ");
                if (input.hasNextInt()){
                    int num = input.nextInt();
                    intList[i] = num;
                    counter += 1;
                    break;
                } else {
                    System.out.println("Invalid input. Try again.");
                    input.next();//tried, if use input.nextLine(); will give warning + Enter integer + Enter integer
                }
            }

        }
        //list number
        System.out.print("Number list: ");
        for (int num : intList) {
            System.out.print(num + " ");
        }

        /*int[] plusResults = new int[size*size];
        int index = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int plusResult = intList[x] + intList[y];
                plusResults[index++] = plusResult;
            }
        }
        int max = Arrays.stream(plusResults).max().getAsInt();
        System.out.println("\nThe Max number is: " + max);*/
        // compare
        int maxListIndex1 = 0;
        for (int i = 0; i < size; i++) {
            if (intList[i] > intList[maxListIndex1]) {
                maxListIndex1 = i;
            }
        }

        // set sentinel value
        int maxListIndex2 = -1;
        for (int j = 0; j < size; j++) {
            if ((maxListIndex2 == -1 || intList[j] > intList[maxListIndex2]) && j != maxListIndex1) {
                maxListIndex2 = j;
            }
        }

        int max1 = intList[maxListIndex1];
        int max2 = intList[maxListIndex2];
        int sum = max1 + max2;
        System.out.println("\nSum is: " + sum);
        System.out.println("Indexer: " + maxListIndex1 + " " + maxListIndex2);

        /*if (intList.length <2) {
            System.out.println("Not enough numbers");
        } else {
            Arrays.sort(intList);
            int max = intList[size - 1] + intList[size - 2];
            System.out.println("\nThe max sum is: " + intList[size - 1] + " + " + intList[size - 2] + " = " + max);
        }*/

    }
}
