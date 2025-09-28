package controller;

import javafx.scene.control.Alert;
import model.Currency;
import model.CurrencyConverter;
import view.CurrencyView;

/**
 * This class connects our view (what we see) with our model (how things work).
 */
public class CurrencyController {
    // References to our view and models
    private CurrencyView view;
    private CurrencyConverter converter;

    /**
     * Create a new controller that connects the view with the model
     */
    public CurrencyController(CurrencyView view) {
        this.view = view;
        this.converter = new CurrencyConverter();

        // Set up what happens when buttons are clicked
        setupEventHandlers();
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

}
