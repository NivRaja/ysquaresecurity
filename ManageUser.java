package ysquaresecuritysystemproject;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ManageUser {
	
	    private List<User> userList;
        private Connection connection;
        private static final String INSERT_USERS_SQL = "INSERT INTO Users (Username, EncryptedPassword) VALUES (?, ?)";
        private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM Users";
	    public ManageUser(Connection connection) {
	        userList = new ArrayList<>();
	        this.connection=connection;
	    }

	    // Method to add a new user
	    public void addUser(User user) {
	        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	        	 preparedStatement.setString(1,user.getUserName());
	             preparedStatement.setString(2, user.getEncryptedPassword());
                preparedStatement.executeUpdate();
	            System.out.println("User added successfully.");
	        }catch(Exception e)
	        {
	        System.out.print(e);
	        }
	    }
	    
	    // Method to get all users
	    public List<User> getAllUsers() {
	    	try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_SQL);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                int userId = resultSet.getInt("UserId");
	                String username = resultSet.getString("Username");
	                String EncryptedPassword = resultSet.getString("EncryptedPassword");

	                User user = new User(userId, username, EncryptedPassword);
	                userList.add(user);
	            }
	    }   catch(Exception e)
	        {
	        System.out.print(e);
	        }         
	        return userList;
	    }
	    
	    // Method to check if a username is already taken
	    public boolean isUsernameTaken(String username) {
	        for (User user : userList) {
	            if (user.getUserName().equals(username)) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    public User getUserById(int Id)
	    {
	        for (User user : userList) {
	            if (user.getUserId()== Id) {
	                return user;
	            }
	        }
	        return null;
	    }
	}

