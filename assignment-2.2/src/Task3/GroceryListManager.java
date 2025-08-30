package Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    void removeItem (String itemName) {
        groceryList.remove(itemName);
        System.out.println("Remove " +  itemName + " from the grocery list..." + "\n");
    }

    boolean checkItem (String itemName) {
        return groceryList.containsKey(itemName);
    }

//display all items as list
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
            System.out.println(index + ". "+ "Name: " + name + ", Price: " + price + ", Category: " + category);
            index++;
        }

    }

// display items by category
    public List<String>displayByCategory (String category) {
        if (groceryList.isEmpty()){
            System.out.println("Grocery List is Empty!");
        }
                //get a view of the map as a set of Map.entry<String, item>
        return groceryList.entrySet()
                //turn set into stream inorder to do next step
                .stream()
                //.filter() just filter,  e -> e.getValue().getCategory().equals(category) make sure just get category
                .filter(e -> e.getValue().getCategory().equals(category))
                //to transforms each entry into it's original key, the stream will become Strings, not map entry objects.
                .map(Map.Entry::getKey)
                //collect items which found from above, into a new java.util.List<String>, final return
                .collect(Collectors.toList());
    }

//entry point
    public static void main (String[] args){
        //add items
        GroceryListManager groceryLM = new GroceryListManager();
        groceryLM.addItem("Apple", 1.2, "fruit");
        groceryLM.addItem("Banana", 1.0, "fruit");
        groceryLM.addItem("Cheese", 3.5, "food");
        groceryLM.addItem("Coffee", 3.5, "food");
        groceryLM.addItem("Pizza",8.0, "food");
        groceryLM.displayList();
        //show category
        String getCategory = "fruit";
        System.out.println("\nChecking " + getCategory);
        List<String> insideCategory = groceryLM.displayByCategory(getCategory);
        System.out.printf("Items in '%s' category: %s%n", getCategory, insideCategory);

        //check item
        String itemCheck = "Apple";
        System.out.println("\nChecking " + itemCheck);
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
