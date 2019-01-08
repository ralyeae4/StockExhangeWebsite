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
 * Servlet implementation class LoginHandlerServlet
 */
@WebServlet("/LoginHandlerServlet")
public class LoginHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession(false); //obtains session bean
		sessionBean loginSessionInfo = (sessionBean) session.getAttribute("bean");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		if(DataCheck.NullOrEmpty(username) || DataCheck.NullOrEmpty(password)){
			loginSessionInfo.setMessage("***** Invalid Login: Do Not Leave Empty Fields *****");
			utilities.Redirects.goToLoginJSP(response);
		}
		//Change to User/Pass check
		else if(model.User_Login_Register_Info.isUsernameInDB(username) == true){
			if(model.User_Login_Register_Info.getUserPassword(username).equals(password))
			{
				Integer userKey = model.User_Login_Register_Info.getUserPrimaryKey(username);
				String keyString = Integer.toString(userKey);
				loginSessionInfo.setUsername(username);
				loginSessionInfo.setUserPrimaryKey(keyString);
				//loginSessionInfo.setMessage("***** " + username + " is in DB ***** Entered: " + password + " matches DB: " + model.User_Login_Register_Info.getUserPassword(username) + " ***** Primary Key: " + userKey + " *****");
				utilities.Redirects.goToStockHoldingsHandlerServlet(response);
				//utilities.Redirects.goToLoginJSP(response);
			}
			else{
				loginSessionInfo.setMessage("***** Invalid Password *****");
				//utilities.Redirects.goToStockHoldingsHandlerServlet(response);
				utilities.Redirects.goToLoginJSP(response);
			}
		}
		else{
			loginSessionInfo.setMessage("***** Invalid Login: Username does not exist *****");
			utilities.Redirects.goToLoginJSP(response);
		}
		
		
		
		
//		else if((username.equalsIgnoreCase("pat")&&password.equalsIgnoreCase("pat"))||(username.equalsIgnoreCase("bob")&&password.equalsIgnoreCase("bob"))){
//			loginSessionInfo.setUsername(username);
//			utilities.Redirects.goToStockHoldingsHandlerServlet(response);
//		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
