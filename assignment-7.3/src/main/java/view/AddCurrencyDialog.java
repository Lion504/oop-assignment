package view;

import controller.CurrencyController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Currency;

/**
 * Dialog for adding a new currency to the database
 * This creates a new window where users can enter currency information
 */
public class AddCurrencyDialog {
    private final Stage parentStage;
    private final CurrencyController controller;
    private Stage dialogStage;
    private boolean currencyAdded = false;

    public AddCurrencyDialog(Stage parentStage, CurrencyController controller) {
        this.parentStage = parentStage;
        this.controller = controller;
    }

    /**
     * Show the add currency dialog and wait for user input
     * @return true if a currency was added, false if cancelled
     */
    public boolean showAndWait() {
        createDialog();
        dialogStage.showAndWait();
        return currencyAdded;
    }

    private void createDialog() {
        dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(parentStage);
        dialogStage.setTitle("Add New Currency");
        dialogStage.setResizable(false);

        // Create the form layout with modern styling
        GridPane grid = new GridPane();
        grid.getStyleClass().add("dialog-root");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // Dialog title
        Label titleLabel = new Label("Add New Currency");
        titleLabel.getStyleClass().add("dialog-title");

        // Currency name field
        Label nameLabel = new Label("Currency Name:");
        nameLabel.getStyleClass().add("field-label");
        TextField nameField = new TextField();
        nameField.setPromptText("e.g., US Dollar");
        nameField.getStyleClass().add("modern-text-field");
        nameField.setPrefWidth(250);

        // Currency code field
        Label codeLabel = new Label("Currency Code:");
        codeLabel.getStyleClass().add("field-label");
        TextField codeField = new TextField();
        codeField.setPromptText("e.g., USD");
        codeField.getStyleClass().add("modern-text-field");
        codeField.setPrefWidth(250);
        // Limit to 3 characters and convert to uppercase
        codeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 3) {
                codeField.setText(oldValue);
            } else {
                codeField.setText(newValue.toUpperCase());
            }
        });

        // Exchange rate field
        Label rateLabel = new Label("Exchange Rate to USD:");
        rateLabel.getStyleClass().add("field-label");
        TextField rateField = new TextField();
        rateField.setPromptText("e.g., 1.25");
        rateField.getStyleClass().add("modern-text-field");
        rateField.setPrefWidth(250);

        // Instructions label
        Label instructionLabel = new Label("Enter the exchange rate where 1 USD = X units of your currency");
        instructionLabel.getStyleClass().add("dialog-instruction");

        // Buttons
        Button saveButton = new Button("Add Currency");
        saveButton.setDefaultButton(true);
        saveButton.getStyleClass().add("primary-button");
        saveButton.setPrefWidth(120);

        Button cancelButton = new Button("Cancel");
        cancelButton.setCancelButton(true);
        cancelButton.getStyleClass().add("cancel-button");
        cancelButton.setPrefWidth(120);

        // Button layout
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(cancelButton, saveButton);

        // Add components to grid
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(codeLabel, 0, 2);
        grid.add(codeField, 1, 2);
        grid.add(rateLabel, 0, 3);
        grid.add(rateField, 1, 3);
        grid.add(instructionLabel, 0, 4, 2, 1);
        grid.add(buttonBox, 0, 5, 2, 1);

        // Event handlers
        saveButton.setOnAction(e -> handleSave(nameField.getText(), codeField.getText(), rateField.getText()));
        cancelButton.setOnAction(e -> dialogStage.close());

        // Create scene and show
        Scene scene = new Scene(grid, 450, 350);

        // Load the CSS file for the dialog
        String cssFile = getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(cssFile);

        dialogStage.setScene(scene);
    }

    private void handleSave(String name, String code, String rateText) {
        try {
            // Validate input
            if (name.trim().isEmpty()) {
                showError("Currency name cannot be empty!");
                return;
            }

            if (code.trim().isEmpty()) {
                showError("Currency code cannot be empty!");
                return;
            }

            if (code.length() != 3) {
                showError("Currency code must be exactly 3 characters!");
                return;
            }

            if (rateText.trim().isEmpty()) {
                showError("Exchange rate cannot be empty!");
                return;
            }

            double rate;
            try {
                rate = Double.parseDouble(rateText.trim());
                if (rate <= 0) {
                    showError("Exchange rate must be a positive number!");
                    return;
                }
            } catch (NumberFormatException e) {
                showError("Please enter a valid number for exchange rate!");
                return;
            }

            // Create new currency object
            Currency newCurrency = new Currency();
            newCurrency.setName(name.trim());
            newCurrency.setCode(code.trim().toUpperCase());
            newCurrency.setRate(rate);

            // Add currency through controller
            controller.addNewCurrency(newCurrency);

            // Show success message
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Currency '" + code.trim().toUpperCase() + "' has been added successfully!");
            successAlert.showAndWait();

            currencyAdded = true;
            dialogStage.close();

        } catch (Exception e) {
            showError("Error adding currency: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
