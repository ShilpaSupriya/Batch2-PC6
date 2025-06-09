package org.cdac.acts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.cdac.acts.dao.UsersDaoImpl;
import org.cdac.acts.dao.UsersDao;
import org.cdac.acts.dao.Users;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsersDao newUser = new UsersDaoImpl();
		
		Users user = new Users (
				request.getParameter("userName"),
				request.getParameter("password"),
				request.getParameter("name"),
				request.getParameter("phone")
		);
		
		newUser.createUser(user);
		
		response.sendRedirect("Login.html");
		
//		Connection dbConnection = null;
//		PreparedStatement psRegister = null;
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			dbConnection =  DriverManager.getConnection("jdbc:mysql://localhost/shopdb", "root", "1210");
//			psRegister = dbConnection.prepareStatement("INSERT INTO users VALUES ( ?, ?, ?, ?)");
//			String user = request.getParameter("userName");
//			String password = request.getParameter("password");
//			String name = request.getParameter("name");
//			String phone = request.getParameter("phone");
//			
//			psRegister.setString(1, user);
//			psRegister.setString(2, password);
//			psRegister.setString(3, name);
//			psRegister.setString(4, phone);
//			psRegister.execute();
			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if(psRegister!=null)
//				try {
//					psRegister.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			if(dbConnection!=null) {
//				try {
//					dbConnection.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			
		}
		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
