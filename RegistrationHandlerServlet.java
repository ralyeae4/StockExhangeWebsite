package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.sessionBean;
import utilities.DataCheck;

/**
 * Servlet implementation class RegistrationHandlerServlet
 */
@WebServlet("/RegistrationHandlerServlet")
public class RegistrationHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		HttpSession session = request.getSession(false); //obtains session bean
		sessionBean registrationSessionInfo = (sessionBean) session.getAttribute("bean");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		
		if(DataCheck.NullOrEmpty(username)||DataCheck.NullOrEmpty(password)||DataCheck.NullOrEmpty(confirm)){
			registrationSessionInfo.setMessage("***** Don't Leave Empty *****");
			utilities.Redirects.goToRegistrationJSP(response);
		}
		else if(model.User_Login_Register_Info.isUsernameInDB(username) == true){
			registrationSessionInfo.setMessage("***** Username " + username + " is already taken *****");
			utilities.Redirects.goToRegistrationJSP(response);
		}
		else{
			if(password.equals(confirm)){
				model.User_Login_Register_Info.addUsernamePassword(username, password);
				Integer userPrimaryKey = model.User_Login_Register_Info.getUserPrimaryKey(username);
				model.User_StockInfo.insertNewStock(userPrimaryKey, "MSFT", 10, 170.40);
				model.User_StockInfo.insertNewStock(userPrimaryKey, "AAPL", 10, 56.60);
				registrationSessionInfo.setUsername(username);
				
				String keyString = Integer.toString(userPrimaryKey);
				registrationSessionInfo.setUserPrimaryKey(keyString);
				utilities.Redirects.goToStockHoldingsHandlerServlet(response);
			}
			else{
				registrationSessionInfo.setMessage("***** Passwords don't match *****");
				utilities.Redirects.goToRegistrationJSP(response);
			}
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
