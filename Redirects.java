package utilities;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

public class Redirects {
	public static void gotoA_enterData(HttpServletResponse response) {
        try {
            response.sendRedirect("A_enterData.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect did not work", ex);
        }
    }	
	
	
	
	public static void goToStockHoldingsHandlerServlet(HttpServletResponse response) {
        try {
            response.sendRedirect("StockHoldingsHandlerServlet");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for stockHoldingsHandlerServlet did not work", ex);
        }
    }
	
	public static void goToStockHoldingsJSP(HttpServletResponse response) {
        try {
            response.sendRedirect("stockHoldings.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for stockHoldings.jsp did not work", ex);
        }
    }
	
	public static void goToLoginJSP(HttpServletResponse response) {
        try {
            response.sendRedirect("login.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for login.jsp did not work", ex);
        }
    }
	
	public static void goToRegistrationJSP(HttpServletResponse response) {
        try {
            response.sendRedirect("registration.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for registration.jsp did not work", ex);
        }
    }
	
}
