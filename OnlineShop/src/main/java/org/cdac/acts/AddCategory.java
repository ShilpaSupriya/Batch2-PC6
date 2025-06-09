package org.cdac.acts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import org.cdac.acts.dao.*;

@WebServlet("/AddCategory")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("categoryName");
		String desc = request.getParameter("categoryDesc");
		String img = request.getParameter("categoryImg");

		Category newCategory = new Category(id, name, desc, img);

		CategoryDao dao = new CategoryDaoImpl();
		dao.addCategory(newCategory);
		PrintWriter out = response.getWriter();
		out.println("<html><body style='text-align:center;'>");
		out.println("<h2>Category Added Successfully</h2>");
		out.println("<a href='Admin'>Go Back to Admin</a>");
		out.println("</body></html>");
	}
}
