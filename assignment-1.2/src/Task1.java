/*
Task 1: Fahrenheit to Celsius Converter
Fahrenheit to Celsius Converter Write a program that prompts the user to enter a temperature in Fahrenheit and converts it to Celsius.
Display the converted temperature on the console with one decimal place.
*/
import java.util.Scanner;
public class Task1{
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a Fahrenheit: ");
    double fahrenheit = sc.nextDouble();
    double celsius = (fahrenheit - 32) * 5 / 9;

    String FormatCelsius = String.format("%.2f", celsius);
    System.out.println(fahrenheit + "Fahrenheit: " + FormatCelsius + "Celsius");
    System.out.println(fahrenheit + "Fahrenheit = " + String.format("%.2f",celsius) + "Celsius");
    System.out.println(String.format("%.2f Fahrenheit = %.2f Celsius", fahrenheit, celsius));
    System.out.printf("%.1f Fahrenheit = %.2f Celsius\n", fahrenheit, celsius);
    }
}