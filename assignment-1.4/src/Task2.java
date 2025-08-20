import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int size;
        while (true) {
            System.out.print("How many numbers of integer you wish in array: ");
            size = input.nextInt();
            if (size >= 2) break;
        }

        int[] intList = new int[size];
        int counter = 1;
        for (int i = 0; i < size; i++) {
            System.out.print("Enter integer " + counter + ": ");
            int num = input.nextInt();
            intList[i] = num;
            counter += 1;
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
        System.out.println("Sum is: " + sum);
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
