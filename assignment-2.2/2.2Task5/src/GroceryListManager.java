import jdk.jfr.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroceryListManager {

    private final Map<String, Item> groceryList = new HashMap<>();

    //combined price + category as one item
    private static class Item {
        private double price;
        private String category;
        private int quantity;

        public Item(double price, String category, int quantity) {
            this.price = price;
            this.category = category;
            this.quantity = quantity;
        }

        //getters
        public double getPrice() {
            return price;
        }

        public String getCategory() {
            return category;
        }

        public int getQuantity() {
            return quantity;
        }

        //Setters
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    //public api
    public void addItem(String name, double price, String category, int quantity) {
        Item value = new Item(price, category, quantity);
        groceryList.put(name, value);
    }

    //remover
    public boolean removeItem(String itemName) {
        Item remover = groceryList.remove(itemName);
        if (remover != null) {
            System.out.println("Remove " + itemName + " from the grocery list..." + "\n");
            return true;
        }
        System.out.println("Remove error, nothing here!");
        return false;
    }

    public boolean checkItem(String itemName) {
        return groceryList.containsKey(itemName);
    }

    //update quantity
    public boolean updateQuantity(String itemName, int quantity) {
        Item Qupdate = groceryList.get(itemName);
        System.out.println("\nUpdate " + itemName + " quantity");
        if (Qupdate != null) {
            Qupdate.setQuantity(quantity);
            System.out.println("Update success, " + itemName + " new quantity " + quantity + "\n");
            System.out.println("After Update");
            return true;
        } else {
            System.out.println("Update error, no item here!");
            return false;
        }

    }

    //display all items as list
    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("Grocery List is Empty!");
        }
        System.out.println("Grocery List: ");
        int index = 1;
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            String name = entry.getKey();
            Item value = entry.getValue();
            Double price = value.getPrice();
            String category = value.getCategory();
            int quantity = value.getQuantity();
            System.out.println(index + ". " + "Name: " + name
                    + ", Price: " + price + " euros"
                    + ", Category: " + category
                    + ", Quantity: " + quantity);
            index++;
        }

    }

    // display items by category
    public List<String> displayByCategory(String category) {
        if (groceryList.isEmpty()) {
            System.out.println("Grocery List is Empty!");
        }
        System.out.println("\nCheck category " + category);
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

    /* //get specific category
     public List<String> getItemByCategory(String category) {
         if (groceryList.isEmpty()) {
             System.out.println("Grocery List is Empty!");
         }
         return groceryList.entrySet()
                 .stream()
                 .filter(e -> e.getValue().getCategory().equalsIgnoreCase(category))
                 .map(Map.Entry::getKey)
                 .collect(Collectors.toList());
     }*/
    //display specific category items
    public void displayCategoryItems(String category) {
        System.out.println("Items in " + category + " category: ");
        boolean found = false;
        double categoryCost = 0;
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            String name = entry.getKey();
            Item value = entry.getValue();

            if (value.getCategory().equals(category)) {
                double itemTotalCost = value.getPrice() * value.getQuantity();
                System.out.println(name + ": " + value.getQuantity() + " cost " + itemTotalCost + " euros");
                found = true;
                categoryCost += itemTotalCost;
            }

        }
        System.out.println("Category cost: " + categoryCost + "euros");
        if (!found) {
            System.out.println(category + " not found items!");
        }
    }


    //display quantity + items
    public void displayAvailableItems() {
        if (groceryList.isEmpty()) {
            System.out.println("Grocery List is Empty!");
        }
        int index = 1;
        System.out.println("Grocery Available items List: ");
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            String name = entry.getKey();
            Item value = entry.getValue();
            Double price = value.getPrice();
            String category = value.getCategory();
            int quantity = value.getQuantity();
            if (quantity <= 0) {
                System.out.println(index + ". Warning! " + name + " is Empty!");
            } else {
                System.out.println(index + ". " + "Name: " + name
                        + ", Price: " + price + " euros"
                        + ", Category: " + category
                        + ", Quantity: " + quantity);
            }
            index++;
        }
    }

    //display and calculate total cost
    public double totalCost() {
        if (groceryList.isEmpty()) {
            System.out.println("Grocery List is Empty!");
        }
        double cost = 0;
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            Item value = entry.getValue();
            Double price = value.getPrice() * value.getQuantity();
            cost += price;
        }
        return cost;
    }

    //display each item total cost
    public void displayTotalCost() {
        if (groceryList.isEmpty()) {
            System.out.println("Grocery List is Empty! Total cost is 0!");
        }
        int index = 1;
        double itemTotalCost = 0;
        System.out.println("Each item cost list: ");
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            String name = entry.getKey();
            Item value = entry.getValue();
            Double totalprice = value.getPrice() + value.getQuantity();
            itemTotalCost += totalprice;
            System.out.printf("%s. Name: %s, cost %.2f euros. %n", index, name, totalprice);
            index++;
        }
        System.out.println("Total cost: " + totalCost() + " euros.");
    }


}
