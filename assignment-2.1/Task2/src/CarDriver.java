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

//decelerate
        System.out.println(myCar.getTypeName() + " brake now.....");
        while (myCar.getSpeed() > 0 && myCar.getGasolineLevel() >= 0) {
            myCar.decelerate(30);
            System.out.println(myCar.getTypeName() + ": decelerate to " + myCar.getSpeed() + " km/h");
        }

    }

}