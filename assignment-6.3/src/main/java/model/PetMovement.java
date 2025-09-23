package model;

/**
 * This class represents virtual pet's movement and pet's "brain"
 */
public class PetMovement {
    // Pet's current position on the screen
    private double petX = 250;  // Starting X position
    private double petY = 250;  // Starting Y position

    // Where the mouse is (where pet wants to go)
    private double targetX = 250;
    private double targetY = 250;

    // How fast pet moves
    private double speed = 2.0;

    // Is the pet state moving?
    private boolean isMoving = false;

    /**
     * Update the pet's position - call this many times per second for smooth movement
     */
    public void updatePosition() {
        if (!isMoving) {
            return; // Pet doesn't move if it's not supposed to
        }

        // Calculate the distance to the target (mouse position)
        double deltaX = targetX - petX;
        double deltaY = targetY - petY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // If we're very close to the mouse, stop moving
        if (distance < 5) {
            isMoving = false;
            return;
        }

        // Move the pet a little bit toward the mouse
        petX += (deltaX / distance) * speed;
        petY += (deltaY / distance) * speed;
    }

    /**
     * Tell the pet where the mouse is (this makes the pet want to go there)
     */
    public void setTarget(double mouseX, double mouseY) {
        this.targetX = mouseX;
        this.targetY = mouseY;
        this.isMoving = true; // Start moving toward the mouse
    }

    /**
     * Stop the pet from moving (when mouse leaves the screen)
     */
    public void stopMoving() {
        this.isMoving = false;
    }

    // Simple getters so other classes can see where the pet is
    public double getPetX() { return petX; }
    public double getPetY() { return petY; }
    public boolean isMoving() { return isMoving; }
}
