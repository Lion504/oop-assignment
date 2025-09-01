package src;

public class Bus extends AbstractVehicle {
    private int capacity;
    private int passengerCount;

    public Bus(String color, double fuelEfficiency, int capacity) {
        super("Bus", "Diesel", color, fuelEfficiency, 200.0, 120.0); // 200L tank, 120 max speed
        this.capacity = capacity;
        this.passengerCount = 0;
    }

    @Override
    public String getInfo() {
        return String.format("Bus Information:\nType: %s\nFuel: %s\nCapacity: %d passengers\nCurrent Passengers: %d\nTank Capacity: %.1f L",
                type, engineType, capacity, passengerCount, tankCapacity);
    }

    // Passenger management methods
    public boolean passengerEnter() {
        if (passengerCount < capacity) {
            passengerCount++;
            return true;
        }
        return false;
    }

    public boolean passengerExit() {
        if (passengerCount > 0) {
            passengerCount--;
            return true;
        }
        return false;
    }

    public int getPassengerCount() { return passengerCount; }
    public int getCapacity() { return capacity; }

}
