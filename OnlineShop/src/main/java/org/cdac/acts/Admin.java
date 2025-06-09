package org.cdac.acts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>Admin Dashboard</title>");
		out.println("<style>");
		out.println("body { font-family: Arial; background-color: #f4f4f4; padding: 20px; }");
		out.println("h2 { text-align: center; }");
		out.println("ul { list-style: none; padding: 0; text-align: center; }");
		out.println("li { margin: 10px 0; }");
		out.println("a { text-decoration: none; color: #333; font-weight: bold; }");
		out.println("a:hover { color: #007BFF; }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>Welcome Admin</h2>");
		out.println("<ul>");
		out.println("<li><a href='AddCategory.html'>Add New Category</a></li>");
		out.println("<li><a href='AddProduct.html'>Add New Product</a></li>");
		out.println("<li><a href='ViewUsers'>View All Users</a></li>");
		out.println("<li><a href='Login.html'>Logout</a></li>");
		out.println("</ul>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

