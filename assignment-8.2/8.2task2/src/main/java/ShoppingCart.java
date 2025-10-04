import java.util.ArrayList;
import java.util.List;

/**
 * ShoppingCart class - manages items in a shopping cart
 * This class allows users to add items, remove items, and calculate total cost
 */
public class ShoppingCart {

    // Inner class to represent an item in the cart
    private static class Item {
        String name;
        double price;

        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    // List to store all items in the cart
    private List<Item> items;

    /**
     * Constructor - creates a new empty shopping cart
     */
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds an item to the shopping cart
     * @param name the name of the item (e.g., "Apple")
     * @param price the price of the item (e.g., 1.0)
     */
    public void addItem(String name, double price) {
        items.add(new Item(name, price));
    }

    /**
     * Removes the first occurrence of an item with the given name
     * If the item doesn't exist, nothing happens
     * @param name the name of the item to remove
     */
    public void removeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).name.equals(name)) {
                items.remove(i);
                break; // Remove only the first occurrence
            }
        }
    }

    /**
     * Gets the number of items in the cart
     * @return the total count of items
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Calculates the total cost of all items in the cart
     * @return the sum of all item prices
     */
    public double calculateTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.price;
        }
        return total;
    }
}

