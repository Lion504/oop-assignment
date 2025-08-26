import java.util.ArrayList;
public class GroceryListManager {

    private ArrayList<String> groceryList = new ArrayList<>();

    public static void main (String[] args){
        GroceryListManager groceryListManager = new GroceryListManager();
        groceryListManager.addItem("Apple");
        groceryListManager.addItem("Banana");
        groceryListManager.addItem("Cheese");
        groceryListManager.addItem("Coffee");
        groceryListManager.addItem("Pizza");
        groceryListManager.displayList();
        String itemCheck = "Apple";
        boolean isInGrocery = groceryListManager.checkItem(itemCheck);
        System.out.println("\nIs " + itemCheck + " in the grocery list? " + isInGrocery + "\n");

        groceryListManager.removeItem(itemCheck);
        //isInGrocery = groceryListManager.checkItem(itemCheck);
        //System.out.println("Is " + itemCheck + " in the grocery list? " + isInGrocery + "\n");
        System.out.print("Update ");
        groceryListManager.displayList();
    }

    void addItem (String item) {
        groceryList.add(item);
    }

    void removeItem (String item) {
        groceryList.remove(item);
        System.out.println("Remove " +  item + " from the grocery list." + "\n");
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
}
