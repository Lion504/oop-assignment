public abstract class AbstractEVehicle extends AbstractVehicle implements EVehicle {
    protected double batteryLevel;
    protected double batteryCapacity;

    public AbstractEVehicle(String type,
                            String engineType,
                            String color,
                            double fuelEfficiency,
                            double maxSpeed,
                            double batteryCapacity) {
        super(type, engineType, color, fuelEfficiency, 0, maxSpeed); // 0 tank capacity for electric
        this.batteryCapacity = batteryCapacity;
        this.batteryLevel = 0;
    }

    //separate EV part for different condition check
    @Override
    public void start() {
        if (batteryLevel > 0) {
            running = true;
            System.out.println(type + " is starting silently...");
        } else {
            System.out.println(type + " cannot start - battery empty!");
        }
    }

    //separate EV part for different condition
    @Override
    public void charge() {
        batteryLevel = batteryCapacity;
        System.out.println(type + " is charging... \nBattery fully charged!");
    }

    //separate EV part for different condition
    protected void useElectricity(double amount) {
        batteryLevel = Math.max(0, batteryLevel - amount);
        if (batteryLevel <= 0) {
            speed = 0;
            running = false;
        }
    }

    //separate EV part for different condition
    public void accelerate(int accelerateRate) {
        if (batteryLevel > 0 && accelerateRate > 0 && running) {
            speed = Math.min(speed + accelerateRate, maxSpeed);
            useElectricity(accelerateRate * 0.2); //will use Electricity
        } else if (batteryLevel <= 0) {
            System.out.println(type + " cannot accelerate - no fuel!");
        } else {
            System.out.println(type + " cannot accelerate - Stopped");
        }
    }

    //separate EV part for different condition check
    @Override
    public String getInfo() {
        return String.format("Electric Motorcycle Information:\nType: %s\nFuel: %s\nColor: %s\nBattery: %.1f/%.1f kWh\nMax Speed: %.1f km/h",
                type, engineType, color, batteryLevel, batteryCapacity, maxSpeed);
    }

    // Common getters for electric vehicles
    public double getBatteryLevel() { return batteryLevel; }
    public double getBatteryCapacity() { return batteryCapacity; }

    // Override to prevent gasoline-related operations
    @Override
    public void addGasoline(double amount) {
        System.out.println("Cannot add gasoline to " + type + " - it's electric!");
    }

}
