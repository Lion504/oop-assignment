import java.util.List;

public class GroceryListchecker {
    //entry point
    public static void main (String[] args) {
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
        //check each item cost and total cost
        groceryLM.displayTotalCost();

        //check items in Category
        String itemTest = "fruit";
        String itemCheck = "Apple";
        //groceryLM.getItemByCategory(itemTest);
        System.out.println("\ncheck items in " + itemTest + " Category.\n");
        groceryLM.displayCategoryItems(itemTest);
        groceryLM.addItem("Sausage", 3.5, "food", 66);
        System.out.println("Add more item\n");
        groceryLM.displayCategoryItems("food");

        //update quantity
        groceryLM.updateQuantity("Apple", 1);
        groceryLM.updateQuantity("Cheese", 20);
        //show + quantity items again, check update
        groceryLM.displayAvailableItems();
        //check each item cost and total cost
        groceryLM.displayTotalCost();

        //show category
        String getCategory = itemTest;
        List<String> insideCategory = groceryLM.displayByCategory(getCategory);
        System.out.printf("Items in '%s' category: %s%n", getCategory, insideCategory);
        List<String> insideCategory1 = groceryLM.displayByCategory("food");
        System.out.printf("Items in '%s' category: %s%n", getCategory, insideCategory1);
        //check item
        String checkitem = itemCheck;
        System.out.println("Check " +  checkitem + " in grocery list");
        boolean isInGrocery = groceryLM.checkItem(checkitem);
        System.out.println("Is " + checkitem + " in the grocery list? " + isInGrocery + "\n");
        //remove + check item
        groceryLM.removeItem(checkitem);
        System.out.print("Update ");
        groceryLM.displayList();
        //check category
        insideCategory = groceryLM.displayByCategory(getCategory);
        System.out.printf("\nNow items in '%s' category: %s%n", getCategory, insideCategory);
        //check each item cost and total cost
        groceryLM.displayTotalCost();

    }
}
