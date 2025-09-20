import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * DictionaryView class (View) - Wiktionary-only implementation.
 * Uses FlowPane layout and provides user-friendly interface with loading indicators.
 */
public class DictionaryView {
    private final DictionaryController controller;

    // UI Components
    private TextField searchField;
    private Button searchButton;
    private TextArea resultArea;
    private Label statusLabel;
    private Label instructionLabel;
    private ProgressIndicator loadingIndicator;

    /**
     * Constructor initializes the view with a controller instance.
     *
     * @param controller the dictionary controller to handle business logic
     */
    public DictionaryView(DictionaryController controller) {
        this.controller = controller;
    }

    /**
     * Creates and displays the main application window.
     *
     * @param primaryStage the primary stage for the application
     */
    public void show(Stage primaryStage) {
        primaryStage.setTitle("Wiktionary Dictionary - Live Lookup");

        // Create the main layout
        VBox mainLayout = createMainLayout();

        // Create scene with larger dimensions for better definition display
        Scene scene = new Scene(mainLayout, 800, 700); // Increased from 600x500
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(500); // Increased from 500
        primaryStage.setMinHeight(500); // Increased from 400

        // Handle window closing to properly shutdown resources
        primaryStage.setOnCloseRequest(event -> {
            controller.shutdown();
        });

        // Show the stage
        primaryStage.show();

        // Set focus to search field
        searchField.requestFocus();
    }

    /**
     * Creates the main layout for the application using VBox as container and FlowPane for components.
     *
     * @return the main layout container
     */
    private VBox createMainLayout() {
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: #f5f5f7;"); // Apple light gray background

        // Title with Apple-style typography
        Label titleLabel = new Label("Wiktionary Dictionary");
        titleLabel.setFont(Font.font("SF Pro Display", FontWeight.BOLD, 32));
        titleLabel.setStyle("-fx-text-fill: #1d1d1f; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 2, 0, 0, 1);");

        // Instruction label with Apple-style subtitle
        instructionLabel = new Label("Discover definitions from the world's largest multilingual dictionary");
        instructionLabel.setFont(Font.font("SF Pro Text", FontWeight.NORMAL, 16));
        instructionLabel.setStyle("-fx-text-fill: #86868b;");

        // Search section using FlowPane as requested
        FlowPane searchPane = createSearchPane();

        // Loading indicator with Apple style
        loadingIndicator = new ProgressIndicator();
        loadingIndicator.setVisible(false);
        loadingIndicator.setPrefSize(24, 24);
        loadingIndicator.setStyle("-fx-accent: #007aff;"); // Apple blue

        // Result area with Apple-style card design - BIGGER SIZE
        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(20); // Increased from 14 to 20 rows
        resultArea.setPrefWidth(750); // Set explicit width
        resultArea.setPrefHeight(400); // Set explicit height for better display
        resultArea.setWrapText(true);
        resultArea.setPromptText("Search results from Wiktionary will appear here...\n\nTry searching for any word - definitions are fetched live from Wiktionary's database.\n\nExamples: computer, serendipity, philosophy, algorithm");
        resultArea.setStyle(
            "-fx-font-family: 'SF Pro Text';" +
            "-fx-font-size: 14px;" + // Slightly larger font for better readability
            "-fx-background-color: white;" +
            "-fx-border-color: #d2d2d7;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 12px;" +
            "-fx-background-radius: 12px;" +
            "-fx-padding: 20px;" + // Increased padding for more space
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);"
        );

        // Status label with Apple style
        String statusText = controller.getDictionaryInfo();
        statusLabel = new Label(statusText);
        statusLabel.setStyle(
            "-fx-text-fill: #86868b;" +
            "-fx-font-size: 12px;" +
            "-fx-font-family: 'SF Pro Text';"
        );

        // Info section about Wiktionary
        VBox infoSection = createInfoSection();

        // Add all components to main layout
        mainLayout.getChildren().addAll(
            titleLabel,
            instructionLabel,
            searchPane,
            loadingIndicator,
            resultArea,
            statusLabel,
            createDivider(),
            infoSection
        );

