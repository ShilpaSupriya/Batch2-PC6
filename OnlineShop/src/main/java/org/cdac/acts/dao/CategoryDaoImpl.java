package org.cdac.acts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class CategoryDaoImpl implements CategoryDao {
	
	Connection dbConnection;
	PreparedStatement ps;

	public CategoryDaoImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/shopdb", "root", "1210");
			ps = dbConnection.prepareStatement("SELECT * FROM CATEGORIES;");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	};


	@Override
	public Iterator<Category> getAllCategories() {
		ResultSet result = null;
		ArrayList<Category> allCategories = new ArrayList<>(); 
		try {
			result = ps.executeQuery();
			while(result.next()) {
				Category category = new Category(
						result.getInt(1),
						result.getString(2),
						result.getString(3),
						result.getString(4)
				);
				allCategories.add(category);
			}
			
			return allCategories.iterator();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(result!= null) {
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public void addCategory(Category category) {
		try(PreparedStatement psAdd = dbConnection.prepareStatement("INSERT INTO CATEGORIES VALUES (?, ?,?,?)")){
			psAdd.setInt(1, category.getCategoryId());
			psAdd.setString(2, category.getCategoryName());
			psAdd.setString(3, category.getCategoryDesc());
			psAdd.setString(4, category.getCategoryImg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateCategory(int id, String change, String col) {
		try (PreparedStatement psUpdate = dbConnection.prepareStatement(
	            "UPDATE categories SET " + col + " = ? WHERE categoryId = ?")) {
	        psUpdate.setString(1, change);
	        psUpdate.setInt(2, id);
	        psUpdate.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}

}
