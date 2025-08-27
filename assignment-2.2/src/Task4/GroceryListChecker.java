package Task4;

import java.util.List;

public class GroceryListChecker {
    //entry point
    public static void main(String[] args) {
        //add items
        GroceryListManager groceryLM = new GroceryListManager();
        groceryLM.addItem("Apple", 1.2, "fruit", 100);
        groceryLM.addItem("Banana", 1.0, "fruit", 50);
        groceryLM.addItem("Cheese", 3.5, "food", 66);
        groceryLM.addItem("Coffee", 3.5, "food", 30);
        groceryLM.addItem("Pizza", 8.0, "food", 24);
        //groceryLM.displayList();

        //show items along with + quantity
        groceryLM.displayAvailableItems();

        //update quantity
        groceryLM.updateQuantity("Apple", 0);

        //show + quantity items again, check update
        groceryLM.displayAvailableItems();

        //show category
        String getCategory = "fruit";
        List<String> insideCategory = groceryLM.displayByCategory(getCategory);
        System.out.printf("\nItems in '%s' category: %s%n", getCategory, insideCategory);

        //check item
        String itemCheck = "Apple";
        System.out.println("Check " +  itemCheck + " in grocery list");
        boolean isInGrocery = groceryLM.checkItem(itemCheck);
        System.out.println("Is " + itemCheck + " in the grocery list? " + isInGrocery + "\n");

        //remove + check item
        groceryLM.removeItem(itemCheck);
        System.out.print("Update ");
        groceryLM.displayList();

        //check category
        insideCategory = groceryLM.displayByCategory(getCategory);
        System.out.printf("\nNow items in '%s' category: %s%n", getCategory, insideCategory);


    }
}
