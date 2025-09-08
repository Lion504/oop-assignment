public class Calculator {
    private int currentValue;
/*
* for copilot, they have completion and chat method alongside with the code cursor, which is good and also the most common idea for software devs
* the copilot completion use GPT‑4.1 Copilot model, which is the only model that supports code completion. even for pro user.
* firstly, I want to write a simple calculator class in java that can add, subtract, multiply, and divide two positive integers.
* the good news copilot completion feature have multiple line code completion, but bad news is it is not writing comments.
* so sometimes if you use copilot to write code, some of the code may not be easy to understand, because there is no comment.
* that is what I want say for copilot completion feature.
*
* for copilot chat, good side is it is like chatGPT, you can ask questions and get answers. which is user friendly.
* they have a code cursor, you can put the code cursor anywhere in the code, and they have several model you can choose,
* if you want you can even use your own API or local model.
* and also you can ask questions about the code. which is good.
* but the bad news is, it can only chat about the code file you current stay in, can not chat about multiple files.
* as a result model is smart but if you can not provide enough context like multiple code file you currently working on, it may give you wrong answer.
* so that is what I want say for copilot chat feature.
* */
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
