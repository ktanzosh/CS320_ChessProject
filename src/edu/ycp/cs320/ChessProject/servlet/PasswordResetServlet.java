package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.ChessProject.controller.PasswordResetController;
import edu.ycp.cs320.ChessProject.model.PasswordReset;
import edu.ycp.cs320.ChessProject.model.LoginPage;

public class PasswordResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Password Reset Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/passwordReset.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Password Reset Servlet: doPost");
		
		
		PasswordReset model = new PasswordReset();
		

		PasswordResetController controller = new PasswordResetController();
		controller.setModel(model);
		

		// holds the error message text, if there is any
		String errorMessage = null;
		
		String errorMessageInvalidC = null;
		
		// decode POSTed form parameters and dispatch to controller
		try {
			String newpass = getStringFromParameter(req, "newPassword");
			String passcompare = getStringFromParameter(req, "checkPassword");

			// check for errors in the form data before using is in a calculation
			if (newpass == null || passcompare == null) {
				errorMessage = "Please put in both passwords";
			}
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else if(controller.equalPassword(newpass, passcompare) == false)
			{
				errorMessage = "Passwords are not the same";
			}
			
			else {
				model.setNewPassword(newpass);
				model.setCheckPassword(passcompare);
				model.setInfo(controller.equalPassword(newpass, passcompare));
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("model", model);
		
		//req.setAttribute("first", req.getParameter("first"));
		//req.setAttribute("second", req.getParameter("second"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		//req.setAttribute("result", product);
		
		// Forward to view to render the result HTML document
		//req.getRequestDispatcher("/_view/passwordReset.jsp").forward(req, resp);
		
		if (model.getInfo() == true) {
			LoginPage lpModel = new LoginPage();
			req.setAttribute("model", lpModel);
			req.getRequestDispatcher("/_view/loginPage.jsp").forward(req, resp);
		}
		else {
			errorMessageInvalidC = "Passwords are not the same";
			req.setAttribute("errorMessage", errorMessageInvalidC);
			req.getRequestDispatcher("/_view/passwordReset.jsp").forward(req, resp);
		}
	}

	// gets double from the request with attribute named s
	private String getStringFromParameter(HttpServletRequest request, String name) {
        if (request.getParameter(name) == null || request.getParameter(name).equals("")) {
            return null;
        } else {
            return new String (request.getParameter(name));
        }
    }
}
