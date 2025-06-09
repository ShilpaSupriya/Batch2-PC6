package org.cdac.acts.dao;

import java.util.Iterator;

public interface CategoryDao {
	public Iterator<Category> getAllCategories();
	public void addCategory(Category category);
	public void updateCategory(int id, String change, String col);
}
