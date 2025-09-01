package src;

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
        if (gasolineLevel > 0 || engineType.equals("Electricity")) {
            running = true;
            System.out.println(type + " is starting...");
        } else {
            System.out.println(type + " cannot start - no fuel or electricity!");
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


    // Reuse existing methods from your Car class
    public void addGasoline(double amount) {
        gasolineLevel = Math.min(tankCapacity, gasolineLevel + amount);
    }

    public void accelerate(int accelerateRate) {
        if ((gasolineLevel > 0 || engineType.equals("Electricity")) && accelerateRate > 0 && running) {
            speed = Math.min(speed + accelerateRate, maxSpeed);
            if (!engineType.equals("Electricity")) {
                useGasoline(accelerateRate * 0.1);
            }
        }
    }

    public void decelerate(int decelerateRate) {
        if (decelerateRate > 0) {
            speed = Math.max(0, speed - decelerateRate);
        }
    }

    protected void useGasoline(double amount) {
        gasolineLevel = Math.max(0, gasolineLevel - amount);
        if (gasolineLevel <= 0 && !engineType.equals("Electricity")) {
            speed = 0;
            running = false;
        }
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


