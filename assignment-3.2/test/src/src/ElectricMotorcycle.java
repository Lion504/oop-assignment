package src;

public class ElectricMotorcycle extends AbstractVehicle implements ElectricVehicle {
    private double batteryLevel;
    private double batteryCapacity;

    public ElectricMotorcycle(String color, double fuelEfficiency) {
        super("Electric Motorcycle", "Electricity", color, fuelEfficiency, 0, 180.0);
        this.batteryCapacity = 50.0; // 50 kWh battery
        this.batteryLevel = 0;
    }

    @Override
    public String getInfo() {
        return String.format("Electric Motorcycle Information:\nType: %s\nFuel: %s\nColor: %s\nBattery: %.1f/%.1f kWh\nMax Speed: %.1f km/h",
                type, engineType, color, batteryLevel, batteryCapacity, maxSpeed);
    }

    @Override
    public void charge() {
        batteryLevel = batteryCapacity;
        System.out.println("Electric Motorcycle is charging... Battery fully charged!");
    }

    @Override
    public void start() {
        if (batteryLevel > 0) {
            running = true;
            System.out.println(type + " is starting silently...");
        } else {
            System.out.println(type + " cannot start - battery empty!");
        }
    }

    public double getBatteryLevel() { return batteryLevel; }
    public double getBatteryCapacity() { return batteryCapacity; }
}
