import javafx.application.Application;
import javafx.stage.Stage;
import view.NoteViewer;

/**
 * NoteApp - This is the MAIN class that starts our entire application
 *
 * This class extends Application, which is a special JavaFX class
 * that provides the foundation for running JavaFX programs
 *
 * Think of this as the "engine" that starts everything up
 */
public class NoteApp extends Application {

    /**
     * start() method - This is the main entry point for JavaFX applications
     * JavaFX automatically calls this method when the app launches
     *
     * @param primaryStage - The main window that JavaFX creates for us
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Step 1: Create our NoteViewer (which handles the UI setup)
            NoteViewer viewer = new NoteViewer();

            // Step 2: Tell the viewer to initialize and show the UI
            viewer.initializeWithController(primaryStage);

            // Step 3: Set what happens when user closes the window
            primaryStage.setOnCloseRequest(event -> {
                System.out.println("Thanks for using the Note Taking App!");
                System.out.println("Your notes were stored in memory during this session.");
                System.out.println("To save notes permanently, you could add file I/O in the future!");
            });

        } catch (Exception e) {
            // If something goes wrong, print the error details
            System.err.println("Error starting the Note Taking App:");
            e.printStackTrace();

            // Show a simple error message to the user
            System.err.println("\nPossible solutions:");
            System.err.println("1. Make sure the note_view.fxml file exists in src/main/resources/");
            System.err.println("2. Check that all JavaFX dependencies are properly installed");
            System.err.println("3. Make sure you're running with Java 11 or later");
        }
    }

    /**
     * stop() method - This runs when the application is shutting down
     */
    @Override
    public void stop() {
        System.out.println("Application is shutting down...");
    }
}

