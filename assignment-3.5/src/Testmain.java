public class Testmain {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println("Addition: " + calc.add(10, 5));      // Should print 15
        System.out.println("Subtraction: " + calc.subtract(10, 3)); // Should print 7
        System.out.println("Multiplication: " + calc.multiply(4, 6)); // Should print 24
        System.out.println("Division: " + calc.divide(20, 4));   // Should print 5

        System.out.println("Current value: " + calc.getCurrentValue()); // Should print 5

        // Reset demonstration
        calc.reset();
        System.out.println("After reset: " + calc.getCurrentValue()); // Should print 0

        // These would throw exceptions if uncommented
        // calc.add(-1, 5);
        // calc.divide(10, 0);
    }
}
