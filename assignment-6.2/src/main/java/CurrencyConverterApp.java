import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.CurrencyController;
import model.Currency;
import view.CurrencyView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class that starts our Currency Converter application.
 */
public class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a list of currencies we want to use in our converter
        List<Currency> currencies = createCurrencyList();

        // Create the view (what we can see)
        CurrencyView view = new CurrencyView(currencies);

        // Create the controller (the brain that makes everything work)
        CurrencyController controller = new CurrencyController(view);

        // Create a scene with our view inside
        Scene scene = new Scene(view.getMainLayout(), 350, 300);

        // Set up the main window
        primaryStage.setTitle("My Currency Converter");
        primaryStage.setScene(scene);

        // Show the window
        primaryStage.show();
    }

    /**
     * Create a list of currencies with their exchange rates
     * */
    private List<Currency> createCurrencyList() {
        List<Currency> currencies = new ArrayList<>();

        // Add some common currencies
        // The rate is compared to USD (which is set to 1.0)
        currencies.add(new Currency("USD", 1.0));     // US Dollar (base currency)
        currencies.add(new Currency("EUR", 0.91));    // Euro
        currencies.add(new Currency("GBP", 0.78));    // British Pound
        currencies.add(new Currency("JPY", 148.0));   // Japanese Yen
        currencies.add(new Currency("CAD", 1.36));    // Canadian Dollar
        currencies.add(new Currency("CNY", 7.25));      // Chinese Yuan
        currencies.add(new Currency("AUD", 1.50));      // Australian Dollar

        return currencies;
    }

    /**
     * Main method - this is where Java starts running our program
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
