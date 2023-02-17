package user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = null;

		try {
			out = response.getWriter();
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			if (user == null) {
				out.print("Please Register !!! </br>");
				out.print("<a href=\"userRegister.html\">Register User</a>");
			} else {
				String pass = user.getPassword();
				String emailId = user.getEmail();
				String name = (user.getUserName()).substring(0, 1).toUpperCase() + user.getUserName().substring(1);

				if (password.equals(pass) && email.equals(emailId)) {
					out.print("<h1>");
					out.print("</br>Welcome " + name);
					out.print("</h1>");
					request.getRequestDispatcher("welcome.html").include(request, response);

					Cookie ck = new Cookie("email", email);
					response.addCookie(ck);
				} else {
					out.print("sorry, username or password error...");
					request.getRequestDispatcher("login.html").include(request, response);
				}
			}
		} catch (IOException e) {
			throw new ServletException("Exception in LoginServlet", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = null;

		try {
			out = response.getWriter();
			HttpSession session = request.getSession();
			session.invalidate();
			out.print("Logged out Successfully...</br>");
			request.getRequestDispatcher("login.html").include(request, response);
			response.sendRedirect("login.html");
		} catch (IOException e) {
			throw new ServletException("Exception in LoginServlet", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
