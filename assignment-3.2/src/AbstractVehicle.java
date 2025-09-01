public abstract class AbstractVehicle implements Vehicle {
    protected String type;
    protected String engineType;
    protected String color;
    protected double fuelEfficiency;
    protected boolean running;
    protected double totalDistance = 0;
    protected double totalFuelConsumed = 0;
    protected double operatingTime = 0;
    // Common vehicle variables
    protected double speed;
    protected double gasolineLevel;
    protected double tankCapacity;
    protected double maxSpeed;

    public AbstractVehicle(String type, String engineType, String color,
                           double fuelEfficiency, double tankCapacity, double maxSpeed) {
        this.type = type;
        this.engineType = engineType;
        this.color = color;
        this.fuelEfficiency = fuelEfficiency;
        this.tankCapacity = tankCapacity;
        this.maxSpeed = maxSpeed;
        this.running = false;
        this.speed = 0;
        this.gasolineLevel = 0;
    }

    @Override
    public void start() {
        if (gasolineLevel > 0) {
            running = true;
            System.out.println(type + " is starting...");
        } else {
            System.out.println(type + " cannot start - no fuel!");
        }
    }

    @Override
    public void stop() {
        running = false;
        speed = 0;
        System.out.println(type + " is stopping...");
    }

    @Override
    public double calculateFuelEfficiency() {
        if (totalFuelConsumed == 0) {
            return fuelEfficiency; // Return stored value
        }
        return (totalFuelConsumed / totalDistance )*100;
    }


    public void addGasoline(double amount) {
        gasolineLevel = Math.min(tankCapacity, gasolineLevel + amount);
        System.out.println(type + " Filling gasoline...\n" + amount + "L gasoline filled!");
    }
    protected void useGasoline(double amount) {
        gasolineLevel = Math.max(0, gasolineLevel - amount);
        if (gasolineLevel <= 0) {
            speed = 0;
            running = false;
        }
    }
//still problem
    public void accelerate() {
        if (gasolineLevel > 0 && running) {
            int acceleration;
            double consumptionRate;

            switch(type.toLowerCase()) {
                case "car":
                    acceleration = 30;
                    consumptionRate = 0.08;
                    break;
                case "motorcycle":
                    acceleration = 40;
                    consumptionRate = 0.05;
                    break;
                case "bus":
                    acceleration = 15;
                    consumptionRate = 0.15;
                default:
                    acceleration = 20;
                    consumptionRate = 0.1;
            }

            double previousSpeed = speed;
            speed = Math.min(speed + acceleration, maxSpeed);

            double timeIncrement = 1.0; // assume 1 hour
            operatingTime += timeIncrement;

            // Calculate distance traveled
            double avgSpeed = (previousSpeed + speed) / 2.0;
            double distance = avgSpeed * timeIncrement;
            totalDistance += distance;

            // Calculate consumption
            double fuelUsed = acceleration * timeIncrement * consumptionRate;
            useGasoline(fuelUsed);
            totalFuelConsumed += fuelUsed;

        } else if (gasolineLevel <= 0) {
            System.out.println(type + " cannot accelerate - no fuel!");
        } else {
            System.out.println(type + " cannot accelerate - Stopped");
        }
    }

    public void decelerate(int decelerateRate) {
        if (decelerateRate > 0) {
            speed = Math.max(0, speed - decelerateRate);
        }
    }

    public String getInfo() {
        return String.format("Car Information:\nType: %s\nFuel: %s\nColor: %s\nTank Capacity: %.1f L\nMax Speed: %.1f km/h",
                type, engineType, color, tankCapacity, maxSpeed);
    }

    // Getters
    public double getSpeed() { return speed; }
    public double getGasolineLevel() { return gasolineLevel; }
    public double getTankCapacity() { return tankCapacity; }
    public double getMaxSpeed() { return maxSpeed; }
    public String getType() { return type; }
    public String getEngineType() { return engineType; }
    public String getColor() { return color; }
    public boolean isRunning() { return running; }
    public double getTotalDistance() { return totalDistance; }
    public double getTotalFuelConsumed() { return totalFuelConsumed; }
    public double getOperatingTime() { return operatingTime; }


}


