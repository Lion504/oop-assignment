# :boom:Calculator Program

A simple Java Calculator class implementing the Model part of an MVC pattern. It performs basic arithmetic on positive integers and stores the result of the last operation.

## :bangbang:Key Features

- Accepts only positive integers; negative inputs throw `IllegalArgumentException`.
- Supports addition, subtraction, multiplication, and division.
- Stores the result of the last operation (accessible via `getLastResult()`).
- `reset()` clears the stored result back to zero.
- Division validates against division by zero and throws `IllegalArgumentException`.

## :balloon:Design

The `Calculator` class is intended as the Model in an MVC architecture: it contains business logic and state without any UI or I/O responsibilities.

```
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

```

## :copilot:Explination:

### Public API (signatures)

```
int add(int a, int b)
int subtract(int a, int b)
int multiply(int a, int b)
int divide(int a, int b)    // integer division; throws if b == 0
int getCurrentValue()
void reset()

```

- Usage example
  This example shows typical usage and what to expect from getCurrentValue().

```
// Create calculator and perform operations
Calculator calc = new Calculator();

int sum = calc.add(5, 3);         // sum == 8, currentValue == 8
int diff = calc.subtract(10, 4);  // diff == 6, currentValue == 6
int prod = calc.multiply(2, 3);   // prod == 6, currentValue == 6
int quot = calc.divide(7, 2);     // quot == 3 (integer division), currentValue == 3

int current = calc.getCurrentValue(); // current == 3

calc.reset();                      // currentValue == 0

```

### Errors and validation

Passing a negative a or b throws IllegalArgumentException with message Cannot use negative integers.

Dividing by zero throws IllegalArgumentException with message Cannot divide by zero.

### Project files

* Calculator.java — the Calculator implementation.
* Testmain.java - for Testing.
* README.md — this file.

## :point\_right:Notes

* Division uses integer division; if fractional results are required, convert inputs to double or change the API to return double.
* The Calculator contains no I/O or UI code and is suitable for unit testing and integration into MVC controllers or views.
