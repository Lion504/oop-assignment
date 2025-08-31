public class Tester {
    public static void main(String[] args) {
        // Test SportsCar
        System.out.println("=== 🏁 Sports car test 🏁 ===");
        SportsCar ferrari = new SportsCar("Ferrari", 80, 350);
        ferrari.addGasoline(50);

        System.out.println("Initial state: " + ferrari);

        // Test multiple acceleration cycles
        System.out.println("\n--- Acceleration Test ---");
        for (int i = 1; i <= 5; i++) {
            ferrari.accelerate(15);
            System.out.printf("Acceleration cycle %d: Speed = %.1f km/h, Fuel = %.1f L%n", i, ferrari.getSpeed(), ferrari.getGasolineLevel());}
        System.out.println("After acceleration: " + ferrari.getSpeed());
        // Test turbo boost
        System.out.println("\n--- Turbo Boost Test ---");
        ferrari.turboBoost();
        System.out.println("After turbo: " + ferrari.getSpeed());
        // Test deceleration
        System.out.println("\n--- Deceleration Test ---");
        ferrari.decelerate(40);
        System.out.println("After decelerate: Speed = " + ferrari.getSpeed());
        ferrari.decelerate(20);
        System.out.println("After decelerate: Speed = " + ferrari.getSpeed());

        // Test Bus
        System.out.println("\n=== 🚌 Bus test ===");
        Bus cityBus = new Bus("City Bus", 200, 120, 40);
        cityBus.addGasoline(100);
        // Test empty bus performance
        System.out.println("\n--- Empty Bus Performance ---");
        for (int i = 1; i <= 3; i++) {
            cityBus.accelerate(20);
            System.out.printf("Empty bus acceleration %d: Speed = %.1f km/h%n",
                    i, cityBus.getSpeed());
        }
        System.out.println("Speed: " + cityBus.getSpeed());

        // Test passenger loading impact
        System.out.println("\n--- Passenger Load Impact Test ---");
        cityBus.passengerEnter(10);
        cityBus.accelerate(20);
        System.out.printf("With 10 passengers: Speed = %.1f km/h%n", cityBus.getSpeed());

        cityBus.passengerEnter(20);
        cityBus.accelerate(20);
        System.out.printf("With 30 passengers: Speed = %.1f km/h%n", cityBus.getSpeed());

        cityBus.passengerEnter(20);
        cityBus.accelerate(20);
        System.out.printf("With 50 passengers: Speed = %.1f km/h%n", cityBus.getSpeed());

        // Test passenger exit
        cityBus.passengerExit(25);
        System.out.println("After 25 exit: " + cityBus.getPassengerCount() + " passengers");
        System.out.printf("Speed = %.1f km/h%n", cityBus.getSpeed());
        cityBus.passengerEnter(15);
        System.out.println("After 15 enter: " + cityBus.getPassengerCount() + " passengers");
        System.out.printf("Speed = %.1f km/h%n", cityBus.getSpeed());
    }
}
