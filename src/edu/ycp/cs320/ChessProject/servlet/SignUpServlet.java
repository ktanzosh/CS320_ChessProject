package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.ChessProject.UserDatabase.User;
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
		

		SignUpPage signUpModel = new SignUpPage();
		User userModel = new User();
		String user = null;
		String password = null;
		String confirmPassword = null;
		String securityQ = null;
		String securityA = null;
		boolean userExists = false;

		//SignUpPageController controller = new SignUpPageController();
		//controller.setModel(model);
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		String errorMessageInvalidC = null;

		
		// decode POSTed form parameters and dispatch to controller
		try {
			user = getStringFromParameter(req, "user");
			password = getStringFromParameter(req,"password");
			confirmPassword = getStringFromParameter(req, "confirmPassword");
			securityQ = getStringFromParameter(req,"sec_question");
			securityA = getStringFromParameter(req,"sec_answer");

			// check for errors in the form data before using is in a calculation
			if (user == null) {
				errorMessage = "Please enter a username";
			}
			
			else if (password == null) {
				errorMessage = "Please enter a password";
			}
			
			else if (confirmPassword == null) {
				errorMessage = "Please enter the password confirmation";
			}
			
			else if (securityQ == null) {
				errorMessage = "Please choose a security question";
			}
			
			else if (securityA == null) {
				errorMessage = "Please enter a security answer";
			}
			
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				userExists = userModel.checkIfUserExsists(user);
				
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid entry";
		}
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		if (!userExists == true) {
			if (signUpModel.CheckPasswordMatch(password, confirmPassword) == true) {

				//TODO: CREATE NEW USER IN DATABASE
				
				req.getRequestDispatcher("/_view/loginPage.jsp").forward(req, resp);
			}
			
			else {
				errorMessageInvalidC = "Passwords do not Match.";
				req.setAttribute("errorMessage", errorMessageInvalidC);
				req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
			}
		}
		else {
			errorMessageInvalidC = "Username already taken.";
			req.setAttribute("errorMessage", errorMessageInvalidC);
			req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
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
