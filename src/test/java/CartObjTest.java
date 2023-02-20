import org.junit.Test;
import static org.junit.Assert.*;

public class CartObjTest {

    @Test
    public void testGettersAndSetters() {
        // Create a CartObj object
        CartObj cartObj = new CartObj("Pizza", 100, 2, "Add");

        // Test getter methods
        assertEquals("Pizza", cartObj.getFoodName());
        assertEquals(100, cartObj.getPrice());
        assertEquals(2, cartObj.getQuantity());
        assertEquals("Add", cartObj.getAction());

        // Test setter methods
        cartObj.setFoodName("Burger");
        assertEquals("Burger", cartObj.getFoodName());

        cartObj.setPrice(150);
        assertEquals(150, cartObj.getPrice());

        cartObj.setQuantity(3);
        assertEquals(3, cartObj.getQuantity());

        cartObj.setAction("Remove");
        assertEquals("Remove", cartObj.getAction());
    }

}
