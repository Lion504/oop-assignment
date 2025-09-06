import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class CSVReader {
    public static void main(String[] args) throws MalformedURLException {
        // Create a new CSVReader object
        URL url = new URL("https://users.metropolia.fi/~jarkkov/temploki.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            // Read the CSV file
            String header = br.readLine();
            int idx = Arrays.asList(header.split(";")).indexOf("UlkoTalo");
            double sum = 0;
            int cnt = 0;
            String line;

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] parts = line.split(";");
                String data = parts[0];
                if (data.startsWith("01.01.2023")) {
                    //System.out.println(parts[0] + " " + parts[idx]);
                    sum += Double.parseDouble(parts[idx].replace(",", "."));
                    cnt++;
                }
            }
            System.out.printf("Sum: %s Count: %s \n01.01.2023 Average Temperature: %.2f ℃", sum, cnt, sum/cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
