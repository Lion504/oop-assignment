import java.util.HashMap;
import java.util.Map;

public class GroceryListManager {

    private final Map<String, Double> groceryList = new HashMap<>();
//public api
    void addItem (String item, Double price) {
        groceryList.put(item, price);
    }

    void removeItem (String item) {
        groceryList.remove(item);
        System.out.println("Remove " +  item + " from the grocery list..." + "\n");
    }

    void displayList () {
        if (groceryList.isEmpty()){
            System.out.println("Grocery List is Empty!");
        }

        System.out.println("Grocery List: ");
        int index = 1;
        for (Map.Entry<String, Double> entry : groceryList.entrySet()) {
            String item = entry.getKey();
            Double price = entry.getValue();
            System.out.println(index + ". "+ "Item: " + item + ", Price: " + price);
            index++;
        }

    }

    boolean checkItem (String item) {
        return groceryList.containsKey(item);
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
        String itemCheck = "Apple";
        boolean isInGrocery = groceryLM.checkItem(itemCheck);
        System.out.println("\nIs " + itemCheck + " in the grocery list? " + isInGrocery + "\n");

        groceryLM.removeItem(itemCheck);
        //isInGrocery = groceryListManager.checkItem(itemCheck);
        //System.out.println("Is " + itemCheck + " in the grocery list? " + isInGrocery + "\n");
        System.out.print("Update ");
        groceryLM.displayList();
    }
}
