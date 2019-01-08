package utilities;

import java.text.DecimalFormat;
import java.util.HashMap;

import beans.sessionBean;

public class createForm {

	public static void buildForm(sessionBean beanObject){
		
		String form = "";
		String stockName;
		String quantity = "0";
		String stockValue;
		String ownedShares;
		
		for (HashMap.Entry<String, String[]> entry : beanObject.getStockHoldings()) {
			stockName = entry.getKey();
            String stockArray[] = entry.getValue();
            stockValue = stockArray[0];
            ownedShares = stockArray[1];
System.out.println(stockName + " " + stockValue + " " + ownedShares);
			
			DecimalFormat df = new DecimalFormat("0.00");

			form = form+ "<form action=\"transactionDetails.jsp\">"
				+ "<tr>"
				+ "<td>" + stockName + "</td>"
				+ "<input type=\"hidden\" name=\"stockName\" value=\"" + stockName + "\">\n" 
				+ "<td>$" + df.format(Double.parseDouble(stockValue)) + "</td>"
				+ "<input type=\"hidden\" name=\"stockValue\" value=\"" + stockValue + "\">\n"
				+ "<td>-0.68</td>"
				+ "<td>Feb 26, 4:00PM EST</td>"
				+ "<td>" + ownedShares + "</td>"
				+ "<input type=\"hidden\" name=\"ownedShares\" value=\"" + ownedShares + "\">\n"
				+ "<td>$" + df.format(Integer.parseInt(ownedShares) * Double.parseDouble(stockValue)) + "</td>"
				+ "<td>"
					+ "<select name=\"action\">"
						+ "<option value=\"buy\">Buy</option>"
						+ "<option value=\"sell\">Sell</option>"
					+ "</select>"
				+ "</td>"
				+ "<td>"
					+ "<input type=\"text\" name=\"quantity\" value=\"" + quantity + "\">\n"
				+ "</td>"
				+ "<td>"
					+ "<input type=\"submit\" value=\"Make Transaction\">"
				+ "</td>"
				+ "</tr>"
			+ "</form>";
		}
		beanObject.setForm(form);
	}
}
	

