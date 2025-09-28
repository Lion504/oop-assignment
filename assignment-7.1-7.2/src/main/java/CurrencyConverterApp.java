import controller.CurrencyController;
import dao.CurrencyDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Currency;
import model.DataSource;
import view.CurrencyView;

import java.util.List;

/**
 * This is the main class that starts our Currency Converter application.
 * Now fully database-dependent using MariaDB - no hardcoded fallback currencies
 */
public class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Test database connection first
        if (!testDatabaseConnection()) {
            showDatabaseErrorAndExit(primaryStage);
            return;
        }

        // Load currencies from database (must succeed for app to work)
        List<Currency> currencies = loadCurrenciesFromDatabase();

        if (currencies.isEmpty()) {
            showDatabaseErrorAndExit(primaryStage);
            return;
        }

        // Create the view (what we can see) with database currencies
        CurrencyView view = new CurrencyView(currencies);

        // Create the controller (the brain that makes everything work)
        // The controller will handle database operations through DAO
        CurrencyController controller = new CurrencyController(view);

        // Create a scene with our view inside
        Scene scene = new Scene(view.getMainLayout(), 400, 350);

        // Set up the main window
        primaryStage.setTitle("Currency Converter - Database Edition");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        // Show the window
        primaryStage.show();

        // Display welcome message with database info
        displayWelcomeMessage(currencies.size());
    }

    /**
     * Test database connection before starting the application
     * @return true if database is accessible, false otherwise
     */
    private boolean testDatabaseConnection() {
        try {
            return DataSource.getInstance().testConnection();
        } catch (Exception e) {
            System.err.println("Database connection test failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Load currencies from database
     * @return List of currencies from database
     */
    private List<Currency> loadCurrenciesFromDatabase() {
        try {
            CurrencyDAO currencyDAO = new CurrencyDAO();
            List<Currency> currencies = currencyDAO.getAllCurrencies();

            if (!currencies.isEmpty()) {
                System.out.println("Successfully loaded " + currencies.size() + " currencies from database");
                return currencies;
            } else {
                System.err.println("No currencies found in database!");
            }
        } catch (Exception e) {
            System.err.println("Error loading from database: " + e.getMessage());
        }

        return List.of(); // Return empty list if database fails
    }

    /**
     * Show error message and exit application if database is not available
     * @param primaryStage The primary stage to show error dialog
     */
    private void showDatabaseErrorAndExit(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Connection Error");
        alert.setHeaderText("Cannot Connect to Database");
        alert.setContentText("The Currency Converter requires a database connection to work.\n");

        alert.showAndWait();
        System.err.println("Application cannot start without database connection. Exiting...");
        System.exit(1);
    }

    /**
     * Display welcome message with application info
     * @param currencyCount Number of currencies loaded from database
     */
    private void displayWelcomeMessage(int currencyCount) {
        System.out.println("=".repeat(35));
        System.out.println("Currency Converter Application Started");
        System.out.println("Currencies Loaded from MariaDB Database: " + currencyCount);
        System.out.println("=".repeat(35));
    }

    /**
     * Main method to launch the JavaFX application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
