public class Car {
    private double speed;
    private double gasolineLevel;
    private String typeName;
    private double tankCapacity;
    public double maxSpeed;

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


}