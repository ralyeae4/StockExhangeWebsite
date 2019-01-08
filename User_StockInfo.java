package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import beans.sessionBean;


public class User_StockInfo {
	//Create
	public static void insertNewStock(Integer userPrimaryKey, String stockSymbol, Integer stockAmount, double stockValue){
		Connection conn = Database_Connect.Connect2DB();
		
		try{
			PreparedStatement ps = conn.prepareStatement("INSERT INTO stock_table (user_primary_key, stock_symbol, stock_amount, stock_value) VALUES (?, ?, ?, ?)");
			ps.setInt(1, userPrimaryKey);
			ps.setString(2, stockSymbol);
			ps.setInt(3, stockAmount);
			ps.setDouble(4, stockValue);
			ps.executeUpdate();
			
		} catch (SQLException ex) {
            Logger.getLogger(User_StockInfo.class.getName()).log(Level.SEVERE, User_StockInfo.class.getName() + ".insertNewStock ", ex);
        }
		
		String stockAmountString = Integer.toString(stockAmount);
		String stockValueString = Double.toString(stockValue);
		sessionBean.setStockHoldings(stockSymbol, stockValueString, stockAmountString);
		
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_StockInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	//Read
	public static void returnStockInfo(Integer userPrimaryKey){
		Connection conn = Database_Connect.Connect2DB();
		String stockSymbol = "";
		Integer stockAmount = 0;
		Double stockValue;
		String stockAmountString = "";
		String stockValueString = "";
		
		try{
			PreparedStatement ps = conn.prepareStatement("SELECT stock_symbol, stock_amount, stock_value FROM stock_table WHERE user_primary_key = ?");
			ps.setInt(1, userPrimaryKey);
			ResultSet result = ps.executeQuery();

			while(result.next()){

				sessionBean.setStockSymbol(result.getString(1));
				sessionBean.setStockAmount(result.getInt(2));
				
				stockSymbol = result.getString(1);
				stockAmount = result.getInt(2);
				stockValue = result.getDouble(3);
			    
			    stockAmountString = Integer.toString(stockAmount);
			    stockValueString = stockValue.toString();
			    
			    //System.out.println("Stock amount string: " + stockAmountString + "  stock Value string: " + stockValueString);
			    
			    sessionBean.setStockHoldings(stockSymbol, stockValueString, stockAmountString);
			}			

			
		} catch (SQLException ex) {
            Logger.getLogger(User_StockInfo.class.getName()).log(Level.SEVERE, User_StockInfo.class.getName() + ".returnStockInfo ", ex);
        }
		
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_StockInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	//Update
	public static void updateStock(Integer amount, Integer userPrimaryKey, String stockSymbol, double stockValue){
		Connection conn = Database_Connect.Connect2DB();
		
		try{
			PreparedStatement ps = conn.prepareStatement("UPDATE stock_table SET stock_amount = ? WHERE user_primary_key = ? && stock_symbol = ?");
			ps.setInt(1, amount);
			ps.setInt(2, userPrimaryKey);
			ps.setString(3, stockSymbol);
			ps.executeUpdate();
			
			String stockAmountString = Integer.toString(amount);
			String stockValueString = Double.toString(stockValue);
		
			sessionBean.setStockHoldings(stockSymbol, stockValueString, stockAmountString);
			
		} catch (SQLException ex) {
            Logger.getLogger(User_StockInfo.class.getName()).log(Level.SEVERE, User_StockInfo.class.getName() + ".updateStock ", ex);
        }
		
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_StockInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	//Delete
	
	
}
