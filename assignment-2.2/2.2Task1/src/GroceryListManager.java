import java.util.ArrayList;
public class GroceryListManager {

    private ArrayList<String> groceryList = new ArrayList<>();
//public api
    void addItem (String item) {
        groceryList.add(item);
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
        boolean isInGrocery = groceryLM.checkItem(itemCheck);
        System.out.println("\nIs " + itemCheck + " in the grocery list? " + isInGrocery + "\n");

        groceryLM.removeItem(itemCheck);
        //isInGrocery = groceryListManager.checkItem(itemCheck);
        //System.out.println("Is " + itemCheck + " in the grocery list? " + isInGrocery + "\n");
        System.out.print("Update ");
        groceryLM.displayList();
    }
}
