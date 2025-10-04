import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test class for ShoppingCart - following TDD approach
 */
public class ShoppingCartTest {

    private ShoppingCart cart;

    /**
     * Set up a new shopping cart before each test
     */
    @Before
    public void setUp() {
        cart = new ShoppingCart();
    }

    /**
     * Test adding items to the cart
     */
    @Test
    public void testAddItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);

        assertEquals(2, cart.getItemCount());
    }

    /**
     * Test removing items from the cart
     */
    @Test
    public void testRemoveItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.removeItem("Apple");

        assertEquals(1, cart.getItemCount());
    }

    /**
     * Test calculating the total cost of items
     */
    @Test
    public void testCalculateTotal() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.addItem("Orange", 0.75);

        assertEquals(2.25, cart.calculateTotal(), 0.01);
    }

    /**
     * Test empty cart has zero items and zero total
     */
    @Test
    public void testEmptyCart() {
        assertEquals(0, cart.getItemCount());
        assertEquals(0.0, cart.calculateTotal(), 0.01);
    }

    /**
     * Test removing an item that doesn't exist (should not crash)
     */
    @Test
    public void testRemoveNonExistentItem() {
        cart.addItem("Apple", 1.0);
        cart.removeItem("Banana"); // Banana doesn't exist

        assertEquals(1, cart.getItemCount()); // Still has Apple
    }

    /**
     * Test adding multiple items with the same name
     */
    @Test
    public void testAddMultipleSameItems() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Apple", 1.0);

        assertEquals(2, cart.getItemCount());
        assertEquals(2.0, cart.calculateTotal(), 0.01);
    }
}