        return mainLayout;
    }

    /**
     * Creates the search section using FlowPane layout with Apple design.
     *
     * @return the search pane containing search field and button
     */
    private FlowPane createSearchPane() {
        FlowPane searchPane = new FlowPane();
        searchPane.setHgap(12);
        searchPane.setVgap(12);
        searchPane.setAlignment(Pos.CENTER);

        // Search field with Apple-style design
        searchField = new TextField();
        searchField.setPromptText("Search for any word...");
        searchField.setPrefWidth(350);
        searchField.setPrefHeight(44); // Apple standard height
        searchField.setStyle(
            "-fx-font-family: 'SF Pro Text';" +
            "-fx-font-size: 16px;" +
            "-fx-background-color: white;" +
            "-fx-border-color: #d2d2d7;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 22px;" +
            "-fx-background-radius: 22px;" +
            "-fx-padding: 0 16px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 4, 0, 0, 1);"
        );

        // Search button with Apple-style design
        searchButton = new Button("Search");
        searchButton.setPrefWidth(100);
        searchButton.setPrefHeight(44);
        searchButton.setStyle(
            "-fx-font-family: 'SF Pro Text';" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: 600;" +
            "-fx-background-color: #007aff;" + // Apple blue
            "-fx-text-fill: white;" +
            "-fx-border-radius: 22px;" +
            "-fx-background-radius: 22px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,122,255,0.3), 8, 0, 0, 2);" +
            "-fx-cursor: hand;"
        );

        // Hover effects for search button
        searchButton.setOnMouseEntered(e ->
            searchButton.setStyle(searchButton.getStyle() + "-fx-background-color: #0056d3;")
        );
        searchButton.setOnMouseExited(e ->
            searchButton.setStyle(searchButton.getStyle().replace("-fx-background-color: #0056d3;", "-fx-background-color: #007aff;"))
        );

        // Event handlers
        searchButton.setOnAction(e -> performAsyncSearch());
        searchField.setOnAction(e -> performAsyncSearch()); // Allow Enter key to trigger search

        searchPane.getChildren().addAll(searchField, searchButton);

        return searchPane;
    }

    /**
     * Creates an Apple-styled info section explaining the Wiktionary integration.
     */
    private VBox createInfoSection() {
        VBox infoSection = new VBox(12);
        infoSection.setAlignment(Pos.CENTER);
        infoSection.setPadding(new Insets(20));
        infoSection.setPrefWidth(700); // Set preferred width to match result area better
        infoSection.setMaxWidth(750);  // Set maximum width
        infoSection.setMinHeight(120); // Set minimum height
        infoSection.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #d2d2d7;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 16px;" +
            "-fx-background-radius: 16px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);"
        );

        Label infoTitle = new Label("About Wiktionary");
        infoTitle.setFont(Font.font("SF Pro Display", FontWeight.BOLD, 18));
        infoTitle.setStyle("-fx-text-fill: #1d1d1f;");

        Label infoText = new Label(
            "🌐  Live definitions from Wiktionary's vast database\n" +
            "⚡  Instant search across millions of words");
        infoText.setFont(Font.font("SF Pro Text", FontWeight.NORMAL, 13));
        infoText.setStyle("-fx-text-fill: #86868b; -fx-line-spacing: 4px;");

        infoSection.getChildren().addAll(infoTitle, infoText);
        return infoSection;
    }

    /**
     * Performs asynchronous search to avoid blocking the UI during Wiktionary lookups.
     */
    private void performAsyncSearch() {
        String searchWord = searchField.getText();
        if (searchWord.trim().isEmpty()) {
            showErrorMessage("Please enter a word to search");
            return;
        }

        // Show loading indicator
        loadingIndicator.setVisible(true);
        searchButton.setDisable(true);
        statusLabel.setText("Searching...");
        statusLabel.setStyle("-fx-text-fill: #f39c12; -fx-font-size: 12px; -fx-font-family: 'SF Pro Text';");

        // Clear previous search results
        resultArea.clear();

        // Use async search to prevent UI blocking
        controller.searchForWordAsync(searchWord, result -> {
            // Hide loading indicator
            loadingIndicator.setVisible(false);
            searchButton.setDisable(false);

            if (result.isSuccess()) {
                // Format the result and show success
                showDefinition(searchWord, result.getMessage());
            } else {
                // Show error message
                showErrorMessage(result.getMessage());
            }

            // Update dictionary stats
            updateStatusLabel(result.isSuccess());

            // Clear search field for next search
            searchField.clear();
            searchField.requestFocus();
        });
    }

    /**
     * Shows a properly formatted definition in the result area
     *
     * @param word the word that was searched
     * @param definition the definition text to display
     */
    private void showDefinition(String word, String definition) {
        // Format the result with proper styling
        resultArea.setText("Word: " + word + "\n\nDefinition:\n" + definition);

        // Apply consistent Apple-style formatting
        resultArea.setStyle(
            "-fx-font-family: 'SF Pro Text';" +
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #1d1d1f;" +
            "-fx-background-color: white;" +
            "-fx-border-color: #d2d2d7;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 12px;" +
            "-fx-background-radius: 12px;" +
            "-fx-padding: 20px;"
        );
    }

    /**
     * Shows an error message in the result area
     *
     * @param errorMessage the error message to display
     */
    private void showErrorMessage(String errorMessage) {
        resultArea.setText("❌ " + errorMessage);

        // Apply error styling with Apple design
        resultArea.setStyle(
            "-fx-font-family: 'SF Pro Text';" +
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #ff3b30;" + // Apple red for errors
            "-fx-background-color: #fff2f2;" + // Light red background
            "-fx-border-color: #ffcccc;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 12px;" +
            "-fx-background-radius: 12px;" +
            "-fx-padding: 20px;"
        );
    }

    /**
     * Updates the status label with appropriate styling
     *
     * @param isSuccess whether the search was successful
     */
    private void updateStatusLabel(boolean isSuccess) {
        String statusText = controller.getDictionaryInfo();

        if (isSuccess) {
            statusLabel.setText("Word found successfully! | " + statusText);
            statusLabel.setStyle("-fx-text-fill: #34c759; -fx-font-size: 12px; -fx-font-family: 'SF Pro Text';"); // Apple green
        } else {
            statusLabel.setText("Search failed. Please try another word. | " + statusText);
            statusLabel.setStyle("-fx-text-fill: #ff3b30; -fx-font-size: 12px; -fx-font-family: 'SF Pro Text';"); // Apple red
        }
    }

    /**
     * Creates a styled divider line.
     *
     * @return the separator line
     */
    private Region createDivider() {
        Region divider = new Region();
        divider.setPrefHeight(1);
        divider.setStyle("-fx-background-color: #d2d2d7;");
        return divider;
    }
}
