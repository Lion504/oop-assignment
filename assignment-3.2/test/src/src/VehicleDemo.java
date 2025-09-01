package src;

public class VehicleDemo {
    public static void main(String[] args) {
        System.out.println("Vehicle Demonstration\n");

        // Create instances of all vehicle types
        Vehicle[] vehicles = {
                new Car("Red", 25.0),
                new Motorcycle("Black", 45.0),
                new Bus("Yellow", 10.0, 40),
                new ElectricCar("Blue", 120.0),
                new ElectricMotorcycle("White", 80.0)
        };

        // Test each vehicle
        for (Vehicle vehicle : vehicles) {
            System.out.println("\n=== Start Testing === ");
            System.out.println("-".repeat(50));

            // Add fuel/charge for testing
            if (vehicle instanceof ElectricCar || vehicle instanceof ElectricMotorcycle) {
                ((ElectricVehicle) vehicle).charge();
            } else {
                ((AbstractVehicle) vehicle).addGasoline(30); // Add some fuel
            }

            // Test start/stop
            System.out.println("\nTest Start/Stop: ");
            vehicle.start();
            vehicle.stop();

            System.out.println("\n" + vehicle.getInfo());

            // Display fuel efficiency
            System.out.printf("Fuel Efficiency: %.1f %s%n",
                    vehicle.calculateFuelEfficiency(),
                    vehicle instanceof ElectricCar || vehicle instanceof ElectricMotorcycle ?
                            "kWh per 100km" : "L per 100km");

        }
    }
}
