import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class FibonacciCSV {
    public static void main(String[] args) {
        long[] fibonacciSeries = new long[60];
        fibonacciSeries[0] = 0;
        fibonacciSeries[1] = 1;

        for (int i = 2; i < 60; i++) {
            fibonacciSeries[i] = fibonacciSeries[i - 1] + fibonacciSeries[i - 2];
        }

        try {
            FileWriter fileWriter = new FileWriter("fibonacci_series.csv");

            // Correct the LongConsumer implementation using an anonymous method
            Arrays.stream(fibonacciSeries).forEach((long value) -> {
                try {
                    fileWriter.write(value + "\n"); // Write each Fibonacci number to a new line
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Display();
    }

    public static void Display() {

        try (BufferedReader fileReader = new BufferedReader(new FileReader("fibonacci_series.csv"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}