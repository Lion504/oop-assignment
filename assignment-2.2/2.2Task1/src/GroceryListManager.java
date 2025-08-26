import java.util.ArrayList;
public class GroceryListManager {

    private ArrayList<String> groceryList = new ArrayList<>();
//public api
    void addItem (String item) {
        groceryList.add(item);
    }

    public boolean removeItem(String itemName) {
        if (itemName != null) {
            System.out.println("Remove " + itemName + " from the grocery list..." + "\n");
            return true;
        }
        System.out.println("Remove error, nothing here!");
        return false;
    }

    void displayList () {
        if (groceryList.isEmpty()){
            System.out.println("Grocery List is Empty!");
        }

        System.out.println("Grocery List: ");
        for (int i=0; i < groceryList.size(); i++) {
            System.out.println((i+1) + ". "+ groceryList.get(i));
        }

    }

    boolean checkItem (String item) {
        return groceryList.contains(item);
    }

//entry point
    public static void main (String[] args){
        GroceryListManager groceryLM = new GroceryListManager();
        groceryLM.addItem("Apple");
        groceryLM.addItem("Banana");
        groceryLM.addItem("Cheese");
        groceryLM.addItem("Coffee");
        groceryLM.addItem("Pizza");
        groceryLM.displayList();
        String itemCheck = "Apple";
        System.out.println("\nCheck " +  itemCheck + " in grocery list");
        boolean isInGrocery = groceryLM.checkItem(itemCheck);
        System.out.println("Is " + itemCheck + " in the grocery list? " + isInGrocery + "\n");

        groceryLM.removeItem(itemCheck);
        //isInGrocery = groceryListManager.checkItem(itemCheck);
        //System.out.println("Is " + itemCheck + " in the grocery list? " + isInGrocery + "\n");
        System.out.print("Update ");
        groceryLM.displayList();
    }
}
