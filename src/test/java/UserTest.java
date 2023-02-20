import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testGetUserName() {
        User user = new User("testuser","address","testemail@example.com", "testpassword");
        assertEquals("testuser", user.getUserName());
    }

    @Test
    public void testGetAddress() {
        User user = new User("testuser","address","testemail@example.com", "testpassword");
        assertEquals("address", user.getAddress());
    }

    @Test
    public void testGetEmail() {
        User user = new User("testuser","address","testemail@example.com", "testpassword");
        assertEquals("testemail@example.com", user.getEmail());
    }

    @Test
    public void testGetPassword() {
        User user = new User("testuser","address","testemail@example.com", "testpassword");
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    public void testSetUserName() {
        User user = new User("testuser","address","testemail@example.com", "testpassword");
        user.setUserName("newtestuser");
        assertEquals("newtestuser", user.getUserName());
    }

    @Test
    public void testSetAddress() {
        User user = new User("testuser","address","testemail@example.com", "testpassword");
        user.setAddress("newaddress");
        assertEquals("newaddress", user.getAddress());
    }

    @Test
    public void testSetEmail() {
        User user = new User("testuser","address","testemail@example.com", "testpassword");
        user.setEmail("newtestemail@example.com");
        assertEquals("newtestemail@example.com", user.getEmail());
    }

    @Test
    public void testSetPassword() {
        User user = new User("testuser","address","testemail@example.com", "testpassword");
        user.setPassword("newtestpassword");
        assertEquals("newtestpassword", user.getPassword());
    }
}
