package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * NoteViewer class - This is the VIEW in MVC pattern
 * It's responsible for loading and displaying our user interface
 *
 * Think of this class as the "window manager" - it creates and shows our app window
 */
public class NoteViewer {
    /**
     * Method to get the controller after loading FXML
     * This is used to communicate with the controller from other classes
     *
     * @param stage - the main window
     * @return the controller instance
     * @throws IOException - if FXML loading fails
     */
    public Object initializeWithController(Stage stage) throws IOException {
        // Step 1: Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/note_view.fxml"));

        // Step 2: Create the UI from the FXML file
        Parent root = loader.load();
        // Get the controller that was created when loading FXML
        Object controller = loader.getController();

        // Step 3: Set up the main window
        setupStage(stage, root, "My Notes App");

        // Optional: Print a message to console
        System.out.println("App started successfully!");

        // Return the controller in case other classes need to use it
        return controller;
    }

    /**
     * Helper method to set up the stage with common settings
     *
     * @param stage - the main window
     * @param root - the root UI element
     * @param title - the window title
     */
    private void setupStage(Stage stage, Parent root, String title) {
        stage.setTitle(title);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setScene(new Scene(root, 900, 650));
        stage.show();
    }
}
