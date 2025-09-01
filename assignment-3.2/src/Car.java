public class Car extends AbstractVehicle {

    public Car(String color, double fuelEfficiency) {
        super("Car", "Petrol", color, fuelEfficiency, 50.0, 180.0); // 50L tank, 180 max speed
    }
}
