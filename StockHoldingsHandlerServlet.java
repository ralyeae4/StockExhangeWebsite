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
 * Servlet implementation class StockHoldingsHandlerServlet
 */
@WebServlet("/StockHoldingsHandlerServlet")
public class StockHoldingsHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockHoldingsHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		HttpSession session = request.getSession(false); //obtains session bean
		boolean beanCheck = session.getAttribute("bean") != null;
		
		if (beanCheck == false){
			utilities.Redirects.goToLoginJSP(response);
			return;
		} else {
			sessionBean stockHoldingsSessionInfo = (sessionBean) session.getAttribute("bean");
			
			System.out.println(stockHoldingsSessionInfo.getStockHoldings());
			String primaryKey = stockHoldingsSessionInfo.getUserPrimaryKey();
			Integer userPrimaryKey = Integer.parseInt(primaryKey);
			model.User_StockInfo.returnStockInfo(userPrimaryKey);
			
			//stockHoldingsSessionInfo.setStockHoldings("GOOG","705.07", "1");
			//stockHoldingsSessionInfo.setStockHoldings("MSFT", "51.30", "3");
			//stockHoldingsSessionInfo.setStockHoldings("AAPL", "96.91", "5");
			
			utilities.createForm.buildForm(stockHoldingsSessionInfo);
		
			utilities.Redirects.goToStockHoldingsJSP(response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
