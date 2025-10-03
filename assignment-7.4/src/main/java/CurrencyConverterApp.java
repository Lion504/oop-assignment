import controller.CurrencyController;
import dao.CurrencyDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Currency;
import util.JPAUtil;
import view.CurrencyView;

import java.util.List;

/**
 * This is the main class that starts our Currency Converter application.
 * Now uses JPA for database operations instead of JDBC
 */
public class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialize JPA first
            JPAUtil.initEntityManagerFactory();
            System.out.println("JPA initialized successfully");

            // Test database connection
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
            view.setParentStage(primaryStage);  // Set parent stage for dialogs

            // Create the controller (the brain that makes everything work)
            // The controller will handle database operations through DAO
            CurrencyController controller = new CurrencyController(view);

            // Create a scene with our view inside
            Scene scene = new Scene(view.getMainLayout(), 500, 550);

            // Load the modern CSS stylesheet
            String cssFile = getClass().getResource("/styles.css").toExternalForm();
            scene.getStylesheets().add(cssFile);

            // Set up the main window
            primaryStage.setTitle("Currency Converter - Modern Edition");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // Show the window
            primaryStage.show();

            // Display welcome message with database info
            displayWelcomeMessage(currencies.size());

        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            showDatabaseErrorAndExit(primaryStage);
        }
    }

    /**
     * Test database connection using CurrencyDAO
     * @return true if database is accessible, false otherwise
     */
    private boolean testDatabaseConnection() {
        try {
            CurrencyDAO currencyDAO = new CurrencyDAO();
            return currencyDAO.testDatabaseConnection();
        } catch (Exception e) {
            System.err.println("Database connection test failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Load currencies from database using JPA - this is now the ONLY source of currency data
     * @return List of currencies from database
     */
    private List<Currency> loadCurrenciesFromDatabase() {
        try {
            CurrencyDAO currencyDAO = new CurrencyDAO();
            List<Currency> currencies = currencyDAO.getAllCurrencies();

            if (!currencies.isEmpty()) {
                System.out.println("Successfully loaded " + currencies.size() + " currencies from database using JPA");
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
        alert.setContentText("The Currency Converter requires a database connection to work.\n\n" +
                           "Please ensure:\n" +
                           "1. MariaDB server is running\n" +
                           "2. Database 'currency_converter_db' exists\n" +
                           "3. User 'appuser' has proper permissions\n" +
                           "4. Currency data is loaded in the database\n\n" +
                           "Run the database_setup.sql script first!");

        alert.showAndWait();
        System.err.println("Application cannot start without database connection. Exiting...");
        System.exit(1);
    }

    /**
     * Display welcome message with application info
     * @param currencyCount Number of currencies loaded from database
     */
    private void displayWelcomeMessage(int currencyCount) {
        System.out.println("=================================");
        System.out.println("Currency Converter Application Started");
        System.out.println("Database Integration: JPA with MariaDB");
        System.out.println("Currencies Loaded from Database: " + currencyCount);
        System.out.println("=================================");
    }

    /**
     * Clean up JPA resources when application stops
     */
    @Override
    public void stop() {
        JPAUtil.close();
        System.out.println("Application stopped and JPA resources cleaned up");
    }

    /**
     * Main method to launch the JavaFX application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
