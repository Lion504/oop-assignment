package Task2;

import java.util.HashMap;
import java.util.Map;

public class GroceryListManager {
    private final Map<String, Item> groceryList = new HashMap<>();

    private static class Item {
        private double price;

        public Item(double price) {
            this.price = price;
        }

        //getters
        public double getPrice() {
            return price;
        }
    }
//public api
    void addItem (String itemName, Double price) {
        Item value = new Item(price);
        groceryList.put(itemName, value);
    }

    void removeItem (String itemName) {
        groceryList.remove(itemName);
        System.out.println("Remove " +  itemName + " from the grocery list..." + "\n");
    }

    void displayList () {
        if (groceryList.isEmpty()){
            System.out.println("Grocery List is Empty!");
        }

        System.out.println("Grocery List: ");
        int index = 1;
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            String itemName = entry.getKey();
            Item value = entry.getValue();
            double price = value.getPrice();
            System.out.println(index + ". "+ "Item: " + itemName + ", Price: " + price);
            index++;
        }

    }

    boolean checkItem (String itemName) {
        return groceryList.containsKey(itemName);
    }

    //display and calculate total cost
    public double totalCost() {
        if (groceryList.isEmpty()) {
            System.out.println("Grocery List is Empty!");
        }
        double cost = 0;
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            Item value = entry.getValue();
            Double price = value.getPrice();
            cost += price;
        }
        System.out.println("Total Cost: " + cost + " euros");
        return cost;
    }

//entry point
    public static void main (String[] args){
        GroceryListManager groceryLM = new GroceryListManager();
        groceryLM.addItem("Apple", 1.2);
        groceryLM.addItem("Banana", 1.0);
        groceryLM.addItem("Cheese", 3.5);
        groceryLM.addItem("Coffee", 3.5);
        groceryLM.addItem("Pizza",8.0);
        groceryLM.displayList();
        groceryLM.totalCost();

        String itemCheck1 = "Apple";

        System.out.println("\nCheck " +  itemCheck1 + " in grocery list");
        boolean isInGrocery = groceryLM.checkItem(itemCheck1);
        System.out.println("Is " + itemCheck1 + " in the grocery list? " + isInGrocery + "\n");

        groceryLM.removeItem(itemCheck1);
        //isInGrocery = groceryListManager.checkItem(itemCheck);
        //System.out.println("Is " + itemCheck + " in the grocery list? " + isInGrocery + "\n");
        System.out.print("Update ");
        groceryLM.displayList();
        groceryLM.totalCost();

    }
}
