package org.cdac.acts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import org.cdac.acts.dao.CategoryDaoImpl;
import org.cdac.acts.dao.CategoryDao;
import org.cdac.acts.dao.Category;

import java.sql.DriverManager;

@WebServlet("/Category")
public class CategoryWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		out.println("<h2>Category List</h2>");
		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Description</th>");
		out.println("<th>Image</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");

		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/shopdb", "root", "1210");
//			PreparedStatement ps = dbConnection.prepareStatement("SELECT * FROM categories");
//			ResultSet rs = ps.executeQuery();
			
			CategoryDao category = new CategoryDaoImpl();
			Iterator<Category> allCategory = category.getAllCategories();
			
			while(allCategory.hasNext()) {
				 Category c = allCategory.next();
	                out.println("<tr>");
	                out.println("<td><a href='Products?CategoryID=" + c.getCategoryId() + "'>" + c.getCategoryName() + "</a></td>");
	                out.println("<td>" + c.getCategoryDesc() + "</td>");
	                out.println("<td><img src='Images/" + c.getCategoryImg() + "' alt='Image'></td>");
	                out.println("</tr>");
			}
//			while (rs.next()) {
//				String id = rs.getString("id");
//				String name = rs.getString("categoryName");
//				String desc = rs.getString("categoryDesc");
//				String img = rs.getString("categoryImg");
//
//				out.println("<tr>");
//				out.println("<td> <a href = 'Products?CategoryID="+ id + "'>" + name + "</a></td>");
//				out.println("<td>" + desc + "</td>");
//				out.println("<td><img src='Images/" + img + "' alt='Image'></td>");
//				out.println("</tr>");
//			
//			}
//			rs.close();
//			ps.close();
//			dbConnection.close();

		} catch (Exception e) {
			
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}
}

