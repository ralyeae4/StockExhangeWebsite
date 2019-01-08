package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database_Connect {
	//public static String user = "root", pass = "";
	public static String user = "ralyeae4", pass = "earrox96";
	
	public static Connection Connect2DB(){
		Connection conn = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/stocks_db", Database_Connect.user, Database_Connect.pass);		
			return conn;
		} catch (ClassNotFoundException ex) {
            Logger.getLogger(Database_Connect.class.getName()).log(Level.SEVERE, Database_Connect.class.getName() + ".Connect2DB" + " ClassNotFoundException", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database_Connect.class.getName()).log(Level.SEVERE, Database_Connect.class.getName() + ".Connect2DB" + " SQLException", ex);
        }
        return conn;		
	}
}
