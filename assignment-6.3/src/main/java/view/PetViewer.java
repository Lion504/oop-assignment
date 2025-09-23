package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

/**
 * This class draws our pet on the screen
 */
public class PetViewer extends Canvas {
    //frame
    private Image[] catImages; // Array to hold cat images for animation
    private Image[] idleFrames; // Array to hold idle frames
    private int currentFrame = 0; // Current frame index for animation
    private long lastFrameTime = 0; // Time when the last frame was updated
    private boolean isCatMoving = false; // Is the pet currently moving?

    //
    private final long FRAME_DURATION = 150; // Duration of each frame in nanoseconds (150ms)

    //size
    private final int CAT_WIDTH = 50; // Width of the cat image
    private final int CAT_HEIGHT = 50; // Height of the cat image

    /**
     * Create a new canvas animation cat
     */
    public PetViewer(double width, double height) {
        super(width, height);

        // Set up mouse event handlers
        setupMouseEvents();
        loadCatImages();
        startAnimation();
    }

    private void loadCatImages() {
        // Load cat images for walking animation
        catImages = new Image[2];
        catImages[0] = new Image(getClass().getResourceAsStream("/images/cat_walk_0.gif"));
        catImages[1] = new Image(getClass().getResourceAsStream("/images/cat_walk_1.gif"));

        // Load idle frames
        idleFrames = new Image[1];
        idleFrames[0] = new Image(getClass().getResourceAsStream("/images/cat_idle_y.gif"));
    }

    /**
     * Start the animation timer
     */
    public void startAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastFrameTime >= FRAME_DURATION * 1_000_000) {
                    // Update frame
                    currentFrame++;
                    if (isCatMoving) {
                        currentFrame %= catImages.length;
                    } else {
                        currentFrame %= idleFrames.length;
                    }
                    lastFrameTime = now;
                }
            }
        };
        timer.start();
    }

    /**
     * Draw the pet at the given position
     */
    public void drawPet(double x, double y, boolean moving) {
        this.isCatMoving = moving; // Update moving state
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight()); // Clear the canvas

        Image currentImage;
        if (isCatMoving) {
            currentImage = catImages[currentFrame % catImages.length];
        } else {
            currentImage = idleFrames[currentFrame % idleFrames.length];
        }

        // Draw the cat image centered at (x, y)
        gc.drawImage(currentImage, x - CAT_WIDTH / 2, y - CAT_HEIGHT / 2, CAT_WIDTH, CAT_HEIGHT);
    }

    /**
     * Set up what happens when the mouse moves or leaves
     */
    private void setupMouseEvents() {
        // We'll connect these to the controller later
        // For now, just make the canvas respond to mouse events
        setFocusTraversable(true);
    }
}
