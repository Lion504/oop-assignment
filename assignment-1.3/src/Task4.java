import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int CorrectCount;
        do {
            CorrectCount = 0;

            for (int i = 1; i <= 10; i++) {
                int a = (int) (Math.random() * 10) + 1;
                int b = (int) (Math.random() * 10) + 1;

                System.out.println("Q" + i + ": " + a + " * " + b + " = ");
                int answer = sc.nextInt();

                int c = a * b;
                if (answer == c) {
                    System.out.println("✅");
                    CorrectCount++;
                } else {
                    System.out.println("❌, correct answer is " + c);
                }
            }
            System.out.println("You got " + CorrectCount + " points!");
            if (CorrectCount < 10) {
                System.out.println("Please try again!");
            }
        } while (CorrectCount < 10);
        System.out.println("🎆 Congratulation, you mastering the multiplication tables! 🎆");
    }
}
