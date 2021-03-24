package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.ChessProject.controller.SignUpPageController;
import edu.ycp.cs320.ChessProject.model.SignUpPage;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("SignUpPage Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("SignUpPage Servlet: doPost");
		

		SignUpPage model = new SignUpPage();
		

		SignUpPageController controller = new SignUpPageController();
		controller.setModel(model);
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		String errorMessageInvalidC = null;

		
		// decode POSTed form parameters and dispatch to controller
		try {
			String user = getStringFromParameter(req, "user");
			System.out.println(user);
			
			String password = getStringFromParameter(req,"password");
			System.out.println(password);
			
			String confirmPassword = getStringFromParameter(req, "confirmPassword");
			System.out.println(confirmPassword);
			
			String securityQ = getStringFromParameter(req,"sec_question");
			System.out.println(securityQ);
			
			String securityA = getStringFromParameter(req,"sec_answer");
			System.out.println(securityA);

			// check for errors in the form data before using is in a calculation
			if (user == null) {
				errorMessage = "Please enter a username";
			}
			
			if (password == null) {
				errorMessage = "Please enter a password";
			}
			
			if (confirmPassword == null) {
				errorMessage = "Please enter the password confirmation";
			}
			
			//if (securityQ == null) {
			//	errorMessage = "Please choose a security question";
			//}
			
			if (securityA == null) {
				errorMessage = "Please enter a security answer";
			}
			
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				model.setUser(user);
				System.out.println(model.getUser());
				
				model.setPassword(password);
				System.out.println(model.getPassword());
				
				model.setPasswordConfirmation(confirmPassword);
				System.out.println(model.getPasswordConfirmation());
				
				//model.setSecurityQuestion(securityQ);
				//System.out.println(model.getUser());
				
				model.setSecurityAnswer(securityA);
				System.out.println(model.getSecurityAnswer());
				
				model.setStoreInfo(controller.storeInfo(user, password, securityA));
				System.out.println(model.getStoreInfo());
				
				model.setCheckPasswordMatch(controller.checkPasswordMatch(password, confirmPassword));
				System.out.println(model.getCheckPasswordMatch());
				
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid entry";
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("model", model);
		//req.setAttribute("second", req.getParameter("second"));
		//req.setAttribute("third", req.getParameter("third"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		//req.setAttribute("result", result);
		
		// Forward to view to render the result HTML document
		if (model.getStoreInfo() == true) {
			if (model.getCheckPasswordMatch() == true) {
				System.out.println("Trying to go to Login");
				req.getRequestDispatcher("/_view/loginPage.jsp").forward(req, resp);
			}
			else {
				System.out.println("Password Problem");
				errorMessageInvalidC = "Passwords do not Match.";
				req.setAttribute("errorMessage", errorMessageInvalidC);
				req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
			}
		}
		else {
			System.out.println("Username Problem");
			
			errorMessageInvalidC = "Username already taken.";
			System.out.println(errorMessageInvalidC);
			
			req.setAttribute("errorMessage", errorMessageInvalidC);
			System.out.println("The error message was set");
			
			req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
			System.out.println("The request dispatcher was performed.");
		}
	
	}

	// gets double from the request with attribute named s
	private String getStringFromParameter(HttpServletRequest request, String name) {
		if (request.getParameter(name) == null || request.getParameter(name).equals("")) {
			return null;
		} 
		else {
			return new String (request.getParameter(name));
		}
	}
}
