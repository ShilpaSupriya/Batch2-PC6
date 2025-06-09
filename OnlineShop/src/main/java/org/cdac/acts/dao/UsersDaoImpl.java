package org.cdac.acts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class UsersDaoImpl implements UsersDao {
	
	Connection db;
	PreparedStatement createUser;
	PreparedStatement getUsers;
	
	public UsersDaoImpl() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			db = DriverManager.getConnection("jdbc:mysql://localhost/shopdb", "root", "1210");
			createUser = db.prepareStatement("INSERT INTO USERS VALUES (?, ?, ? , ?)");
			getUsers = db.prepareStatement("SELECT * FROM USERS;");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void createUser(Users user) {
		
		try {
			createUser.setString(1, user.getUserName());
			createUser.setString(2, user.getPassword());
			createUser.setString(3, user.getName());
			createUser.setString(4, user.getPhone());
			createUser.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public Iterator<Users> getUsers() {
			//ResultSet res = getUsers.executeQuery()
		return null;
	}

	@Override
	public boolean authenticate(String username, String password) {
	    try (PreparedStatement ps = db.prepareStatement("SELECT * FROM USERS WHERE userName = ? AND password = ?")) {
	        ps.setString(1, username);
	        ps.setString(2, password);
	        ResultSet rs = ps.executeQuery();
	        return rs.next(); 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public boolean adminCheck(String username, String password) {

		    return username.equals("admin") && password.equals("admin");
	}

}
