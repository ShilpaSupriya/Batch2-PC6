package org.assignments.day1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
//import java.sql.SQLException;
//import java.sql.Statement;

public class Assignment1 {

	public static void main(String[] args) {
		try (Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webjavab2", "root", "cdac");
				PreparedStatement psSelect = dbConnection.prepareStatement("select * from users where username = ?");
				PreparedStatement psInsert = dbConnection.prepareStatement("INSERT INTO USERS VALUES (?,?,?,?,?)");
				PreparedStatement psSearch = dbConnection.prepareStatement("SELECT * FROM USERS WHERE CITY = ?");
				PreparedStatement psUpdate = dbConnection.prepareStatement("update users set password = ? where username = ?");
				Scanner scanner = new Scanner(System.in);
				)
				{
			int choice;
	        
			do {
	            System.out.println("\nUser Management System");
	            System.out.println("1. Register a User");
	            System.out.println("2. List All Users based on City");
	            System.out.println("3. Update Password of a User");
	            System.out.println("4. Display user information based on User Name");
	            System.out.println("5. Exit");
	            System.out.print("Enter your choice: ");
	            
	            choice = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch(choice) {
	                case 1:
	                	System.out.println("\nUser Registration");
	                    System.out.print("Enter username: ");
	                    String username = scanner.next();
	                    
	                    System.out.print("Enter name: ");
	                    String name = scanner.next();
	                    
	                    System.out.print("Enter password: ");
	                    String password = scanner.next();
	                    
	                    System.out.print("Enter email: ");
	                    String email = scanner.next();
	                    
	                    System.out.print("Enter city: ");
	                    String city = scanner.next();
	                    
	                    psInsert.setString(1, username);
	        			psInsert.setString(2, password);
	        			psInsert.setString(3, name);
	        			psInsert.setString(4, email);
	        			psInsert.setString(5, city);
	        			psInsert.execute();
	                    System.out.println("User registered successfully!");
	                    break;
	                    
	                case 2:
	                	System.out.print("Enter city: ");
	                    String 	searchCity = scanner.next();
	                    psSearch.setString(1, searchCity);
	                    ResultSet resultSearch = psSearch.executeQuery();
	                    while(resultSearch.next()) {
	        				String sUserName = resultSearch.getString(1); 
	        				String sPassword = resultSearch.getString(2);
	        				String sName = resultSearch.getString(3);
	        				String sMail = resultSearch.getString(4);
	        				String sCity = resultSearch.getString(5);
	        				
	        				System.out.println(sUserName + " | " + sPassword + " | " + sName + " | " + sMail + " | " + sCity );
	        			}
	                    resultSearch.close();
	                    break;
	                    
	                case 3:
	                	System.out.println("\nUpdate password");
	                    System.out.print("Enter username: ");
	                    String usernameUpdate = scanner.next();
	                    System.out.print("Updated password: ");
	                    String passwordUpdate = scanner.next();
	                    psUpdate.setString(1, passwordUpdate);
	                    psUpdate.setString(2, usernameUpdate);
	                    psUpdate.execute();
	                    System.out.println("\nUpdated password");
	                    break;
	                    
	                case 4:
	                	 System.out.print("Enter username: ");
		                 String username2 = scanner.next();
		                 psSelect.setString(1, username2);
	                	ResultSet result = psSelect.executeQuery();
	                	while(result.next()) {
	        				String sUserName = result.getString(1); 
	        				String sPassword = result.getString(2);
	        				String sName = result.getString(3);
	        				String sMail = result.getString(4);
	        				String sCity = result.getString(5);
	        				
	        				System.out.println(sUserName + " | " + sPassword + " | " + sName + " | " + sMail + " | " + sCity );
	        			}
	                    break;
	                    
	                case 5:
	                    System.out.println("Exiting the application...");
	                    break;
	                    
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while(choice != 5);
	            

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
