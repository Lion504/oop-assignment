package controller;

import javafx.animation.AnimationTimer;
import model.PetMovement;
import view.PetViewer;

/**
 * It connects the pet's brain (model) with what we see (view)
 */
public class PetController {
    private PetMovement petModel;    // The pet's brain
    private PetViewer petView;       // What draws the pet
    private AnimationTimer timer;    // Makes everything move smoothly

    /**
     * Create a new controller
     */
    public PetController(PetMovement model, PetViewer view) {
        this.petModel = model;
        this.petView = view;

        setupMouseEvents();
        startAnimation();
    }

    /**
     * Set up what happens when the mouse moves around
     */
    private void setupMouseEvents() {
        // When mouse moves over the canvas, tell the pet to follow it
        petView.setOnMouseMoved(event -> {
            // Get mouse position and tell the pet to go there
            double mouseX = event.getX();
            double mouseY = event.getY();
            petModel.setTarget(mouseX, mouseY);
        });

        // When mouse leaves the canvas, stop the pet
        petView.setOnMouseExited(event -> {
            petModel.stopMoving();
        });

        // When mouse enters the canvas, start following again
        petView.setOnMouseEntered(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            petModel.setTarget(mouseX, mouseY);
        });
    }

    /**
     * Start the animation - this makes everything move smoothly
     */
    private void startAnimation() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Update the pet's position slowly
                petModel.updatePosition();

                // Draw the pet at its new position
                petView.drawPet(
                    petModel.getPetX(),
                    petModel.getPetY(),
                    petModel.isMoving()
                );
            }
        };
        timer.start(); // Start the animation!
    }

    /**
     * Stop everything (useful when closing the app)
     */
    public void stop() {
        if (timer != null) {
            timer.stop();
        }
    }
}
