import javafx.application.Application;
import javafx.stage.Stage;

/**
 * DictionaryApp - Main application class that serves as the entry point for the JavaFX Dictionary application.
 * This class extends Application and orchestrates the MVC components.
 */
public class DictionaryApp extends Application {

    /**
     * The main entry point for the JavaFX application.
     * This method is called after the JavaFX runtime has been initialized.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Create the controller (which initializes the model)
            DictionaryController controller = new DictionaryController();

            // Create the view with the controller
            DictionaryView view = new DictionaryView(controller);

            // Show the application
            view.show(primaryStage);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to start the Dictionary application: " + e.getMessage());
        }
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
