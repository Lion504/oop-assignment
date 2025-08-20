import java.util.Scanner;
import java.util.Random;
public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] firstName = {"Juhani", "Pekka", "Matti", "Timo", "Juha",
                "Antti", "Mikko", "Janne", "Marko", "Ville",
                "Maria", "Anna", "Johanna", "Liisa", "Sofia",
                "Emilia", "Anni", "Laura", "Aino", "Ella"
        };
        String[] lastName = {
                "Korhonen", "Virtanen", "Mäkinen", "Nieminen", "Mäkelä",
                "Hämäläinen", "Laine", "Heikkinen", "Koskinen", "Järvinen",
                "Lehtonen", "Lehtinen", "Saarinen", "Salminen", "Heinonen",
                "Heikkilä", "Salmi", "Kettunen", "Hiltunen", "Savolainen"
        };

        System.out.print("How many names do you want: (Enter integer) ");
        int times = sc.nextInt();
        Random random = new Random();

        System.out.print(times + " Random name generating... \n");

        for (int i = 0; i < times; i++) {
            int randomFirstIndex = random.nextInt(firstName.length);
            int randomLastIndex = random.nextInt(lastName.length);

            String name = firstName[randomFirstIndex] + " " + lastName[randomLastIndex];
            System.out.print("Random Name " + i + ": " + name + "\n");
        }
    }


}