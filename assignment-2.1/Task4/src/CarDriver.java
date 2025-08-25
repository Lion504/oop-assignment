public class CarDriver {
    public static void main(String[] args) {

        Car myCar = new Car("Ferrari", 0, 90, 320);
        myCar.fillTank();
        System.out.println(myCar.getTypeName() + " initial speed: " + myCar.getSpeed() + ", gasoline level: " + myCar.getGasolineLevel());

//accelerate simulate
        System.out.println(myCar.getTypeName() + " accelerate now.....");
        for (int i = 0; i < 20; i++) {
            myCar.accelerate(30);
            System.out.println(myCar.getTypeName() + ": Accelerate to " + myCar.getSpeed() + " km/h");
            if (myCar.getSpeed() == myCar.maxSpeed) {
                System.out.println(myCar.getTypeName() + "reach it limits " + myCar.getSpeed() + " km/h");
                break;
            }
        }

// turn on CruiseControl
        myCar.setCruiseControlSpeed(60);
        boolean turnCruiseControlOn = myCar.turnCruiseControlOn();
        if (turnCruiseControlOn) {
            System.out.println("Cruise Control is " + (myCar.isCruiseControlOn() ? "on" : "off"));
        } else {
            System.out.println("Cruise Control can not be set");
        }
//change speed after turn on
        while (myCar.getSpeed() > 0 && myCar.getGasolineLevel() >= 0) {
            myCar.speedChange();
            System.out.println(myCar.getTypeName() + ": Cruise Control speed change to " + myCar.getSpeed() + " km/h");
            if (myCar.getSpeed() == myCar.getCruiseControlSpeed()) {
                System.out.println(myCar.getTypeName() + ": Cruise Control set speed reached");
                break;
            }
        }
//turn off
        myCar.turnCruiseControlOff();
        System.out.println("Cruise Control is " + (myCar.isCruiseControlOn() ? "on" : "off"));

        //test high cruise control speed
        myCar.setCruiseControlSpeed(200);
        turnCruiseControlOn = myCar.turnCruiseControlOn();
        System.out.println( "Target Speed set to " + myCar.getCruiseControlSpeed() + " Cruise Control Speed set too high! Cruise Control is " + (myCar.isCruiseControlOn() ? "on" : "off"));
        if (!turnCruiseControlOn) {
            System.out.println("speed need between 30 - 120");
        }

        //test low cruise control speed
        myCar.setCruiseControlSpeed(20);
        turnCruiseControlOn = myCar.turnCruiseControlOn();
        System.out.println("Target Speed set to " + myCar.getCruiseControlSpeed() + " Cruise Control Speed set too low! Cruise Control is " + (myCar.isCruiseControlOn() ? "on" : "off"));
        if (!turnCruiseControlOn) {
            System.out.println("speed need between 30 - 120");
        }

//decelerate
        System.out.println(myCar.getTypeName() + " brake now.....");
        while (myCar.getSpeed() > 0 && myCar.getGasolineLevel() >= 0) {
            myCar.decelerate(30);
            System.out.println(myCar.getTypeName() + ": decelerate to " + myCar.getSpeed() + " km/h");
        }

    }

}