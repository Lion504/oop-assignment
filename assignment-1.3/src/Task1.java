import java.util.Scanner;

public class Task1 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter number a: ");
        Double a = input.nextDouble();
        System.out.print("Please enter number b: ");
        Double b = input.nextDouble();
        System.out.print("Please enter number c: ");
        Double c = input.nextDouble();
        System.out.println("The equation is: " + a + " X^2" + " + " + b + " + " + c + " = 0");

        Double d = Math.pow(b, 2) - 4 * a * c;

        if (d > 0){
            Double root1 = ((-b + Math.sqrt(d)) / 2 * a);
            Double root2 = ((-b - Math.sqrt(d)) / 2 * a);
            System.out.println("The roots are: " +  root1 + " , " + root2 + ".");
        } else if(d == 0) {
            Double root = ((-b + Math.sqrt(d)) / 2 * a);
            System.out.println("The root is: " + root);
        } else {
            System.out.println("No Real Roots");
        }
    }
}