package user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterUserServlet
 */
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			// Get the form data
			String userName = request.getParameter("name");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// Validate the form data
			if (userName == null || userName.isEmpty() || address == null || address.isEmpty() || email == null
					|| email.isEmpty() || password == null || password.isEmpty()) {

				out.println("<html><body>");
				out.println("<h3>Error: All fields are required </h3>");
				out.println("<a href='userRegister.html'>Go Back</a>");
				out.println("</body></html>");
				return;
			}

			// Create the user object
			User user = new User();

			user.setUserName(userName);
			user.setAddress(address);
			user.setEmail(email);
			user.setPassword(password);

			// Store the user object in a session
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(30 * 60); // 30 minutes in seconds
			session.setAttribute("user", user);

			User users = (User) session.getAttribute("user");

			// Check if the user object is not null
			if (users != null) {
				out.print("Welcome " + users.getUserName().toUpperCase());
			}

			out.println("</br>Successfully registered !!! </br> Please Login...</br>");
			request.getRequestDispatcher("login.html").include(request, response);
		} catch (Exception e) {
			out.print(e.getMessage());
		}
	}

}