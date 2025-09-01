public abstract class AbstractVehicle implements Vehicle {
    protected String type;
    protected String engineType;
    protected String color;
    protected double fuelEfficiency; // mpg or kWh per km
    protected boolean running;

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
        return fuelEfficiency;
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
    public void accelerate(int accelerateRate) {
        if (gasolineLevel > 0 && accelerateRate > 0 && running) {
            speed = Math.min(speed + accelerateRate, maxSpeed);
            useGasoline(accelerateRate * 0.1); //will use gas
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

}


