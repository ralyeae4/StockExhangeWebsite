package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.sessionBean;

/**
 * Servlet implementation class TransactionHandlerServlet
 */
@WebServlet("/TransactionHandlerServlet")
public class TransactionHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);	        
//		boolean beanCheck = session.getAttribute("bean") != null;
//		
//		if (beanCheck == false){
//			utilities.Redirects.goToLoginJSP(response);
//			return;
//		}

		//else {
			sessionBean beanObject = (sessionBean) session.getAttribute("bean");
			String stockName = request.getParameter("stockName");
			String stockValue = request.getParameter("stockValue");
			String ownedShares = request.getParameter("ownedShares");
			String quantity = request.getParameter("quantity");
			String action = request.getParameter("action");
			
			if(action.equals("sell") && (Integer.parseInt(quantity)>Integer.parseInt(ownedShares))){
				beanObject.setMessage("You cannot sell more than you own.");
				utilities.Redirects.goToStockHoldingsJSP(response);
			}
			else if(Float.parseFloat(quantity)<0){
				beanObject.setMessage("You cannot " + action + " a negative number.");
				utilities.Redirects.goToStockHoldingsJSP(response);
			}
			else{
				Integer updatedOwned = 0;
				
				if(action.equals("buy")){
					updatedOwned = Integer.parseInt(quantity) + Integer.parseInt(ownedShares);
				}
				else if(action.equals("sell")){
					updatedOwned = Integer.parseInt(ownedShares) - Integer.parseInt(quantity);
				}
				
				int key = Integer.parseInt(beanObject.getUserPrimaryKey());
				
				double value = Double.parseDouble(stockValue);
				model.User_StockInfo.updateStock(updatedOwned, key, stockName, value);

				
				utilities.createForm.buildForm(beanObject);
				response.sendRedirect("stockHoldings.jsp");
			}
			//response.sendRedirect("stockHoldingsServlet"); 
		}
	//}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
