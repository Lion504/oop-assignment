/**
 * Pen class represents a pen with a cap that can be on or off
 * The pen can only draw when the cap is off
 * The pen color can only be changed when the cap is on
 */
public class Pen {

    /**
     * Enum for available pen colors
     */
    public enum Color {
        RED, BLUE, GREEN, BLACK, YELLOW
    }

    // The current color of the pen
    private Color color;

    // Is the cap on? (true = cap on, can't draw; false = cap off, can draw)
    private boolean capOn;

    /**
     * Default constructor - creates a red pen with cap on
     */
    public Pen() {
        this.color = Color.RED;  // Default color is red
        this.capOn = true;        // Cap starts on
    }

    /**
     * Constructor with specified color
     * @param color The initial color of the pen
     */
    public Pen(Color color) {
        this.color = color;
        this.capOn = true;  // Cap starts on
    }

    /**
     * Remove the cap from the pen
     * After this, the pen can draw
     */
    public void capOff() {
        this.capOn = false;
    }

    /**
     * Put the cap back on the pen
     * After this, the pen cannot draw
     */
    public void capOn() {
        this.capOn = true;
    }

    /**
     * Try to draw with the pen
     * @return A string describing what happens:
     *         - Empty string if cap is on (can't draw)
     *         - "Drawing [color]" if cap is off (can draw)
     */
    public String draw() {
        if (capOn) {
            return "";  // Can't draw with cap on
        } else {
            return "Drawing " + color.toString().toLowerCase();
        }
    }

    /**
     * Change the color of the pen
     * The color can ONLY be changed when the cap is ON
     * If cap is off, the color change is ignored
     * @param newColor The new color to change to
     */
    public void changeColor(Color newColor) {
        if (capOn) {
            this.color = newColor;
        }
        // If cap is off, do nothing - can't change color while drawing!
    }
}
