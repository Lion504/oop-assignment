package src;

public class Motorcycle extends AbstractVehicle {

    public Motorcycle(String color, double fuelEfficiency) {
        super("Motorcycle", "Gasoline", color, fuelEfficiency, 15.0, 200.0); // 15L tank, 200 max speed
    }

    @Override
    public String getInfo() {
        return String.format("Motorcycle Information:\nType: %s\nFuel: %s\nColor: %s\nTank Capacity: %.1f L\nMax Speed: %.1f km/h",
                type, engineType, color, tankCapacity, maxSpeed);
    }
}
