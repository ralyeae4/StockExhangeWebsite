package beans;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class sessionBean {

	private String username = "";
	private String message = "";
	private String form;
	private static HashMap<String, String[]> stockHoldings = new HashMap<String, String[]>();
	private String userPrimaryKey = "";
	private static String stockSymbol = "";
	private static Integer stockAmount = 0;
	
	// getters

    public String getUsername()
    {
        return username;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public String getForm() {
		return form;
	}
    
    public Set<Entry<String, String[]>> getStockHoldings()
    {
    	Set<Entry<String, String[]>> set = stockHoldings.entrySet();
    	return set;
    }
    
    public String getUserPrimaryKey() {
		return userPrimaryKey;
	}
    
    public String getStockSymbol() {
		return stockSymbol;
	}
    
    public Integer getStockAmount() {
		return stockAmount;
	}
    
    
    //setters
    public void setUsername(String value)
    {
        this.username = value;
    }
    
    public void setMessage(String value)
    {
        this.message = value;
    }

    public void setForm(String value) {
		this.form = value;
	}
        
    public static void setStockHoldings(String argument1, String argument2, String argument3)
    {
        stockHoldings.put(argument1, new String[] {argument2, argument3});
    }

	public void setUserPrimaryKey(String userPrimaryKey) {
		this.userPrimaryKey = userPrimaryKey;
	}

	public static void setStockSymbol(String stockSymbolDB) {
		stockSymbol = stockSymbolDB;
	}

	public static void setStockAmount(Integer stockAmountDB) {
		stockAmount = stockAmountDB;
	}

}
