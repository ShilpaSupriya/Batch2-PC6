package org.cdac.acts.dao;

import java.util.Iterator;

public interface UsersDao {
	public void createUser(Users user);
	public boolean authenticate(String username, String password);
	public boolean adminCheck(String username, String password);
	public Iterator<Users> getUsers();
	
}
