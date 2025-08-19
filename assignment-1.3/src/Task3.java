import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b;
        System.out.print("Prim number checker\n");
        do {
            System.out.print("Please enter 1st positive integer number: ");
            a = sc.nextInt();
            if (a <= 1) {
                System.out.println("Please enter an integer > 1");
            } else {
                System.out.println("1st num is valid");
            }
        } while (a <= 1);

        do {
            System.out.print("Please enter 2nd positive integer number: ");
            b = sc.nextInt();
            if (b <= a) {
                System.out.println("2nd num should greater than " + a);
            } else {
                System.out.println("2nd is valid \n🔎check prime numbers now...");
            }
        } while (b < a);


        /*System.out.println("All numbers:");
        for (int i = a; i <= b; i++) {
            System.out.print(i + " ");
        }*/

        System.out.println("\n Check result:");

        for (int i = a; i <= b; i++) {
            if (i == 2 || i == 3) {
                System.out.print(i + " prime number ✅\n");
            } else if (i % 2 == 0 || i % 3 == 0) {
                System.out.print(i + " prime number ❌\n");
            } else {
                for (int j = 5; j * j <= i; j += 6) {
                    if (i % j == 0 || i % (j + 2) == 0) {
                        System.out.print(i + " prime number ❌\n");
                    } else System.out.print(i + " prime number ✅\n");
                }
            }
        }
    }
}
