package org.cdac.acts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AddCart
 */
@WebServlet("/AddCart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("Login.html");
			return;
		}
		
		String name = (String) session.getAttribute("userName");
		String temp = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(temp);
		temp = request.getParameter("productId");
		int productId = Integer.parseInt(temp);
		temp = request.getParameter("price");
		float price = Float.parseFloat(temp);
		
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
		out.println("<h2>Cart</h2>");
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
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
