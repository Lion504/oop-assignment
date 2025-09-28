package controller;

import dao.CurrencyDAO;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Currency;
import model.CurrencyConverter;
import view.AddCurrencyDialog;
import view.CurrencyView;

import java.util.List;

/**
 * This class connects our view (what we see) with our model (how things work).
 * Now enhanced with database integration using DAO pattern and new currency functionality
 */
public class CurrencyController {
    private final CurrencyView view;
    private CurrencyConverter converter;
    private CurrencyDAO currencyDAO;
    private List<Currency> currencies;

    /**
     * Create a new controller that connects the view with the model
     * Now loads currencies from database instead of hardcoded values
     */
    public CurrencyController(CurrencyView view) {
        this.view = view;
        this.currencyDAO = new CurrencyDAO();

        // Load currencies from database
        this.currencies = loadCurrenciesFromDatabase();
        this.converter = new CurrencyConverter(currencies);

        // Set up what happens when buttons are clicked
        setupEventHandlers();

        // Display database statistics
        displayDatabaseInfo();
    }

    /**
     * Show dialog to add a new currency to the database
     * @param parentStage The parent stage for the dialog
     */
    public void showAddCurrencyDialog(Stage parentStage) {
        AddCurrencyDialog dialog = new AddCurrencyDialog(parentStage, this);
        boolean currencyAdded = dialog.showAndWait();

        if (currencyAdded) {
            // Refresh the currency data in the view
            refreshCurrencies();
        }
    }

    /**
     * Add a new currency to the database
     * @param currency Currency to add
     * @throws RuntimeException if insertion fails
     */
    public void addNewCurrency(Currency currency) {
        currencyDAO.insertCurrency(currency);
    }

    /**
     * Refresh currencies from database and update the view
     */
    public void refreshCurrencies() {
        try {
            // Reload currencies from database
            this.currencies = currencyDAO.getAllCurrencies();
            this.converter = new CurrencyConverter(currencies);

            // Update the view with new currencies
            view.updateCurrencies(currencies);

            System.out.println("Currencies refreshed successfully. Total: " + currencies.size());

        } catch (Exception e) {
            System.err.println("Error refreshing currencies: " + e.getMessage());
            showError("Failed to refresh currency list: " + e.getMessage());
        }
    }

    /**
     * Load currencies from database - now JPA-based, no fallback
     * @return List of currencies
     */
    private List<Currency> loadCurrenciesFromDatabase() {
        List<Currency> currencies = currencyDAO.getAllCurrencies();

        if (currencies.isEmpty()) {
            System.err.println("Warning: No currencies loaded from database");
            // Show error in UI if database fails
            showError("Database connection failed. Cannot load currency data.\n\nPlease ensure:\n" +
                     "1. MariaDB server is running\n" +
                     "2. Database 'currency_converter_db' exists\n" +
                     "3. Currency data is loaded in the database");
        } else {
            System.out.println("Successfully loaded " + currencies.size() + " currencies from database using JPA");
        }

        return currencies;
    }

    /**
     * Display some database information for debugging
     */
    private void displayDatabaseInfo() {
        try {
            List<Currency> currencies = currencyDAO.getAllCurrencies();

            System.out.println("=== Database Information ===");
            System.out.println("Total currencies loaded: " + currencies.size());
            System.out.println("=".repeat(30));

        } catch (Exception e) {
            System.err.println("Error getting database info: " + e.getMessage());
        }
    }

    /**
     * Connect buttons to actions
     */
    private void setupEventHandlers() {
        // When the Convert button is clicked, run the convertCurrency method
        view.getConvertButton().setOnAction(event -> convertCurrency());

        // When the Add Currency button is clicked, show the add currency dialog
        view.getAddCurrencyButton().setOnAction(event ->
            showAddCurrencyDialog(view.getParentStage()));
    }

    /**
     * Convert the currency and display the result
     */
    private void convertCurrency() {
        try {
            // Get the amount from the text field
            String amountText = view.getAmountField().getText();

            // Check if the amount is valid
            if (amountText.isEmpty()) {
                showError("Please enter an amount!");
                return;
            }

            // Convert the text to a number
            double amount = Double.parseDouble(amountText);

            // Get the selected currencies
            Currency fromCurrency = view.getFromCurrency().getValue();
            Currency toCurrency = view.getToCurrency().getValue();

            // Check if currencies are selected
            if (fromCurrency == null || toCurrency == null) {
                showError("Please select both currencies!");
                return;
            }

            // Do the conversion
            double result = converter.convert(amount, fromCurrency, toCurrency);

            // Display the result with 2 decimal places
            String formattedResult = String.format("%.2f %s = %.2f %s",
                    amount, fromCurrency.getCode(),
                    result, toCurrency.getCode());

            // Update result label with modern styling
            view.getResultLabel().setText(formattedResult);
            view.getResultLabel().getStyleClass().remove("empty");
            if (!view.getResultLabel().getStyleClass().contains("result-label")) {
                view.getResultLabel().getStyleClass().add("result-label");
            }

        } catch (NumberFormatException e) {
            // If the user enters something that's not a number
            showError("Please enter a valid number!");
        } catch (Exception e) {
            // For any other errors that might happen
            showError("An error occurred: " + e.getMessage());
        }
    }


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
