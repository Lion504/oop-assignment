import java.util.Scanner;
public class Task2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Right triangle hypotenuse calculator");

        System.out.println("Type 1st lengths of the legs of a right triangle: ");
        Double inputLengths_a = input.nextDouble();

        System.out.println("Type 2nd length of the legs of a right triangle: ");
        Double inputLengths_b = input.nextDouble();

        Double hypotenuse = Math.sqrt(Math.pow(inputLengths_a, 2) + Math.pow(inputLengths_b, 2));
        System.out.println("The hypotenuse for right triangle is:" + hypotenuse);
    }
}
