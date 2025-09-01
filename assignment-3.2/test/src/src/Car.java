package src;

public class Car extends AbstractVehicle {

    public Car(String color, double fuelEfficiency) {
        super("Car", "Petrol", color, fuelEfficiency, 50.0, 180.0); // 50L tank, 180 max speed
    }

    public Car(String color, double fuelEfficiency, double tankCapacity, double maxSpeed) {
        super("Car", "Petrol", color, fuelEfficiency, tankCapacity, maxSpeed);
    }

    @Override
    public String getInfo() {
        return String.format("Car Information:\nType: %s\nFuel: %s\nColor: %s\nTank Capacity: %.1f L\nMax Speed: %.1f km/h",
                type, engineType, color, tankCapacity, maxSpeed);
    }
}
