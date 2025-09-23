import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import controller.PetController;
import model.PetMovement;
import view.PetViewer;

/**
 * This is the main class that starts our Virtual Pet application!
 */
public class PetApp extends Application {

    private PetController controller; // Our controller

    /**
     * This method starts our application - JavaFX calls this automatically
     */
    @Override
    public void start(Stage primaryStage) {
        // Create the window title
        primaryStage.setTitle("🐾 Caaat Walker 🐾");

        // Create instructions for the user
        Text instructions = new Text("Move your mouse over the canvas and watch your pet follow! 🐕");
        instructions.setStyle("-fx-font-size: 16px; -fx-fill: darkblue;");

        // Create our pet's brain (model)
        PetMovement petModel = new PetMovement();

        // Create the canvas where we draw our pet (view)
        PetViewer petView = new PetViewer(600, 400); // 600 pixels wide, 400 pixels tall
        petView.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        // Create the controller that connects everything
        controller = new PetController(petModel, petView);

        // Put everything in a container
        VBox root = new VBox(10); // 10 pixels space between items
        root.getChildren().addAll(instructions, petView);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        // Create the scene (like a movie scene) it can be same with canvas
        Scene scene = new Scene(root, 640, 480);

        // Set up the window
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Don't let user resize the window
        primaryStage.show(); // Show the window!

        // Draw the pet for the first time
        petView.drawPet(petModel.getPetX(), petModel.getPetY(), petModel.isMoving());
    }

    /**
     * This method runs when the application is closing
     */
    @Override
    public void stop() {
        if (controller != null) {
            controller.stop(); // Stop the animation
        }
    }

    /**
     * This is where our program starts!
     */
    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}

