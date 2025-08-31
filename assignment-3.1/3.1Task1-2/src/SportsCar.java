public class SportsCar extends Car {
    private static final double ACCELERATION = 1.8;
    private static final double DECELERATION = 1.6;
    private static final double FUEL_CONSUMPTION = 2.0;

    public SportsCar(String carType, double tankCapacity, double maxSpeed) {
        super(carType, tankCapacity, maxSpeed);
    }

    @Override
    public void accelerate(int accelerateRate) {
        if (hasGasoline() && accelerateRate > 0) {
            int enhancedRate = (int)(accelerateRate * ACCELERATION);
            speed = Math.min(speed + enhancedRate, maxSpeed);

            // Sports cars consume more fuel
            useGasoline(accelerateRate * 0.1 * FUEL_CONSUMPTION);
        }
    }

    @Override
    public void decelerate(int decelerateRate) {
        if (decelerateRate > 0) {
            int enhancedRate = (int)(decelerateRate * DECELERATION);
            speed = Math.max(0, speed - enhancedRate);
        }
    }

    // Additional sports car specific method
    public void turboBoost() {
        if (hasGasoline() && speed > 50) {
            accelerate(20);
            System.out.println("🚗💨 TURBO BOOST ACTIVATED!");
        } else {
            System.out.println("❌ Cannot activate turbo: insufficient speed or no fuel");
        }
    }

    @Override
    public String toString() {
        return String.format("SportsCar[%s] - Speed: %.1f km/h, Fuel: %.1f L",
                getCarType(), speed, gasolineLevel);
    }
}
