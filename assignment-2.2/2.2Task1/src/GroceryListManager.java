import java.util.ArrayList;
public class GroceryListManager {

    private ArrayList<String> groceryList = new ArrayList<>();
//public api
    void addItem (String itemName) {
        for (int j = 0; j < groceryList.size(); j++) {
            if (groceryList.get(j).equals(itemName)) {
                System.out.println("Duplicated. Item " + itemName + " is already present in the grocery list");
            }
        } groceryList.add(itemName);
    }

    public boolean removeItem(String itemName) {
        if (itemName != null) {
            for  (int i = 0; i < groceryList.size(); i++) {
                if (groceryList.get(i).equals(itemName)) {
                    System.out.println("Remove " + itemName + " from the grocery list..." + "\n");
                    groceryList.remove(itemName);
                    return true;
                }
            }
        }
        System.out.println("Remove " + itemName + " error, nothing here!\n");
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

    boolean checkItem (String itemName) {
        return groceryList.contains(itemName);
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

        String itemCheck1 = "Apple";
        String itemCheck2 = "Orange";

        //add duplicated
        System.out.println("Add " + itemCheck1 + " again");
        groceryLM.addItem(itemCheck1);

        System.out.println("\nCheck " +  itemCheck1 + " in grocery list");
        boolean isInGrocery = groceryLM.checkItem(itemCheck1);
        System.out.println("Is " + itemCheck1 + " in the grocery list? " + isInGrocery + "\n");

        groceryLM.removeItem(itemCheck1);
        groceryLM.removeItem(itemCheck2);

        //isInGrocery = groceryListManager.checkItem(itemCheck1);
        //System.out.println("Is " + itemCheck1 + " in the grocery list? " + isInGrocery + "\n");
        System.out.print("Update ");
        groceryLM.displayList();
    }
}
