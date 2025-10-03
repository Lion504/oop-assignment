package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Currency;

import java.util.List;

/**
 * This class creates the visual part of our Currency Converter.
 * Now includes functionality to add new currencies
 */
public class CurrencyView {
    // These are all the parts that we can see and click on our converter
    private TextField amountField;
    private ComboBox<Currency> fromCurrency;   // Dropdown to pick what currency converting from
    private ComboBox<Currency> toCurrency;     // Dropdown to pick what currency converting to
    private Button convertButton;              // Button convert the money
    private Button addCurrencyButton;          // Button to add new currency
    private Label resultLabel;                 // Shows the result after conversion
    private Label exchangeRateLabel;           // Shows exchange rates between selected currencies
    private VBox mainLayout;                   // The main container that holds everything
    private Stage parentStage;                 // Reference to parent stage for dialogs

    /**
     * Create the Currency Converter view with a list of available currencies
     */
    public CurrencyView(List<Currency> currencies) {
        // Build the user interface
        createUI(currencies);
    }

    /**
     * Set the parent stage for opening dialogs
     * @param stage Parent stage
     */
    public void setParentStage(Stage stage) {
        this.parentStage = stage;
    }

    /**
     * This method builds all the visual parts of our converter
     */
    private void createUI(List<Currency> currencies) {
        // Create the main container with modern styling
        mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getStyleClass().add("main-container");

        // Create a grid to organize everything neatly
        GridPane grid = new GridPane();
        grid.getStyleClass().add("form-grid");
        grid.setVgap(15);  // Vertical spacing between elements
        grid.setHgap(15);  // Horizontal spacing between elements

        // Create a title for our converter
        Label titleLabel = new Label("Currency Converter");
        titleLabel.getStyleClass().add("title-label");

        // Create all the input fields and buttons

        // 1. Amount field with label
        Label amountLabel = new Label("Amount:");
        amountLabel.getStyleClass().add("field-label");
        amountField = new TextField();
        amountField.setPromptText("Enter amount to convert");
        amountField.getStyleClass().add("modern-text-field");

        // 2. From currency dropdown with label
        Label fromLabel = new Label("From Currency:");
        fromLabel.getStyleClass().add("field-label");
        fromCurrency = new ComboBox<>();
        fromCurrency.getStyleClass().add("modern-combo-box");
        fromCurrency.setPrefWidth(200);

        // 3. To currency dropdown with label
        Label toLabel = new Label("To Currency:");
        toLabel.getStyleClass().add("field-label");
        toCurrency = new ComboBox<>();
        toCurrency.getStyleClass().add("modern-combo-box");
        toCurrency.setPrefWidth(200);

        // 4. Add all available currencies to both dropdowns
        populateCurrencyDropdowns(currencies);

        // 5. Convert button
        convertButton = new Button("Convert Currency");
        convertButton.getStyleClass().add("primary-button");
        convertButton.setPrefWidth(160);

        // 6. Add Currency button
        addCurrencyButton = new Button("Add New Currency");
        addCurrencyButton.getStyleClass().add("secondary-button");
        addCurrencyButton.setPrefWidth(160);

        // 7. Button layout
        HBox buttonBox = new HBox();
        buttonBox.getStyleClass().add("button-container");
        buttonBox.getChildren().addAll(convertButton, addCurrencyButton);

        // 8. Result display
        resultLabel = new Label("Enter an amount and select currencies to convert");
        resultLabel.getStyleClass().addAll("result-label", "empty");

        // 9. Exchange rate display
        exchangeRateLabel = new Label("Exchange rate will be shown here");
        exchangeRateLabel.getStyleClass().add("exchange-rate-label");

        // Add everything to the grid in the right positions
        grid.add(titleLabel, 0, 0, 2, 1);  // Span across 2 columns
        grid.add(amountLabel, 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(fromLabel, 0, 2);
        grid.add(fromCurrency, 1, 2);
        grid.add(toLabel, 0, 3);
        grid.add(toCurrency, 1, 3);
        grid.add(buttonBox, 0, 4, 2, 1);   // Span across 2 columns
        grid.add(resultLabel, 0, 5, 2, 1); // Span across 2 columns
        grid.add(exchangeRateLabel, 0, 6, 2, 1); // Span across 2 columns

        // Add the grid to our main container
        mainLayout.getChildren().add(grid);

        // Custom cell factory to display flag icons in the currency dropdowns
        Callback<ListView<Currency>, ListCell<Currency>> cellFactory = new Callback<>() {
            @Override
            public ListCell<Currency> call(ListView<Currency> param) {
                return new ListCell<>() {
                    private final ImageView imageView = new ImageView();

                    {
                        // Set fixed size for flag images
                        imageView.setFitWidth(16);
                        imageView.setFitHeight(12);
                        imageView.setPreserveRatio(true);
                    }

                    @Override
                    protected void updateItem(Currency item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Display currency code and name
                            setText(item.getCode() + (item.getName().isEmpty() ? "" : " - " + item.getName()));

                            try {
                                // Load and set the flag image from the URL
                                Image image = new Image(item.getFlagIconUrl(), true); // Load asynchronously
                                imageView.setImage(image);
                                setGraphic(imageView);
                            } catch (Exception e) {
                                // If image loading fails, show no image
                                setGraphic(null);
                                System.err.println("Failed to load flag image for " + item.getCode() + ": " + e.getMessage());
                            }
                        }
                    }
                };
            }
        };

        // Set the custom cell factory for both currency dropdowns
        fromCurrency.setCellFactory(cellFactory);
        fromCurrency.setButtonCell(cellFactory.call(null));
        toCurrency.setCellFactory(cellFactory);
        toCurrency.setButtonCell(cellFactory.call(null));
    }

