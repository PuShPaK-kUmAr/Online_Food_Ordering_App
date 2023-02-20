import static org.mockito.Mockito.*;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import org.junit.*;
import org.mockito.*;

public class LoginServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;

    @InjectMocks
    LoginServlet loginServlet; 

    @Test
    public void testDoPost() throws Exception {
        PrintWriter writer = new PrintWriter(new StringWriter());
        User user = new User("testuser","address","testemail@example.com", "testpassword");

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(response.getWriter()).thenReturn(writer);
        
        when(request.getParameter("email")).thenReturn("testemail@example.com"); 
        when(request.getParameter("password")).thenReturn("testpassword");


//        request.setParameter("email", "testemail@example.com"); 
//        request.setParameter("password", "testpassword"); 

        new LoginServlet().doPost(request, response);

        verify(session, atLeast(1)).getAttribute("user");
        verify(response, atLeast(1)).getWriter();
    }

    @Test
    public void testDoGet() throws Exception {
        PrintWriter writer = new PrintWriter(new StringWriter());

        when(response.getWriter()).thenReturn(writer);
        when(request.getRequestDispatcher(anyString())).thenReturn(rd);

        new LoginServlet().doGet(request, response);

        verify(session, atLeast(1)).invalidate();
        verify(rd).include(request, response);
        verify(response).sendRedirect("login.html");
    }
}
