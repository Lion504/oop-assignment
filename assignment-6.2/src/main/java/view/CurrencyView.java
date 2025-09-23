package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Currency;

import java.util.List;

/**
 * This class creates the visual part of our Currency Converter.
 */
public class CurrencyView {
    // These are all the parts that we can see and click on our converter
    private TextField amountField;        // Where we type how much money to convert
    private ComboBox<Currency> fromCurrency;   // Dropdown to pick what currency we're converting from
    private ComboBox<Currency> toCurrency;     // Dropdown to pick what currency we're converting to
    private Button convertButton;         // Button we click to convert the money
    private Label resultLabel;            // Shows the result after conversion
    private VBox mainLayout;              // The main container that holds everything

    /**
     * Create the Currency Converter view with a list of available currencies
     */
    public CurrencyView(List<Currency> currencies) {
        // Build the user interface
        createUI(currencies);
    }

    /**
     * This method builds all the visual parts of our converter
     */
    private void createUI(List<Currency> currencies) {
        // Create the main container with some spacing
        mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(20));

        // Create a grid to organize everything neatly
        GridPane grid = new GridPane();
        grid.setVgap(10);  // Vertical spacing between elements
        grid.setHgap(10);  // Horizontal spacing between elements

        // Create a title for our converter
        Label titleLabel = new Label("Currency Converter");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Create all the input fields and buttons

        // 1. Amount field with label
        Label amountLabel = new Label("Amount:");
        amountField = new TextField();
        amountField.setPromptText("Enter amount");

        // 2. From currency dropdown with label
        Label fromLabel = new Label("From Currency:");
        fromCurrency = new ComboBox<>();

        // 3. To currency dropdown with label
        Label toLabel = new Label("To Currency:");
        toCurrency = new ComboBox<>();

        // 4. Add all available currencies to both dropdowns
        fromCurrency.getItems().addAll(currencies);
        toCurrency.getItems().addAll(currencies);

        // Select the first currency by default
        if (!currencies.isEmpty()) {
            fromCurrency.setValue(currencies.get(0));
            // If there's at least 2 currencies, select the second one as the target
            if (currencies.size() > 1) {
                toCurrency.setValue(currencies.get(1));
            } else {
                toCurrency.setValue(currencies.get(0));
            }
        }

        // 5. Convert button
        convertButton = new Button("Convert");
        convertButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        // 6. Result display
        resultLabel = new Label("Result will appear here");
        resultLabel.setStyle("-fx-font-size: 14px;");

        // Add everything to the grid in the right positions
        grid.add(titleLabel, 0, 0, 2, 1);  // Span across 2 columns
        grid.add(amountLabel, 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(fromLabel, 0, 2);
        grid.add(fromCurrency, 1, 2);
        grid.add(toLabel, 0, 3);
        grid.add(toCurrency, 1, 3);
        grid.add(convertButton, 1, 4);
        grid.add(resultLabel, 0, 5, 2, 1);  // Span across 2 columns

        // Add the grid to our main container
        mainLayout.getChildren().add(grid);
    }

    // Getters so the controller can access our UI components
    public VBox getMainLayout() {
        return mainLayout;
    }
    public TextField getAmountField() {
        return amountField;
    }
    public ComboBox<Currency> getFromCurrency() {
        return fromCurrency;
    }
    public ComboBox<Currency> getToCurrency() {
        return toCurrency;
    }
    public Button getConvertButton() {
        return convertButton;
    }
    public Label getResultLabel() {
        return resultLabel;
    }
}
