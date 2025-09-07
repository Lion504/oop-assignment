public class Calculator {
    private int currentValue;

    public Calculator() {
        this.currentValue = 0;
    }

    // Resets the calculator to zero
    public void reset() {
        this.currentValue = 0;
    }

    // Adds two positive integers; throws exception if either is negative
    public int add(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Cannot use negative integers");
        }
        this.currentValue = a + b;
        return this.currentValue;
    }

    // Subtracts two positive integers; throws exception if either is negative
    public int subtract(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Cannot use negative integers");
        }
        this.currentValue = a - b;
        return this.currentValue;
    }

    // Multiplies two positive integers; throws exception if either is negative
    public int multiply(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Cannot use negative integers");
        }
        this.currentValue = a * b;
        return this.currentValue;
    }

    // Divides two positive integers; throws exception if either is negative or b is zero
    public int divide(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Cannot use negative integers");
        }
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        this.currentValue = a / b;  // Integer division
        return this.currentValue;
    }

    // Returns the current value of the calculator
    public int getCurrentValue() {
        return this.currentValue;
    }
}
