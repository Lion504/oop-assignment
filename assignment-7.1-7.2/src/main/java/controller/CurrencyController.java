package controller;

import dao.CurrencyDAO;
import javafx.scene.control.Alert;
import model.Currency;
import model.CurrencyConverter;
import view.CurrencyView;

import java.util.List;

/**
 * This class connects our view (what we see) with our model (how things work).
 * Now enhanced with database integration using DAO pattern
 */
public class CurrencyController {
    private final CurrencyView view;
    private final CurrencyConverter converter;
    private CurrencyDAO currencyDAO;

    /**
     * Create a new controller that connects the view with the model
     * Now loads currencies from database instead of hardcoded values
     */
    public CurrencyController(CurrencyView view) {
        this.view = view;
        this.currencyDAO = new CurrencyDAO();

        // Load currencies from database
        List<Currency> currencies = loadCurrenciesFromDatabase();
        this.converter = new CurrencyConverter(currencies);

        // Set up what happens when buttons are clicked
        setupEventHandlers();

        // Display database statistics
        displayDatabaseInfo();
    }

    /**
     * Load currencies from database with fallback to hardcoded values
     * @return List of currencies
     */
    private List<Currency> loadCurrenciesFromDatabase() {
        List<Currency> currencies = currencyDAO.getAllCurrencies();

        if (currencies.isEmpty()) {
            System.out.println("Warning: No currencies loaded from database, using fallback values");
            // If database is empty or unavailable, show error but don't crash
            showWarning("Database connection failed.");
        } else {
            System.out.println("Successfully loaded " + currencies.size() + " currencies from database");
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

            view.getResultLabel().setText(formattedResult);

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
