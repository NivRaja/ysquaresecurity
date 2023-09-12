package ysquaresecuritysystemproject;

public class User {
	
	    private int userId;
	    private String userName;
	    private String encryptedPassword;
	    
	    public User(int userId, String userName, String encryptedPassword) {
	        this.userId = userId;
	        this.userName = userName;
	        this.encryptedPassword = encryptedPassword;
	    }
	    
	    
	    // Getters and setters for userId, userName, and password
	    public int getUserId() {
	        return userId;
	    }

	    public String getUserName() {
	        return userName;
	    }
	    
	    public String getEncryptedPassword() {
	        return encryptedPassword;
	    }

	    @Override
	    public String toString() {
	        return "User [userId=" + userId + ", userName=" + userName + ", encryptedPassword=" + encryptedPassword + "]";
	    }
	}


