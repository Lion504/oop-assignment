public class Television {

    private boolean on = false;
    private int channel = 0;

    public Television() {
        // Initialize to off and channel 0
    }

    public boolean isOn() {

        return on;
    }

    public void pressOnOff() {

        on = !on;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int ch) {
        if (ch >= 0 && ch <= 10) {
            channel = ch;
        } else {
            channel = 1;
        }
    }
}
