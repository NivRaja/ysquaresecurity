package ysquaresecuritysystemproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;
public class ysquaresecuritysystemproject {

	public static void main(String[] args) {
		
		    try
		        {
		            System.out.println("Security System");
		            Scanner scan = new Scanner(System.in);
		            String key = "Secretkey";
		            Password password = new Password(key);
		            
		             
		        		
		            	
		          Class.forName("com.mysql.jdbc.Driver");
		            	    Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/securitysystem"
		            	    		+ "","root","suvniv");
		            	    if (connection != null) {
		                        System.out.println("Connected to the database!");
		                        
		                    }
		            	    else
		            	    {
		            	    	System.out.println("Database connection broken");
		            	    	System.exit(0);
		            	    }
		            	    ManageUser manageUser = new ManageUser( connection);	
		            
		            while(true)
		            {
		                System.out.println("Menu");
		                
		                System.out.println("1.Add User");
		                System.out.println("2.List Users");
		                System.out.println("3.Decrypt User Password");
		                System.out.println("4.Exit");

		                int userInput = scan.nextInt();

		                if(userInput==1)
		                {
		                    System.out.println("Adding User");
		                    System.out.println("Enter User Name");
		                    String userName = scan.next();
		                    
		                    if(manageUser.isUsernameTaken(userName))
		                    {
		                        System.out.println("UserName Already Taken");
		                        continue;
		                    }
		                    
		                    System.out.println("Enter password");
		                    String plainPassword = scan.next();
		                    
		                    String encryptedPassword = password.encryptPassword(plainPassword,key);
		                    
		                    User user = new User(0,userName,encryptedPassword);
		                    
		                    manageUser.addUser(user);
		                    
		                }
		                else if(userInput==2)
		                {
		                    System.out.println("Listing User");
		                    
		                    List <User> userList = manageUser.getAllUsers();
		                    
		                    for(int i=0;i<userList.size();i++)
		                    {
		                        System.out.println(userList.get(i).toString());
		                    }
		                }
		                else if(userInput==3)
		                {
		                    System.out.println("Decrypt User Password");
		                    System.out.println("Enter the userId to Decrypt the password");
		                    int userIdToDecrypt = scan.nextInt();
		                    User userToDecryptPassword = manageUser.getUserById(userIdToDecrypt);
		                    if(userToDecryptPassword!=null)
		                    {
		                        String decryptedPassword = password.decryptPassword(userToDecryptPassword.getEncryptedPassword(),key);
		                        System.out.println("Decrypted Password:"+decryptedPassword);

		                    }
		                    else
		                    {
		                        System.out.println("Invalid userId Entered");
		                    }    
		                }
		                else
		                {
		                    break;
		                }
		                    
		                
		            }
		            connection.close();
		            
		        }
		        catch(Exception ex)
		        {
		            ex.printStackTrace();
		            System.out.println(ex.getMessage());
		        }
	}

}
