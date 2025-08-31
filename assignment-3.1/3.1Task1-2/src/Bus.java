public class Bus extends Car {
    private int passengerCount;
    private final int maxPassengers;
    private static final double PASSENGER_WEIGHT = 0.02;

    public Bus(String carType, double tankCapacity, double maxSpeed, int maxPassengers) {
        super(carType, tankCapacity, maxSpeed);
        if (maxPassengers <= 0) {
            throw new IllegalArgumentException("Maximum passengers must be positive");
        }
        this.maxPassengers = maxPassengers;
        this.passengerCount = 0;
    }

       // Getters
    public int getPassengerCount() {
        return passengerCount;
    }
    public int getMaxPassengers() {
        return maxPassengers;
    }
    public int getAvailableSeats() {
        return maxPassengers - passengerCount;
    }

    public boolean isFull() {
        return passengerCount >= maxPassengers;
    }
    public boolean isEmpty() {
        return passengerCount == 0;
    }
    //overloading single passenger
    public boolean passengerEnter() {
        return passengerEnter(1);
    }

    // overloading multiple passengers
    public boolean passengerEnter(int passengerNum) {
        if (passengerNum <= 0) {
            System.out.println("❌ Invalid number of passengers");
            return false;
        }

        if (passengerCount + passengerNum <= maxPassengers) {
            passengerCount += passengerNum;
            if (passengerNum == 1) {
                System.out.println("🚌 Passenger in. Current: " + passengerCount + "/" + maxPassengers);
            } else {
                System.out.println("🚌 " + passengerNum + " passengers entered. Current: " + passengerCount + "/" + maxPassengers);
            }
            return true;
        }
        System.out.println("❌ Not enough space for " + passengerNum + " passenger(s)");
        return false;
    }

    // overloading  single exit
    public boolean passengerExit() {
        return passengerExit(1);
    }

    // overloading  multiple exit
    public boolean passengerExit(int passengerNum) {
        if (passengerNum <= 0) {
            System.out.println("❌ Invalid number of passengers");
            return false;
        }

        if (passengerCount >= passengerNum) {
            passengerCount -= passengerNum;
            if (passengerNum == 1) {
                System.out.println("🚌 Passenger exited. Current: " + passengerCount + "/" + maxPassengers);
            } else {
                System.out.println("🚌 " + passengerNum + " passengers exited. Current: " + passengerCount + "/" + maxPassengers);
            }
            return true;
        }

        System.out.println("❌ Not enough passengers to exits " + passengerNum + " passenger(s)");
        return false;
    }
    // Override accelerate to account for passenger weight
    @Override
    public void accelerate(int accelerateRate) {
        if (hasGasoline() && accelerateRate > 0) {
            // Buses accelerate slower when loaded with passengers
            double loadFactor = 1.0 - (passengerCount * 0.02);
            int adjustedRate = Math.max(1, (int)(accelerateRate * loadFactor));
            speed = Math.min(speed + adjustedRate, maxSpeed);
            // Buses use more fuel with more passengers
            double passengerFuel = 1.0 + (passengerCount * PASSENGER_WEIGHT);
            useGasoline(accelerateRate * 0.1 * passengerFuel);
        }
    }

    @Override
    public String toString() {
        return String.format("Bus[%s] - Speed: %.1f km/h, Passengers: %d/%d, Fuel: %.1f L",
                getCarType(), speed, passengerCount, maxPassengers, gasolineLevel);
    }
}
