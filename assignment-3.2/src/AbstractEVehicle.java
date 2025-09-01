public abstract class AbstractEVehicle extends AbstractVehicle implements EVehicle {
    protected double batteryLevel;
    protected double batteryCapacity;
    protected double totalElectricityConsumed = 0;
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
    public void accelerate() {
        if (batteryLevel > 0 && running) {
            int acceleration;
            double consumptionRate;

            switch(type.toLowerCase()) {
                case "electric car":
                    acceleration = 35;
                    consumptionRate = 0.18;
                    break;
                case "electric motorcycle":
                    acceleration = 45;
                    consumptionRate = 0.12;
                    break;
                default:
                    acceleration = 25;
                    consumptionRate = 0.2;
            }

            int actualRate = acceleration;

            double previousSpeed = speed;
            speed = Math.min(speed + acceleration, maxSpeed);

            double timeIncrement = 1.0;
            operatingTime += timeIncrement;

            double avgSpeed = (previousSpeed + speed) / 2.0;
            double distance = avgSpeed * timeIncrement;
            totalDistance += distance;

            double electricityUsed = actualRate * timeIncrement * consumptionRate;
            useElectricity(electricityUsed);
            totalElectricityConsumed += electricityUsed;


        } else if (batteryLevel <= 0) {
            System.out.println(type + " cannot accelerate - no fuel!");
        } else {
            System.out.println(type + " cannot accelerate - Stopped");
        }
    }

    //separate EV part for different condition check
    @Override
    public double calculateFuelEfficiency() {
        if (totalElectricityConsumed == 0) {
            return fuelEfficiency; // Return stored value if no consumption yet
        }
        return (totalElectricityConsumed / totalDistance) * 100;
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
    public double getTotalElectricityConsumed() { return totalElectricityConsumed; }
    // Override to prevent gasoline-related operations
    @Override
    public void addGasoline(double amount) {
        System.out.println("Cannot add gasoline to " + type + " - it's electric!");
    }

}
