
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String foodName = null;
		int price = 0;
		int quantity = 0; 
		String action = null;
		try {
			if (request.getParameter("pizza_submit") != null) {
				foodName = "Pizza";
				price = Integer.parseInt(request.getParameter("pizza_price"));
				quantity = Integer.parseInt(request.getParameter("pizza_quantity"));
				action = "Added";
			} else if (request.getParameter("pasta_submit") != null) {
				foodName = "Pasta";
				price = Integer.parseInt(request.getParameter("pasta_price"));
				quantity = Integer.parseInt(request.getParameter("pasta_quantity"));
				action = "Added";
			} else if (request.getParameter("burger_submit") != null) {
				foodName = "Burger";
				price = Integer.parseInt(request.getParameter("burger_price"));
				quantity = Integer.parseInt(request.getParameter("burger_quantity"));
				action = "Added";
			} else if (request.getParameter("sandwich_submit") != null) {
				foodName = "Sandwich";
				price = Integer.parseInt(request.getParameter("sandwich_price"));
				quantity = Integer.parseInt(request.getParameter("sandwich_quantity"));
				action = "Added";
			} else if (request.getParameter("fries_submit") != null) {
				foodName = "French Fries";
				price = Integer.parseInt(request.getParameter("fries_price"));
				quantity = Integer.parseInt(request.getParameter("fries_quantity"));
				action = "Added";
			}

			CartObj cartItem = new CartObj(foodName, price, quantity, action); 

			// add the CartObj to a list
			@SuppressWarnings("unchecked")
			List<CartObj> cartItems = (List<CartObj>) (Object) request.getSession().getAttribute("cartItems");

			if (cartItems == null) {
			    cartItems = new ArrayList<>();
			}

			cartItems.add(cartItem);
			request.getSession().setAttribute("cartItems", cartItems);

			// set the "Add to Cart" button to "Added" 
			String buttonName = request.getParameter("pizza_submit");
			if (buttonName == null) {
				buttonName = request.getParameter("pasta_submit");
			}
			if (buttonName == null) {
				buttonName = request.getParameter("burger_submit");
			}
			if (buttonName == null) {
				buttonName = request.getParameter("sandwich_submit");
			}
			if (buttonName == null) {
				buttonName = request.getParameter("fries_submit");
			}
			if (buttonName != null) {
				request.setAttribute(buttonName, "Added");
			}

			request.getRequestDispatcher("welcome.html").include(request, response);
		} catch (Exception error) {
			request.setAttribute("error", "An error has occurred: " + error.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();

			List<CartObj> cartItems = (List<CartObj>) session.getAttribute("cartItems");
			if (cartItems == null || cartItems.isEmpty()) {
				out.println("<h1>Your cart is empty</h1>");
			} else {
				out.println("<h1>Cart</h1>");
				out.println("<table>");
				out.println("<tr><th>Food Item</th><th>Price</th><th>Quantity</th></tr>");
				for (CartObj item : cartItems) {
					out.println("<tr><td>" + item.getFoodName() + "</td><td>" + item.getPrice() * item.getQuantity()
							+ "</td><td>" + item.getQuantity() + "</td></tr>");
				}
				out.println("</table>");
			}
			// Add a button to go back to the previous page
			out.println("<a href='welcome.html'>Go Back to Food Menu</a>");
		} catch (Exception e) {
			throw new ServletException("Exception in CartServlet", e);
		} finally {
			out.close();
		}
	}
}