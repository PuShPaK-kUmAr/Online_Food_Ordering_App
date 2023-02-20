
import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServletTest {

    private CartServlet cartServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    
    @Before
    public void setUp() throws Exception {
        cartServlet = new CartServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
    }

    @Test
    public void testDoPost() throws Exception {
        Mockito.when(request.getParameter("pizza_submit")).thenReturn("pizza_submit");
        Mockito.when(request.getParameter("pizza_price")).thenReturn("10");
        Mockito.when(request.getParameter("pizza_quantity")).thenReturn("2");
        Mockito.when(request.getSession()).thenReturn(session);
        List<CartObj> expectedCartItems = new ArrayList<>();
        expectedCartItems.add(new CartObj("Pizza", 10, 2, "Added"));
        Mockito.when(session.getAttribute("cartItems")).thenReturn(null);
        cartServlet.doPost(request, response);
        Mockito.verify(session).setAttribute("cartItems", expectedCartItems);
        Mockito.verify(request).setAttribute("pizza_submit", "Added");
    }

    @Test
    public void testDoGetWithEmptyCart() throws Exception {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("cartItems")).thenReturn(null);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        cartServlet.doGet(request, response);
        writer.flush();
        String output = stringWriter.toString();
        assertTrue(output.contains("Your cart is empty"));
    }

    @Test
    public void testDoGetWithNonEmptyCart() throws Exception {
        List<CartObj> cartItems = new ArrayList<>();
        cartItems.add(new CartObj("Pizza", 10, 2, "Added"));
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("cartItems")).thenReturn(cartItems);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        cartServlet.doGet(request, response);
        writer.flush();
        String output = stringWriter.toString();
        assertTrue(output.contains("<h1>Cart</h1>"));
        assertTrue(output.contains("<td>Pizza</td><td>20</td><td>2</td>"));
        assertTrue(output.contains("<a href='welcome.html'>Go Back to Food Menu</a>"));
    }

}
