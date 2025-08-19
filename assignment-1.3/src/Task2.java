import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String num;
        while (true) {
            System.out.print("Please enter a binary number: ");
            num = input.nextLine();

            if (num.isEmpty()){
                System.out.println("Input is empty. Please try again.");
                continue;
            }

            boolean Valid = true;
            for (int i = 0; i < num.length(); i++) {
                if (num.charAt(i) != '1' && num.charAt(i) != '0') {
                    Valid = false;
                    System.out.print("Please enter valid binary number only 1 or 0, ");
                    break;
                }

            }
            if (Valid) {
                break;
            } else {
                System.out.println("Please try again.");
            }

        }

        int decimal = 0;
        int len = num.length();
        for (int i = 0; i < len; i++) {
            if (num.charAt(i) == '1') {
                decimal += Math.pow(2, len - i - 1);
            }
        }
        System.out.println("Decimal is:" + decimal);
    }
}
