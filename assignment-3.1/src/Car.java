
public class Car {

    private double speed;
    private double gasolineLevel;
    private String typeName;
    private double tankCapacity;
    private boolean cruiseControlOn = false;
    private double cruiseControlSpeed = 0;
    public double maxSpeed;

    //constructor Car
    public Car(String typeName, double initialspeed, double tankCapacity, double maxSpeed) {
        this.speed = initialspeed;
        this.typeName = typeName;
        this.gasolineLevel = 0;
        this.tankCapacity = tankCapacity;
        this.maxSpeed = maxSpeed;
    }

    double getSpeed() {
        return speed;
    }

    String getTypeName() {
        return typeName;
    }

    void fillTank() {
        gasolineLevel = tankCapacity;
    }

    double getGasolineLevel() {
        return gasolineLevel;
    }

    //accelerate
    public void accelerate(int accelerateRate) {
        if (gasolineLevel > 0 || accelerateRate > 0) {
            if (speed < maxSpeed) {
                speed += accelerateRate;
            } else if  (speed >= maxSpeed) {
                speed = maxSpeed;
            }
        } else
            speed = 0;
    }

    //decelerate
    void decelerate(int decelerateRate) {
        if (gasolineLevel > 0 || decelerateRate > 0) {
            speed = Math.max(0, speed - decelerateRate);//prevent negative value
        } else
            speed = 0;

    }

    //condition turn on cruisecontrol
    public boolean turnCruiseControlOn() {
        double minCruiseControlSpeed = 30;
        double maxCruiseControlSpeed = 120;
        if (cruiseControlSpeed < minCruiseControlSpeed || cruiseControlSpeed > maxCruiseControlSpeed) {
            return false;
        }
        if (gasolineLevel <= 0 || speed <= 0 ) {
            return false;
        }
        cruiseControlOn = true;
        return true;
    }

    void turnCruiseControlOff() {
        cruiseControlOn = false;
    }

    //set targetspeed
    void setCruiseControlSpeed(double targetSpeed) {
        cruiseControlSpeed = targetSpeed;
    }

    //get target speed
    double getCruiseControlSpeed() {
        return cruiseControlSpeed;
    }

    //condition
    boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    //speed change when cruise control on
    void speedChange() {
        if (!cruiseControlOn) return;

        if (speed < cruiseControlSpeed) {
            accelerate(10);
        } else if (speed > cruiseControlSpeed) {
            decelerate(10);
        }
        if (gasolineLevel <= 0) {
            turnCruiseControlOff();
        }
    }

}