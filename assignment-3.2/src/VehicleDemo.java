public class VehicleDemo {
    public static void main(String[] args) {
        System.out.println("Vehicle Demonstration\n");

        // Create instances of all vehicle types
        Vehicle[] vehicles = {
                new Car("Red", 6.7, 50),
                new Motorcycle("Black", 1.5, 10),
                new Bus("Yellow", 11.2, 100),
                new ECar("Blue", 8.0),
                new EMotorcycle("White", 3.0)
        };


        // Test each vehicle
        for (Vehicle vehicle : vehicles) {
            //
            AbstractVehicle abVehicle = (AbstractVehicle) vehicle;


            System.out.println("\n=== Start Testing " + abVehicle.getType() + " ===");
            System.out.println("-".repeat(50));
            System.out.println(vehicle.getInfo());

            // Show initial status
            System.out.printf("Initial Status - Speed: %.1f km/h, Start: %s%n",
                    abVehicle.getSpeed(),
                    abVehicle.isRunning());

            // Add fuel/charge
            if (vehicle instanceof ECar || vehicle instanceof EMotorcycle) {
                //why just can put here?
                EVehicle ev = (EVehicle) vehicle;

                System.out.printf("Battery before charge: %.1f/%.1f kWh%n",
                        ev.getBatteryLevel(), ev.getBatteryCapacity());
                ev.charge();
                System.out.printf("Battery after charge: %.1f/%.1f kWh%n",
                        ev.getBatteryLevel(), ev.getBatteryCapacity());
            } else {
                AbstractVehicle av = (AbstractVehicle) vehicle;
                System.out.printf("Fuel before: %.1f/%.1f L%n",
                        av.getGasolineLevel(), av.getTankCapacity());
                av.addGasoline(30);
                System.out.printf("Fuel after: %.1f/%.1f L%n",
                        av.getGasolineLevel(), av.getTankCapacity());
            }

            // Test start/stop
            System.out.println("\nTest Start/Stop: ");
            vehicle.start();
            System.out.printf("- %s Start: %s%n",
                    ((AbstractVehicle) vehicle).getType(),
                    ((AbstractVehicle) vehicle).isRunning());
            // accelerate
            ((AbstractVehicle) vehicle).accelerate();
            System.out.printf("After acceleration - Speed: %.1f km/h (Max: %.1f km/h)%n",
                    ((AbstractVehicle) vehicle).getSpeed(),
                    ((AbstractVehicle) vehicle).getMaxSpeed());
            // decelerate
            ((AbstractVehicle) vehicle).decelerate(30);
            System.out.printf("After decelerate - Speed: %.1f km/h (Max: %.1f km/h)%n",
                    ((AbstractVehicle) vehicle).getSpeed(),
                    ((AbstractVehicle) vehicle).getMaxSpeed());
            //stop
            vehicle.stop();
            System.out.printf("- %s Start: %s%n",
                    ((AbstractVehicle) vehicle).getType(),
                    ((AbstractVehicle) vehicle).isRunning());

            //System.out.println("\n" + ((AbstractVehicle) vehicle).getGasolineLevel());

            // Display fuel efficiency
            System.out.printf("Distance traveled: %.1f km%n",
                    ((AbstractVehicle) vehicle).getTotalDistance());
            System.out.printf("Operating time: %.1f hours%n",
                    ((AbstractVehicle) vehicle).getTotalFuelConsumed());
            System.out.printf("\nFuel Efficiency: %.1f %s%n",
                            vehicle.calculateFuelEfficiency(),
            vehicle instanceof ECar || vehicle instanceof EMotorcycle ? "kWh/100km" : "L/100km");
        }
    }
}
