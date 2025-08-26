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
        System.out.println("Grocery List: " + groceryListManager.groceryList);
    }

    void addItem (String item) {
        groceryList.add(item);
    }

    void removeItem (String item) {
        groceryList.remove(item);
    }

    void displayList () {
        groceryList.forEach(System.out::println);
        System.out.println("\nGrocery List: " +  groceryList + "\n");
    }

    void checkItem (String item) {
        groceryList.contains(item);
    }
}
