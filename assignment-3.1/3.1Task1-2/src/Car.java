
public class Car {

    protected double speed;
    protected double gasolineLevel;
    private  String carType;
    private  double tankCapacity;
    private  boolean cruiseControlOn = false;
    private  double cruiseControlSpeed = 0;
    public  double maxSpeed;

    //constructor Car
    public Car(String carType, double tankCapacity, double maxSpeed) {
        if (tankCapacity <= 0 || maxSpeed <= 0) {
            throw new IllegalArgumentException("Invalid car info");
        }
        this.carType = carType;
        this.tankCapacity = tankCapacity;
        this.maxSpeed = maxSpeed;
        this.speed = 0;
        this.gasolineLevel = 0;

    }
    //getter
    public double getSpeed() {
        return speed;
    }
    public String getCarType() {
        return carType;
    }
    public double getTankCapacity() {
        return tankCapacity;
    }
    public double getMaxSpeed() {
        return maxSpeed;
    }
    public double getGasolineLevel() {
        return gasolineLevel;
    }
    //get target speed
    public double getCruiseControlSpeed() {
        return cruiseControlSpeed;
    }

    //setter
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    //set targetspeed
    public void setCruiseControlSpeed(double targetSpeed) {
        cruiseControlSpeed = targetSpeed;
    }

    //condition check
    public boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    //helper check gas
    protected boolean hasGasoline() {
        return gasolineLevel > 0;
    }

    //helper use Gasoline
    protected void useGasoline(double amount) {
        gasolineLevel = Math.max(0, gasolineLevel - amount);
        if (!hasGasoline()) {
            speed = 0; // stops no fuel
            turnCruiseControlOff();
        }
    }

    //add fuel
    public void addGasoline(double amount) {
        gasolineLevel = Math.min(tankCapacity, gasolineLevel + amount);
    }

    //accelerate
    public void accelerate(int accelerateRate) {
        if (hasGasoline() && accelerateRate > 0) {
            speed = Math.min(speed + accelerateRate, maxSpeed);//prevent over speed
            useGasoline(accelerateRate * 0.1);
        }
    }

    //decelerate
    public void decelerate(int decelerateRate) {
        if (decelerateRate <= 0) {
            speed = 0;
            return;
        }
        speed = Math.max(0, speed - decelerateRate);//prevent negative value
    }

    //turn on cruisecontrol
    public boolean turnCruiseControlOn() {
        double minCruiseControlSpeed = 30;
        double maxCruiseControlSpeed = 150;
        if (cruiseControlSpeed == 0) { cruiseControlSpeed = speed; }
        if (cruiseControlSpeed < minCruiseControlSpeed || cruiseControlSpeed > maxCruiseControlSpeed) {
            return false;
        }
        if (!hasGasoline() || speed <= 0) {
            return false;
        }
        cruiseControlOn = true;
        return true;
    }

    //turn Off cruisecontrol
    void turnCruiseControlOff() {
        cruiseControlOn = false;
    }

    //speed change when cruise control on
    void speedChange() {
        if (!cruiseControlOn) return;

        double speedDifference = cruiseControlSpeed - speed;

        if (Math.abs(speedDifference) <= 1) {
            speed = cruiseControlSpeed;
        } else if (speedDifference > 0) {
            int adjustRate = Math.min(10, (int)speedDifference);
            accelerate(adjustRate);
        } else {
            int adjustRate = Math.min(10, (int)Math.abs(speedDifference));
            decelerate(adjustRate);
        }

        if (!hasGasoline()) {
            turnCruiseControlOff();
        }
    }

}