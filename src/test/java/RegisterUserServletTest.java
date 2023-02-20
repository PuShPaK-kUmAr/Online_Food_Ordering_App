import static org.mockito.Mockito.*;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import org.junit.*;
import org.mockito.*;

public class RegisterUserServletTest {
	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	HttpSession session;

	@Mock
	RequestDispatcher rd;

	@InjectMocks
    RegisterUserServlet registerUserServlet; 

	@Test
	public void testDoPost() throws Exception {
	    PrintWriter writer = new PrintWriter(new StringWriter());

	    when(request.getSession()).thenReturn(session);
	    when(response.getWriter()).thenReturn(writer);
	    when(request.getParameter("name")).thenReturn("testname");
	    when(request.getParameter("address")).thenReturn("testaddress");
	    when(request.getParameter("email")).thenReturn("testemail@example.com");
	    when(request.getParameter("password")).thenReturn("testpassword");

	    new RegisterUserServlet().doPost(request, response);

	    verify(session, atLeast(1)).setAttribute("user", any(User.class));
	    verify(request, atLeast(1)).getRequestDispatcher("login.html");
	}

}