    /**
     * Populate currency dropdowns with the given currencies
     * @param currencies List of currencies to populate
     */
    private void populateCurrencyDropdowns(List<Currency> currencies) {
        fromCurrency.getItems().clear();
        toCurrency.getItems().clear();

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
    }

    /**
     * Update currencies in the dropdowns (called after adding new currency)
     * @param currencies Updated list of currencies
     */
    public void updateCurrencies(List<Currency> currencies) {
        // Remember current selections
        Currency selectedFrom = fromCurrency.getValue();
        Currency selectedTo = toCurrency.getValue();

        // Update the dropdown contents
        populateCurrencyDropdowns(currencies);

        // Try to restore previous selections if they still exist
        if (selectedFrom != null && currencies.contains(selectedFrom)) {
            fromCurrency.setValue(selectedFrom);
        }
        if (selectedTo != null && currencies.contains(selectedTo)) {
            toCurrency.setValue(selectedTo);
        }

        System.out.println("Currency dropdowns updated with " + currencies.size() + " currencies");
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

    public Button getAddCurrencyButton() {
        return addCurrencyButton;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public Label getExchangeRateLabel() {
        return exchangeRateLabel;
    }

    public Stage getParentStage() {
        return parentStage;
    }

    /**
     * Update the exchange rate display based on selected currencies
     * Shows rates like "1 USD = 0.8546 EUR" and "1 EUR = 1.1702 USD"
     */
    public void updateExchangeRateDisplay(Currency fromCur, Currency toCur) {
        if (fromCur == null || toCur == null) {
            exchangeRateLabel.setText("Select currencies to see exchange rates");
            exchangeRateLabel.getStyleClass().removeAll("rate-info");
            exchangeRateLabel.getStyleClass().add("rate-empty");
            return;
        }

        if (fromCur.equals(toCur)) {
            exchangeRateLabel.setText("Same currency selected");
            exchangeRateLabel.getStyleClass().removeAll("rate-info");
            exchangeRateLabel.getStyleClass().add("rate-empty");
            return;
        }

        // Calculate exchange rates (both directions)
        double fromRate = fromCur.getRate();
        double toRate = toCur.getRate();

        // Rate from "from" currency to "to" currency
        double forwardRate = toRate / fromRate;
        // Rate from "to" currency back to "from" currency
        double reverseRate = fromRate / toRate;

        // Format the display text
        String rateText = String.format("1 %s = %.4f %s\n1 %s = %.4f %s",
            fromCur.getCode(), forwardRate, toCur.getCode(),
            toCur.getCode(), reverseRate, fromCur.getCode());

        exchangeRateLabel.setText(rateText);
        exchangeRateLabel.getStyleClass().removeAll("rate-empty");
        exchangeRateLabel.getStyleClass().add("rate-info");
    }
}
