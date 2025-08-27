public class CoffeeMakerDriver {
    public static void main(String[] args) {
        CoffeeMaker myCoffee =  new CoffeeMaker("Espresso", 50, true);
        myCoffee.turnOnOff();
        System.out.println("Coffee maker is " + (myCoffee.isOn()? "on" : "off") + "\nCoffee type is " + myCoffee.getCoffeeType() + "\nCoffee amount is " +  myCoffee.getAmount() + " ml");
        myCoffee.turnOnOff();
        System.out.println("Coffee maker is " + (myCoffee.isOn()? "on" : "off"));

        myCoffee.setCoffeeType("Latte");  // Test

        myCoffee.turnOnOff();  // Turns back on
        System.out.println("Coffee maker is " + (myCoffee.isOn() ? "on" : "off") + "\nCoffee type is " + myCoffee.getCoffeeType() + "\nCoffee amount is " + myCoffee.getAmount() + " ml");
        myCoffee.turnOnOff();
        System.out.println("Coffee maker is " + (myCoffee.isOn()? "on" : "off"));
    }
}