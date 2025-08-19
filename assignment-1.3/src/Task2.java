import java.util.Scanner;
public class Task2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a binary number: ");
        String num = input.nextLine();
        int decimal = 0;
        int len = num.length();
        for (int i = 0; i<len; i++){
            if (num.charAt(i) == '1'){
                decimal += Math.pow(2, len - i - 1);
            }
        }
        System.out.println("Decimal is:" + decimal);
    }
}
