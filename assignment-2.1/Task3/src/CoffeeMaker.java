public class CoffeeMaker {
    private String coffeeType;
    private boolean on = false;
    private final double amount;

    public CoffeeMaker(String coffeeType, double amount, boolean on) {
        this.coffeeType = coffeeType;
        this.amount = amount;
        this.on = false;
    }

    public boolean isOn() {
        return on;
    }

    public void turnOnOff() {
        on = !on;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public double getAmount() {
        return amount;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }
}

