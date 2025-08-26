import java.util.HashMap;
import java.util.Map;

public class GroceryListManager {

    private final Map<String, item> groceryList = new HashMap<>();
//combined price + category as one item
    private static class item {
        private double price;
        private String category;

        public item(double price, String category) {
            this.price = price;
            this.category = category;
        }
        public double getPrice() {
            return price;
        }
        public String getCategory() {
            return category;
        }
    }

    //public api
    void addItem (String name, double price, String category) {
        item value = new item(price, category);
        groceryList.put(name, value);
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
        for (Map.Entry<String, item> entry : groceryList.entrySet()) {
            String name = entry.getKey();
            item value = entry.getValue();
            Double price = value.getPrice();
            String category = value.getCategory();
            System.out.println(index + ". "+ "Item: " + name + ", Price: " + price + ", Category: " + category);
            index++;
        }

    }

    boolean checkItem (String item) {
        return groceryList.containsKey(item);
    }

//entry point
    public static void main (String[] args){
        GroceryListManager groceryLM = new GroceryListManager();
        groceryLM.addItem("Apple", 1.2, "fruit");
        groceryLM.addItem("Banana", 1.0, "fruit");
        groceryLM.addItem("Cheese", 3.5, "food");
        groceryLM.addItem("Coffee", 3.5, "food");
        groceryLM.addItem("Pizza",8.0, "food");
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
