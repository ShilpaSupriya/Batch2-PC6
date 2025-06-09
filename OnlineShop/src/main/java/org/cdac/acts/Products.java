package org.cdac.acts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Products
 */
@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("Login.html");
			return;
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("table { width: 80%; margin: auto; border-collapse: collapse; font-family: Arial; }");
		out.println("th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }");
		out.println("th { background-color: #f2f2f2; }");
		out.println("img { width: 100px; height: 100px; }");
		out.println("body { background-color: #fafafa; padding: 20px; }");
		out.println("h2 { text-align: center; font-family: Arial; }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>Products List</h2>");
		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Price</th>");
		out.println("<th>Image</th>");
		out.println("<th>Url</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		String Sid = request.getParameter("CategoryID");
		int id = Integer.parseInt(Sid);
		Connection dbConnection = null;
		PreparedStatement psGetProducts = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/shopdb", "root", "1210");
			psGetProducts = dbConnection.prepareStatement("SELECT * FROM products where categoryId = ?");
			psGetProducts.setInt(1, id);
			result = psGetProducts.executeQuery();
			
			while (result.next()) {
				String name = result.getString("productName");
				String price = result.getString("productPrice");
				String img = result.getString("productImg");

				out.println("<tr>");
				out.println("<td>" + name + "</td>");
				out.println("<td>" + price + "</td>");
				out.println("<td><img src='Images/" + img + "' alt='Image'></td>");
				out.println("<td><a href= 'AddCart?CategoryId=" +  result.getString("categoryId") + "&productId?=" + result.getString("productId") +  "&price=" + price + "'> Add to Cart</a></td>");
				out.println("</tr>");
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(psGetProducts != null){
				try {
					psGetProducts.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(dbConnection!=null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
