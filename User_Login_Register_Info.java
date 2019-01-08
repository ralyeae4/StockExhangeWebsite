package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User_Login_Register_Info {

	//Check if username is in database
	public static boolean isUsernameInDB(String username){
		
		Connection conn = Database_Connect.Connect2DB();
		String usernameFromDB = "";
		boolean usernameFound = false;
		
		try{
			PreparedStatement usernameExists = conn.prepareStatement("SELECT username FROM account_table");
			ResultSet rs = usernameExists.executeQuery();
			
			while(rs.next()){
				usernameFromDB = rs.getString("username");
				
				if(usernameFromDB.equalsIgnoreCase(username)){
					usernameFound = true;
				}
			}
		} catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() +   "; " + ex.getMessage(), ex);
        }		
		
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usernameFound;
	}
	
	
	
	//check is username password combo is in database
	//1. pass in provided username/password to a method 
	//		that returns boolean
	//2. Create a method that returns the password based on
	//		the username.  In controller, check if the returned
	//		password matches the user provided password
	public static String getUserPassword(String username){
		Connection conn = Database_Connect.Connect2DB();
		String foundPassword = "";
		
		try{
			PreparedStatement ps = conn.prepareStatement("SELECT password FROM account_table WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			foundPassword = rs.getString(1);
		} catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName(), ex);
        }
		
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foundPassword;
	}
	
	
	
	
	//Obtain userPrimaryKey
	public static Integer getUserPrimaryKey(String username){
		Connection conn = Database_Connect.Connect2DB();
		int userKey = 0;
		
		try{
			PreparedStatement key = conn.prepareStatement("SELECT user_primary_key FROM account_table WHERE username = ?");
			key.setString(1, username);
			ResultSet resultUserKey = key.executeQuery();
			resultUserKey.next();
			userKey = resultUserKey.getInt(1);
		}catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName(), ex);
        }
		
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userKey;
	}
	
	
	//Enter new username & password into database
	public static void addUsernamePassword(String username, String password){
		Connection conn = Database_Connect.Connect2DB();
		
		try{
			PreparedStatement ps = conn.prepareStatement("INSERT INTO account_table (username, password) VALUES (?, ?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
		} catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() + ".addUsernamePassword ", ex);
        }
		
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
