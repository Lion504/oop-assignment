import java.util.Arrays;
import java.util.Scanner;
public class Task2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("How many numbers of integer you wish in array: ");
        int size = input.nextInt();
        int[] intList = new int[size];
        int counter = 1;

        for (int i = 0; i < size; i++) {
            System.out.print("Enter integer " + counter + ": ");
            int num = input.nextInt();
            intList[i] = num;
            counter += 1;

        }

        System.out.print("Number list: ");
        for (int num : intList) {
            System.out.print(num + " ");
        }

        /*if (intList.length <2) {
            System.out.println("Not enough numbers");
        } else {
            Arrays.sort(intList);
            int max = intList[size - 1] + intList[size - 2];
            System.out.println("\nThe max sum is: " + intList[size - 1] + " + " + intList[size - 2] + " = " + max);
        }*/

    }
}
