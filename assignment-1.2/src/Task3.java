import java.util.Scanner;
/*
Leiviskä, naula and luoti are medieval Finnish units of measurement.
One leiviskä is 20 naula.
One naula is 32 luoti.
One luoti is 13.28 grams.
*/
public class Task3 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("medieval Finnish units Converter");
        System.out.print("Enter a number of grams: ");
        Double num = input.nextDouble();

        Double luoti = num / 13.28;
        Double naula = luoti / 32;
        Double leiviskä = naula / 20;

        System.out.print("Weight (g): " + num +"\n");
        System.out.printf("%.2f grams is %.2f leiviskä, %.2f naula, %.2f luoti.", num, leiviskä, naula, luoti);
    }

}
