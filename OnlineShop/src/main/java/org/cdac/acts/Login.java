package org.cdac.acts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import org.cdac.acts.dao.UsersDaoImpl;
import org.cdac.acts.dao.UsersDao;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Connection dbConnection = null;
//		PreparedStatement psLogin = null;
//		ResultSet resultLogin = null;
		
		
		
		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			dbConnection =  DriverManager.getConnection("jdbc:mysql://localhost/shopdb", "root", "1210");
//			psLogin = dbConnection.prepareStatement("SELECT * FROM users where username = ? and password = ?");
			PrintWriter out = response.getWriter();
			String user = request.getParameter("userName");
			String password = request.getParameter("password");
//			psLogin.setString(1, user);
//			psLogin.setString(2, password);
//			resultLogin = psLogin.executeQuery();
		
		UsersDao userAuth = new UsersDaoImpl();
		
		if(userAuth.adminCheck(user, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", user);
			response.sendRedirect("Admin");
			return;
		} else if(userAuth.authenticate(user, password)) {
//				out.println("<html><body>");
//				out.println("<font color='blue'>LOGIN SUCCESSFUL</font>");
//				out.println("</body></html>");
				HttpSession session = request.getSession();
				session.setAttribute("userName", user);
				response.sendRedirect("Category");
			} else {
				out.println("<html><body>");
				out.println("<font color='red'>LOGIN INVALID</font>");
				out.println("<a href='Login.html'> Try again! </a>");
				out.println("</body></html>");
			}
}
			


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
